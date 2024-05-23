# NSE India Selenium Automation Project

## Overview

This project is designed to automate the specified test scenario for NSE India using Selenium with Java.

## Project Structure

- `src/main/java/com/firstorg/nseindia/pageobjects`: Contains page object classes.
- `src/main/java/com/firstorg/nseindia/test`: Contains test scripts.
- `src/main/java/com/firstorg/nseindia/testexecute`: Contains classes for executing and coordinating the test suite.
- `src/main/java/com/firstorg/nseindia/reports`: Contains classes related to generating and managing test reports.
- `src/main/java/com/firstorg/nseindia/utility`: Contains utility classes.

## Setup

1. Clone the repository.
2. Make sure you have latest Chrome & Edge browser in your system.
3. User can select on which browser(Chrome/Edge) to execute test suite. For this user has to navigate to ../nseindiaWebAutomation/src/main/resources/config/nseindia-config-data.json file and update the key "driver-config.driver.browser"'s value to either "Chrome" or "Edge" as per requirement.
4. Make you are not connected to any VPN at first run.

## Running the Tests

Execute the `NSEIndiaAutomation` test suite in following two ways:

1. Through .bat file: Navigate to project parent folder(../nseindiaWebAutomation/) and double click on "run-Test-Suite.bat" file present there.
2. Through maven build tool: Navigate to project parent folder(../nseindiaWebAutomation/) and execute "mvn test" command using cmd in the same location.

## Tests Results

- For each test run, test result report will be created at ../nseindiaWebAutomation/test-reports/ folder location.
- User can get into latest dynamically created folder at ../nseindiaWebAutomation/test-reports/Report_YYYYMMDDHHMMSS/HTMLReports/index.html location.

## Test Logs

-For each test run, logs will be saved at ../nseindiaWebAutomation/logs folder location