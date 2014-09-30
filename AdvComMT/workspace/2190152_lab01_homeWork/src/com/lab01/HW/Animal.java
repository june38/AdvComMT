//5631363921 Sutatta ADisornvorakij
package com.lab01.HW;

public class Animal {
	private String name = "";
	private int x;
	private int height;
	
	public Animal(String a,int b,int c){
		this.name=a;
		this.x=b;
		this.height=c;
	}
	
	public void setName(String name){
		this.name=name;
	}
	
	public void setPosition(int x){
		this.x=x;
	}
	
	public void setHeight(int height){
		this.height=height;
	}
	
	public String getName(){
		return name;
	}
	
	public int getPosition(){
		return x;
	}
	
	public int getHeight(){
		return height;
	}
	
}
