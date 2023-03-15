package com.example.Music_Streaming_Service.service;


import com.example.Music_Streaming_Service.dao.IAdminRepository;
import com.example.Music_Streaming_Service.model.Admin;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class adminService {

    @Autowired
    IAdminRepository adminRepository;
    public void saveAdmin(Admin requestAdmin) {
        adminRepository.save(requestAdmin);
    }

    public JSONObject getadmin(String adminName) {
       Admin admin=adminRepository.findByAdminName(adminName);
       JSONObject jsonObject=new JSONObject();
       if(admin!=null){
           jsonObject.put("admindetails",admin);
       }
       else{
          jsonObject.put("admin","admin does not exists");
       }
       return jsonObject;
    }

    public String savelogin(Admin admin) {

       Admin newadmin= adminRepository.findByAdminName(admin.getAdminName());
       if(newadmin.getPassword().equals(admin.getPassword())){
           return "login succcesfull";
       }
       return "password is wrong";
    }
}
