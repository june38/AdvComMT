import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class LetterShooter extends JFrame implements Runnable{
	ShotLetter [] letters = new ShotLetter[20];
	DrawingPanel drawing = new DrawingPanel();
	static final int REFRESH_RATE = 10, WIDTH = 800, HEIGHT = 800;
	Thread repaintThread;
	int currentLetterX = 0;
	public LetterShooter(){
		super("Font Shooter");
		setLayout(null);
		drawing.setBounds(0,0,WIDTH,HEIGHT);
		add(drawing);
		drawing.addKeyListener(drawing);
		drawing.setFocusable(true);
		repaintThread = new Thread(this);
		repaintThread.start();
	}
	
	@Override
	public void run() {
		while(true){
			try{
				this.repaint();
				Thread.sleep(REFRESH_RATE);
			}catch(InterruptedException e){}
		}
		
	}
	
	public static void createAndShowGUI(){
		LetterShooter mainFrame = new LetterShooter();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		mainFrame.setResizable(false);
		mainFrame.pack();
        mainFrame.setVisible(true);
	}
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
	}
		
	private boolean isOutOfFrame(ShotLetter l,Graphics g){
		return l.getY()+l.getLetterHeight(g) < 0;
	}
	
	private boolean addLetter(ShotLetter l){
		boolean foundNull = false;
		for(int i=0;i<letters.length;i++){
			if(letters[i]==null){
				foundNull = true;
				letters[i] = l;
				break;
			}
		}
		return foundNull;
	}
	
	class DrawingPanel extends JPanel implements KeyListener{
		final int MARGIN = 40, LETTER_SPACING = 10;
		public DrawingPanel(){
			setBackground(Color.black);
			setOpaque(true);
		}
		@Override
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			for(int i=0;i<letters.length;i++){
				if(letters[i]!=null){
					letters[i].draw(g);
					if(isOutOfFrame(letters[i],g)){
						letters[i].stopMove();
						letters[i] = null;
					}
				}
			}
		}
		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			int randomSpeed = (int)Math.floor(Math.random()*5+5);
			ShotLetter l = new ShotLetter(arg0.getKeyChar(),currentLetterX,this.getSize().height,randomSpeed); 
			addLetter(l);
			l.startMove();
			currentLetterX += LETTER_SPACING;
			currentLetterX = currentLetterX%(this.getSize().width-2*MARGIN)+MARGIN;
			System.out.println("Key : "+arg0.getKeyChar());
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}
	
	class ShotLetter implements Runnable{
		private char c;
		private Color color;
		private int x,y;
		private int speed;
		private final int MOVE_RATE = 10, FONT_SIZE = 50;
		private Font font = new Font(Font.SANS_SERIF, Font.BOLD, FONT_SIZE);
		Thread moveThread;
//		boolean move;
		public ShotLetter(char c,int x, int y,int speed){
			setChar(c);
			setX(x);
			setY(y);
			setSpeed(speed);
			color = new Color(
						(float)(Math.random()*0.5+0.5),
						(float)(Math.random()*0.5+0.5),
						(float)(Math.random()*0.5+0.5)
					);
		}
		private void setSpeed(int speed) {
			this.speed = speed;		
		}
		public void setChar(char c) {
			this.c = c;
		}
		public void setY(int y) {
			this.y = y;
		}
		public int getY() {
			return this.y;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getLetterHeight(Graphics g){
			FontMetrics fm = g.getFontMetrics();
			return fm.getHeight();
		}
		public int getLetterWidth(Graphics g){
			FontMetrics fm = g.getFontMetrics();
			return fm.stringWidth(c+"");
		}
		public void draw(Graphics g){
			g.setFont(font);
			g.setColor(color);
			g.drawString(c+" ",x,y);
		}
		public void startMove(){
			this.x += MOVE_RATE;
			System.out.println(this.x+" ");
			moveThread = new Thread(this);
			moveThread.start();
//			move = true;
		}
		public void move(){
			setY(getY()-speed);
		}
		public void stopMove(){
			moveThread.stop();
			setY(-1000);
//			move = false;
		}
		public void run(){
			while(y> -1000){
				try {
					Thread.sleep(MOVE_RATE);
					move();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}

		}
	}	
	
}
