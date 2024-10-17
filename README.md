# Conversor de Divisas - Alura Challenge

Este proyecto es un conversor de divisas que permite realizar conversiones entre múltiples monedas utilizando una API en tiempo real. Desarrollado como parte de un desafío de Alura, el sistema ofrece un menú interactivo para seleccionar diferentes tipos de conversiones.

## Características
- **Monedas soportadas**: COP, USD, EUR, entre otras.
- **Conversión personalizada**: Permite ingresar pares de divisas personalizados.
- **Formato de salida**: Los resultados se muestran con el formato adecuado según la divisa.

## Requisitos
- **Java 11** o superior.
- Conexión a Internet para realizar las consultas a la API de tasas de cambio.

## Instalación

1. Clona el repositorio:
    ```bash
    git clone https://github.com/YonathanArroyave/AluraChallengeConversorArroDev.git
    ```
2. Navega al directorio del proyecto:
    ```bash
    cd AluraChallengeConversorArroDev
    ```
3. Asegúrate de tener configurado Java 11 o superior en tu entorno de desarrollo.

## Uso

1. Ejecuta la clase principal `ConversorDivisas` desde tu entorno de desarrollo o terminal:
    ```bash
    java ConversorDivisas
    ```
2. Aparecerá un menú donde podrás seleccionar entre las siguientes opciones:
    - **COP -> USD**
    - **COP -> EUR**
    - **USD -> COP**
    - **EUR -> COP**
    - **Conversión personalizada**: Permite ingresar pares de divisas a elección.
3. Sigue las instrucciones para introducir la cantidad a convertir.

## Estructura del Proyecto

- **ConversorDivisas**: Clase principal que inicia el programa.
- **MenuConversor**: Controla las interacciones del menú.
- **ServicioConversion**: Realiza las consultas a la API y maneja la lógica de conversión.
- **FormateadorMoneda**: Formatea las cantidades convertidas.
- **ResultadoConversion**: Estructura que almacena el resultado de la conversión.

## API Utilizada

El proyecto utiliza la API [ExchangeRate-API](https://www.exchangerate-api.com/) para obtener las tasas de cambio actualizadas. Se requiere una clave de API válida, que está incluida en el código, pero puedes reemplazarla por una clave propia.

## Contribuciones
Las contribuciones son bienvenidas. Por favor, abre un issue o pull request con tus sugerencias.
