package com.Topic04.HW;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class PhotoHunt extends JFrame implements ItemListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextArea textArea;
	JPanel imagePanel, buttonPanel;
	JMenuBar menuBar;
	JButton firstButton, secondButton;
	JMenu firstMenu, secondMenu;
	protected Action firstAction;
	static JTextField solutionField;
	public PhotoHunt(String title){
		super(title);
		createMenuBar();
		this.add(menuBar,BorderLayout.NORTH);
		imagePanel = new ImagePanel();
		imagePanel.setOpaque(true);
		this.add(imagePanel);
		createButtonPanel();
		this.add(buttonPanel, BorderLayout.SOUTH);
		
			
	}
	
	public class ShowAction extends AbstractAction{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public ShowAction() {
			super("Show Solution");
			putValue(AbstractAction.ACCELERATOR_KEY,KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
			putValue(AbstractAction.SHORT_DESCRIPTION,"Show a Solution");
		}		
		public void actionPerformed(ActionEvent e) {
          solutionField.setText("There are 5 Differences"); 
        }
	}
	public class HideAction extends AbstractAction{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public HideAction() {
			super("Hide Solution");
			putValue(AbstractAction.ACCELERATOR_KEY,KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
			putValue(AbstractAction.SHORT_DESCRIPTION,"Hide a Solution");
		}		
		public void actionPerformed(ActionEvent e) {
          solutionField.setText(""); 
        }
	}
	private void createButtonPanel() {
		buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(0,0,0,200));
		buttonPanel.setPreferredSize(new Dimension(200,70));
		firstButton = new JButton(new ShowAction());
		secondButton = new JButton(new HideAction());
		solutionField = new JTextField();
		solutionField.setPreferredSize(new Dimension(500,20));
		solutionField.setBackground(Color.white);
		solutionField.setForeground(Color.black);
		solutionField.setFont(new Font("Menlo",Font.PLAIN,12));
		buttonPanel.add(firstButton);
		buttonPanel.add(secondButton);
		buttonPanel.add(solutionField, BorderLayout.SOUTH);
		
	}
	private void createMenuBar() {
		menuBar = new JMenuBar();
		menuBar.setBackground(new Color(0,0,0,30));
		firstMenu = new JMenu("Menu");
		firstMenu.setBackground(new Color(0,0,0,100));
		secondMenu = new JMenu("Choices");
		secondMenu.setBackground(new Color(0,0,0,100));
		
		JMenuItem i1 = new JMenuItem("Picture 1");
		JMenuItem i2 = new JMenuItem("Picture 2");
		JMenuItem i3 = new JMenuItem(new ShowAction());
		JMenuItem i4 = new JMenuItem(new HideAction());
		firstMenu.add(i1);
		firstMenu.add(i2);
		secondMenu.add(i3);
		secondMenu.add(i4);
		menuBar.add(firstMenu);
		menuBar.add(secondMenu);
	}
	private static void createAndShowGUI() {
        PhotoHunt frame = new PhotoHunt("------KNP Photo Hunt V2.0b------");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700,400);
        frame.setBackground(new Color(86,90,117,120));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub	
	}
	public static void main(String[] args) {
         javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }	
	class ImagePanel extends JPanel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private int side = 50;
		boolean firstTime = true;
	    private int X ;
	    private int Y ;
	    public ImagePanel() {
	    	 addMouseListener(new MouseAdapter() {
	             public void mousePressed(MouseEvent e) {
	                 drawCircle(e.getX(),e.getY());
	                 firstTime = false;
	             }
	         }); 
	    	
	    }
	    private void drawCircle(int x, int y) {
	        X=x-20;
	        Y=y-20;
	        repaint(x-20,y-20,side,side);
	    }
	    protected void paintComponent(Graphics g) {
	    	if(!firstTime){
	    		Graphics2D g2 = (Graphics2D) g;
		        g2.setColor(Color.RED);
		        Ellipse2D temp = new Ellipse2D.Double(X,Y,side,side);
		        g2.draw(temp);
	    	}
	    }
	    
	}



}

