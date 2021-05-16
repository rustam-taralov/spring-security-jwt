package com.example.demo.student;

import lombok.Data;


public class Student {

	private  Integer id;

	private  String name;

	public Student(){

	}

	public Student(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "StudentController [id=" + id + ", name=" + name + "]";
	}
}
