package practice_problem2;

import java.awt.Color;

public class Dot {
	private int x = 0;
	private int y = 0;
	private int width = 5;
	private int height = 5;
	private Color c;
	public Color getColor() {
		return c;
	}

	public void setColor(Color c) {
		this.c = c;
	}

	public Dot(){
		this(0,0,5,Color.WHITE);
	}
	
	public Dot(int x, int y,int radius,Color c){
		setX(x);
		setY(y);
		setHeight(radius);
		setWidth(radius);
		setColor(c);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
