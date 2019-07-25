# applause
Coding assignment for Applause

FYI: The data from the various csv file is loaded into a embedded H2 db on startup of the application.

To run this project:
1) clone it to your local machine.
2) Either set it up in an IDE and run as a Springboot project or run the command "mvn spring-boot:run" from the project directory in the command line.

3)Examples of get calls(you can either curl from the command line or use postman)

 curl -X GET 'http://localhost:8080/applause/testers/bugs?country=US'
 curl -X GET 'http://localhost:8080/applause/testers/bugs?country=US&device=iPhone 4'
 curl -X GET 'http://localhost:8080/applause/testers/bugs?country=US&device=iPhone 4'
 curl -X GET 'http://localhost:8080/applause/testers/bugs?device=iPhone 4&device=iPhone 4S&country=US'


