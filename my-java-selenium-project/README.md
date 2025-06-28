# My Java Selenium Project

This project is a Java-based Selenium framework designed for automated testing of web applications using the TestNG framework. It includes a base page class for common functionalities and a test class to verify the base page's functionality.

## Project Structure

```
my-java-selenium-project
├── src
│   ├── main
│   │   └── java
│   │       └── pages
│   │           └── BasePage.java
│   └── test
│       └── java
│           └── tests
│               └── BasePageTest.java
├── build.gradle
├── log4j2.xml
└── README.md
```

## Setup Instructions

1. **Clone the Repository**: 
   Clone this repository to your local machine using:
   ```
   git clone <repository-url>
   ```

2. **Install Java**: 
   Ensure that you have Java Development Kit (JDK) installed on your machine. You can download it from [Oracle's website](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).

3. **Install Gradle**: 
   Make sure you have Gradle installed. You can follow the instructions on the [Gradle website](https://gradle.org/install/).

4. **Build the Project**: 
   Navigate to the project directory and run:
   ```
   gradle build
   ```

5. **Run Tests**: 
   To execute the tests, use the following command:
   ```
   gradle test
   ```

## Usage

- The `BasePage` class initializes the Chrome WebDriver and provides common functionalities for other page classes.
- The `BasePageTest` class contains test methods that utilize TestNG annotations for setup and teardown processes.

## Logging

Logging is configured using Log4j2. The configuration file `log4j2.xml` defines the logging levels and output formats.

## Contributing

Feel free to submit issues or pull requests for improvements or bug fixes.