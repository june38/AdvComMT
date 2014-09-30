package topic2;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends Shape {
	private int d = 1;
	public Circle(int id,Color faceColor, Color edgeColor, int d){
		super(id,faceColor,edgeColor);
		this.d = d;
	}
	public static Circle genRandomInstance(int id){
		int d = (int)Math.round(Math.random()*40)+10;
		return new Circle(id,Shape.genRandomColor(),Shape.genRandomColor(),d);
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(faceColor);
		g.fillOval(topLeft.x,topLeft.y,d,d);
		g.setColor(edgeColor);
		g.drawOval(topLeft.x,topLeft.y,d,d);
	}

	@Override
	public double getArea() {
		return Math.PI*(d/2)*(d/2);
	}

}
