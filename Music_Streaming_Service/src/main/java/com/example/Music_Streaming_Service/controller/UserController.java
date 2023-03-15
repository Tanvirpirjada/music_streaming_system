package com.example.Music_Streaming_Service.controller;

import com.example.Music_Streaming_Service.dao.IUserRepository;
import com.example.Music_Streaming_Service.model.User;
import com.example.Music_Streaming_Service.service.UserService;
import com.example.Music_Streaming_Service.util.CommonUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
public class UserController {

    @Autowired
    CommonUtils commonUtils;

    @Autowired
    IUserRepository userRepository;

    @Autowired
    UserService userService;


    @GetMapping("getuser")
    public ResponseEntity getuser (@Nullable @RequestParam Integer userId){
        JSONObject jsonObject=userService.getuser(userId);
        return new ResponseEntity<>(jsonObject.toString(),HttpStatus.OK);
    }

    @PostMapping("loginuser")
    public ResponseEntity loginuser(@RequestBody  String loginrequest){
        JSONObject jsonObject=new JSONObject(loginrequest);
        JSONObject erroeObject=validateuserName(jsonObject);
        String error="";
        if(erroeObject.isEmpty()){
            User user = setlogin(jsonObject);
          error=  userService.savelogin(user);
        }
        else{
            return new ResponseEntity<>(erroeObject.toString(),HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(error,HttpStatus.OK);

    }

    private User setlogin(JSONObject jsonObject) {
        User user=new User();
        user.setUserName(jsonObject.getString("userName"));
        user.setPassword(jsonObject.getString("password"));
        return user;
    }

    private JSONObject validateuserName(JSONObject jsonObject) {
        JSONObject errorList=new JSONObject();
        if(!jsonObject.has("userName")){
            errorList.put("errorMessage","this filed in mandatory");
        }
        if(!jsonObject.has("password")){
            errorList.put("errorMessage","This field is mandatory");
        }

        return errorList;
    }

    @PostMapping("saveUser")
    public ResponseEntity saveuser(@RequestBody User requestUser){
        JSONObject jsonObject=new JSONObject(requestUser);
        JSONObject errorObject=validateuser(jsonObject);
        if(errorObject.isEmpty()){
            User user=setuser(jsonObject);
            userService.saveuser(user);
        }
        else{
            return new ResponseEntity<>(errorObject.toString(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("user registered",HttpStatus.CREATED);
    }

    private User setuser(JSONObject jsonObject) {
        User newuser=new User();
        newuser.setUserName(jsonObject.getString("userName"));
        newuser.setEmail(jsonObject.getString("email"));
        newuser.setPassword(jsonObject.getString("password"));
        newuser.setPhoneNumber(jsonObject.getString("phoneNumber"));

        if(jsonObject.has("fullName")){
            newuser.setFullName(jsonObject.getString("fullName"));
        }

        if(jsonObject.has("gender")){
            newuser.setGender(jsonObject.getString("gender"));
        }

        Timestamp createdDate=new Timestamp(System.currentTimeMillis());
        newuser.setCreatedDate(createdDate);

        return newuser;
    }

    private JSONObject validateuser(JSONObject jsonObject) {

        JSONObject errorList=new JSONObject();

        if(jsonObject.has("userName")){
            if(userRepository.findByUserName(jsonObject.getString("userName"))!=null){
                errorList.put("userName","Already Exists");
            }
        }
        else{
            errorList.put("errorMessage","This field is mandatory");
        }


        if(jsonObject.has("password")){
            if(!commonUtils.isValidPassword(jsonObject.getString("password"))){
                errorList.put("password","is invalid must be in this format : Tanvir@112");
            }
        }
        else{
            errorList.put("errorMessage","This field is mandatory");
        }


        if(jsonObject.has("email")){
            if(!commonUtils.isValidEmail(jsonObject.getString("email"))){
                errorList.put("email","invalid email must be in this format:tanvir@gmail.com");
            }
        }
        else{
            errorList.put("errorMessage","This field is mandatory");
        }

        if(jsonObject.has("phoneNumber")){
            if(!commonUtils.isPhoneNumberValid(jsonObject.getString("phoneNumber"))){
                errorList.put("phoneNumber","invalid phoneNumber");
            }
        }
        else{
            errorList.put("errorMessage","This field is mandatory");
        }

        return errorList;
    }
}
