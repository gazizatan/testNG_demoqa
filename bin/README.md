# DemoQA TestNG Automation

UI automation for https://demoqa.com using Java, Selenium WebDriver, TestNG, Log4j2, WebDriverManager, and ExtentReports.

## Project Structure

```
demoqa-testng
├── pom.xml
├── testng.xml
├── README.md
├── logs/
│   └── test.log
├── reports/
│   ├── extent-report.html
│   └── screenshots/
└── src
    └── test
        ├── java
        │   ├── base
        │   │   └── BaseTest.java
        │   ├── listeners
        │   │   └── TestListener.java
        │   ├── tests
        │   │   ├── TextBoxTest.java
        │   │   ├── CheckBoxTest.java
        │   │   └── ButtonsTest.java
        │   └── utils
        │       ├── DriverFactory.java
        │       ├── ExtentManager.java
        │       └── ScreenshotUtil.java
        └── resources
            └── log4j2.xml
```

## Setup

1. Ensure Java 17+ and Maven are installed.
2. From `demoqa-testng`, run:

```bash
mvn clean test
```

## Outputs

- Logs: `logs/test.log`
- Extent report: `reports/extent-report.html`
- Screenshots: `reports/screenshots/`
