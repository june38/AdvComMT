package HW3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;


public class HW_03 extends JFrame implements ActionListener{
	
	private JLabel htmlLabel;
	private JPanel leftPanel;
	private JPanel rightPanel;
	private String welcomeText1 = "Hello my name is Neptune :)\n";
	private String welcomeText2 = "Nice to meet u\n";
	private String text="STAR HOME<3";
	JTextArea textAreaRight = new JTextArea();
	JTextArea textAreaLeft = new JTextArea();
	int count=0;
	private String Galaxy="Welcome to our GALAXY!!";
	private String Space="Are u enjoy talking with Apple";
	private ImageIcon image;
	static final int FPS_MIN = 0;
	static final int FPS_MAX = 30;
	static final int FPS_INIT = 15; 
	
	public HW_03(String t){
		super(t);
		this.setLayout(new BorderLayout());
		//this.add(new JSlider(),BorderLayout.NORTH);
		//this.add(textArea,BorderLayout.CENTER);
		createLabel();
	    this.add(htmlLabel,BorderLayout.CENTER);
	        
		createRightPanel();
		this.add(rightPanel,BorderLayout.EAST);
		
		createLeftPanel();
		this.add(leftPanel,BorderLayout.WEST);
		
	}
	
	private void createLabel(){
		htmlLabel = new JLabel();
		htmlLabel.setPreferredSize(new Dimension(100,100));
		htmlLabel.setVerticalAlignment(SwingConstants.CENTER);
		htmlLabel.setHorizontalAlignment(SwingConstants.CENTER);
		htmlLabel.setBorder(BorderFactory.createEtchedBorder());
		htmlLabel.setText(text);
		htmlLabel.setLayout(new GridLayout(5, 0));

		JButton Galaxy = new JButton("Galaxy");
		Galaxy.setActionCommand("Galaxy");
		Galaxy.addActionListener(this);
	    htmlLabel.add(Galaxy);
	    
	    JButton Space = new JButton("Space");
		Galaxy.setActionCommand("Space");
		Galaxy.addActionListener(this);
	    htmlLabel.add(Space);
		
	}
	
	private void createLeftPanel() {
		leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
       
		textAreaLeft = new JTextArea(15,20);
		textAreaLeft.setFont(new Font("SansSerif",Font.PLAIN,18));
		textAreaLeft.setBackground(Color.BLUE);
		textAreaLeft.setForeground(Color.WHITE);
		textAreaLeft.setText(welcomeText1);
		
        JScrollPane scrollPane = new JScrollPane(textAreaLeft);
        leftPanel.add(scrollPane,BorderLayout.WEST); 
        
        JButton okButton = new JButton("OK");
		okButton.setActionCommand("reset");
        okButton.addActionListener(this);
        leftPanel.add(okButton,BorderLayout.SOUTH);
		

    	final JSlider slider = new JSlider(JSlider.HORIZONTAL,FPS_MIN, FPS_MAX, FPS_INIT);
    	leftPanel.add(slider,BorderLayout.NORTH);                         
    	
    	slider.setMajorTickSpacing(10);
    	slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
    	slider.setPaintLabels(true);
    	
	}
	
	private void createRightPanel() {
		rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
       
		textAreaRight = new JTextArea(15,20);
		textAreaRight.setFont(new Font("SansSerif",Font.PLAIN,18));
		textAreaRight.setBackground(Color.BLACK);
		textAreaRight.setForeground(Color.RED);
		
        JScrollPane scrollPane = new JScrollPane(textAreaRight);
        rightPanel.add(scrollPane,BorderLayout.EAST); 
		
        JButton sendButton = new JButton("Send");
		sendButton.setActionCommand("change");
		sendButton.addActionListener(this);
        rightPanel.add(sendButton,BorderLayout.SOUTH);

        image = new ImageIcon(getClass().getResource("Neptune.jpg"));
        JLabel iceLabel = new JLabel(image);
		iceLabel.setPreferredSize(new Dimension(200,200));
		rightPanel.add(iceLabel,BorderLayout.NORTH);
		
	}
	
	public void actionPerformed(ActionEvent e) {
        JButton src = (JButton)e.getSource();
        if(src.getActionCommand().equals("reset")){
        	textAreaRight.setText(textAreaLeft.getText());
        }else if(src.getActionCommand().equals("change")){
        	if(count==0){
        		textAreaLeft.setText(welcomeText2);count++;
        	}else{ 
        		textAreaLeft.setText(textAreaRight.getText());count++;
        	}
        }else if(src.getActionCommand().equals("Galaxy")){
        		textAreaRight.setText(Galaxy);
        	}else if(src.getActionCommand().equals("Space")){
        		textAreaRight.setText(Galaxy);
        	}
    }

    private static void createAndShowGUI() {
        HW_03 frame = new HW_03("Talking with Apple");
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
