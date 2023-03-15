package com.example.Music_Streaming_Service.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "userPlaylist_tbl")
public class UserPlayList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "list_id")
    private int listId;

    private String listName;

    private Timestamp createdDate;

    private Timestamp updatedDate;


    @OneToMany(fetch = FetchType.LAZY)
    private List<Song> songList;


    @JoinColumn(name = "user_id")
    @ManyToOne
    private User userId;
}
