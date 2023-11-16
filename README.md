# Weather Forecast
### Desarrollo de Aplicaciones para Ciencia de Datos
**Curso**: Segundo  
**Titulación**: Grado en Ciencia e Ingeniería de Datos  
**Escuela**: Escuela de Ingeniería Informática  
**Universidad**: Universidad de Las Palmas de Gran Canaria (ULPGC)

## Resumen de la Funcionalidad
El proyecto "Weather Forecast" es una aplicación Java diseñada para capturar y almacenar datos meteorológicos. Utiliza la API de OpenWeatherMap para obtener previsiones del tiempo y las almacena en una base de datos SQLite. La aplicación consulta la API cada 6 horas, recopilando datos como temperatura, probabilidad de precipitación, humedad, nubosidad y velocidad del viento para varias ubicaciones. Estos datos se utilizan para realizar análisis y predicciones meteorológicas, sirviendo como una herramienta valiosa en el campo de la ciencia de datos y el análisis meteorológico.

## Recursos Utilizados
- **Entorno de Desarrollo**: IntelliJ IDEA
- **Control de Versiones**: Git, facilitando la gestión del código fuente y el seguimiento de los cambios realizados durante el desarrollo.
- **Herramientas de Documentación**: MarkDown utilizado para la creación de este documento, facilitando una presentación clara y estructurada del proyecto.

## Diseño
El proyecto sigue varios principios y patrones de diseño para asegurar un código limpio, mantenible y escalable:

1. **Patrón Singleton** en clases como `SQLiteWeatherStore`, asegurando que solo exista una instancia de conexión a la base de datos durante la ejecución del programa.

2. **Principio de Responsabilidad Única**: Cada clase en el proyecto tiene una responsabilidad única. Por ejemplo, `OpenWeatherMapProvider` se encarga exclusivamente de la lógica de obtención de datos de la API, mientras que `SQLiteWeatherStore` gestiona todas las operaciones relacionadas con la base de datos.

3. **Inyección de Dependencias**: El proyecto utiliza la inyección de dependencias para reducir el acoplamiento entre clases. Por ejemplo, la lista de ubicaciones se pasa a `OpenWeatherMapProvider`, permitiendo que esta clase sea más flexible y fácil de probar.

4. **Programación Orientada a Objetos (OOP)**: El uso de OOP facilita la organización del código, la reutilización y la extensibilidad. Las clases como `Location` y `Weather` encapsulan los datos y comportamientos relacionados.

5. **Manejo de Excepciones**: El proyecto maneja adecuadamente las excepciones, especialmente en operaciones de red y base de datos, para garantizar la estabilidad de la aplicación.

## Conclusión
"Weather Forecast" es una aplicación robusta y funcional para el análisis y la predicción meteorológica. Su diseño orientado a objetos, junto con el uso de patrones de diseño y principios de desarrollo de software, la convierten en una herramienta valiosa para cualquier proyecto que requiera datos meteorológicos precisos y actualizados.
