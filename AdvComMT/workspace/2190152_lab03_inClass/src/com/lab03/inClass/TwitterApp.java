package com.lab03.inClass;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class TwitterApp extends JFrame implements ActionListener{
		
	private JPanel TextPanel;
	private JPanel CenterPanel;
	JTextArea textArea = new JTextArea();
	JTextArea centerArea = new JTextArea();
	private String keyword = "keyword";
	private String welcomeText1 = "Hello my name is Neptune :)\n";

	public TwitterApp(String title){
		super(title);
		this.setLayout(new BorderLayout());
		
		createTextPanel();
		this.add(TextPanel,BorderLayout.EAST);
		createCenterPanel();
		this.add(CenterPanel,BorderLayout.SOUTH);
	}
	
	
	
	
	private void createTextPanel() {
		TextPanel = new JPanel();
		TextPanel.setLayout(new BorderLayout());
       
		textArea = new JTextArea(15,25);
		textArea.setFont(new Font("SansSerif",Font.PLAIN,18));
		textArea.setBackground(Color.BLUE);
		textArea.setForeground(Color.WHITE);
		textArea.setText(welcomeText1);
		TextPanel.add(textArea);
		
		 JButton okButton = new JButton("Search");
		 okButton.setActionCommand("reset");
	     okButton.addActionListener(this);
	     TextPanel.add(okButton,BorderLayout.EAST);
	     TextPanel.add(okButton);
	}

	private void createCenterPanel() {
		CenterPanel = new JPanel();
		CenterPanel.setLayout(new BorderLayout());
       
		centerArea = new JTextArea(10,15);
		centerArea.setFont(new Font("SansSerif",Font.PLAIN,18));
		centerArea.setBackground(Color.RED);
		centerArea.setForeground(Color.WHITE);
		centerArea.setText(welcomeText1);
		CenterPanel.add(centerArea);
	}



	public void actionPerformed(ActionEvent e) {
        JButton src = (JButton)e.getSource();
        if(src.getActionCommand().equals("reset")){
        	textArea.setText(textArea.getText());
        }
    }

    private static void createAndShowGUI() {
        TwitterApp frame = new TwitterApp("Twitter Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
       SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	createAndShowGUI();
            }
        });
    }
}
