package com.lab04.homework;

//5631363921 Sutatta Adisornvorakij

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class NiceQuote extends JFrame implements ActionListener, MouseMotionListener{
	
	private static final long serialVersionUID = 1L;
	JPanel bigPanel,userPanel, outputPanel, picturePanel;
	JLabel keywordLabel;
	JTextField keywordText;
	JButton inButton,deButton;
	JScrollPane paneArea;
	JTextArea resultText;
	String keyword;
	String temp;
	JMenuBar menuBar;
	JMenu firstMenu, secondMenu;
	JFileChooser fileChooser;
	Image image;
	private static NiceQuote Frame;
	private JMenuItem i1,i2,i3;
	int x=100;
	int y=100;
	int fontSize = 80;
	
	public NiceQuote(String title){
		super(title);
		fileChooser = new JFileChooser();
		createMenuBar();
		this.add(menuBar,BorderLayout.NORTH);
		createBigPanel();
		this.add(bigPanel, BorderLayout.CENTER);
	}
	

	private void createBigPanel() {
		bigPanel = new JPanel();
		bigPanel.setLayout(new BorderLayout());
		bigPanel.setBackground(Color.pink);
		createUserPanel();
		bigPanel.add(userPanel, BorderLayout.NORTH);
		createOutputPanel();
		bigPanel.add(picturePanel, BorderLayout.CENTER);
	}
	
	private void createOutputPanel() {
		picturePanel = new ImagePanel();
		picturePanel.addMouseMotionListener(this);
		picturePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
	private void createUserPanel() {
		userPanel = new JPanel();
		userPanel.setBackground(Color.yellow);
		keywordLabel = new JLabel("Your Quote");
		keywordText = new JTextField();
		keywordText.setFocusable(false);
		keywordText.setBackground(Color.white);
		keywordText.setForeground(Color.black);
		keywordText.setColumns(40);
		keywordText.getDocument().addDocumentListener( new DocumentListener() {
			public void changedUpdate(DocumentEvent e){
				repaint();
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				repaint();
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				
				repaint();
			}
		}); 
		
		keywordText.setFont(new Font("Menlo",Font.PLAIN,12));
		
		inButton = new JButton("Increase font size");
		inButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				fontSize+=5;
				y+=5;
				repaint();
			}
		});
		deButton = new JButton("Decrease font size");
		deButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				fontSize-=5;
				y-=5;
				repaint();
			}
		});
		
		userPanel.add(keywordLabel, BorderLayout.WEST);
		userPanel.add(keywordText, BorderLayout.CENTER);
		userPanel.add(inButton, BorderLayout.WEST);
		userPanel.add(deButton, BorderLayout.WEST);
		
	}
	
	private void createMenuBar() {
		menuBar = new JMenuBar();
		menuBar.setBackground(new Color(0,0,0,30));
		firstMenu = new JMenu("Menu");
		firstMenu.setBackground(new Color(0,0,0,100));
		
		i1 = new JMenuItem("New");
		i1.addActionListener(this);
		
		i1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				NiceQuote.this.dispose();
				Frame = new NiceQuote("NiceQuote");
				Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        Frame.setSize(700,400);
		        Frame.setBackground(new Color(102,178,225));
		        Frame.setLocationRelativeTo(null);
		        Frame.setVisible(true);	
			}		
		});
		i2 = new JMenuItem("Load Background");
		i2.addActionListener(this);
		i3 = new JMenuItem("Exit");
		
		i3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int choice = JOptionPane.showConfirmDialog(
						NiceQuote.this,
						"Are you sure?",
						"Please confirm",
						JOptionPane.YES_NO_OPTION);
				if(choice==JOptionPane.YES_OPTION){
					System.exit(0);
				}
			}		
		});
		
		firstMenu.add(i1);
		firstMenu.add(i2);
		firstMenu.addSeparator();
		firstMenu.add(i3);
		menuBar.add(firstMenu);
	}
	
	 public void actionPerformed(ActionEvent e){
		 if(e.getSource()==i2){
			 int returnVal = fileChooser.showOpenDialog(this);
			 if (returnVal == JFileChooser.APPROVE_OPTION) {
				 File file = fileChooser.getSelectedFile();
				 image = new ImageIcon(file.getPath()).getImage();
				 try {
					BufferedImage img = ImageIO.read(file);
						this.setSize(img.getWidth()+25, img.getHeight()+100);
						this.setLocationRelativeTo(null);
						keywordText.setFocusable(true);
					} catch (IOException e1) {
					e1.printStackTrace();
				}
				 repaint();
			 }
		 }  
   }

	
	private static void createAndShowGUI() {
		Frame = new NiceQuote("NiceQuote");
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      Frame.setSize(700,400);
      Frame.setBackground(new Color(102,178,225));
      Frame.setLocationRelativeTo(null);
      Frame.setVisible(true);	
      
	}
	
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable() {
          public void run() {
          	createAndShowGUI();
          }
      });
	}
	class ImagePanel extends JPanel{
		public void paintComponent(Graphics g){
			g.drawImage(image, 0, 0, this);
			g.setFont((new Font("Menlo",Font.PLAIN,fontSize)));
	       	g.drawString(keywordText.getText(), x, y);
		}
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		x=e.getX();
		y=e.getY();
		repaint();
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}

