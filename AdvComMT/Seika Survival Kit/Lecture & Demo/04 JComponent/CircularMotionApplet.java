package topic4;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
public class  CircularMotionApplet extends JApplet{
	MyDrawing canvas;
	final int CONTROL_HEIGHT = 50;
	Animator animator;
	boolean stopped = true;
	MyControl control;

	public void init(){
		Dimension dim = getSize();
		setLayout(new BorderLayout());
		canvas = new MyDrawing(10,Color.WHITE);
		animator = new Animator(canvas,50);
		add(canvas,BorderLayout.CENTER);
		
		control = new MyControl();
		add(control,BorderLayout.SOUTH);
	}
	public void start(){
		animator.startAnimate();
		stopped = false;
	}
	public void stop(){
		animator.stopAnimate();
		stopped = true;
	}

	class MyDrawing extends JPanel{
		
		int degree = 0, r, ballRadius,x,y;
		Dimension dim;
		Color ballColor;
		int xC,yC;
		final int MARGIN = 10;
		final int DEGREE_INC = 5;

		public MyDrawing(int ballRadius,Color c){
			super(true); //double-buffering ON
			this.ballRadius = ballRadius;
			ballColor = c;
			setBackground(new Color(100,100,200));
			setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,new Color(100,100,200),Color.LIGHT_GRAY));
		}

		
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			dim = getSize(null);
			xC = dim.width/2;
			yC = dim.height/2;
			r = (int)Math.floor(Math.min(dim.width/2.0,dim.height/2.0))-MARGIN;
			degree += DEGREE_INC;
			x = (int)Math.round(r*Math.sin(Math.PI*degree/180));
			y = (int)Math.round(r*Math.cos(Math.PI*degree/180));
			g.setColor(ballColor);
			g.fillOval(xC+x-ballRadius,yC+y-ballRadius,ballRadius*2,ballRadius*2);
			g.drawLine(xC,yC,xC+x,yC+y);
		}
		
	}

	class MyControl extends JButton{
		public MyControl(){
			setText("STOP");
			addActionListener(new MyButtonHandler());
		}
	}
	class MyButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(stopped){
				animator.startAnimate();
				control.setText("STOP");
				stopped = false;
			}else{
				animator.stopAnimate();
				control.setText("START");
				stopped = true;
			}
		}
	}
}
