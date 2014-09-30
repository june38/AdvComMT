package topic1;

import java.applet.Applet;
import java.awt.Graphics;

public class AppletDemo extends Applet {
	private int n;
	public void init(){
		
	}
	public void paint(Graphics g){
		n = (int)Math.round((Math.random()*10));
		g.drawOval(0, 0, 50, 50);
		g.drawString(n+"",10,20);
	}
}
