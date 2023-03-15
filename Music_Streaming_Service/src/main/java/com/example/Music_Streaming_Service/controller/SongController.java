package com.example.Music_Streaming_Service.controller;


import com.example.Music_Streaming_Service.dao.IAdminRepository;
import com.example.Music_Streaming_Service.model.Admin;
import com.example.Music_Streaming_Service.model.Song;
import com.example.Music_Streaming_Service.service.SongService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.List;

@RestController
public class SongController {

    @Autowired
    IAdminRepository adminRepository;

    @Autowired
    SongService songService;


    @GetMapping("getsong")
    public ResponseEntity getsong( @RequestParam Integer adminId, @Nullable @RequestParam  Integer songId){
        JSONObject modysongList=songService.getsong(adminId,songId);
        return new ResponseEntity(modysongList.toString(),HttpStatus.OK);
    }

    @PutMapping("UpdateSong/{songId}")
    public ResponseEntity updatesong(@PathVariable Integer songId, @RequestBody String requestsong){
        JSONObject json=new JSONObject(requestsong);
        JSONObject errorObject=validateSong(json);
        if(errorObject.isEmpty()) {
            Song newSong = setsong(json);
             songService.updatesong(songId, newSong);
        }
        else{
            return new ResponseEntity(errorObject.toString(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("song updated",HttpStatus.CREATED);
    }

    @DeleteMapping("deletesong")
    public ResponseEntity deletesong (@RequestParam int SongId, @RequestParam int adminId){
       List<Admin> admins= adminRepository.findByAdminID(adminId);
       if(admins.isEmpty()){
           return new ResponseEntity("Not have permission to delete song",HttpStatus.BAD_REQUEST);
       }
       else{
           songService.deletesong(SongId);
       }
       return new ResponseEntity("Song deleted",HttpStatus.NO_CONTENT);
    }

    @PostMapping("saveSong")
    public ResponseEntity  saveSong(@RequestBody String requestSong){
        JSONObject songObject=new JSONObject(requestSong);
        JSONObject  errorObject=validateSong(songObject);
        if(errorObject.isEmpty()){
            Song newSong=setsong(songObject);
            songService.saveSong(newSong);
        }
        else{
            return new ResponseEntity<>(errorObject.toString(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("song released",HttpStatus.CREATED);
    }

    public Song setsong(JSONObject json) {
        Song song=new Song();
        int id= json.getInt("adminId");
        Admin admin=adminRepository.findById(id).get();
        song.setAdmin(admin);

        if(json.has("singerName")){
            song.setSingerName(json.getString("singerName"));
        }

        if(json.has("songName")){
            song.setSongName(json.getString("songName"));
        }

        if(json.has("type")){
            song.setType(json.getString("type"));
        }

        Timestamp releasedDate=new Timestamp(System.currentTimeMillis());

        song.setReleasedDate(releasedDate);

        return song;
    }

    private JSONObject validateSong(JSONObject jsonObject) {
        JSONObject json=new JSONObject();

        if(jsonObject.has("adminId")){
            List<Admin> admin=adminRepository.findByAdminID(jsonObject.getInt("adminId"));
            if(admin.isEmpty()){
                json.put("errorMessage","permission denied");
            }
        }
        return json;
    }
}
