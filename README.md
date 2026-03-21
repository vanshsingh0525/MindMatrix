# 🎯 Udemy Testing Automation Project

## 📌 Overview

This project is a **Selenium-based test automation framework** developed to automate key user workflows on the Udemy platform. It is designed using industry best practices to ensure scalability, maintainability, and reusability.

---

## 🚀 Features

* 🔍 Search functionality automation
* 🛒 Add to cart and verify pricing
* 🔐 Login and authentication testing
* 🌐 Cross-browser testing support
* 📊 Test execution using TestNG
* 🧩 Modular framework (Page Object Model)

---

## 🛠️ Tech Stack

* **Language:** Java
* **Automation Tool:** Selenium WebDriver
* **Test Framework:** TestNG
* **Build Tool:** Maven
* **Version Control:** Git & GitHub

---

## 📂 Project Structure

```
MindMatrix/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── pages/        # Page Object classes
│   │       ├── utils/        # Utility classes
│   │
│   ├── test/
│       └── java/
│           ├── tests/        # Test cases
│
├── drivers/                  # WebDriver executables
├── pom.xml                   # Maven dependencies
├── testng.xml                # TestNG configuration
└── README.md
```

---

## ⚙️ Setup Instructions

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/your-repo/MindMatrix.git
cd MindMatrix
```

---

### 2️⃣ Install Dependencies

```bash
mvn clean install
```

---

### 3️⃣ Run Tests

```bash
mvn test
```

Or using TestNG XML:

```bash
mvn clean test -DsuiteXmlFile=testng.xml
```

---

## 🧪 Test Scenarios Covered

* Search for courses on Udemy
* Validate course details
* Add course to cart
* Verify price calculation
* Login functionality

---

## 📸 Reporting

* Test execution reports generated via TestNG
* Logs for debugging failures

---

## 🤝 Contribution

1. Create a new branch
2. Make changes
3. Commit your code
4. Push to GitHub
5. Create a Pull Request

---

## 👨‍💻 Author

**Yashraj Chouhan**

---

## 📌 Notes

* Ensure correct WebDriver is placed inside the `drivers` folder
* Update browser version compatibility if needed

---

## ⭐ Acknowledgement

This project is part of a **hackathon initiative** to demonstrate automation testing skills using Selenium.

---
