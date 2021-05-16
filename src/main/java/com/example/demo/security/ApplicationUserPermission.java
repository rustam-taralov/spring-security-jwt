package com.example.demo.security;

public enum ApplicationUserPermission {
	STUDENT_READ("student:read"),
	STUDENT_WRITE("student:write"),
	COURSE_READ("course:read"),
	COURSE_WRITE("course:write");
	
	private final String permisson;

	private ApplicationUserPermission(String permisson) {
		this.permisson = permisson;
	}

	public String getPermission() {
		return permisson;
	}
	
	

}
