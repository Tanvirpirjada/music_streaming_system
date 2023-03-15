package com.example.Music_Streaming_Service.controller;


import com.example.Music_Streaming_Service.dao.ISongRepository;
import com.example.Music_Streaming_Service.dao.IUserPlayListRepository;
import com.example.Music_Streaming_Service.dao.IUserRepository;
import com.example.Music_Streaming_Service.model.Song;
import com.example.Music_Streaming_Service.model.User;
import com.example.Music_Streaming_Service.model.UserPlayList;
import com.example.Music_Streaming_Service.service.SongService;
import com.example.Music_Streaming_Service.service.UserPlayListService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserPlayListController {

   @Autowired
    IUserRepository userRepository;

   @Autowired
    IUserPlayListRepository userPlayListRepository;
   @Autowired
    UserPlayListService userPlayListService;

   @Autowired
    ISongRepository songRepository;

   @GetMapping("getplaylist")
   public ResponseEntity getplaylist(@Nullable @RequestParam Integer listId, @RequestParam Integer userId){
       JSONObject json=new JSONObject();
       if(!userRepository.findByUserId(userId).isEmpty()){
           json=userPlayListService.getlist(listId,userId);
       }
       else{
           return new ResponseEntity<>("permission denied",HttpStatus.BAD_REQUEST);
       }
       return  new ResponseEntity<>(json.toString(),HttpStatus.OK);
   }


   @GetMapping("getsongs")
   public ResponseEntity getsonglist(@RequestParam Integer listId){

       JSONObject json=userPlayListService.getsongs(listId);


       return  new ResponseEntity(json.toString(),HttpStatus.OK);
   }
   @PutMapping("savsongs")
   public ResponseEntity savesongs(@RequestParam Integer songId, @RequestParam Integer listId){
       userPlayListService.savesongs(songId,listId);
       return new ResponseEntity("playlist saved",HttpStatus.CREATED);
   }


   @DeleteMapping("deleteplaylist")
   public void deletelist(@RequestParam Integer listId, @RequestParam Integer userId){
      userPlayListService.deleteplaylist(listId,userId);
   }
    private List<Song>  setsongs(JSONObject jsonObject) {
        List<Song> songList=new ArrayList<>();
        if(jsonObject.has("songId")){
            Song song=songRepository.findById(jsonObject.getInt("songId")).get();
            songList.add(song);
        }

        return songList;

    }

    private JSONObject validateuser(JSONObject jsonObject) {
       JSONObject json =new JSONObject();
       if(jsonObject.has("userId")){
          List<User>user= userRepository.findByUserId(jsonObject.getInt("userId"));
          if(!user.isEmpty()){
              UserPlayList userPlayList=userPlayListRepository.findByListId(jsonObject.getInt("listId")).get(0);
              if(user.get(0).getUserId()!=userPlayList.getUserId().getUserId()){
                  json.put("erroeMessage","Access denied");
              }
          }
          else {
              json.put("errorMessage","user doesn't exists");
          }

       }
       return json;
    }


    @PostMapping("saveplayList")
    public ResponseEntity saveplaylist(@RequestBody String playlist){
        JSONObject playListObject=new JSONObject(playlist);
        JSONObject errorObject=valdateuser(playListObject);
        if(errorObject.isEmpty()){
            UserPlayList playList=setplayList(playListObject);
            userPlayListService.saveplaylist(playList);
        }
        else{
            return  new ResponseEntity<>(errorObject.toString(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Playlist save successfully",HttpStatus.CREATED);
    }

    private UserPlayList setplayList(JSONObject playListObject) {

        UserPlayList playList=new UserPlayList();

        if(playListObject.has("listName")){
            playList.setListName(playListObject.getString("listName"));
        }

        if(playListObject.has("userId")){
            User user=userRepository.findById(playListObject.getInt("userId")).get();
            playList.setUserId(user);
        }

        Timestamp createdDate=new Timestamp(System.currentTimeMillis());
        playList.setCreatedDate(createdDate);

        return playList;
    }

    private JSONObject valdateuser(JSONObject playListObject) {
        JSONObject error=new JSONObject();

        if(playListObject.has("userId")){
            List<User> user=userRepository.findByUserId(playListObject.getInt("userId"));
            if(user.isEmpty()){
                error.put("erroMessage","permission denied");
            }
        }
        else{
             error.put("erroeMessage","user is mandatory parameter");
        }

        return error;
    }

}
