# Music Streaming System
 * This project is a Java and Spring Boot based music streaming system. The project utilizes the MVC (Model-View-Controller) architecture to ensure that the code is modular and easily readable. The music streaming system has two models, the admin and user, and their respective permissions to perform CRUD (Create, Read, Update, Delete) operations on songs and playlists.

# Technologies Used
 The following technologies were used to build this music streaming system:

* Spring Boot
* Java
* MySQL database/SQL

# Project Flow

* Controller
The request comes from the client-side and hits the controller class, which then processes the request based on the request type and endpoints declared in the controller class's methods using annotations. The methods of the service class are called from the controller class.

* Service
The service class contains the main business logic for the music streaming system. The methods of the service class are called from the controller class, where we use in-built or native queries of the repository interface.

* Repository
The repository is an interface that extends the JPAREPOSITORY interface, and we used the methods of JPAREPOSITORY and native queries/queries by the method, which we write in the repository interface.

* Database
With the help of Hibernate, we were able to connect to the database. For this project, we used a MySQL database.

# Data Structure Used in Project
* The project mainly uses ArrayList for storing songs and errors.

# Request and Endpoint URLs
Here are the request and endpoint URLs for the music streaming system:

* Swagger Deployment URL

You can access the Swagger documentation for this API at the following URL:

http://65.2.94.104:8080/swagger-ui.html

* Endpoints

Here are the endpoints available in this API:

* Admin

Create Admin: POST /saveAdmin

Get Admin: GET /getAdmin

Login Admin: POST /loginAdmin

* Song
Save Song: POST /saveSong

Get Song: GET /getsong

Update Song: PUT /UpdateSong

Delete Song: DELETE /deletesong

* User

Create User: POST /saveUser

Get User: GET /getUser

Login User: POST /loginUser

* Playlist

Save Playlist: POST /savePlaylist

Get Playlist: GET /getPlaylist

Save Songlist/Update Songlist to Playlist: PUT /saveSongs

Delete Playlist: DELETE /deletePlaylist

Get Songlist from Playlist: GET /getSongs

# Project Summary
In summary, this project is a music streaming system that allows two types of users, the admin and the user, to perform CRUD operations on songs and playlists. The project uses the MVC architecture to make the code modular and readable. It utilizes various technologies such as Spring Boot, Java, and MySQL database/SQL to provide an efficient and effective music streaming system.






