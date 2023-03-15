package com.example.Music_Streaming_Service.service;


import com.example.Music_Streaming_Service.dao.IAdminRepository;
import com.example.Music_Streaming_Service.dao.ISongRepository;
import com.example.Music_Streaming_Service.model.Admin;
import com.example.Music_Streaming_Service.model.Song;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SongService {

    @Autowired
    IAdminRepository adminRepository;

    @Autowired
    ISongRepository songRepository;
    public void saveSong(Song newSong) {
        songRepository.save(newSong);
    }

    public JSONObject getsong(Integer adminId, Integer songId) {
        JSONObject json=new JSONObject();

        ;


         if(songId==null){
             List<Song> songList=songRepository.findByAdmin(adminId);
              json.put("SongDetails",setsong(songList));
         }
         else {
              List<Song>songList=songRepository.findByAdminAndSongId(adminId,songId);
             json.put("songDetails",setsong(songList));
         }

         return json;
    }

    private JSONArray setsong(List<Song> songList1) {
        JSONArray jsonArray=new JSONArray();

        for(Song songs: songList1){
            JSONObject json=new JSONObject();
            json.put("songName",songs.getSongName());
            json.put("singerName",songs.getSingerName());
            json.put("releasedDate",songs.getReleasedDate());
            jsonArray.put(json);
        }
        return jsonArray;
    }


    public void updatesong(Integer songId, Song requestsong) {
        Song song=songRepository.findById(songId).get();
            requestsong.setSongId(song.getSongId());
            songRepository.save(requestsong);
    }

    public void deletesong(int songId) {
        Song song=songRepository.findById(songId).get();

        songRepository.delete(song);
    }
}
