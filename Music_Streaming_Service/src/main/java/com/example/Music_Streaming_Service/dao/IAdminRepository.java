package com.example.Music_Streaming_Service.dao;

import com.example.Music_Streaming_Service.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface IAdminRepository extends JpaRepository<Admin, Integer> {

    @Query(value = "select * from admin_tbl where admin_name = :adminName",nativeQuery = true)
    public Admin findByAdminName(String adminName);

    @Query(value = "select * from admin_tbl where admin_id = :adminId",nativeQuery = true)
    public List<Admin> findByAdminID(Integer adminId);

    @Query(value = "select * from admin_tbl where admin_name = :adminName",nativeQuery = true)
    public List<Admin> findAllByAdminName(String adminName);
}
