package practice_problem3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class LetterShooter extends JFrame{
	List <FlyingLetter> aliveLetter = new LinkedList<FlyingLetter>();
	MyPanel panel = new MyPanel();
	
	public LetterShooter(){
		super("Letter Shooter");
		setLayout(new BorderLayout());
		add(panel,BorderLayout.CENTER);
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
			
			public void run(){
				createGUI();
			}
		});
	}
}
class MyPanel extends JPanel{
	public MyPanel(){
		this.setLayout(null);
		this.setBackground(Color.black);
		this.setOpaque(true);
		this.setFocusable(true);
		this.addKeyListener(new KeyListener(){

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				System.out.println(e.getKeyChar());
				FlyingLetter a = new FlyingLetter(e.getKeyChar()+"",250,250);
				//aliveLetter.add();
				MyPanel.this.add(a);
				MyPanel.this.validate();
			}
		});
	}
}


class FlyingLetter extends JLabel implements Runnable{
	Point topleft;
	final int speed = 5;
	Thread updateThread = null;
	public FlyingLetter(String s,int x,int y){
		super(s);
		this.setForeground(Color.WHITE);
		this.setFont(new Font("SansSerif",Font.PLAIN,18));
		this.setText(s);
		
		topleft = new Point(x,y);
		this.setBounds(topleft.x,topleft.y,10,10);
		updateThread = new Thread(this);
		updateThread.start();
	}
	public void move(){
		topleft.y += speed;
	}
	public boolean isOffScreen(){
		return false;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(updateThread!=null){
			try {
			Thread.sleep(200);
			move();
			}
			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(isOffScreen()){
				updateThread=null;
			}
		}
	}
}