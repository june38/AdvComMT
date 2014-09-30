package problem1;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Time extends Applet{

	int x = 10;
	int y = 20;
	String initS;
	String startS = "";
	String paintS = "";
	@Override
	public void init(){
		super.init();
		Calendar today = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("HH.mm.ss.SS");
		format.setCalendar(today);
		initS = format.format(today.getTime());

	    
	}
	@Override
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		setBackground(Color.BLACK);
		Calendar today = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("HH.mm.ss.SS");
		format.setCalendar(today);
		paintS = format.format(today.getTime());
		g2.setColor(Color.RED);
	
		g2.drawString("start() called at "+startS, x, y); // start()
		
		g2.setColor(Color.YELLOW);
		g2.drawString("init() called at "+initS, x, y+20); // init()
		
		g2.setColor(Color.GREEN);
		g2.drawString("paint() called at "+paintS, x, y+40); // init()
	}
	
	@Override
	public void start(){
		super.start();
		Calendar today = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("HH.mm.ss.SS");
		format.setCalendar(today);
		startS = format.format(today.getTime());
	}
}
