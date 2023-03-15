package com.example.Music_Streaming_Service.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_tbl")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    private String userName;

    private String fullName;

    private String email;

    private String password;

    private String phoneNumber;

    private Timestamp createdDate;

    private Timestamp updatedTime;

    private String gender;


}
