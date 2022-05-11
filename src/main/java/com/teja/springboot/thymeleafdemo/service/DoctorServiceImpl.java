package com.teja.springboot.thymeleafdemo.service;

import com.teja.springboot.thymeleafdemo.dao.DoctorRepository;
import com.teja.springboot.thymeleafdemo.entity.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

    private DoctorRepository doctorRepository;

    @Autowired
    public DoctorServiceImpl(DoctorRepository theDoctorRepository) {
        doctorRepository = theDoctorRepository;
    }

    @Override
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor findById(int theId) {
        Optional<Doctor> result = doctorRepository.findById(theId);

        Doctor theDoctor = null;

        if (result.isPresent()) {
            theDoctor = result.get();
        }
        else {
            // we didn't find the doctor
            throw new RuntimeException("Did not find doctor id - " + theId);
        }

        return theDoctor;
    }

    @Override
    public void save(Doctor theDoctor) {
        doctorRepository.save(theDoctor);
    }

    @Override
    public void deleteById(int theId) {
        doctorRepository.deleteById(theId);
    }

    @Override
    public List<Doctor> getByKeyword(String keyword){
        return doctorRepository.findByKeyword(keyword);
    }

}