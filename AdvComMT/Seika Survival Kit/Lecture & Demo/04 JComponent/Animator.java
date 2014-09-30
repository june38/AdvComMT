package topic4;

import java.awt.*;
public class Animator implements Runnable{
	protected Component comp; // component to be animated
	protected int delay;
	protected Thread animationThread;
	public Animator(Component comp,int delay){
		this.comp = comp;
		setDelay(delay);
	}
	public void setDelay(int delay){
		this.delay = delay;
	}
	public int getDelay(){
		return delay;
	}
	public void startAnimate(){
		animationThread = new Thread(this);
		animationThread.start();
	}
	public void stopAnimate(){
		animationThread = null;
	}
	public void run(){
		while(Thread.currentThread()==animationThread){
			try{
				Thread.sleep(delay);
			}catch(InterruptedException e){}
			comp.repaint();
		}
	}

}
