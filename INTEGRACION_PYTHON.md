# INTEGRACION_PYTHON.md

## Guía de Integración - API Sabor Urbano con Python

Esta guía explica cómo el proyecto Python se conecta con la API REST de Sabor Urbano para realizar análisis de datos.

---

## 🔗 Conexión a la API

### URL Base
```
http://localhost:8080/api
```

### Headers Requeridos
```http
Content-Type: application/json
Accept: application/json
```

---

## 📊 Endpoints Disponibles para Python

### Usuarios
```
GET    /api/usuarios              → Obtener todos los usuarios
GET    /api/usuarios/{id}         → Obtener un usuario específico
POST   /api/usuarios              → Crear usuario
DELETE /api/usuarios/{id}         → Eliminar usuario
```

### Platillos
```
GET    /api/platillos             → Obtener todos los platillos
GET    /api/platillos/{id}        → Obtener un platillo específico
POST   /api/platillos             → Crear platillo
```

### Categorías
```
GET    /api/categorias            → Obtener todas las categorías
GET    /api/categorias/{id}       → Obtener una categoría
POST   /api/categorias            → Crear categoría
```

### Comentarios
```
GET    /api/comentarios           → Obtener todos los comentarios
GET    /api/comentarios/{id}      → Obtener un comentario
POST   /api/comentarios           → Crear comentario
DELETE /api/comentarios/{id}      → Eliminar comentario
```

### Calificaciones
```
GET    /api/calificaciones        → Obtener todas las calificaciones
GET    /api/calificaciones/{id}   → Obtener una calificación
POST   /api/calificaciones        → Crear calificación
DELETE /api/calificaciones/{id}   → Eliminar calificación
```

---

## 🐍 Ejemplos en Python

### 1. Obtener Todos los Usuarios

```python
import requests

url = "http://localhost:8080/api/usuarios"
response = requests.get(url)

if response.status_code == 200:
    usuarios = response.json()
    for usuario in usuarios:
        print(f"ID: {usuario['id']}, Nombre: {usuario['nombreCompleto']}")
else:
    print(f"Error: {response.status_code}")
```

### 2. Crear un Nuevo Usuario

```python
import requests
import json

url = "http://localhost:8080/api/usuarios"

data = {
    "nombreCompleto": "Juan Pérez",
    "email": "juan@example.com"
}

headers = {
    "Content-Type": "application/json"
}

response = requests.post(url, json=data, headers=headers)

if response.status_code == 201:
    nuevo_usuario = response.json()
    print(f"Usuario creado con ID: {nuevo_usuario['id']}")
else:
    print(f"Error: {response.status_code}")
    print(response.text)
```

### 3. Obtener Todos los Comentarios

```python
import requests

url = "http://localhost:8080/api/comentarios"
response = requests.get(url)

if response.status_code == 200:
    comentarios = response.json()
    return comentarios  # Para análisis de sentimiento
else:
    print(f"Error: {response.status_code}")
```

### 4. Obtener Todas las Calificaciones

```python
import requests

url = "http://localhost:8080/api/calificaciones"
response = requests.get(url)

if response.status_code == 200:
    calificaciones = response.json()
    # Procesar para análisis estadístico
    return calificaciones
else:
    print(f"Error: {response.status_code}")
```

---

## 📈 Casos de Uso - Análisis de Datos

### Caso 1: Análisis de Sentimiento en Comentarios

