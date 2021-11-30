Home task #19
1. all home task rules (git hub, gitignore, maven, tests)
2. new project
3. new POJO classes with inheritance (at least 1 parent)
4. use each of 3 JPA inheritance strategy 
5. DAO
6. Tests
___

## What's done:
1.3 simple JPA inheritance strategy(superclass,joined table,single table)
2.simple DAo
3.A few tests
4.Made without testcontainers through main method with liquibase
___
## How to use?
    To run environment:  docker-compose up -d
    To run liquibase and fill in database: mvn compile exec:java
    mvn clean install to run tests
___
