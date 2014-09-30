
import java.awt.Graphics;
import java.awt.Graphics2D;


import javax.swing.JApplet;


public class /*class name*/ extends JApplet{
	
	
	public void init(){
		setSize(700,500);
		
	}
	
	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		super.paint(g2);
		
	}
}