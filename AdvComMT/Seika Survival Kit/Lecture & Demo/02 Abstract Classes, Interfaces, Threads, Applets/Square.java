package topic2;

import java.awt.Color;
import java.awt.Graphics;

public class Square extends Shape {
	private int w;
	public Square(int id,Color faceColor, Color edgeColor, int w){
		super(id,faceColor,edgeColor);
		this.w = w;
	}
	public static Square genRandomInstance(int id){
		int w = (int)Math.round(Math.random()*40)+10;
		return new Square(id,Shape.genRandomColor(),Shape.genRandomColor(),w);
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(faceColor);
		g.fillRect(topLeft.x,topLeft.y,w,w);
		g.setColor(edgeColor);
		g.drawRect(topLeft.x,topLeft.y,w,w);
	}

	@Override
	public double getArea() {
		return w*w;
	}

}
