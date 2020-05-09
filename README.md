# Spring-MongoDB-TestCases

### Overview
This project will help you to create a Spring boot application with MongoDB as the database and also help you to write test cases for DAO,Service,Controller layer. 

I have created a Spring Boot project which stores Hero and can perform CURD operations on them. This is inspired by anime called My Hero Academia where there will be Heroes and have superpowers(quirk). Basically the Hero have the following attribute id,name,quirk(power).

### Note
To setup MongoDB as the database we have to do three things
* ##### Keep ```@Document``` on top of the model class. Where  ```collection``` is the table.
``` 
@Document(collection = "Heroes")
public class Hero{
}
```
* ##### In /src/main/resources/```application.properties``` we have to specify the Database name.
```
spring.data.mongodb.database=MyHeroAcademia
```
* ##### While creating the Repository interfce we have to extend with ```MongoRepository```.
```
Example - public interface MyHeroAcademiaRepository extends MongoRepository<MyHeroAcademia, Long> {
}
```
### Project Structure
```
├── src
  ├── main
    ├── java/com/example/demo
      ├── Controller         #This contains Controller class which handles all the API request.
      ├── Repository         #This contains Repository interface and DAO class which provides method to store & modify data.
      ├── Service            #This contains Service class which handles the business logic.
      └── Model              #This contains Hero class & Message Response class  .
```
### Setup 
* Download Spring Tool Suite and run it  and then click on
``` 
File -> Import -> Existing Maven Project -> "Select the Spring-MongoDB-TestCases " -> Finish
```
### Run 
* To Run the Spring Boot project
``` Right Click on the imported folder -> Run As -> Spring Boot App```
* To Run the Test cases 
``` Right Click on the imported folder -> Run As -> JUnit Test```
