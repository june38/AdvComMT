package PB1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JApplet;

public class Problem1 extends JApplet implements Runnable{
	Thread print;
	Thread time;
	
	int textSize = 30;
	String text1 ="start() called at: ";
	String text2 ="init() called at: ";
	String text3 ="paint() called at: ";
	public void init(){
		this.setSize(300,300);
		print = new Thread(this);
		print.start();
		print.setName("print");
		time = new Thread(this);
		time.setName("time");
		time.start();
		
	}
	protected void makeBlackBackground(Graphics g){
		Dimension dim = this.getSize();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,dim.width,dim.height);
	}
	@Override
	public void run() {
				
	}
	
	public void paint(Graphics g){
		 makeBlackBackground(g);
	}
	 public void drawString(Graphics g){
		 g.setFont(new Font("Dialog",Font.PLAIN,textSize));
		 g.drawString(text1,10,10); 
		 g.drawString(text2,10,10);
		 g.drawString(text3,10,10);
	 }
}
