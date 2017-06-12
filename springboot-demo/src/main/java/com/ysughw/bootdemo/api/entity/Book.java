package com.ysughw.bootdemo.api.entity;

import java.io.Serializable;

public class Book implements Serializable{
	
	private static final long serialVersionUID = 3918088604103084835L;
	
	public String id ;
	public String name ;
	public String grade ;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
}
