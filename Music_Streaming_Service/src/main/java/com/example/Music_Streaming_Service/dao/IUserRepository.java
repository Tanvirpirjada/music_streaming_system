package com.example.Music_Streaming_Service.dao;

import com.example.Music_Streaming_Service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IUserRepository  extends JpaRepository<User,Integer> {

    @Query(value = "select * from user_tbl where user_name= :userName",nativeQuery = true)
    public User findByUserName(String userName);

    @Query(value = "select * from user_tbl where user_id= :userId",nativeQuery = true)
    public List<User> findByUserId(int userId);
}