```python
from textblob import TextBlob
import requests

def analizar_sentimientos():
    """
    Obtiene comentarios y realiza análisis de sentimiento
    """
    url = "http://localhost:8080/api/comentarios"
    response = requests.get(url)
    
    comentarios = response.json()
    resultados = []
    
    for comentario in comentarios:
        texto = comentario['textoComentario']
        
        # Análisis de sentimiento
        blob = TextBlob(texto)
        polaridad = blob.sentiment.polarity
        
        sentimiento = "Positivo" if polaridad > 0 else "Negativo" if polaridad < 0 else "Neutral"
        
        resultados.append({
            "id": comentario['idComentario'],
            "usuario": comentario['usuario']['nombreCompleto'],
            "texto": texto,
            "sentimiento": sentimiento,
            "polaridad": polaridad
        })
    
    return resultados

# Usar
analisis = analizar_sentimientos()
for item in analisis:
    print(f"{item['usuario']}: {item['sentimiento']} ({item['polaridad']:.2f})")
```

### Caso 2: Platillos Más Calificados

```python
import requests
from statistics import mean

def obtener_platillos_populares():
    """
    Calcula el promedio de calificaciones por platillo
    """
    url = "http://localhost:8080/api/calificaciones"
    response = requests.get(url)
    
    calificaciones = response.json()
    
    # Agrupar por platillo
    platillos = {}
    for cal in calificaciones:
        platillo_id = cal['platillo']['idPlatillo']
        puntuacion = cal['puntuacion']
        
        if platillo_id not in platillos:
            platillos[platillo_id] = {
                'nombre': cal['platillo']['nombre'],
                'calificaciones': []
            }
        
        platillos[platillo_id]['calificaciones'].append(puntuacion)
    
    # Calcular promedios
    resultado = []
    for platillo_id, data in platillos.items():
        promedio = mean(data['calificaciones'])
        resultado.append({
            'nombre': data['nombre'],
            'promedio': promedio,
            'total_calificaciones': len(data['calificaciones'])
        })
    
    # Ordenar por promedio descendente
    resultado.sort(key=lambda x: x['promedio'], reverse=True)
    
    return resultado

# Usar
populares = obtener_platillos_populares()
for platillo in populares:
    print(f"{platillo['nombre']}: {platillo['promedio']:.2f} ⭐ ({platillo['total_calificaciones']} votos)")
```

### Caso 3: Preferencias por Categoría

```python
import requests

def analizar_preferencias_categorias():
    """
    Analiza las preferencias de platillos por categoría
    """
    url_platillos = "http://localhost:8080/api/platillos"
    url_calificaciones = "http://localhost:8080/api/calificaciones"
    
    platillos = requests.get(url_platillos).json()
    calificaciones = requests.get(url_calificaciones).json()
    
    # Crear mapa de platillos
    mapa_platillos = {p['idPlatillo']: p for p in platillos}
    
    # Agrupar por categoría
    categorias = {}
    for cal in calificaciones:
        platillo = mapa_platillos.get(cal['platillo']['idPlatillo'])
        categoria = platillo['categoria']['nombreCategoria']
        puntuacion = cal['puntuacion']
        
        if categoria not in categorias:
            categorias[categoria] = []
        
        categorias[categoria].append(puntuacion)
    
    # Calcular estadísticas
    resultado = {}
    for categoria, puntuaciones in categorias.items():
        resultado[categoria] = {
            'promedio': sum(puntuaciones) / len(puntuaciones),
            'total': len(puntuaciones),
            'minima': min(puntuaciones),
            'maxima': max(puntuaciones)
        }
    
    return resultado

# Usar
preferencias = analizar_preferencias_categorias()
for categoria, stats in preferencias.items():
    print(f"{categoria}:")
    print(f"  Promedio: {stats['promedio']:.2f}")
    print(f"  Total votos: {stats['total']}")
    print(f"  Rango: {stats['minima']} - {stats['maxima']}")
```

### Caso 4: Usuarios Más Activos

