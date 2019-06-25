# Fido Expense Tracker

<splash page image here>
![Splash page](./src/main/resources/static/index.jpg)


## Author
Liz Mahoney (emd5), Levi Porter (levibrooke), Sudip Adhikari (sadhikari07), and Kishor Pandey (kishorpan2)

## Version 
1.0.0

## Overview
A pet expense tracker app that keeps track of month-to-month expenses. Features include:

	- Consists of category expense breakdown. 

## Links 
Deployed Link - <http://github.com>

## Getting Started

- On your local, create a folder named `fido`. 
- Then `git init`. 
- On github, copy the link and clone repo inside of the folder that you created `git clone https://github.com/team-char/pet-expenses-tracker.git`.
- Go inside the directory `pet-expenses-tracker` Then open intelliJ `idea .`
- In the import project popup screen, select `used import`, and select appropriate `JDK` (can be 8 or above)
cd.
-Go to `src` > `main` > `resource` > `application.properties`
    - Change `spring.jpa.hibernate.ddl-auto=create` to `spring.jpa.hibernate.ddl-auto=update`
- Then Run application to build the database for `/sign-up` and `/login`

***To Run Application***

In terminal run: `./gradlew bootrun`

***To view gradle commands***

In terminal run: `./gradlew tasks`

***To Run Test***

In terminal run: `./gradlew test`

## Setup

Use the Spring `Initializr` to set 
up an app with dependencies on Web, Thymeleaf, JPA, Postgres, and Security (and optionally DevTools for auto refresh of app on building). Remember to do your initial commit on the master branch before creating your feature branch. Also, see the below note about configuring Spring Security.

## Routes

`/sign-up`
`/login`
`sign-out`





## Resources 
- Sprint Auth Cheat Sheet - https://github.com/codefellows/seattle-java-401d4/blob/master/SpringAuthCheatSheet.md
- Hiberate Many-to-Many - https://www.baeldung.com/hibernate-many-to-many
- RDS Cheat Sheet - https://github.com/codefellows/seattle-java-401d4/blob/master/RDSCheatSheet.md
