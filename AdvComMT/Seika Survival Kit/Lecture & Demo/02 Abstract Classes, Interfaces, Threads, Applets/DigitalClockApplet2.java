package topic2;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class DigitalClockApplet2 extends DigitalClockApplet{
	double currentAngle = 0;
	
	public void paint(Graphics g){
		makeBlackBackground(g);
		rotateCanvas(g);
		drawTimeString(g);	
	}

	private void rotateCanvas(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		currentAngle = (currentAngle+0.02)%(2*Math.PI);
		g2.setColor(currentColor);
		g2.translate(300,300);
		g2.rotate(currentAngle);
		g2.fillOval(-5,-5,10,10);
		g2.drawLine(0, 0, 300, 0);
	}
}
