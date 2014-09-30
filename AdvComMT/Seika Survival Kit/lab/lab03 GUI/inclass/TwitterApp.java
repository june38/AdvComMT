//5631216321 Kunnapat Rungruangsatien
//5631351321 Sanyapong Jirathanapin

package com.lab03.inClass;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class TwitterApp extends JFrame implements ActionListener, MouseListener{
	TwitterConnector twitter;
	JPanel userPanel, outputPanel;
	JLabel keywordLabel;
	JTextField keywordText;
	JButton searchButton;
	JScrollPane paneArea;
	JTextArea resultText;
	String keyword;
	public TwitterApp(String title){
		super(title);
		createUserPanel();
		this.add(userPanel, BorderLayout.NORTH);
		createOutputPanel();
		this.add(outputPanel, BorderLayout.CENTER);
	}
	
	
	private void createOutputPanel() {
		outputPanel = new JPanel();
		getResultText();
		paneArea = new JScrollPane(resultText);
		paneArea.setPreferredSize(new Dimension(600,300));
		paneArea.setBackground(new Color(225,255,255,60));
		
		outputPanel.add(paneArea, BorderLayout.CENTER);
	}
	private void createUserPanel() {
		userPanel = new JPanel();
		userPanel.setBackground(new Color(192,192,192,160));
		userPanel.setPreferredSize(new Dimension(100,35));
		keywordLabel = new JLabel("Keyword");
		keywordText = new JTextField();
		keywordText.setPreferredSize(new Dimension(500,20));
		keywordText.setBackground(Color.white);
		keywordText.setForeground(Color.black);
		keywordText.setFont(new Font("Menlo",Font.PLAIN,12));
		searchButton = new JButton("Search :)");
		searchButton.setFont(new Font("Menlo",Font.PLAIN,12));
		searchButton.setActionCommand("search");
		searchButton.addActionListener(this);
		
		userPanel.add(keywordLabel, BorderLayout.WEST);
		userPanel.add(keywordText, BorderLayout.CENTER);
		userPanel.add(searchButton, BorderLayout.EAST);
		
	}

	private void getResultText() {
		resultText = new JTextArea();
		resultText.setBackground(new Color(255,255,255));
		resultText.setForeground(Color.black);
		resultText.setFont(new Font("Menlo",Font.PLAIN,12));
		resultText.addMouseListener(this);
	}	
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("search")){
			keyword = keywordText.getText();
		} else if (e.getActionCommand().equals("research")){
			keywordText.setText(keyword);
		}
		String result = "";
		twitter = new TwitterConnector();
		try {
			result = twitter.getResults(keyword);
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		resultText.setText(result);
		
		

	}
	
	private static void createAndShowTwitterApp() {
		TwitterApp twitterFrame = new TwitterApp("---Twitter-V.1b---");
		twitterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        twitterFrame.setSize(700,400);
        twitterFrame.setBackground(new Color(102,178,225,80));
        twitterFrame.setLocationRelativeTo(null);
        twitterFrame.setVisible(true);	
	}
	
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	createAndShowTwitterApp();
            }
        });
	}


	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getButton() == MouseEvent.BUTTON3){
			keyword = resultText.getSelectedText();
			JPopupMenu menu = new JPopupMenu();
			JMenuItem searchChoice = new JMenuItem("Search for " + keyword+".");
			
			menu.add(searchChoice);
			searchChoice.setActionCommand("research");
			searchChoice.addActionListener(this);
			
			
			menu.show(resultText,arg0.getX(),arg0.getY());
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
	
}
