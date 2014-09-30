//5631216321 Kunnapat Rungruangsatien
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
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	final int MAXOBJECTS = 20; // maximum number of objects to be drawn
	final int XLEFTMOST = 0; // left most x-coordinate of the screen
	final int XRIGHTMOST = 700; //right most x-coordinate of the screen
	final int YTOPMOST = 0; // top most y-coordinate of the screen
	final int YBOTTOMMOST = 500; //bottom most y-coordinate of the screen
	final int MAXWIDTH = 100; // maximum width of an object
	final int step = 3; //rate which shape moves
	Random ranDomShapeID = new Random(); // object used for creating random integer
	Shape[] shapes = new Shape[MAXOBJECTS]; //store objects to draw
	int[][] shapeProperties = new int[MAXOBJECTS][4]; //store the properties of each shape {{circle or rectangle, x , y, size},....}
	int[] upOrDowns = new int[MAXOBJECTS]; //store direction of each object
	int counter = 0; // create a counter for the program to know that it is first time that paint or not (if yes show dialogue box)
	//Thread
	Thread repainter;
	int delay = 20;
	public void init(){
		setSize(XRIGHTMOST,YBOTTOMMOST);
		fillArrayWith1or0(upOrDowns);
		repainter = new Thread(this);
		repainter.setName("Repainter Thread");
		repainter.start();
	}
	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		super.paint(g2);
		if(counter == 0){
			for(int i = 0; i < shapes.length; i++){
				if(Math.random()*1<0.5){
					shapes[i] = getRandomSquare(g2,i);
					while(i != 0 && isInVacantSpace(i)){
						shapes[i] = getRandomSquare(g2,i);
					}
				}
				else{
					shapes[i] = getRandomCircle(g2,i);
					while(i != 0 && isInVacantSpace(i)){
						shapes[i] = getRandomCircle(g2,i);
					}
				}
			}
			for(int i = 0; i< shapes.length; i++){
				g2.setColor(Color.blue);
				g2.fill(shapes[i]);
				//System.out.println(shapeProperties[i][0]+"\t"+shapeProperties[i][1]+"\t"+shapeProperties[i][2]);
			}
		} else {
			for(int i = 0; i < shapes.length; i++){
				if(isInMargin(i)){
					if(shapeProperties[i][0] == 0){
						if(upOrDowns[i] == 0){
							shapes[i] = new Rectangle2D.Double(shapeProperties[i][1], shapeProperties[i][2]-=step, shapeProperties[i][3], shapeProperties[i][3]);
						}else{
							shapes[i] = new Rectangle2D.Double(shapeProperties[i][1], shapeProperties[i][2]+=step, shapeProperties[i][3], shapeProperties[i][3]);
						}
					} else {
						if(upOrDowns[i] == 0){
							shapes[i] = new Ellipse2D.Double(shapeProperties[i][1], shapeProperties[i][2]-=step, shapeProperties[i][3], shapeProperties[i][3]);
						} else {
							shapes[i] = new Ellipse2D.Double(shapeProperties[i][1], shapeProperties[i][2]+=step, shapeProperties[i][3], shapeProperties[i][3]);
						}
					}
				}else{
					changeDirection(i);
					if(shapeProperties[i][0] == 0){
						if(upOrDowns[i] == 0){
							shapes[i] = new Rectangle2D.Double(shapeProperties[i][1], shapeProperties[i][2]-=step, shapeProperties[i][3], shapeProperties[i][3]);
						}else{
							shapes[i] = new Rectangle2D.Double(shapeProperties[i][1], shapeProperties[i][2]+=step, shapeProperties[i][3], shapeProperties[i][3]);
						}
					} else {
						if(upOrDowns[i] == 0){
							shapes[i] = new Ellipse2D.Double(shapeProperties[i][1], shapeProperties[i][2]-=step, shapeProperties[i][3], shapeProperties[i][3]);
						} else {
							shapes[i] = new Ellipse2D.Double(shapeProperties[i][1], shapeProperties[i][2]+=step, shapeProperties[i][3], shapeProperties[i][3]);
						}
					}
				}
			}
			for(int i = 0; i< shapes.length; i++){
				g2.setColor(Color.blue);
				g2.fill(shapes[i]);
				//System.out.println(shapeProperties[i][0]+"\t"+shapeProperties[i][1]+"\t"+shapeProperties[i][2]);
			}
		}
	}
	private void changeDirection(int i) {
		if(upOrDowns[i] == 0) upOrDowns[i] = 1;
		else upOrDowns[i] = 0;
	}
	private boolean isInMargin(int i) {
		Rectangle2D temp = new Rectangle2D.Double(shapeProperties[i][1], shapeProperties[i][2], shapeProperties[i][3],shapeProperties[i][3]);
		Rectangle2D canvas = new Rectangle2D.Double(XLEFTMOST,YTOPMOST,XRIGHTMOST,YBOTTOMMOST);
		return (canvas.contains(temp));
	}
	private boolean isInVacantSpace(int i) {
		boolean temp = false;
		for(int j = 0; j < i; j++){
			temp = temp || shapes[i].getBounds2D().intersects(shapes[j].getBounds2D());
		}
		return temp;
	}

	private Shape getRandomSquare(Graphics2D g2, int i) {
		int squareProperty[] = getShapeProperty(); // method to return the array of {x coordinate, y coordinate, side length}
		Rectangle2D square = new Rectangle2D.Double(squareProperty[0], squareProperty[1], squareProperty[2], squareProperty[2]);
		shapeProperties[i][0] = 0;
		shapeProperties[i][1] = squareProperty[0];
		shapeProperties[i][2] = squareProperty[1];
		shapeProperties[i][3] = squareProperty[2];
		return square;
	}
	private Shape getRandomCircle(Graphics2D g2, int i) {
		int circleProperty[] = getShapeProperty(); // method to return the array of {x coordinate, y coordinate, side length}
		Ellipse2D circle = new Ellipse2D.Double(circleProperty[0], circleProperty[1], circleProperty[2], circleProperty[2]);
		shapeProperties[i][0] = 1;
		shapeProperties[i][1] = circleProperty[0];
		shapeProperties[i][2] = circleProperty[1];
		shapeProperties[i][3] = circleProperty[2];
		return circle;
	}
	private int[] getShapeProperty() {
		int tempX = randomCoordinate(XRIGHTMOST);
		int tempY = randomCoordinate(YBOTTOMMOST);
		int sideLength = randomSideLength();
		while((XRIGHTMOST - tempX-5) < (sideLength) || (YBOTTOMMOST - tempY-5) < (sideLength)){
			tempX = randomCoordinate(XRIGHTMOST);
			tempY = randomCoordinate(YBOTTOMMOST);
			sideLength = randomSideLength();
		}
		int[] temp = {tempX, tempY, sideLength};
		return temp;
	}
	private int randomSideLength() {
		return ranDomShapeID.nextInt(MAXWIDTH);
	}
	private int randomCoordinate(int range) {
		return ranDomShapeID.nextInt(range);
	}
	private void fillArrayWith1or0(int n[]) {
		for(int i = 0; i < n.length; i++){
			if(Math.random()*1<0.5) n[i] = 0;
			else n[i] = 1;
		}
	}
	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(delay);
				if(counter == 0){
					JOptionPane.showMessageDialog(null,"Press <OK> to start");
					counter++;
				} else {
					this.repaint();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}