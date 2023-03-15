package com.example.Music_Streaming_Service.service;

import com.example.Music_Streaming_Service.dao.IUserRepository;
import com.example.Music_Streaming_Service.model.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    IUserRepository userRepository;
    public void saveuser(User user) {
        userRepository.save(user);
    }

    public JSONObject getuser(Integer userId) {
        JSONObject json=new JSONObject();
        List<User> userList;
        if(userId!=null){
            userList=new ArrayList<>();
            userList.add(userRepository.findById(userId).get());
            json.put("userDetails",setuser(userList));
        }
        else {
            userList=userRepository.findAll();
            json.put("userDetails",setuser(userList));
        }
        return  json;
    }

    private JSONArray setuser(List<User> userList) {
        JSONArray jsonArray=new JSONArray();
        for(User users: userList){
            JSONObject json=new JSONObject();
            json.put("userName",users.getUserName());
            json.put("fullName",users.getFullName());
            json.put("email",users.getEmail());
            json.put("CreatedDate",users.getCreatedDate());
            jsonArray.put(json);
        }
        return jsonArray;
    }

    public String savelogin(User user) {

        User newuser=userRepository.findByUserName(user.getUserName());
        if(newuser.getPassword().equals(user.getPassword())){
            return "login succesfully";
        }
        return "password is invalid";
    }
}
