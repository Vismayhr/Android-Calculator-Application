# Android Calculator Application #

## Prerequisites: ##
The application adheres to a minimum SDK version of 23. It is necessary to have this version or higher to run the application.

To run the application on a computer, the required tools/softwares are:
* Android SDK
* Android Studio + Emulator

To run the application on an Android phone, enable developer mode on the phone and connect it to the computer. Then, select the 'Run' option in Android Studio and select the connected phone as the target. This will launch the application on the phone.

## Installation: ##
To install Android Studio and the Android SDK, follow the instructions given [here](https://developer.android.com/studio/install).

## Testing: ##
Instrumented Unit Testcases have been implemented in the code. These test cases have been written using the JUnit package. Android implements Instrumented Unit testing by using the functionality of the JUnit package and adapting it to be run in the Android environment (in the emulator).
The test cases cover the different functionalities of the application and also demonstrate scenarios of how exceptions are handled. For eg: Division by zero.
The test cases can be found at: "CalculatorApp\app\src\androidTest\java\com\mobilecomputing\macs\vismay\calculatorapp\InstrumentedTests.java"

## Version: ##
Current version: v1.0

## Authors: ##
Vismay Revankar - vismayhr@dal.ca