```python
import requests

def usuarios_mas_activos():
    """
    Identifica los usuarios que más comentarios y calificaciones han hecho
    """
    url_comentarios = "http://localhost:8080/api/comentarios"
    url_calificaciones = "http://localhost:8080/api/calificaciones"
    
    comentarios = requests.get(url_comentarios).json()
    calificaciones = requests.get(url_calificaciones).json()
    
    # Contar actividades por usuario
    usuarios = {}
    
    for comentario in comentarios:
        usuario_id = comentario['usuario']['id']
        if usuario_id not in usuarios:
            usuarios[usuario_id] = {
                'nombre': comentario['usuario']['nombreCompleto'],
                'comentarios': 0,
                'calificaciones': 0
            }
        usuarios[usuario_id]['comentarios'] += 1
    
    for calificacion in calificaciones:
        usuario_id = calificacion['usuario']['id']
        if usuario_id not in usuarios:
            usuarios[usuario_id] = {
                'nombre': calificacion['usuario']['nombreCompleto'],
                'comentarios': 0,
                'calificaciones': 0
            }
        usuarios[usuario_id]['calificaciones'] += 1
    
    # Calcular actividad total y ordenar
    resultado = []
    for usuario_id, data in usuarios.items():
        data['actividad_total'] = data['comentarios'] + data['calificaciones']
        resultado.append(data)
    
    resultado.sort(key=lambda x: x['actividad_total'], reverse=True)
    
    return resultado

# Usar
activos = usuarios_mas_activos()
for usuario in activos[:10]:  # Top 10
    print(f"{usuario['nombre']}: {usuario['actividad_total']} actividades")
    print(f"  - Comentarios: {usuario['comentarios']}")
    print(f"  - Calificaciones: {usuario['calificaciones']}")
```

---

## 🔄 Manejo de Errores

```python
import requests
from requests.exceptions import RequestException

def obtener_datos_seguro(endpoint):
    """
    Obtiene datos con manejo robusto de errores
    """
    url = f"http://localhost:8080/api{endpoint}"
    
    try:
        response = requests.get(url, timeout=10)
        response.raise_for_status()  # Lanza excepción si status != 2xx
        return response.json()
    
    except requests.exceptions.Timeout:
        print("Error: La solicitud tardó demasiado")
        return None
    
    except requests.exceptions.ConnectionError:
        print("Error: No se pudo conectar a la API")
        print("Verifica que la aplicación Spring Boot esté corriendo en puerto 8080")
        return None
    
    except requests.exceptions.HTTPError as e:
        print(f"Error HTTP: {e.response.status_code}")
        print(f"Respuesta: {e.response.text}")
        return None
    
    except Exception as e:
        print(f"Error inesperado: {str(e)}")
        return None

# Usar
datos = obtener_datos_seguro("/usuarios")
if datos:
    print(f"Se obtuvieron {len(datos)} usuarios")
```

---

## 📦 Dependencias Python Recomendadas

```
requests>=2.28.0          # Para hacer peticiones HTTP
pandas>=1.5.0             # Para análisis de datos
numpy>=1.23.0             # Para cálculos numéricos
matplotlib>=3.5.0         # Para visualizaciones
seaborn>=0.12.0           # Para gráficos estadísticos
textblob>=0.17.0          # Para análisis de sentimiento
nltk>=3.8.0               # Para procesamiento de lenguaje natural
scikit-learn>=1.1.0       # Para machine learning
```

Instalar con:
```bash
pip install -r requirements.txt
```

---

## 🚀 Flujo de Integración Típico

```
1. API Java (Spring Boot) en puerto 8080
   ↓
2. Script Python realiza peticiones GET/POST
   ↓
3. Python procesa datos (análisis, cálculos, ML)
   ↓
4. Resultados almacenados o visualizados
   ↓
5. Dashboard o reportes generados
```

---

## 📝 Notas Importantes

1. **Asegúrate que la API está corriendo** antes de ejecutar scripts Python
2. **Usa timeouts** en las peticiones para evitar bloqueos
3. **Maneja errores** de conexión apropiadamente
4. **Cachea datos** cuando sea posible para mejorar rendimiento
5. **Respeta los límites** de rate limiting (si se implementan)

---

¡Listo para integrar Python con la API! 🐍🔗
