package com.ashish.rest;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

@Path("students")
public class StudentController {
	
	StudentRepository studentRepo = new StudentRepository();
	
	@GET	
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Student> getStudent(){
		
		System.out.println("get student called");
		return studentRepo.getStudents();
	}
	
	
	@POST
	@Path("createstudent")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Student createStudent(Student student){
		return studentRepo.createStudent(student);
	}
	
	@GET
	@Path("getstudent/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Student getAStudent(@PathParam("id") int id){
		return studentRepo.getAStudent(id);
	}
	

	@PUT
	@Path("updatestudent")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Student updateStudent(Student student){
		
		if(studentRepo.getAStudent(student.getId()).getId()==0)
			studentRepo.createStudent(student);
		else
			studentRepo.updateStudent(student);
		return student;
	}
	
	@DELETE
	@Path("deletestudent/{id}")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Student deleteStudent(@PathParam("id") int id){
		
		Student student = studentRepo.getAStudent(id);
		
		if(student.getId()!=0)
			studentRepo.deleteStudent(id);
		
		return student;	
			
	}
}
