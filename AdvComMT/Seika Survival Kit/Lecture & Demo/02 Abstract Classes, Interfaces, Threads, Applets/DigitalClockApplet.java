package topic2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Calendar;
import java.applet.Applet;

public class DigitalClockApplet extends Applet implements Runnable{
	Thread repainter;
	int delay;
	Color currentColor = new Color(128,255,255);

	public void init(){
		repainter = new Thread(this);
		delay = 100; // 10 fps;
		repainter.start();
	}
	public void paint(Graphics g){
		makeBlackBackground(g);
		drawTimeString(g);
	}

	protected void makeBlackBackground(Graphics g){
		Dimension dim = this.getSize();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,dim.width,dim.height);
	}
	
	protected void drawTimeString(Graphics g){
		int h = Calendar.getInstance().get(Calendar.HOUR);
		int m = Calendar.getInstance().get(Calendar.MINUTE);
		int s = Calendar.getInstance().get(Calendar.SECOND);
		String strH = h<10?("0"+h):(""+h);
		String strM = m<10?("0"+m):(""+m);
		String strS = s<10?("0"+s):(""+s);
		String timeString = strH+":"+strM+":"+strS;
		g.setColor(currentColor);
		g.setFont(new Font("Dialog",Font.PLAIN,80));
		int strHeight = g.getFontMetrics().getHeight();
		g.drawString(timeString,0,strHeight/2+10);
	}
	
	@Override
	public void run() {
		while(Thread.currentThread()!=null){
			try {
				Thread.sleep(delay);
				this.repaint();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}
	}
}
