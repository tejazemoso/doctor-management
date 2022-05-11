package com.teja.springboot.thymeleafdemo.service;

import com.teja.springboot.thymeleafdemo.entity.Doctor;

import java.util.List;

public interface DoctorService {

    public List<Doctor> findAll();

    public Doctor findById(int theId);

    public void save(Doctor thedoctor);

    public void deleteById(int theId);

    public List<Doctor> getByKeyword(String keyword);

}
