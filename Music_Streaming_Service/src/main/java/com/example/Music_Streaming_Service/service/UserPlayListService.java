package com.example.Music_Streaming_Service.service;


import com.example.Music_Streaming_Service.dao.IAdminRepository;
import com.example.Music_Streaming_Service.dao.ISongRepository;
import com.example.Music_Streaming_Service.dao.IUserPlayListRepository;
import com.example.Music_Streaming_Service.dao.IUserRepository;
import com.example.Music_Streaming_Service.model.Admin;
import com.example.Music_Streaming_Service.model.Song;
import com.example.Music_Streaming_Service.model.User;
import com.example.Music_Streaming_Service.model.UserPlayList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserPlayListService {

    @Autowired
    IUserPlayListRepository userPlayListRepository;


    @Autowired
    ISongRepository songRepository;
    @Autowired
    IUserRepository userRepository;

    @Autowired
    IAdminRepository adminRepository;
    public void saveplaylist(UserPlayList playList) {
        userPlayListRepository.save(playList);
    }

    public JSONObject getlist(Integer listId, Integer userId) {
        List<UserPlayList> userPlayLists;
        JSONObject json=new JSONObject();
       if(listId!=null){
          userPlayLists=userPlayListRepository.findByListId(listId);
           JSONArray jsonArray=setuserPlaylist(userPlayLists);
          json.put("PlayList", jsonArray);
        }
        else if(listId==null){
            userPlayLists=userPlayListRepository.findByUserId(userId);

           JSONArray jsonArray=setuserPlaylist(userPlayLists);
           json.put("PlayList", jsonArray);
        }
        return json;
    }

    private JSONArray setuserPlaylist(List<UserPlayList> lists) {
          JSONArray jsonArray=new JSONArray();

        for(UserPlayList playList: lists){
            JSONObject json=new JSONObject();
            json.put("listName", playList.getListName());
            json.put("createddate",playList.getCreatedDate());
            JSONArray jsonArray1=new JSONArray();
            JSONObject jsonObject=new JSONObject();
            List<User> user= userRepository.findByUserId(playList.getUserId().getUserId());
            User user1=user.get(0);
            jsonObject.put("userName",user1.getUserName());
            jsonObject.put("email",user1.getEmail());
            jsonObject.put("fullName",user1.getFullName());
            jsonArray1.put(jsonObject);
            json.put("userDetails",jsonArray1);
            jsonArray.put(json);
        }
        return  jsonArray;

    }

    public void savesongs(Integer songId, Integer listId) {
        UserPlayList playList=userPlayListRepository.findById(listId).get();
        Song song=songRepository.findById(songId).get();
        List<Song> songList=new ArrayList<>();

        songList.add(song);

        playList.setSongList(songList);

        userPlayListRepository.save(playList);
    }

    public JSONObject getsongs(Integer listId) {
        List<Song> songList=userPlayListRepository.findById(listId).get().getSongList();
        JSONObject Json=setsonglist(songList);
        return Json;
    }

    private JSONObject setsonglist(List<Song> songList) {
        JSONObject json=new JSONObject();
        for(Song song:songList){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("songName",song.getSongName());
            jsonObject.put("singerName",song.getSingerName());
            jsonObject.put("releaseddate",song.getReleasedDate());
            JSONArray jsonArray=new JSONArray();
            JSONObject object=new JSONObject();
            Admin admin=adminRepository.findById(song.getAdmin().getAdminId()).get();
            object.put("adminName",admin.getAdminName());
            object.put("adminId",admin.getAdminId());
            jsonArray.put(object);
            jsonObject.put("admin",jsonArray);
            json.put("songList",jsonObject);
        }
        return json;
    }


    public void deleteplaylist(Integer listId, Integer userId) {
        List<UserPlayList> userPlayList=userPlayListRepository.findByListId(listId);
        if(!userPlayList.isEmpty() && userPlayList.get(0).getUserId().getUserId()==userId){
            userPlayListRepository.delete(userPlayList.get(0));
        }
    }
}
