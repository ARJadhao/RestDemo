package com.ashish.rest;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;


public class StudentRepository {
	
	//List<Student> students = null; // used for dummy db
	Connection con =null;
	
	public StudentRepository(){
		
		/* with a dummy DB
		students = new ArrayList();
		
		Student s1 = new Student();
		s1.setId(11);
		s1.setName("Ashish");
		s1.setPoints(59);
		
		Student s2 = new Student();
		s2.setId(22);
		s2.setName("James");
		s2.setPoints(95);
		
		students.add(s1);
		students.add(s2);*/
		
		
		// USING MySQL and JDBC
		String url = "jdbc:mysql://127.0.0.1:3306/restdb";
		String uName = "root";
		String password = "admin@123";
		
		try {
			//Class.forName("com.mysql.cj.jdbc.Driver");
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,uName,password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
		}
	}
	
	public List<Student> getStudents(){
		
	//	return students;
		
		List<Student> students = new ArrayList<>();
		String query = "select * from student";
		try {
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while(rs.next()){
				Student s = new Student();
				s.setId(rs.getInt(1));
				s.setName(rs.getString(2));
				s.setPoints(rs.getInt(3));
				
				students.add(s);
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
		
		
		return students;
	}
	
	public Student createStudent(Student student){
		
		/*students.add(student);
		
		return student;*/
		
		String query = "insert into student values(?,?,?)";
		
		
		try {
			
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, student.getId());
			statement.setString(2, student.getName());
			statement.setInt(3, student.getPoints());
			statement.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return student;
		
	}
	
	public Student getAStudent(int id){
		
		/*for(Student iter: students){
			if(iter.getId()==id){
				return iter;
			}
			
		}
		return new Student();*/
		
		Student s = new Student();
		String query = "select * from student where id="+id;
		Statement statement;
		try {
			statement = con.createStatement();
			ResultSet rs = statement.executeQuery(query);
			if(rs.next()){
				s.setId(rs.getInt(1));
				s.setName(rs.getString(2));
				s.setPoints(rs.getInt(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return s;
	}

public Student updateStudent(Student student){
		
		/*students.add(student);
		
		return student;*/
		
		String query = "update student set name=?, points=? where id=?";
		
		
		try {
			
			PreparedStatement statement = con.prepareStatement(query);
			
			statement.setString(1, student.getName());
			statement.setInt(2, student.getPoints());
			statement.setInt(3, student.getId());
			statement.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return student;
		
	} 

	public Student deleteStudent(int id){
		
String query = "delete from student where id=?";
		
		
		try {
			
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, id);
			statement.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
}
