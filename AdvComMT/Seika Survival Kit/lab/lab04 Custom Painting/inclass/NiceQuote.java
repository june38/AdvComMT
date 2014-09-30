//Kunnapat Rungruangsatien 5631216321
//Sanyapong Jirathanapin 5631351321

package com.lab04.inClass;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class NiceQuote extends JFrame implements ActionListener{
	JMenuBar menuBar;
	JMenu fileMenu;
	JMenuItem newItem, loadItem, exitItem;
	JFileChooser fc;
	JPanel panel, quotePanel, mainPanel;
	JLabel quoteLabel;
	JTextField quoteField;
	Image image;
	int i = 0;
	public NiceQuote(String title){
		super(title);
		createMenuBar();
		this.add(menuBar, BorderLayout.NORTH);
		createPanel();
		this.add(panel, BorderLayout.CENTER);
	}
	private void createPanel() {
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		createQuotePanel();
		panel.add(quotePanel,BorderLayout.NORTH);
		createFileChoose();	
		createMainPanel();
		panel.add(mainPanel, BorderLayout.CENTER);
		
	}
	private void createQuotePanel() {
		quotePanel = new JPanel();
		quoteLabel = new JLabel("Your Quote");
		quoteLabel.setForeground(Color.white);
		quoteLabel.setFont(new Font("Menlo",Font.PLAIN,12));
		quotePanel.add(quoteLabel);
		createQuoteField();
		quotePanel.add(quoteField);
	}
	private void createQuoteField() {
		quoteField = new JTextField();
		quoteField.setBackground(Color.white);
		quoteField.setForeground(Color.black);
		quoteField.setFont(new Font("Menlo",Font.PLAIN,12));
		quoteField.setColumns(60);
		quoteField.setFocusable(false);
		
		quoteField.getDocument().addDocumentListener( new DocumentListener() {
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
		}
		
	private void createMainPanel() {
		mainPanel = new ImagePanel();
		mainPanel.setBorder(BorderFactory.createLineBorder(new Color(64,64,64)));
		
	}
	private void createFileChoose() {
        fc = new JFileChooser();
	}
	private void createMenuBar() {
		menuBar = new JMenuBar();
		menuBar.setBackground(new Color(96,96,96));
		fileMenu = new JMenu("File");
		newItem = new JMenuItem("New");
		newItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				NiceQuote.this.dispose();
				createAndShowGUI();
			}		
		});
		loadItem = new JMenuItem("Load Background");
		loadItem.addActionListener(this);
		exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(new ActionListener(){
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
		fileMenu.add(newItem);
		fileMenu.add(loadItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		menuBar.add(fileMenu);
    }
	protected static ImageIcon createImageIcon(String path) {
	    java.net.URL imgURL = NiceQuote.class.getResource(path);
	    if (imgURL != null) {
	    	return new ImageIcon(imgURL);
	    } else {
	        System.out.println("Couldn't find file: " + path);
	        return null;
	    }
	}
	public static void createAndShowGUI(){
		NiceQuote frame = new NiceQuote("------Nice Quote V1.0b------");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,500);
        frame.setBackground(new Color(128,128,128,80));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}
	public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
           public void run() {
               createAndShowGUI();
           }
       });
   }
	@Override
	public void actionPerformed(ActionEvent e) {
		 if (e.getSource() == loadItem) {
	            int returnVal = fc.showOpenDialog(NiceQuote.this);
	            if (returnVal == JFileChooser.APPROVE_OPTION) {
	                File file = fc.getSelectedFile();
	            	image = new ImageIcon(file.getPath()).getImage();
	            	try {
						BufferedImage img = ImageIO.read(file);
						this.setSize(img.getWidth()+20,img.getHeight()+100);
						this.setLocationRelativeTo(null);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	            	i = 0;
	            	repaint();
	            	quoteField.setFocusable(true);
	            } else {
	                System.out.println("Invalid File");
	            }
	        }	
	}

	class ImagePanel extends JPanel{
		public void paintComponent(Graphics g){	
			g.drawImage(image,0,0,this);
		    g.setFont((new Font("Menlo",Font.PLAIN,80)));
		    g.drawString(quoteField.getText(), 100, 100);
	    } 
	}

    
  
		
}
