package com.example.Music_Streaming_Service.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
@Table(name = "song_tbl")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int songId;


    @Column(name = "song_name")
    private String songName;
    private String singerName;

    private String type;

    private Timestamp releasedDate;


    @JoinColumn(name = "admin_id")
    @ManyToOne
    private Admin admin;
}
