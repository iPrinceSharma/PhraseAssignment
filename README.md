# PhraseAssignment

This is a simple spring boot web application which utilize Phrase APIs to interact with the Phrase Account.

As part of the assignment we have have utilize two APIs in our application
1) Login API to get the auth token (/api2/v1/auth/login)
2) Get Projects API to get the projects list available in Phrase Account (/api2/v1/projects)

## To run and configure the application follow the below steps:

1) Download the source code and load in any of your favorite code editor like Eclipse or intellij idea. This application uses java version 15
2) Run DemoApplication.java to start your spring boot application (asssuming your JDK is properly configured to the PATH variable)
3) Once the application is up and running. Navigate to http://localhost:8080/getConfiguration to update the Phrase Configuration
4) After updating the configuration, Now you can navigate to home page  http://localhost:8080/ to see the list of projects available in Phrase TMS account.

## Demo 
1) Setup Page
<img width="1440" alt="Screenshot 2022-12-05 at 11 36 00 PM" src="https://user-images.githubusercontent.com/75582871/205711157-de6bdd23-b84c-421d-afee-efde58a7849a.png">

2) Projects List
<img width="1440" alt="Screenshot 2022-12-05 at 11 36 12 PM" src="https://user-images.githubusercontent.com/75582871/205711212-a655e893-68b5-4b89-b519-0a8b1e3d8ee5.png">



