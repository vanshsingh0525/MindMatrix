# Udemy Test Automation Framework

Selenium + Cucumber + TestNG based test automation framework for Udemy platform.

## Project Structure

```
UdemyTesting/
├── pom.xml                                    # Maven dependencies and build configuration
├── testng.xml                                 # Main TestNG suite (runs all teams)
├── testng-CourseSearch.xml                    # Course Search team suite
├── testng-Team2.xml                           # Team 2 suite
├── testng-Team3.xml                           # Team 3 suite
├── testng-Team4.xml                           # Team 4 suite
├── src/
│   ├── main/
│   │   ├── java/
│   │   └── resources/
│   └── test/
│       ├── java/
│       │   └── com/udemy/
│       │       ├── hooks/                     # Cucumber hooks (COMMON - DO NOT MODIFY)
│       │       │   └── Hooks.java
│       │       ├── pages/                     # Page Objects (COMMON - DO NOT MODIFY)
│       │       │   ├── BasePage.java
│       │       │   ├── HomePage.java
│       │       │   ├── SearchResultsPage.java
│       │       │   └── CourseDetailsPage.java
│       │       ├── runners/                   # Test runners (one per team)
│       │       │   ├── CourseSearchRunner.java
│       │       │   ├── Team2Runner.java
│       │       │   ├── Team3Runner.java
│       │       │   └── Team4Runner.java
│       │       ├── stepdefinitions/           # Step definitions (one folder per team)
│       │       │   ├── CourseSearch/
│       │       │   ├── Team2/
│       │       │   ├── Team3/
│       │       │   └── Team4/
│       │       └── utils/                     # Utilities (COMMON - DO NOT MODIFY)
│       │           ├── ConfigReader.java
│       │           ├── DriverManager.java
│       │           └── ElementUtils.java
│       └── resources/
│           ├── config.properties              # Configuration (COMMON)
│           ├── extent.properties              # Extent Reports config
│           ├── extent-config.xml
│           ├── log4j2.properties
│           └── features/                      # Feature files (one folder per team)
│               ├── CourseSearch/
│               ├── Team2/
│               ├── Team3/
│               └── Team4/
└── test-output/                               # Generated reports and screenshots
    ├── reports/
    └── screenshots/
```

## Team Guidelines

### Common Files (DO NOT MODIFY without team discussion)
- `src/test/java/com/udemy/utils/` - All utility classes
- `src/test/java/com/udemy/hooks/Hooks.java` - Cucumber hooks
- `src/test/java/com/udemy/pages/BasePage.java` - Base page class
- `src/test/java/com/udemy/pages/HomePage.java` - Home page object
- `src/test/java/com/udemy/pages/SearchResultsPage.java` - Search results page object
- `src/test/java/com/udemy/pages/CourseDetailsPage.java` - Course details page object
- `src/test/resources/config.properties` - Configuration properties

### Team-Specific Files (Each team works in their own folders)
- **CourseSearch Team**: `stepdefinitions/CourseSearch/`, `features/CourseSearch/`
- **Team 2**: `stepdefinitions/Team2/`, `features/Team2/`
- **Team 3**: `stepdefinitions/Team3/`, `features/Team3/`
- **Team 4**: `stepdefinitions/Team4/`, `features/Team4/`

## Getting Started

### Prerequisites
- Java JDK 21+
- Maven 3.8+
- Chrome/Firefox/Edge browser

### Clone Repository
```bash
git clone <repository-url>
cd UdemyTesting
```

### Install Dependencies
```bash
mvn clean install -DskipTests
```

### Run Tests

**Run all tests:**
```bash
mvn test
```

**Run specific team's tests:**
```bash
# Course Search Team
mvn test -DsuiteXmlFile=testng-CourseSearch.xml

# Team 2
mvn test -DsuiteXmlFile=testng-Team2.xml

# Team 3
mvn test -DsuiteXmlFile=testng-Team3.xml

# Team 4
mvn test -DsuiteXmlFile=testng-Team4.xml
```

**Run with specific browser:**
```bash
# Edit src/test/resources/config.properties
browser=chrome  # or firefox, edge
```

**Run in headless mode:**
```bash
# Edit src/test/resources/config.properties
headless=true
```

## Test Reports

After test execution, reports are available at:
- **Extent Report**: `test-output/reports/ExtentReport.html`
- **Cucumber Report**: `test-output/reports/<TeamName>/cucumber-report.html`
- **Screenshots**: `test-output/screenshots/`

## Branch Strategy

1. **main**: Production-ready code. Only merge after code review.
2. **develop**: Integration branch for all teams.
3. **feature/course-search**: Course Search team branch
4. **feature/team2**: Team 2 branch
5. **feature/team3**: Team 3 branch
6. **feature/team4**: Team 4 branch

### Workflow
1. Clone main branch
2. Create your feature branch from main
3. Work on your use case in your designated folders
4. Push changes to your feature branch
5. Create PR to merge into develop
6. After testing, merge develop to main

## Use Cases

### Course Search & Detail Validation (Team 1)
1. Open Udemy homepage
2. Handle cookies/location popups
3. Search for a course topic (e.g., "Python")
4. Verify search results with multiple course cards
5. Apply filters (rating/level)
6. Open first course card
7. Verify course title, rating, and instructor name

### Team 2, 3, 4
- Add your use case descriptions here

## Contact

For framework-related issues, contact the QA Lead.
