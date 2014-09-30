//5631216321_Kunnapat
package com.lab01.HW;

import java.awt.*;
import java.applet.Applet;
public class AnimalBanner extends Applet {
	static int N = 5;
	static Animal[] animal = new Animal[N];
	static Image[] image = new Image[N];
	
	
		
	public void init(){
		setSize(500,200);
		for(int i = 0; i < animal.length; i++){
			animal[i] = new Animal();
		}
		for(int i = 0; i < animal.length; i++){
			animal[i].setName(getParameter("name"+i));
			animal[i].setX(Integer.parseInt(getParameter("x"+i)));
			animal[i].setHeight(Integer.parseInt(getParameter("height"+i)));
		}
	}
	
	public void saveImage(){
		for(int i = 0; i < image.length; i++){
			image[i] = getImage(getDocumentBase(), animal[i].getName()+".png");
		}
	}

	public void paint(Graphics g){ 
		saveImage();
		for(int i = 0; i < image.length; i++){
			int x = animal[i].getX();
			int h = animal[i].getHeight();
			g.drawImage(image[i], x, 20, 70, h, this);
		}
	}
	
	
	
}

