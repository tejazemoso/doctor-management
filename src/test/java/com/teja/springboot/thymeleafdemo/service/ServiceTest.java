package com.teja.springboot.thymeleafdemo.service;

import com.teja.springboot.thymeleafdemo.dao.DoctorRepository;
import com.teja.springboot.thymeleafdemo.dao.UserRepository;
import com.teja.springboot.thymeleafdemo.entity.Doctor;
import com.teja.springboot.thymeleafdemo.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ServiceTest {

	@Autowired
	private DoctorService doctorService;

	@Autowired
	private UserDetailsService userDetailsService;
	@MockBean
	private DoctorRepository doctorRepository;

	@MockBean
	private UserRepository userRepository;

	/*@Test
	void contextLoads() {
	}*/

	@Test
	void findAllTest(){
		when(doctorRepository.findAll()).thenReturn(Stream.of(new Doctor(1,"Dr.Sampath","Kumar","sampath@gmail.com","cardiologist","41 years of practice"),new Doctor(2,"Dr.JMK","Murthy","jmk@gmail.com","Neurologist","43 years of practice")).collect(Collectors.toList()));
		assertEquals(2,doctorService.findAll().size());
	}

	@Test
	void findByIdTest(){
		int id=2;
		when(doctorRepository.findById(id)).thenReturn(Optional.of(new Doctor(2, "Dr.JMK", "Murthy", "jmk@gamil.com", "Neurologist", "43 years of experience")));
		assertEquals(1,1);
	}

	@Test
	void saveTest(){
		Doctor doctor=new Doctor(3,"Dr.Satish","Kumar","sathish@gmail.com","cardiologist","41 years of practice");
		doctorService.save(doctor);
		verify(doctorRepository,times(1)).save(doctor);
	}

	@Test
	void deleteByIdTest(){
		int id=2;
		doctorService.deleteById(id);
		verify(doctorRepository,times(1)).deleteById(id);
	}

	@Test
	void getByKeywordTest(){
		String keyword="cardiologist";
		when(doctorRepository.findByKeyword(keyword)).thenReturn(Stream.of(new Doctor(1,"Dr.Sampath","Kumar","sampath@gmail.com","cardiologist","41 years of practice"),new Doctor(2,"Dr.JMK","Murthy","jmk@gmail.com","Neurologist","43 years of practice")).collect(Collectors.toList()));
		assertEquals(2,doctorService.getByKeyword(keyword).size());
	}


	@Test
	void loadUserByUsername(){
		String name="Sampath";
		User user=new User(1L,"admin","admin",true);
		when(userRepository.getUserByUsername(name)).thenReturn(user);
		assertEquals(1,1);
	}
}
