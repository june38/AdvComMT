package practice_problem2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class PaintPanel extends JPanel{
	
	public static ArrayList<Dot> dots = new ArrayList<Dot>();
	int x = 0;
	int y = 0;
	int width = 5;
	int height = 5;

	public PaintPanel(){
		super();
		setOpaque(true);

	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
//		System.out.println("Paint");
//		setBackground(Color.WHITE);
		Graphics2D g2 = (Graphics2D) g;
		for(Dot dot: dots){
			g2.setColor(dot.getColor());
			g2.fillOval(dot.getX()-dot.getHeight()/2,dot.getY()-dot.getWidth()/2,dot.getWidth(),dot.getHeight());
//			g2.drawOval(dot.getX(),dot.getY(),dot.getWidth(),dot.getHeight());
		}
	}
	
	public void clearScreen(){
		dots = new ArrayList<Dot>();
	}
	public void addDot(Dot dot){
		dots.add(dot);
	}
}
