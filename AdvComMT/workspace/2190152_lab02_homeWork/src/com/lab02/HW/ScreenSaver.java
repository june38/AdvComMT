package com.lab02.HW;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import javax.swing.JApplet;
import javax.swing.JOptionPane;

public class ScreenSaver extends JApplet implements Runnable{
	final int MAXOBJECTS = 20; // maximum number of objects to be drawn
	final int XLEFTMOST = 0; // left most x-coordinate of the screen
	final int XRIGHTMOST = 700; //right most x-coordinate of the screen
	final int YTOPMOST = 0; // top most y-coordinate of the screen
	final int YBOTTOMMOST = 500; //bottom most y-coordinate of the screen
	final int MAXWIDTH = 100; // maximum width of an object
	Shape[] shapes = new Shape[MAXOBJECTS]; //store objects to draw
	int[] upOrDowns = new int[MAXOBJECTS]; //store direction of each object
	Random ranDomShapeID = new Random(); // object used for creating random integer

	Thread repainter;
	int count =0 ;
	int delay= 100;
	int[][] move = new int[MAXOBJECTS][4];
	
	public void init(){
		setSize(700,500);
		repainter = new Thread(this);
		fill(upOrDowns);
		repainter.start();
	}
	
	private int[] fill(int[] a) {
		for(int i=0;i<a.length;i++){
			a[i]=ranDomShapeID.nextInt(2);
		}
		return null;
	}

	public  void paint(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		super.paint(g2);
		if(count==0){
			for(int i=0;i<shapes.length;i++){
				if(ranDomShapeID.nextInt(2)==1){
					shapes[i]=getRec(g2,i);
					while((i!=0)&&checkShape(g2,i)){
						shapes[i]=getRec(g2,i);
					}
				}else {
					shapes[i]=getCircle(g2,i);
					while((i!=0)&&checkShape(g2,i)){
						shapes[i]=getCircle(g2,i);
					}
				}
			}
			for(int i=0;i<shapes.length;i++){
				g2.setColor(Color.green);
				g2.fill(shapes[i]);
			}
			count=1;
		}else{
			for(int i=0;i<shapes.length;i++){
				if(hitTheWall(i)){
					if(move[i][3]==1){
						if(upOrDowns[i]==0){
							shapes[i]= new Rectangle2D.Double(move[i][0],move[i][1]-=5,move[i][2],move[i][2]);
						}else{
							shapes[i]= new Rectangle2D.Double(move[i][0],move[i][1]+=5,move[i][2],move[i][2]);
						}
					}else{
						if(upOrDowns[i]==0){
							shapes[i]= new Ellipse2D.Double(move[i][0],move[i][1]-=5,move[i][2],move[i][2]);
						}else{
							shapes[i]= new Ellipse2D.Double(move[i][0],move[i][1]+=5,move[i][2],move[i][2]);
						}
					}
				}else{
					changeDirec(i);
					if(move[i][3]==1){
						if(upOrDowns[i]==0){
							shapes[i]= new Rectangle2D.Double(move[i][0],move[i][1]-=5,move[i][2],move[i][2]);
						}else{
							shapes[i]= new Rectangle2D.Double(move[i][0],move[i][1]+=5,move[i][2],move[i][2]);
						}
					}else{
						if(upOrDowns[i]==0){
							shapes[i]= new Ellipse2D.Double(move[i][0],move[i][1]-=5,move[i][2],move[i][2]);
						}else{
							shapes[i]= new Ellipse2D.Double(move[i][0],move[i][1]+=5,move[i][2],move[i][2]);
						}
					}
				}
		
			}
			for(int i=0;i<shapes.length;i++){
				g2.setColor(Color.green);
				g2.fill(shapes[i]);
			}
		}
		
		
	}
	
	private boolean hitTheWall(int i) {
		Rectangle2D temp = new Rectangle2D.Double(move[i][0],move[i][1],move[i][2],move[i][2]);
		Rectangle2D big = new Rectangle2D.Double(XLEFTMOST,YTOPMOST,XRIGHTMOST,YBOTTOMMOST);
		return (big.contains(temp));
	}

	private void changeDirec(int i) {
		if(upOrDowns[i]==0){
			upOrDowns[i]=1;
		}else{
			upOrDowns[i]=0;
		}
		
	}

	private boolean checkShape(Graphics2D g2, int i) {
			boolean temp = false;
			for(int j=0;j<i;j++){
				temp=temp||shapes[i].getBounds2D().intersects(shapes[j].getBounds2D());
			}
		return temp;
	}

	private Shape getCircle(Graphics2D g2, int i) {
		int temp[]=getShape();
		int x=temp[0]; int y=temp[1] ; int side = temp[2];
		Ellipse2D cir2d = new Ellipse2D.Double(x,y,side,side);
		move[i][0]=x; move[i][1]=y; move[i][2]=side;move[i][3]=0;
		return cir2d;
	}

	private int[] getShape() {
		int x = getCoor(XRIGHTMOST); 
		int y = getCoor(YBOTTOMMOST); 
		int side = getSide(MAXWIDTH);
		while((XRIGHTMOST-x<side)||(YBOTTOMMOST-y<side)){
			x = getCoor(XRIGHTMOST); 
			y = getCoor(YBOTTOMMOST); 
			side = getSide(MAXWIDTH);
		}
		int a[] = {x,y,side};
		return a;
	}

	private int getSide(int a) {
		return ranDomShapeID.nextInt(a);
	}

	private int getCoor(int a) {
		return ranDomShapeID.nextInt(a);
	}

	private Shape getRec(Graphics2D g2,int i) {
		int temp[]=getShape();
		int x=temp[0]; int y=temp[1] ; int side = temp[2];
		Rectangle2D rec2d = new Rectangle2D.Double(x,y,side,side);
		move[i][0]=x; move[i][1]=y; move[i][2]=side;move[i][3]=1;
		return rec2d;
	}

	public void run() {
		while(true){ 
			try {
				if(count==0){
					JOptionPane.showMessageDialog(null,"READY TO MOVE:)");
					count++;
				}else{
					Thread.sleep(delay); 
					this.repaint(); 
				}
			} 
			catch (InterruptedException e) {
				e.printStackTrace(); 
				} 
		}
	}
	
}