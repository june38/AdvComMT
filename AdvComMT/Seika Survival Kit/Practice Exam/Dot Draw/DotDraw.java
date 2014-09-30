package com.practice;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class DotDraw extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<Ellipse2D> s = new ArrayList<Ellipse2D>(); 
	ArrayList<Color> c = new ArrayList<Color>();
	ListIterator li = s.listIterator();
	JPanel optionPanel, drawingPanel;
	JLabel size;
	JSlider sizeSlider;
	JCheckBox colorCheck;
	Shape shape[];
	boolean black = false, clear = false;
	Color color = Color.white;
	int x = 10, y = 10, brushSize;
	public DotDraw(String title){
		super(title);
		createOptionPanel();
		this.add(optionPanel, BorderLayout.EAST);
		createDrawingPanel();
		this.add(drawingPanel, BorderLayout.WEST);
	}

	private void createDrawingPanel() {
		drawingPanel = new ImagePanel();
		drawingPanel.setPreferredSize(new Dimension(200,400));
		drawingPanel.addMouseMotionListener(new MouseMotionListener(){
			@Override
			public void mouseDragged(MouseEvent e) {
				System.out.println("dragged");
				x = e.getX(); y = e.getY();
				repaint();
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
	}

	private void createOptionPanel() {
		optionPanel = new JPanel();
		optionPanel.setPreferredSize(new Dimension(200,400));
		size = new JLabel("SIZE: 0");
		optionPanel.add(size); 
		sizeSlider = new JSlider(JSlider.HORIZONTAL,0, 100, 0);
		sizeSlider.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent e) {
				size.setText("SIZE: "+ sizeSlider.getValue());
			}
			
			
		});
		optionPanel.add(sizeSlider);
		colorCheck = new JCheckBox("Pen ON/OFF");
		colorCheck.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(!black){
					color = Color.BLACK;
					black = true;
				} else {
					color = Color.white;
					black = false;
				}
			}
			
		});
		optionPanel.add(colorCheck);
		JButton clearButton = new JButton("Clear");
		clearButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				c.clear();
				s.clear();
				clear = true;
				repaint();
			}
			
		});
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
			
		});
		optionPanel.add(clearButton);
		optionPanel.add(exitButton);
	}

	public static void main(String[] args){
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }

			private void createAndShowGUI() {
				DotDraw frame = new DotDraw("----Dot Draw V1.0----");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        frame.setSize(400,400);
		        frame.setVisible(true);
			}
        });
	}
	class ImagePanel extends JPanel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void paint(Graphics g){
			Graphics2D g2 = (Graphics2D) g;
			c.add(color);
			Object[] temp0 = c.toArray();
			brushSize = sizeSlider.getValue();
			Ellipse2D e1 = new Ellipse2D.Double(x-(brushSize/2), y-(brushSize/2), brushSize, brushSize);
			s.add(e1);
			Object[] temp1 =  s.toArray();
			for(int i = 0; i < temp1.length; i++){
				g2.setColor((Color) temp0[i]);
				g2.fill((Shape) temp1[i]);
			}
			if(clear){
				Color c = getBackground();
				g.setColor(c);
				g.fillRect(0,0,200,400); 
				clear = false;
			}
		}
	}
}
