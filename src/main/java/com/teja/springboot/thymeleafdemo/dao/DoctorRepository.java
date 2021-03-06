package com.teja.springboot.thymeleafdemo.dao;

import com.teja.springboot.thymeleafdemo.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
   @Query(value = "select * from doctor d where d.first_name like %:keyword% or d.specialist like %:keyword%", nativeQuery = true)
   List<Doctor> findByKeyword(@Param("keyword") String keyword);

   Optional<Doctor> findByEmail(String email);
}
