package topic3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
 
public class HtmlLabelDemo extends JFrame implements ActionListener {
	private JPanel leftPanel;
	private JLabel htmlLabel;
	private JButton changeButton;
	private JTextArea htmlTextArea;
	private String initialHtml = "<html>\n"+
								 "  <h1>HELLO</h1>\n"+
								 "  <p>HTML code goes here</p>\n"+
								 "</html>\n";
	
	public HtmlLabelDemo(String title){
		super(title);
		this.setLayout(new BorderLayout());
		createLeftPanel();
        this.add(leftPanel,BorderLayout.WEST);
        createLabel();
        this.add(htmlLabel,BorderLayout.EAST);
        createChangeButton();
        this.add(changeButton,BorderLayout.CENTER);
	}
	
	private void createChangeButton(){
		changeButton = new JButton(">>");
		changeButton.setActionCommand("change");
		changeButton.addActionListener(this);
	}
	
	private void createLabel(){
		htmlLabel = new JLabel();
		htmlLabel.setPreferredSize(new Dimension(200,400));
		htmlLabel.setVerticalAlignment(SwingConstants.CENTER);
		htmlLabel.setHorizontalAlignment(SwingConstants.CENTER);
		htmlLabel.setBorder(BorderFactory.createEtchedBorder());
		htmlLabel.setText(initialHtml);
	}
	
	private void createLeftPanel(){
        leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        
		htmlTextArea = new JTextArea(15,25);
		htmlTextArea.setBackground(Color.BLACK);
		htmlTextArea.setForeground(Color.CYAN);
		htmlTextArea.setFont(new Font("SansSerif",Font.PLAIN,18));
        htmlTextArea.setText(initialHtml);
        JScrollPane scrollPane = new JScrollPane(htmlTextArea);
        leftPanel.add(scrollPane,BorderLayout.CENTER); 
        
        leftPanel.add(new JLabel("HTML Source"),BorderLayout.NORTH);
        
        JButton resetButton = new JButton("Reset");
        resetButton.setActionCommand("reset");
        resetButton.addActionListener(this);
        leftPanel.add(resetButton,BorderLayout.SOUTH);
        
        /*
        JButton changeTheLabel = new JButton("Change the label");
        changeTheLabel.setMnemonic(KeyEvent.VK_C);
        changeTheLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        changeTheLabel.addActionListener(this);
 
        theLabel = new JLabel(initialText) {
            public Dimension getPreferredSize() {
                return new Dimension(200, 200);
            }
            public Dimension getMinimumSize() {
                return new Dimension(200, 200);
            }
            public Dimension getMaximumSize() {
                return new Dimension(200, 200);
            }
        };
        theLabel.setVerticalAlignment(SwingConstants.CENTER);
        theLabel.setHorizontalAlignment(SwingConstants.CENTER);
 
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
        leftPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                    "Edit the HTML, then click the button"),
                BorderFactory.createEmptyBorder(10,10,10,10)));
        leftPanel.add(scrollPane);
        leftPanel.add(Box.createRigidArea(new Dimension(0,10)));
        leftPanel.add(changeTheLabel);
 
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));
        rightPanel.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("A label with HTML"),
                        BorderFactory.createEmptyBorder(10,10,10,10)));
        rightPanel.add(theLabel);
 
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        add(leftPanel);
        add(Box.createRigidArea(new Dimension(10,0)));
        add(rightPanel);
        */
    }
 
    //React to the user pushing the Change button.
    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton)e.getSource();
        if(src.getActionCommand().equals("reset")){
        	htmlTextArea.setText(initialHtml);
        }else if(src.getActionCommand().equals("change")){
        	htmlLabel.setText(htmlTextArea.getText());
        }
    }

    private static void createAndShowGUI() {
        HtmlLabelDemo frame = new HtmlLabelDemo("Test HTML on a JLabel");
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
