
# Hi, I'm Flavia! 😉

## 👩 About Me
💻Dedicated back-end software developer | 👨‍💻Motivated to work for companies to help achieve back-end goals | Java, Spring Boot | Passionate about logic and solving problems using it and technology | 💼 Actively looking for a job 


## 🛠 Skills
Back-end development, Software development, Web development, Java, Spring framework, Spring boot, Data structures, Algorithms, OOP, MySQL, Relational databases, SQL, Git, HTML, CSS, Web services, Rest APIs, Unit Testing

## 🔗 Links
[![portfolio](https://img.shields.io/badge/my_portfolio-000?style=for-the-badge&logo=ko-fi&logoColor=white)](https://github.com/flavia-caluser)
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/flavia-caluser/)

Here's all you need to know about my danceClasses project:

# 🎶 My DanceClasses App 🎶
The application is developed using Java Spring Boot and MySQL, showcasing it's robust foundation. Its  main purpose is to help organise and stock all the necessary information about the students, instructors and courses in order to ease the organising duties of the instructors. 
## Features 🖱️

In this app you can log in as a:
- Student 
- Instructor

As an instructor, I can:
- Add new students, attendances, payments, lessons
- Delete all the above
- Using a cronJob, all the instructors get a weekly mail letting them know if there are any birthdays that week and if there are, they also get the names of the students
- Change details about the users(new name, new email etc)

As a student, I can:
- Add reviews
- Add anonymous reviews
- Delete reviews

Functionalities that can be done by both types of users:
- See all courses with their details
- See all reviews of a course
- Register
- Authenticate
- See the last or all payments of a student


## Built with

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)

## Demo

Work in progress 💪



## API Reference

#### Register

```http
  POST /auth/register
```

| Parameter | Type     | Description                                                        |
| :-------- | :------- |:-------------------------------------------------------------------|
| `body`    | `json`   | **Required**. The log in data and the role of the user to be added |

Request body example:

```json
{
  "username": "testy2",
  "password": "testy2",
  "role": "Student"
}
```  
After running this request, the client receives a token which they need to put in the authorize section from the upper right side.
Now they have 30 minutes to access all requests based on it's role.

This is an example of what should be included in the request header:

```http
Authorization: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjcxMTQzMzEyfQ.dxIzsD9Bm8y_kw3MOoZ2JXIKOg--uZaA5XNtBLdGYc4Ps3nlzBFDwBJi0bEeHlCggonZ6nQ2zwCI0D5a7dXjmw
```  

#### Authenticate

```http
  GET /auth/token
```

| Parameter | Type   | Description                                                 |
|:----------|:-------|:------------------------------------------------------------|
| `body`    | `json` | **Required**. The log in data of the student to be verified | 

Request body example:

```json
{
  "username": "student1",
  "password": "student1"
}
```  

#### Add an instructor to the database

```http
  POST /instructor/add
```

| Parameter | Type     | Description                                   |
| :-------- | :------- |:----------------------------------------------|
| `body`    | `json`   | **Required**. The instructor data to be added |

Request body example:

```json
{
  "name": "Testy Tester",
  "emailAddress": "instructor@gmail.com"
}
```  


#### Find an instructor by name

```http
  GET /instructor/${name}
```

| Parameter | Type     | Description                                   |
|:----------| :------- |:----------------------------------------------|
| `name`    | `string` | **Required**. Name of the searched instructor |


#### Change an instructor's email address

```http
  PUT /instructor/changeEmail/${id}
```

| Parameter | Type   | Description                    |
|:----------|:-------|:-------------------------------|
| `id`      | `Long`  | **Required**. Id of instructor | 
| `body`    | `json` | **Required**. The new email    |

Request body example:

```json
{
  "emailAddress": "instructor@gmail.com"
}
```  
#### Delete an instructor

```http
  DELETE /instructor/${id}
```

| Parameter | Type   | Description                                  |
| :-------- |:-------|:---------------------------------------------|
| `id`      | `Long` | **Required**. Id of instructor to be deleted |


#### Add a new student to the DB

```http
  POST /student/add
```


| Parameter | Type     | Description                                         |
| :-------- | :------- |:----------------------------------------------------|
| `body`    | `json`   | **Required**. The properties of student to be added |

Request body example:

```json
{
  "name": "name",
  "birthDate": "1988-03-31",
  "emailAddress": "mail@gmail.com"
}
``` 

#### Find a student by name

```http
  GET /student/${name}
```

