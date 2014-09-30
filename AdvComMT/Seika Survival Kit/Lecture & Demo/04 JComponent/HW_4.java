package HW4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import HW4.HW_4.CountAction;


public class HW_4 extends JFrame implements ItemListener{
	JTextArea textArea;
	JPanel MyPanel,buttonPanel;
	JMenuBar menuBar;
	JMenu selected;
	static JTextField textField;
	JButton resetButton,countButton;
	static JTextField solutionField;
	static int count = 0;
	
	public HW_4(String t){
		super(t);
		createMenuBar();
		this.add(menuBar,BorderLayout.NORTH);
		MyPanel = new MyPanel();
		MyPanel.setOpaque(true);
		this.add(MyPanel);
		createButtonPanel();
		this.add(buttonPanel,BorderLayout.SOUTH);
	}
	public static class CountAction extends AbstractAction{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		static int count;
		public CountAction() {
			super("Count");
			putValue(AbstractAction.ACCELERATOR_KEY,KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
			putValue(AbstractAction.SHORT_DESCRIPTION,"Count");
		}		
		public void actionPerformed(ActionEvent e) {
          solutionField.setText("Count selected"+"= "+count); 
        }
	}
	public class ResetAction extends AbstractAction{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public ResetAction() {
			super("Reset Solution");
			putValue(AbstractAction.ACCELERATOR_KEY,KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
			putValue(AbstractAction.SHORT_DESCRIPTION,"Reset a Solution");
		}		
		public void actionPerformed(ActionEvent e) {
          solutionField.setText("Hide count value"); 
        }
	}
	
	private void createButtonPanel() {
		buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(0,0,0,100));
		buttonPanel.setPreferredSize(new Dimension(200,70));
		countButton = new JButton(new CountAction());
		resetButton = new JButton(new ResetAction());
		solutionField = new JTextField();
		solutionField.setPreferredSize(new Dimension(500,20));
		solutionField.setBackground(Color.white);
		solutionField.setForeground(Color.black);
		solutionField.setFont(new Font("Menlo",Font.PLAIN,12));
		buttonPanel.add(countButton);
		buttonPanel.add(resetButton);
		buttonPanel.add(solutionField, BorderLayout.SOUTH);
		
	}
	private void createMenuBar() {
		menuBar = new JMenuBar();
		menuBar.setBackground(new Color(0,0,0,30));
		selected = new JMenu("Selected");
		selected.setBackground(new Color(0,0,0,100));
		
		JMenuItem i1 = new JMenuItem(new CountAction());
		JMenuItem i2 = new JMenuItem(new ResetAction());
		selected.add(i1);;
		selected.add(i2);
		menuBar.add(selected);
	}
	
	public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
           public void run() {
               createAndShowGUI();
           }
       });
	}
	
	 private static void createAndShowGUI() {
	        HW_4 frame = new HW_4("Counting!!!");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(700,400);
	        frame.setBackground(Color.WHITE);
	        frame.setLocationRelativeTo(null);
	        frame.setVisible(true);
	 }
		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			
		} 

	}

class MyPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private int side = 25;
	boolean firstTime = true;
    private int X ;
    private int Y ;
    public MyPanel() {
    	 addMouseListener(new MouseAdapter() {
             public void mousePressed(MouseEvent e) {
                 drawRec(e.getX(),e.getY());
                 firstTime = false;
             }
         });   
    }
    private void drawRec(int x, int y) {
        X=x;
        Y=y;
        repaint(x,y,side,side);
        CountAction.count++;
    }
    protected void paintComponent(Graphics g) {
    	if(!firstTime){
    		Graphics2D g2 = (Graphics2D) g;
	        g2.setColor(Color.GREEN);
	        Rectangle2D temp = new Rectangle2D.Double(X,Y,side,side);
	        g2.fill(temp);
    	}
    }
    
}