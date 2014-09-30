package topic2;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import java.applet.Applet;

public class ShapeDrawingApplet extends Applet {
	private Shape [] shapes;
	private Point [] positions;
	private final int PADDING = 10;
	
	public void init(){
		Dimension d = getSize();
		int numShapes = (int)Math.ceil(Math.random()*10+10);
		shapes = new Shape[numShapes];
		for(int i=0;i<numShapes;i++){
			Point location = new Point(
								(int)Math.round((d.width-2*PADDING)*Math.random()+PADDING),
								(int)Math.round((d.height-2*PADDING)*Math.random()+PADDING)
							);
			shapes[i] = Shape.genRandomInstance(i);
			shapes[i].placeAt(location);
		}
	}
	
	public void paint(Graphics g){
		super.paint(g);
		for(Shape s : shapes){
			s.draw(g);
		}
	}
}
