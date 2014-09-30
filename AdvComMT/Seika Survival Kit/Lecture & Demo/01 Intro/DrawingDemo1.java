package topic1;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;

public class DrawingDemo1 extends Applet {
	public void paint(Graphics g) 
	{	g.setColor(Color.MAGENTA);
		g.fillRect(10,10,60,60);
		g.setColor(Color.BLUE);
		g.fillRect(20,20,40,40);
		g.setColor(Color.YELLOW);
		g.fillOval(20,20,40,40);
	}
}
