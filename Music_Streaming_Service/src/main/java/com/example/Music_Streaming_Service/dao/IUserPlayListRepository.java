package com.example.Music_Streaming_Service.dao;

import com.example.Music_Streaming_Service.model.UserPlayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IUserPlayListRepository extends JpaRepository<UserPlayList, Integer> {

    @Query(value = "select * from user_playlist_tbl where list_id= :listId",nativeQuery = true)
    public List<UserPlayList> findByListId(Integer listId);

    @Query(value = "select * from user_playlist_tbl where user_id= :userId",nativeQuery = true)
    public List<UserPlayList> findByUserId( Integer userId);
}
