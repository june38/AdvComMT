package topic2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public abstract class Shape {
	private int id;
	protected Color faceColor = Color.WHITE;
	protected Color edgeColor = Color.BLACK;
	protected Point topLeft = new Point(0,0);
	public Shape(int id, Color faceColor, Color edgeColor){
		this.id = id;
		this.faceColor = faceColor;
		this.edgeColor = edgeColor;
	}
	public int getId(){
		return id;
	}
	abstract public void draw(Graphics g);
	abstract public double getArea();
	
	public void placeAt(Point p){
		topLeft.x = p.x;
		topLeft.y = p.y;
	}
	
	public static Shape genRandomInstance(int id){
		if(Math.random()>0.5){
			return Square.genRandomInstance(id);
		}else{
			return Circle.genRandomInstance(id);
		}
	}
	public static Color genRandomColor(){
		return new Color((float)Math.random(),(float)Math.random(),(float)Math.random());
	}
}
