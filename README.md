# Music Streaming System
 * This project is a Java and Spring Boot based music streaming system. The project utilizes the MVC (Model-View-Controller) architecture to ensure that the code is modular and easily readable. The music streaming system has two models, the admin and user, and their respective permissions to perform CRUD (Create, Read, Update, Delete) operations on songs and playlists.
 
 ## Screenshots
  ![Screenshot 2023-04-05 074124](https://user-images.githubusercontent.com/111841729/229968485-efb7bddd-7745-41fe-9d8a-951b82b7c58b.png)
![SwagegrDeployment](https://user-images.githubusercontent.com/111841729/229968507-fa0c1908-1d65-4859-ac1a-346bd8322552.png)
 ![Beige Minimalist Mood Photo Collage (1)](https://user-images.githubusercontent.com/111841729/229968238-e65bf4c9-b11f-457b-a2c6-af3dd7cadd87.png)
 ![Beige Minimalist Mood Photo Collage](https://user-images.githubusercontent.com/111841729/229968262-9e2b51bd-3347-46bd-aa70-ddcae06bb4d7.png) 
 ![Beige Minimalist Mood Photo Collage (2)](https://user-images.githubusercontent.com/111841729/229969636-9bf2a2d1-64e4-4fd7-b9f5-999bd79e72e1.png)


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






