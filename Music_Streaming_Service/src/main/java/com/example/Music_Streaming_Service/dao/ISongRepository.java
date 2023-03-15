package com.example.Music_Streaming_Service.dao;

import com.example.Music_Streaming_Service.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ISongRepository extends JpaRepository<Song,Integer> {

    @Query(value = "select * from song_tbl where song_name= :songName",nativeQuery = true)
    public Song findBySongName(String songName);

    @Query(value = "select * from song_tbl where admin_id= :admin",nativeQuery = true)
    public List<Song> findByAdmin(int admin);

    @Query(value = "select * from song_tbl where admin_id= :admin and song_id= :songId",nativeQuery = true)
    public List<Song>  findByAdminAndSongId(Integer admin, Integer songId);
}
