package HW2_inClass;

//5631347921 Sorawit Watcharakamthorn
//5631363921 Sutatta Adisornvorakij

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font; 
import java.awt.Graphics; 
import java.awt.Graphics2D; 
import java.awt.Polygon;
import java.awt.geom.Line2D; 
import java.lang.*;

import javax.swing.JApplet; 
import javax.swing.JOptionPane;

public class StarWarsTitle extends JApplet implements Runnable{
	Thread Text; 
	Thread t2;
	String text = "A long time ago,";
	String text2 = " in a galaxy, far, far away ... ";
	int delay =140; 
	int x0 = 50; 
	int y0 = 500; 
	int textSize = 50; 
	int ship1X = 500; int ship1Y = 0;
	int ship2X = 700; int ship2Y =-200;
	int bulletX = ship2X; int bulletY =ship2Y;
	int bx =0; int by =0;
	int temp =0;
	boolean suspended =false;
	
	public void init(){
		setSize(500,500);
		Text = new Thread(this); 
		Text.setName("TEXT");
		Text.start();
		t2 = new Thread(this);
		t2.setName("t2");
		t2.start();
		
	} 
	protected void makeBlackBackground(Graphics g){
		Dimension dim = this.getSize();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,dim.width,dim.height);
	}
	
	public void paint(Graphics g){ 
		super.paint(g); 
		if(textSize >=0 ){
		drawString(g);
		textSize--;
		}
		else{makeBlackBackground(g);
		drawship1(g);
		}
	} 
	void drawship1(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		Polygon p1 = new Polygon();
		p1.addPoint(ship1X, ship1Y);
		p1.addPoint(ship1X+40, ship1Y-80);
		p1.addPoint(ship1X+80, ship1Y-40);
		g2d.setColor(Color.RED);
		g2d.fillPolygon(p1);
		
		
		Polygon p2 = new Polygon();
		p2.addPoint(ship2X, ship2Y);
		p2.addPoint(ship2X+80, ship2Y-160);
		p2.addPoint(ship2X+160, ship2Y-80);
		g2d.setColor(Color.BLUE);
		g2d.fillPolygon(p2);

		
		Polygon pb = new Polygon();
		pb.addPoint(bulletX-bx, bulletY+by);
		pb.addPoint(bulletX+10-bx, bulletY-20+by);
		pb.addPoint(bulletX+20-bx, bulletY-10+by);
		g2d.setColor(Color.YELLOW);
		g2d.fillPolygon(pb);
		ship2X -= 15;
		ship2Y += 15;
		ship1X -= 15;
		ship1Y += 15;
		bulletX -= 15;
		bulletY += 15;
		bx += 10;
		by +=10;
		temp++;
		if(temp>15){
			bx=0;by=0; temp=0;
		}
		
	}
	protected void drawString(Graphics g){
		g.setFont(new Font("Dialog",Font.PLAIN,textSize));
		y0 = y0 - 10; 
		x0 = x0 + 3; 
		g.drawString(text,x0,y0); 
		g.drawString(text2,x0-50,y0+100); 
	
	}
	
	public void run() { 
		if(Thread.currentThread().getName().equals("t2")){
			try{
				Text.join();
			} catch (InterruptedException e){
				
			}
			
		}
		while(true){ 
			try { 
				if(ship2X<=-150){
					JOptionPane.showMessageDialog(null,"The End");
					break;
				}
				
				Thread.sleep(delay); 
				this.repaint(); 
				if(textSize==0) break;
				
			} 
			catch (InterruptedException e) {
				e.printStackTrace(); 
				} 
			
		}
	}
}