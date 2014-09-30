package topic4;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import topic4.Ship.MoveAction;

public class StarField extends JPanel {
	Star [] stars;
	Animator animator = new Animator(this,100);
	StarMover starMover = new StarMover();
	Ship ship;
	public StarField(){
		setBackground(Color.BLACK);
		setOpaque(true);
		initStars(200);
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F"),"freezeTime");
		this.getActionMap().put("freezeTime", new FreezeAction());
		startAnimate();
	}
	private void initStars(int n){
		stars = new Star[n];
		for(int i=0;i<stars.length;i++){
			stars[i] = new Star();
		}
	}
	public void addShip(Ship ship){
		this.ship = ship;
		add(ship);
	}
	
	public void startAnimate(){
		animator.startAnimate();
	}
	public void stopAnimate(){
		animator.stopAnimate();
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for(int i=0;i<stars.length;i++){
			stars[i].draw(g);
		}
		ship.draw(getSize());
	}
	class Star{
		private double x;
		private double y;
		private double speedY;
		private int STAR_DIAMETER = 4;
		public Star(){
			reset();
			y = Math.random();
		}
		public void reset(){
			y = 0;
			x = Math.random();
			speedY = 0.01+Math.random()*0.05;
		}
		public void move(){
			y += speedY;
			if(y>1){
				reset();
			}
		}
		public void draw(Graphics g){
			Dimension d = getSize();
			g.setColor(Color.WHITE);
			g.fillOval((int)(x*d.width),(int)(y*d.height),STAR_DIAMETER,STAR_DIAMETER);
		}
	}
	class StarMover implements Runnable{
		Thread moverThread;
		public StarMover(){
			resume();
		}
		public void pause(){
			moverThread = null;
		}
		public void resume(){
			moverThread = new Thread(this);
			moverThread.start();
		}
		public void run() {
			while(Thread.currentThread()==moverThread){
				try{
					Thread.sleep(100);
				}catch(InterruptedException e){}
				for(int i=0;i<stars.length;i++){
					stars[i].move();
				}
			}		
		}	
	}
	class FreezeAction extends AbstractAction{
		boolean isMoved = true;
		@Override
		public void actionPerformed(ActionEvent e) {
			if(isMoved){
				starMover.pause();
				isMoved = false;
			}else{
				starMover.resume();
				isMoved = true;
			}
		}
	}
}
