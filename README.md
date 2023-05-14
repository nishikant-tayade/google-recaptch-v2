# Google reCAPTCHA Web Application Demo

This is a small web application that demonstrates how to use Google reCAPTCHA in your web application. It also includes a runtime polymorphism feature to choose between RestTemplate and WebClient on the fly based on a config property.

## Prerequisites

Before running this application, you need to have the following installed on your machine:

- Java 11 or higher
- Maven 3.6.3 or higher
- Google reCAPTCHA site and secret keys

## Installation

1. Clone this repository to your local machine.
2. Open a terminal and navigate to the project directory.
3. Set your reCAPTCHA site and secret keys as environment variables (e.g. RECAPTCHA_SITE_KEY and RECAPTCHA_SECRET_KEY).
4. Build the project using the command `mvn clean package`.
5. Run the application using the command `java -jar target/recaptcha-demo-1.0.jar`.

## Usage

Once the application is running, open your web browser and navigate to `http://localhost:8080`. You should see a simple form with a Google reCAPTCHA checkbox. Complete the reCAPTCHA and submit the form to see a success message. If the reCAPTCHA is not completed, an error message will be displayed.

To switch between RestTemplate and WebClient, set the `recaptcha.site.verify.via` property in the `application.properties` file to either `RestTemplate` or `Webclient`. 
