//5631363921 Sutatta ADisornvorakij
package com.lab01.HW;
import java.awt.*;
import java.awt.image.*;
import java.applet.Applet;

public class AnimalBanner extends Applet {
	int N = 6;
	Image v[]= new Image[N];
	Animal a[] = new Animal[N];
	
	public void init(){
		this.setSize(600,600);
		for(int i = 0; i < a.length; i++){
			a[i] = new Animal("",0,0);
		}
		for(int i=0;i<a.length;i++){
			a[i].setName(getParameter("name"+i));
			a[i].setPosition(Integer.parseInt(getParameter("x"+i)));
			a[i].setHeight(Integer.parseInt(getParameter("height"+i)));
		}
	}
	
	public void picture(){
		for(int i=0; i<v.length;i++){
			v[i]=getImage(getDocumentBase(),a[i].getName()+".png");
		}
	}
	
	public void paint(Graphics g){
		picture();
		for(int i=0; i<v.length;i++){
			g.drawImage(v[i],a[i].getPosition(),20,70,a[i].getHeight(),this);
		}
	}
}
		
	
