# N26
### Prerequisites

* Java 1.8
* Maven 3.5.0 or later

### Building

This project runs with maven. Running a simple clean/install will clean and build the project and will run the unit tests.

```
$ mvn clean install
```

### Running

The project is made on Boot Strap with Tomcat embedded. The Spring Boot Maven plugin includes a run goal which can be used to quickly compile and run the application, by using: 

```
$ mvn spring-boot:run
```
Also, after building the application, a .jar file is created on the target folder that can be executed to run it.

### Port
The default port is **8080** 
* http://localhost:8080/transaction