| Parameter | Type     | Description                                |
|:----------| :------- |:-------------------------------------------|
| `name`       | `string` | **Required**. Name of the searched student |

#### See a student's courses

```http
  GET /student/courses/${name}
```

| Parameter | Type     | Description                                |
|:----------| :------- |:-------------------------------------------|
| `name`       | `string` | **Required**. Name of the searched student |

#### Change a student's email

```http
  PUT /student/changeEmail/${id}
```

| Parameter | Type   | Description                              |
|:----------|:-------|:-----------------------------------------|
| `id`      | `Long` | **Required**. Id of the searched student |

#### Delete a student

```http
  DELETE /student/${id}
```

| Parameter | Type   | Description                               |
| :-------- |:-------|:------------------------------------------|
| `id`      | `Long` | **Required**. Id of student to be deleted |



#### Add a course to the database

```http
  POST /course/add
```

| Parameter | Type     | Description                               |
| :-------- | :------- |:------------------------------------------|
| `body`    | `json`   | **Required**. The course data to be added |

Request body example:

```json
{
  "name": "Salsa si Bachata Incepatori 3",
  "instructorsNames": ["name1", "name2"],
  "startDate": "2024-04-11T20:15:00",
  "endDate": "2024-09-12T20:15:00"
}
```
#### Add a lesson to the database

```http
  POST /course/addLesson
```

| Parameter | Type     | Description                               |
| :-------- | :------- |:------------------------------------------|
| `body`    | `json`   | **Required**. The lesson data to be added |

Request body example:

```json
{
  "name": "Salida mujer",
  "courseName": "Kizomba Incepatori 1",
  "dateAndTime": "2024-07-15T20:15:00"
}
```
#### Add a review to the database

```http
  POST /course/addReview/${courseId}/${studentId}
```

| Parameter | Type     | Description                               |
| :-------- | :------- |:------------------------------------------|
| `body`    | `json`   | **Required**. The review data to be added |

Request body example:

```json
{
  "comment": "Super!"
}
```

#### Add an anonymous review to the database

```http
  POST /course/addReview/${courseId}
```

| Parameter | Type     | Description                               |
| :-------- | :------- |:------------------------------------------|
| `body`    | `json`   | **Required**. The review data to be added |

Request body example:

```json
{
  "comment": "Super!"
}
```
#### Get all reviews of a course

```http
  GET /course/allCourseReviews/${courseId}
```

| Parameter | Type   | Description                                 |
|:----------|:-------|:--------------------------------------------|
| `id`      | `Long` | **Required**. The id of the searched course |

#### Get all courses

```http
  GET /course/all
```

| Parameter | Type   | Description                                 |
|:----------|:-------|:--------------------------------------------|
| `id`      | `Long` | **Required**. The id of the searched course |

#### Change the name of a course

```http
  PUT /course/change/name/${courseId}/${newName}
```

| Parameter | Type     | Description                                         |
|:----------|:---------|:----------------------------------------------------|
| `id`      | `Long`      | **Required**. The id of the searched course         |
| `newName`     | `string` | **Required**. The name that will be given to the course |

#### Change the dates of a course

```http
  PUT /course/change/dates/${courseId}
```

| Parameter | Type   | Description                                 |
|:----------|:-------|:--------------------------------------------|
| `id`      | `Long` | **Required**. The id of the searched course |
| `body`    | `json` | **Required**. The new dates                 |

Request body example:

```json
{
  "startDate": "2024-04-11T20:15:00",
  "endDate": "2024-09-12T20:15:00"
}
```

#### Change the price of a lesson 

```http
  PUT /course/change/lessonPrice/${courseId}
```

| Parameter | Type   | Description                                 |
|:----------|:-------|:--------------------------------------------|
| `id`      | `Long` | **Required**. The id of the searched course |
| `body`    | `json` | **Required**. The new price                 |

Request body example:

```json
{
  "newPrice": 40.00
}
```

#### Change the price of all lessons

```http
  PUT /course/change/lessonPrice/all
```

| Parameter | Type     | Description                                 |
|:----------|:---------|:--------------------------------------------|
| `body`    | `json`   | **Required**. The new price                 |

Request body example:

```json
{
  "newPrice": 40.00
}
```

As the others, you can also _delete_ a _course_, a _lesson_ or a _review_ with their id in the URL.

#### Add a payment to a student

```http
  POST /payment/add/${studentName}
```

