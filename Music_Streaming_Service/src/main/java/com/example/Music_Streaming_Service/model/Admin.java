package com.example.Music_Streaming_Service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "admin_tbl")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private int adminId;


    //@NotBlank(message = "This field in not be blank")

    @Column(name = "admin_name")
    private String  adminName;

    private String firstName;

    private String lastName;

   // @NotBlank(message = "This field in not be blank")
    private String email;

   // @NotBlank(message = "This field in not be blank")
    private String password;

   // @NotBlank(message = "This field in not be blank")
    private String phoneNumber;

    private Timestamp createdDate;

    private Timestamp updatedDate;

    private String gender;


}
