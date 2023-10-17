ZooFeeder App

A bit of practice with full stack development.
TLDR - Watch the video at the bottom

About:
This repo is intended for me to brush the dust off my REACT, JAVA, and design pattern skills.
The goal of the project is to create a simple react/java application that implentsthe Model-view-controller deisgn pattern and allows a user to create animals in a zoo then feed them.

The ui of the application is built in REACT has the following features:

- Home page that list the animals and the last time they were fed
- Create Animal page that captures information about an animal
- Animal details page that gives more information include feeding history.
  - page features an animal delete option
  - Add to / Delete from the feeding history
- All data is populated to a mysql database via REST api calls

The backend of this application was built using Java, Springboot, and JPA with data being stored in a mySQL database.
The endpoints are spread accross two controllers and allow for creating, deleting, and updating the Animals, Keepers, and Feedings of the application.
To allow for cleaner JSON objects(and data hiding for sensitive fields) coming out of the endpoints I created a number of DTOs(Data Transfer Objects)
and linked it to my Model layer using ModelMapper.

Future Plans:
As of 9/24/23 I've reach the MVP for the project. However, I plan to continue using it for practice.  
 Future enhances road map:

1.  automated test cases for both backend and frontend.  (frontend completed using cyrpress; backend is todo)
2.  REDUX implentation in the REACT portion of the project (completed in pull request)
3.  User Sign in and Creation
4.  Frontend styling

Demo:
[![Watch the video](https://img.youtube.com/vi/UqDk4cJz-7Y/maxresdefault.jpg)](https://youtu.be/UqDk4cJz-7Y)
