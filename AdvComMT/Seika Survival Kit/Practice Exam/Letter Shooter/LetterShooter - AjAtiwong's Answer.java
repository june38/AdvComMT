import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class LetterShooter extends JFrame implements Runnable{
	MyPanel panel = new MyPanel();
	Thread repaintThread;
	public LetterShooter(){
		super("Letter Shooter");
		setLayout(new BorderLayout());
		add(panel,BorderLayout.CENTER);
		repaintThread = new Thread(this);
	}
	
	private static void createGUI(){
		JFrame frame = new LetterShooter();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(500,500));
		frame.pack();
		frame.setVisible(true);
	}
	public static void main(String [] args){
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				createGUI();
			}		
		});
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(repaintThread != null){
			try {
				Thread.sleep(10);
				this.repaint();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

class MyPanel extends JPanel{
	List <FlyingLetter> aliveLetter = new LinkedList<FlyingLetter>(); 
	public MyPanel(){
		this.setLayout(null);
		this.setBackground(Color.BLACK);
		this.setOpaque(true);
		this.setFocusable(true);
		this.addKeyListener(new KeyListener(){

			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {
				FlyingLetter l = new FlyingLetter(""+e.getKeyChar(),250,510);
				aliveLetter.add(l);
				MyPanel.this.add(l);
				MyPanel.this.repaint();
			}
			
		});
	}
}

class FlyingLetter extends JLabel implements Runnable{
	Point topleft;
	final int SPEED = 10;
	Thread updateThread = null;
	public FlyingLetter(String s,int x,int y){
		super(s);
		this.setForeground(Color.WHITE);
		topleft = new Point(x,y);
		this.setBounds(topleft.x,topleft.y,10,10);
		updateThread = new Thread(this);
		updateThread.start();
	}
	public void move(){
		topleft.y -= SPEED;
		this.setBounds(topleft.x,topleft.y,10,10);
	}
	public boolean isOffScreen(){
		return topleft.y <= -10;
	}
	@Override
	public void run() {
		while(updateThread!=null){
			try {
				Thread.sleep(20);
				move();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(isOffScreen()){
				updateThread = null;
			}
		}
	}
}
