package problem2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class Slotmachine extends JFrame{
	private static Slotmachine mJFrame;
	private static final int WIDTH = 300;
	private static final int HEIGHT = 100;
	private static JPanel container;
	private static JLabel score;
	private static JButton slot1;
	private static JButton slot2;
	private static JButton slot3;
	private static JButton startBtn;
	private static Thread t1;
	private static Thread t2;
	private static Thread t3;
	public static void main(String[] args){
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createGUI();
                
            }
        });
		
	}
	public Slotmachine(String s){
		super(s);
	}
	
	public static void createGUI(){
		mJFrame = new Slotmachine("DotDraw");
		mJFrame.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		mJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		mJFrame.setBackground(Color.ORANGE);
		BorderLayout borderLayout = new BorderLayout(0,1);
		mJFrame.setLayout(borderLayout);
		createContainer();
		
		
//		createUpperContainer();
//		createLowerContainer();
//		createStatusBar();
		
		mJFrame.add(container,BorderLayout.CENTER);

		mJFrame.pack();
		mJFrame.setVisible(true);
	}
	private static void createContainer() {
		// TODO Auto-generated method stub
		container = new JPanel(new FlowLayout());
		container.setBackground(Color.GREEN);
		container.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		
		score = new JLabel("0");
		slot1 = new JButton("X");
		Dimension d = new Dimension(WIDTH/5,HEIGHT*2/6);
//		slot1.setPreferredSize(d);
		slot2 = new JButton("X");
//		slot2.setPreferredSize(d);
		slot3 = new JButton("X");
//		slot3.setPreferredSize(d);
		startBtn = new JButton("Start");
		
		t1 = new Thread(){
			@Override
			public void run(){
				int i = 0;
				while(true){
					if(slot2.isEnabled()){
						try {
							Thread.sleep((int)((Math.random()+1)*500));
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if(i>9){
							i = 0;
						}
						slot1.setText(""+i);
						i++;
					}
				}
				
			}
		};
		t2 = new Thread(){
			@Override
			public void run(){
				int i = 0;
				while(true){
					if(slot2.isEnabled()){
						try {
							Thread.sleep((int)((Math.random()+1)*500));
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if(i>9){
							i = 0;
						}
						slot2.setText(""+i);
						i++;
					}
					
				}
				
			}
		};
		
		t3 = new Thread(){
			@Override
			public void run(){
				int i = 0;
				while(true){
					if(slot2.isEnabled()){
						try {
							Thread.sleep((int)((Math.random()+1)*500));
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if(i>9){
							i = 0;
						}
						slot3.setText(""+i);
						i++;
					}
					
				}
				
			}
		};
		slot1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(!startBtn.isEnabled())
				slot1.setEnabled(false);
				checkState();
			}
		});
		slot2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(!startBtn.isEnabled())
				slot2.setEnabled(false);
				checkState();
			}
		});
		slot3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(!startBtn.isEnabled())
				slot3.setEnabled(false);
				checkState();
			}
		});
		startBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				startBtn.setEnabled(false);
				slot1.setEnabled(true);
				slot2.setEnabled(true);
				slot3.setEnabled(true);
				if(!t1.isAlive()){
				t1.start();
				t2.start();
				t3.start();
				}
			}
		});
		
		
		
		container.add(score);
		container.add(slot1);
		container.add(slot2);
		container.add(slot3);
		container.add(startBtn);
		
	}
	public static void checkState(){
		if(!slot1.isEnabled() && !slot2.isEnabled() && !slot3.isEnabled()){
			if(slot1.getText().equals(slot2.getText())&&slot2.getText().equals(slot3.getText())){
				try{
					score.setText(Integer.parseInt(score.getText())+Integer.parseInt(slot1.getText())+"");
				}catch(NumberFormatException e){
					e.printStackTrace();
				}
			}
			startBtn.setEnabled(true);
			
		}
	}

}
