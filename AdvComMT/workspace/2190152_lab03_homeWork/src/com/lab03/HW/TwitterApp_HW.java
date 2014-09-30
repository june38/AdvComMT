package com.lab03.HW;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;


public class TwitterApp_HW extends JFrame implements ActionListener{
	
	private JPanel topPanel;
	private JPanel centerPanel;
	private JLabel topLabel;
	private JLabel bottomLabel;
	private JMenuItem itembox;
	private JPopupMenu popUp;
	JTextField receiver = new JTextField();
	JTextArea search = new JTextArea();
	JTextArea text = new JTextArea();
	static String status = "";
	boolean clicked = false;
	static int delay = 10;
	static int i = 0;
	Thread statusThread = new Thread(new Runnable(){
	
		@Override
		public void run() {
			try {
				while(true){
					if(clicked){
						clicked = false;
						connectTwitter();
					}
					Thread.sleep(delay);
				}
			} catch (Exception e){
				e.printStackTrace();
			}
			
		}
		
	});
	Thread twitterThread = new Thread(new Runnable(){

		@Override
		public void run() {
			
				try {
					while(true){
						bottomLabel.setText(tc.status);
						Thread.sleep(delay);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
			
		}
		
	});
	TwitterConnector tc = new TwitterConnector();
	
	public TwitterApp_HW(String title){
		super(title);
		this.setLayout(new BorderLayout());
		createTopPanel();
		this.add(topPanel,BorderLayout.NORTH);
		createCenterPanel();
		this.add(centerPanel,BorderLayout.CENTER);
		createBottomLabel();
		this.add(bottomLabel,BorderLayout.SOUTH);
	}
	
	private void createBottomLabel() {
		bottomLabel = new JLabel();
		bottomLabel.setPreferredSize(new Dimension(100,20));
		bottomLabel.setBorder(BorderFactory.createEtchedBorder());
		
	}

	private void createCenterPanel() {
		centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());
		
		text = new JTextArea(15,25);
		text.setFont(new Font("SansSerif",Font.PLAIN,18));
		text.setBackground(Color.WHITE);
		text.setForeground(Color.BLACK);
		centerPanel.add(text);
		text.addMouseListener(new MouseListener(){


			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON3||SwingUtilities.isRightMouseButton(e)){
					 itembox = new JMenuItem("Search for:"+ " "+text.getSelectedText());
					 
					 popUp= new JPopupMenu();
					 popUp.add(itembox);
					 receiver.setText(text.getSelectedText());
					 itembox.addActionListener(new ActionListener (){
						 public void actionPerformed (ActionEvent e){	 
							 try {
								 popUp.setVisible(false);
								 
								 text.setText(tc.getResults(text.getSelectedText()));
								 
						
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
						 }
					 });
					 popUp.setLocation(e.getLocationOnScreen());
					 pack();
					 popUp.setVisible(true);
				 }
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		JScrollPane scrollPane = new JScrollPane(text);
        centerPanel.add(scrollPane,BorderLayout.CENTER); 
	}

	private void createTopPanel(){
		topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
       
		topLabel = new JLabel();
		topLabel.setPreferredSize(new Dimension(100,20));
		topLabel.setVerticalAlignment(SwingConstants.CENTER);
		topLabel.setHorizontalAlignment(SwingConstants.CENTER);
		topLabel.setBorder(BorderFactory.createEtchedBorder());
		topLabel.setText("Keyword");
		topPanel.add(topLabel,BorderLayout.WEST);
			
		search = new JTextArea(15,25);
		search.setFont(new Font("SansSerif",Font.PLAIN,18));
		search.setBackground(Color.WHITE);
		search.setForeground(Color.BLACK);
		search.setText("hello");
		
		receiver = new JTextField();
		receiver.setColumns(40);
		topPanel.add (receiver,BorderLayout.CENTER);    
		
		JButton search = new JButton("Search");
		search.setActionCommand("search");
		search.addActionListener(this);
        topPanel.add(search,BorderLayout.EAST);
	}

	

	public void actionPerformed(ActionEvent e) {
		if(i == 0){
			statusThread.start();
			twitterThread.start();
			i = 1;
		}

		 JButton src = (JButton)e.getSource();
	        if(src.getActionCommand().equals("search")){
	        	 try {
	        		 clicked = true;
				} catch (Exception e1) {
					e1.printStackTrace();
				}
	        }
	}
	
	 private void connectTwitter() throws Exception {
		 text.setText(tc.getResults(receiver.getText()));
		
	}

	private static void createAndShowGUI() {
	        TwitterApp_HW frame = new TwitterApp_HW("Twitter Demo");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(700,500);
	        frame.pack();
	        frame.setVisible(true);
	    }
	 
	 public static void main(String[] args) {
	       SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	            	createAndShowGUI();
	        		System.out.print(status);
	            }
	        });
	    }
}
