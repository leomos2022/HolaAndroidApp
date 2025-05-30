# HolaAndroidApp - Taller de Desarrollo Android con Jetpack Compose

Este proyecto es el resultado de un taller práctico de desarrollo Android, donde exploramos las capacidades modernas de Jetpack Compose junto con animaciones y gestión de estados.

## Capturas de Pantalla

<div align="center">
  <img src="android1.png" alt="Pantalla de bienvenida" width="250"/>
  <img src="android2.png" alt="Pantalla de exploración" width="250"/>
  <img src="android3.png" alt="Pantalla final con emoji" width="250"/>
</div>

## Detalles Técnicos

### 1. Gestión de Estados
```kotlin
var messageState by remember { mutableStateOf(0) }
val messages = listOf(
    "¡Hola, Android!",
    "HolaAndroid,\n\nexploremos este increíble\nLenguaje de Programación",
    "😊 ¡Android Studio\nes lo Máximo!"
)
```
- Utilizamos `remember` y `mutableStateOf` para mantener el estado de los mensajes
- La lista `messages` contiene los textos que se mostrarán secuencialmente
- El estado se actualiza de manera cíclica con el operador módulo `% messages.size`

### 2. Animaciones de Color
```kotlin
val backgroundColors = listOf(BackgroundColor1, BackgroundColor2, BackgroundColor3)
val transition = rememberInfiniteTransition(label = "background")
val colorTransition by transition.animateColor(
    initialValue = backgroundColors[0],
    targetValue = backgroundColors[2],
    animationSpec = infiniteRepeatable(
        animation = tween(3000),
        repeatMode = RepeatMode.Reverse
    ),
    label = "color"
)
```
- Implementamos transiciones de color infinitas usando `rememberInfiniteTransition`
- La animación dura 3000ms (3 segundos) y se revierte automáticamente
- Utilizamos colores personalizados definidos en el tema de la aplicación

### 3. Diseño y Layout
```kotlin
Box(
    modifier = Modifier
        .fillMaxSize()
        .background(colorTransition),
    contentAlignment = Alignment.Center
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(24.dp)
    )
}
```
- Estructura jerárquica de composables: Box → Column → Elementos UI
- Uso de modificadores para control preciso del layout
- Alineación y padding consistentes para mejor UX

### 4. Animaciones de Contenido
```kotlin
AnimatedContent(
    targetState = messageState,
    transitionSpec = {
        slideInVertically { height -> height } + fadeIn() with
        slideOutVertically { height -> -height } + fadeOut()
    },
    label = "message"
)
```
- Implementación de `AnimatedContent` para transiciones suaves entre mensajes
- Combinación de animaciones de deslizamiento y desvanecimiento
- Uso de la API experimental de animaciones con `@OptIn(ExperimentalAnimationApi::class)`

### 5. Estilización de Componentes
```kotlin
Button(
    onClick = { messageState = (messageState + 1) % messages.size },
    colors = ButtonDefaults.buttonColors(containerColor = ButtonPurple),
    modifier = Modifier
        .padding(16.dp)
        .animateContentSize()
) {
    Text(
        "Presióname",
        fontSize = 20.sp,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    )
}
```
- Personalización de colores del botón
- Animación del tamaño del contenido
- Tipografía y espaciado consistentes

## Conceptos Aprendidos

1. **Jetpack Compose Basics**
   - Composables y recomposición
   - Gestión de estados con `remember` y `mutableStateOf`
   - Modificadores y sus efectos en el layout

2. **Animaciones en Compose**
   - Transiciones de color infinitas
   - Animaciones de contenido
   - Combinación de múltiples efectos de animación

3. **Material Design 3**
   - Implementación de temas personalizados
   - Uso de componentes Material como Button
   - Sistema de colores y tipografía

4. **Mejores Prácticas**
   - Separación de concerns en composables
   - Uso de previsualizaciones con `@Preview`
   - Manejo de APIs experimentales con `@OptIn`

## Estructura del Proyecto
app/
├── src/
│ ├── main/
│ │ ├── java/com/example/holaandroidapp/
│ │ │ ├── MainActivity.kt
│ │ │ └── ui/theme/
│ │ │ ├── Color.kt
│ │ │ ├── Theme.kt
│ │ │ └── Type.kt
│ │ └── res/
│ └── androidTest/
└── build.gradle.kts
## Requisitos de Desarrollo

- Android Studio Hedgehog | 2023.1.1 o superior
- Kotlin 1.9.0 o superior
- Jetpack Compose BOM 2024.01.00
- Gradle 8.2
- SDK mínimo: Android 21 (Android 5.0)

## Configuración del Proyecto

1. **Dependencias principales** (build.gradle.kts):
```kotlin
dependencies {
    implementation(platform("androidx.compose:compose-bom:2024.01.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.activity:activity-compose")
}
```

2. **Configuración de Compose**:
```kotlin
android {
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
}
```

## Instalación y Ejecución

1. Clona el repositorio:
```bash
git clone https://github.com/leomos2022/HolaAndroidApp.git
```

2. Abre el proyecto en Android Studio

3. Sincroniza el proyecto con Gradle

4. Ejecuta la aplicación en:
   - Emulador de Android
   - Dispositivo físico vía USB
   - Dispositivo físico vía WiFi (Android 11+)

## Contribución

Si deseas contribuir al proyecto:
1. Haz un fork del repositorio
2. Crea una nueva rama para tus cambios
3. Envía un pull request con tus mejoras

## Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo `LICENSE` para más detalles.
