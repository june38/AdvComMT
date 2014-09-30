// 5631216321 Kunnapat Rungruangsatien
// 5631351321 Sanyapong Jirathanapin
package com.lab02.inClass;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.geom.Line2D;

import javax.swing.JApplet;
import javax.swing.JOptionPane;


public class StarWarsTitle extends JApplet implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Thread repainterText;
	Thread repainterSpaceship;
	String textOne = "A Long time ago";
	String textTwo = "In a galaxy, far far away";
	int delay = 100;
	int x0 = 100;
	int x1 = 40;
	int y0 = 500;
	int y1 = 550;
	int textSize = 60;
	int shipX = 0;
	int shipY = 0;
	int timer = 0;
	int bX1 = 0;
	int bY1 = 0;
	int bX2 = 380;
	int bY2 = 255;
	
	public void init(){
		setSize(700,500);
		repainterText = new Thread(this);
		repainterSpaceship = new Thread(this);
		repainterText.setName("TEXT");
		repainterSpaceship.setName("SPACESHIP");
		repainterText.start();
		repainterSpaceship.start();
	}
	
	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		super.paint(g2);
		callBackground(g2);
		if(textSize > 0){
			System.out.println("DrawText "+textSize);
			drawString(g2);
			textSize--;
		} else if(textSize == 0){
			textSize--;
		}else{
			System.out.println("DrawSpaceship");
			drawSpaceship(g2);
			drawBigSpaceShip(g2);
			bulletOne(g2);
			bulletTwo(g2);
			timer++;
		}
	}
	
	private void callBackground(Graphics2D g2){
		Image i = getImage(getDocumentBase(), "galaxy.jpg");
		g2.drawImage(i, 0, 0, this);
	}

	private void drawBigSpaceShip(Graphics2D g2) {
		g2.setColor(Color.WHITE);
		Polygon poly=new Polygon();
        poly.addPoint(850-shipX,-100+shipY);
        poly.addPoint(970-shipX,-140+shipY);
        poly.addPoint(910-shipX,-200+shipY);
		g2.fillPolygon(poly);
		g2.drawPolygon(poly);
		shipX += 15;
		shipY += 10;
		
	}

	private void drawSpaceship(Graphics2D g2) {
		g2.setColor(Color.WHITE);
		Polygon poly=new Polygon();
		poly.addPoint(700-shipX,0+shipY);
		poly.addPoint(760-shipX,-20+shipY);
	    poly.addPoint(730-shipX,-50+shipY);
	    g2.fillPolygon(poly);
	    g2.drawPolygon(poly);
		shipX += 15;
		shipY += 10;
	}
	protected void bulletOne(Graphics2D g2){
		g2.setColor(Color.WHITE);
		if(shipX>150&&shipX<485){
			Polygon poly=new Polygon();
	        poly.addPoint(700-bX1,0+bY1);
	        poly.addPoint(712-bX1,-4+bY1);
	        poly.addPoint(706-bX1,-10+bY1);
			g2.fillPolygon(poly);
			g2.drawPolygon(poly);
			bX1+=45;
			bY1+=30;
		}
	}
	private void bulletTwo(Graphics2D g2) {
		g2.setColor(Color.WHITE);
		if(shipX>485&&shipX<700){
			Polygon poly=new Polygon();
	        poly.addPoint(700-bX2,0+bY2);
	        poly.addPoint(712-bX2,-4+bY2);
	        poly.addPoint(706-bX2,-10+bY2);
			g2.fillPolygon(poly);
			g2.drawPolygon(poly);
			bX2+=45;
			bY2+=30;
		}
	}

	protected void drawString(Graphics2D g2){
		g2.setColor(Color.WHITE);
		g2.setFont(new Font("Dialog",Font.PLAIN,textSize));
		y0 = y0 - 10;
		x0 = x0 + 3;
		y1 = y1-10;
		x1 = x1 + 3;
		g2.drawString(textOne,x0,y0);
		g2.drawString(textTwo,x1,y1);
	}
	
	public void run() {
		if(Thread.currentThread().getName().equals("SPACESHIP")){
			try {
				repainterText.join();
			} catch (InterruptedException e) {
			}
		}
		
		
		System.out.println("============================");
		System.out.println(Thread.currentThread().getName());
		System.out.println("============================");
		
		
		
		
		
		while(true){
			try {
				Thread.sleep(delay);
				this.repaint();
				if(textSize == 0) break;
				if(timer == 35) {
					JOptionPane.showMessageDialog(null,"The End");
					break;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}
	}	
}