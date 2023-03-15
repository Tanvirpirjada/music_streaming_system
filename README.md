# music_streaming_system
design music_streaming_system using Springboot


* This is a java and Springboot based Music_streaming-Syastem.In this project We Used MVC Architacture Which halp us to make out code more modular and more readable.We design music_streaming_system where wthe admine has authority to apply crud operations on songs like admin able to save song , delete song, update song, get or read song.And there is secound model user which is map with playlist model and user only have permission to do crud on his play list

# Use dFreamework And languages
* SprignBoot
* Java 
* Mysql database/sql

# Flow
* Contoller 
-> The request from postman come in cotroller class based on request type and end points which we declair in controller class method using annotations.And from controller class methods of service class is called.

* service 
-> service class in where we write our main buisness logic. from controller class the methods of service class is called where we use inbuild or native quiries of repository interface. 


*Repository
-> repository is an interface  which extend other interface JPAREPOSITORY and we used the methods of JPAREPOSITORY And native queries/querie by method
 which we write in repository interface.
 
 
 * Database
 With the help of hibernate We are able to connect with out database here we use mysql database.
 
 
 # Data structure used in Project
 * mostly we used ArrayList for Storing songs and errors.


# Requests And Endpoins urls

* swageer Deployent url : http://65.2.94.104:8080/swagger-ui.html

* CreateAdmin : http://localhost:8080/saveAdmin

* GetAdmin : http://localhost:8080/getAdmin

* login Admin : http://localhost:8080/loginAdmin

* Save song :  http://localhost:8080/saveSong

* get song : http://localhost:8080/getsong

* update song: http://localhost:8080/UpdateSong

* Delete Song : http://localhost:8080/deletesong

* create user : http://localhost:8080/saveSong

* Get user : http://localhost:8080/getuser

* login user : http://localhost:8080/loginuser

* Save playlist : http://localhost:8080/saveplayList

* getPlaylist : http://localhost:8080/getplaylis

* save songlist/update songlist  to play list: http://localhost:8080/saveSongs

* delete playlist : http://localhost:8080/deleteplaylist

* get songlist from playlist : http://localhost:8080/getsongs



# Project summary

* In this project we there is two user model admin and user.
* Admin have permission to apply crud On song model.
* User have permission to add song in playlist or apply crud on playlist.
* In this project We Used MVC Architacture Which halp us to make out code more modular and more readable.

