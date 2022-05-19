package com.teja.springboot.thymeleafdemo.controller;

import java.util.List;

import com.teja.springboot.thymeleafdemo.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.teja.springboot.thymeleafdemo.entity.Doctor;

@Controller
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    public DoctorController(){}
    public DoctorController(DoctorService theDoctorService) {
        doctorService = theDoctorService;
    }

    @GetMapping(path={"/list","/list/search"})
    public String listDoctors(Model theModel,String keyword) {

        if(keyword!=null){
            List<Doctor> list=doctorService.getByKeyword(keyword);
            theModel.addAttribute("doctors",list);
        }
        else{
            // get doctors from db
            List<Doctor> theDoctors = doctorService.findAll();

            // add to the spring model
            theModel.addAttribute("doctors", theDoctors);
        }
        return "doctors/list-doctors";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        // create model attribute to bind form data
        Doctor theDoctor = new Doctor();

        theModel.addAttribute("doctor", theDoctor);

        return "doctors/doctor-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("doctorId") int theId,
                                    Model theModel) {

        // get the doctor from the service
        Doctor theDoctor = doctorService.findById(theId);

        // set doctor as a model attribute to pre-populate the form
        theModel.addAttribute("doctor", theDoctor);

        // send over to our form
        return "doctors/doctor-form";
    }


    @PostMapping("/save")
    public String saveDoctor(@ModelAttribute("doctor") Doctor theDoctor) {

        // save the doctor
        doctorService.save(theDoctor);

        // use a redirect to prevent duplicate submissions
        return "redirect:/doctors/list";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam("doctorId") int theId) {

        // delete the doctor
        doctorService.deleteById(theId);

        // redirect to /doctors/list
        return "redirect:/doctors/list";

    }

}
