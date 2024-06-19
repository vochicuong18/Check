# Appium Contact Demo 📱

This repository is a demo project demonstrating how to use Appium to automate testing of a contact app on **Android** and **iOS**.

**Note:** This is a basic demo project and can be extended with more features and test cases.

## Features:
- **Cross-Platform Testing:** This demo showcases testing the same functionality across both Android and iOS.
- **Page Object Model (POM):** Adopts the Page Object Model for better code organization and reusability.
- **Test Data Management:** Utilizes a separate file (`users.json`) to manage test data, making test cases flexible and easy to update.
- **Extend Reports:** Implements Extend Reports to generate comprehensive and detailed test reports with screenshots and logs.
- **TestNG Framework:** Uses TestNG framework for test case management, execution, and reporting.

## Prerequisites:
- Java Development Kit (JDK)
- Apache Maven
- Appium Server
-  **Android:**
   - Android Emulator or Real Device
- **iOS:**
    - Xcode
    - iOS Simulator or Real Device
    - Xcode Command Line Tools
    - WebDriverAgent (WDA)

## Getting Started
1. **Clone the repository:** 
   ```bash
   git clone https://github.com/vochicuong18/appium-contact-demo.git
   
2. **Configure environment:**
- Ensure Appium Server is running.
- Set up desired capabilities for your platform (Android or iOS) in environment.json.
  
3. **Run test:**
   ```bash
   mvn clean test
