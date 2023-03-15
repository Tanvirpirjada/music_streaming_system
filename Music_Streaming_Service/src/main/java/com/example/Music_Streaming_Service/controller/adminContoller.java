package com.example.Music_Streaming_Service.controller;


import com.example.Music_Streaming_Service.dao.IAdminRepository;
import com.example.Music_Streaming_Service.model.Admin;
import com.example.Music_Streaming_Service.service.adminService;
import com.example.Music_Streaming_Service.util.CommonUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
public class adminContoller {

     @Autowired
    IAdminRepository adminRepository;

     @Autowired
     CommonUtils adminUtil;


     @Autowired
    adminService adminService;


     @GetMapping("getAdmin")
     public ResponseEntity getadmin(@Nullable @RequestParam String adminName){
         JSONObject json=adminService.getadmin(adminName);
         return new ResponseEntity<>(json.toString(),HttpStatus.OK);
     }

     @PostMapping("loginAdmin")
     public ResponseEntity loginAdmin(@RequestBody String loginRequest){
         JSONObject jsonObject=new JSONObject(loginRequest);
         JSONObject errorObject=validateUsername(jsonObject);
         String error="";
         if(errorObject.isEmpty()){
             Admin admin=setlogin(jsonObject);
             error=adminService.savelogin(admin);
         }
         else{
             return  new ResponseEntity(errorObject.toString(),HttpStatus.BAD_REQUEST);
         }
         return  new ResponseEntity(error,HttpStatus.OK);
     }

    private Admin setlogin(JSONObject jsonObject) {
         Admin admin=new Admin();
         admin.setAdminName(jsonObject.getString("adminName"));
         admin.setPassword(jsonObject.getString("password"));
         return admin;
    }

    private JSONObject validateUsername(JSONObject jsonObject) {

         JSONObject json=new JSONObject();
         if(jsonObject.has("adminName")){
             if(adminRepository.findAllByAdminName(jsonObject.getString("adminName")).isEmpty()){
                 json.put("adminNAme","invalid adminName");
             }
         }
         else{
             json.put("errorMessege","this is not be blank");
         }
         return json;
    }


    @PostMapping("saveAdmin")
    public ResponseEntity createadmin( @RequestBody Admin requestAdmin){
        JSONObject adminObject=new JSONObject(requestAdmin);
        JSONObject errorObject=validateAdmin(adminObject);
        if(errorObject.isEmpty()){
            Admin setAdmin=setadmin(adminObject);
             adminService.saveAdmin(setAdmin);
        }
        else{
            return  new ResponseEntity(errorObject.toString(),HttpStatus.BAD_REQUEST);
        }

    return new ResponseEntity("admine save", HttpStatus.CREATED);
    }

    private Admin setadmin(JSONObject adminObject) {
        Admin admin=new Admin();

        admin.setAdminName(adminObject.getString("adminName"));
        admin.setEmail(adminObject.getString("email"));
        admin.setPassword(adminObject.getString("password"));
        admin.setPhoneNumber(adminObject.getString("phoneNumber"));

        if(adminObject.has("gender")){
            admin.setGender(adminObject.getString("gender"));
        }

        if(adminObject.has("firstName")){
            admin.setFirstName(adminObject.getString("firstName"));
        }

        if(adminObject.has("lastName")){
            admin.setLastName(adminObject.getString("lastName"));
        }

            Timestamp createdDate=new Timestamp(System.currentTimeMillis());
            admin.setCreatedDate(createdDate);

        return admin;
    }

    private JSONObject validateAdmin(JSONObject adminObject) {

        JSONObject errorList=new JSONObject();

        if(adminObject.has("adminName")){
            if(adminRepository.findByAdminName(adminObject.getString("adminName"))!=null){
                errorList.put("adminName","Already Exists");
            }
        }
        else{
             errorList.put("errorMessage","This field is mandatory");
        }


        if(adminObject.has("password")){
            if(!adminUtil.isValidPassword(adminObject.getString("password"))){
                errorList.put("password","is invalid must be in this format : Tanvir@112");
            }
        }
        else{
            errorList.put("errorMessage","This field is mandatory");
        }


        if(adminObject.has("email")){
             if(!adminUtil.isValidEmail(adminObject.getString("email"))){
                 errorList.put("email","invalid email must be in this format:tanvir@gmail.com");
             }
        }
        else{
            errorList.put("errorMessage","This field is mandatory");
        }

        if(adminObject.has("phoneNumber")){
            if(!adminUtil.isPhoneNumberValid(adminObject.getString("phoneNumber"))){
                errorList.put("phoneNumber","invalid phoneNumber");
            }
        }
        else{
            errorList.put("errorMessage","This field is mandatory");
        }

        return errorList;
    }
}