| Parameter     | Type     | Description                                                        |
|:--------------|:---------|:-------------------------------------------------------------------|
| `studentName` | `string` | **Required**. The name of the student for whom you add the payment |
| `body`        | `json`   | **Required**. The payment details to be added                         |

Request body example:

```json
{
  "date": "2024-08-01T20:15:00",
  "paymentMethod": "CASH",
  "lessonNameList": ["Hip Roll", "Coregrafie", "Suzzie Q"]
}
```

#### See all payment made by a student

```http
  GET /payment/all/${studentName}
```

| Parameter     | Type     | Description                                                          |
|:--------------|:---------|:---------------------------------------------------------------------|
| `studentName` | `string` | **Required**. The name of the student whose payments you want to see |

#### See the last payment made by a student

```http
  GET /payment/last/${studentName}
```

| Parameter     | Type     | Description                                                         |
|:--------------|:---------|:--------------------------------------------------------------------|
| `studentName` | `string` | **Required**. The name of the student whose payment you want to see |

With the id of the payment, you can delete it as the other deleting functionalities.

#### Add an attendance to a student

```http
  POST /attendance/add/${lessonId}/${studentName}
```

| Parameter     | Type     | Description                                                         |
|:--------------|:---------|:--------------------------------------------------------------------|
| `lessonId`    | `Long`   | **Required**. The id of the attended lesson.                        |
| `studentName` | `string` | **Required**. The name of the student for whom you add the attendance |

#### See all attendances of a student

```http
  GET /attendance/all/${studentName}
```

| Parameter     | Type     | Description                                                          |
|:--------------|:---------|:---------------------------------------------------------------------|
| `studentName` | `string` | **Required**. The name of the student whose attendances are searched |

#### Delete an attendance

```http
  DELETE /attendance/${id}
```

| Parameter | Type   | Description                                          |
|:----------|:-------|:-----------------------------------------------------|
| `id`       | `Long` | **Required**. The id of the attendance to be deleted |


## Prerequisites

For building and running the application you need:
- JDK 1.8 or higher
- Maven 3

For building and running the application with Docker, you need:

- Docker Desktop (for Windows and Mac) or Docker Engine (for Linux)
- Docker Compose (optional, for orchestrating multi-container applications)

## Dependencies

You don't need any additional dependencies.
All dependecies related to database management, server management, security management and so on, will be automatically injected by Maven using the pom.xml file located in the root folder of the project.
## Installation

Clone the project

```bash
  git clone https://github.com/flavia-caluser/DanceClasses
```

Go to the project directory

```bash
  cd my-project
```

## Run Locally

Use maven to build the app and, to run it, and to start the local embedded Tomcat server

```bash
  mvn spring-boot:run
```


## Running Tests

To run tests, run the following command

```bash
  mvn test
```


### Build the Docker Image

Navigate to the root directory of your project where the Dockerfile is located, and run the following command:

```http
docker build -t danceClasses .
```

### Run the Application

```http
docker run -p 8080:8080 danceClasses
```

### Run using Docker Compose (Optional)

If your application requires running multiple services (like an app server and a database), you can use Docker Compose to manage these services. Here is an example docker-compose.yml file for your application:

```http
docker-compose up
```

This command builds and starts both the application and the database containers. The application will connect to the MySQL database as configured in your application's properties.

## Deployment with Docker
Deploying your dockerized application can vary based on your hosting provider. Typically, you would push your Docker image to a container registry (e.g., Docker Hub, GitHub Container Registry) and then pull and run it on your production server. Here are the basic steps for pushing to Docker Hub:

### Tag your image
```http
docker tag danceClasses yourusername/danceClasses:latest
```

### Push your image to the registry
```http
docker push yourusername/event-reservation-app:latest
```

After pushing your image, you can follow your hosting provider instructions.


## Usage
First, you're going to have to register, so go to the /auth/register endpoint and enter a user, a password and role, either Student or Instructor.
After this, you have to authenticate, so you have to go to the /auth/token endpoint and enter the credentials you created and you'll get a token which you have to copy and put on the right side of the page and click on the "Authorize" button and then paste there the token.
From now on, you can use all other available endpoints, as per swagger documentation, according to your role, after 30 minutes you have to refresh the page and authenticate again.


## Roadmap

In the future, application can be extended with following:

- Getting the revenues of a course between two dates 
- Getting the revenues for all courses between two dates
- A Q&A functionality

## Badges


![Maintained](https://img.shields.io/badge/Maintained%3F-yes-green.svg)
![GIT](https://img.shields.io/badge/GIT-E44C30?style=for-the-badge&logo=git&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)