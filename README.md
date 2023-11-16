# Weather Forecast
### Application Development for Data Science(DACD)
**Course**: Second  
**Degree**: Bachelor in Data Science and Engineering  
**School**: School of Computer Engineering  
**University**: Universidad de Las Palmas de Gran Canaria (ULPGC)

## Functionality Summary
The "Weather Forecast" project is a Java application designed to capture and store meteorological data. It utilizes the OpenWeatherMap API to fetch weather forecasts and stores the data in an SQLite database. The application queries the API every 6 hours, collecting data such as temperature, probability of precipitation, humidity, cloudiness, and wind speed for various locations. This data is used for weather analysis and prediction, serving as a valuable tool in the field of data science and meteorological analysis.

## Utilized Resources
- **Development Environment**: IntelliJ IDEA
- **Version Control**: Git, facilitating source code management and tracking changes made during development.
- **Documentation Tools**: MarkDown used for creating this document, enabling a clear and structured presentation of the project.

## Design
The project follows several design patterns and principles to ensure clean, maintainable, and scalable code:

1. **Singleton Pattern** in classes like `SQLiteWeatherStore`, ensuring that only one instance of the database connection exists during the program execution.

2. **Single Responsibility Principle**: Each class in the project has a single responsibility. For instance, `OpenWeatherMapProvider` is solely responsible for the API data fetching logic, whereas `SQLiteWeatherStore` handles all database-related operations.

3. **Dependency Injection**: The project uses dependency injection to reduce coupling between classes. For example, the list of locations is passed to `OpenWeatherMapProvider`, making this class more flexible and testable.

4. **Object-Oriented Programming (OOP)**: The use of OOP facilitates the organization of the code, reusability, and extensibility. Classes such as `Location` and `Weather` encapsulate related data and behaviors.

5. **Exception Handling**: The project properly handles exceptions, especially in network and database operations, to ensure the application's stability.

## Class Diagram
Below is the class diagram that illustrates the structure and dependencies in the "Weather Forecast" application:

![image](https://github.com/josemtx/dataCapturing/blob/ca968221d39e012680ea592158b69f239f68c836/Diagrama%20Weather.png)

## Conclusion
"Weather Forecast" is a robust and functional application for weather analysis and prediction. Its object-oriented design, combined with the use of design patterns and software development principles, makes it a valuable tool for any project that requires accurate and up-to-date meteorological data.
