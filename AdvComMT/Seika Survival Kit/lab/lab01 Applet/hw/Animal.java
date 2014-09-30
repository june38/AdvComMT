package com.lab01.HW;

public class Animal {
	private String name;
	private int x;
	private int height;
	
	public Animal(){
		this.name = "";
		this.x = 0;
		this.height = 0;
	}
	public String getName(){
		return this.name;
	}
	public int getX(){
		return this.x;
	}
	public int getHeight(){
		return this.height;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setX(int x){
		this.x = x;
	}
	public void setHeight(int height){
		this.height = height;
	}
	
	
}
