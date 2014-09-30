package practice_problem2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class DotDraw extends JFrame{
	private static DotDraw mJFrame;
	
	private static final int WIDTH = 400;
	private static final int HEIGHT = 400;
	private static PaintPanel containerLeft;
	private static JPanel containerRight;
	private static JLabel label;
	private static JSlider jSlide;
	private static JPanel jAction;
	private static Color color;
	private static boolean clicked = false;
	private static int RADIUS = 5;
	public static void main(String[] args){
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createGUI();
                
            }
        });
		
	}
	
	public DotDraw(String title){
		super(title);
	}
	
	public static void createGUI(){
		mJFrame = new DotDraw("DotDraw");
		mJFrame.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		mJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		mJFrame.setBackground(Color.ORANGE);
		BorderLayout borderLayout = new BorderLayout(0,1);
		mJFrame.setLayout(borderLayout);
		color = Color.WHITE;
		createLeftContainer();
		
		createRightContainer();
//		createUpperContainer();
//		createLowerContainer();
//		createStatusBar();
		
		mJFrame.add(containerLeft,BorderLayout.WEST);
		mJFrame.add(containerRight,BorderLayout.EAST);
//		mJFrame.add(status,BorderLayout.SOUTH);
		mJFrame.pack();
		mJFrame.setVisible(true);
	}

	private static void createLeftContainer() {
		// TODO Auto-generated method stub
		containerLeft = new PaintPanel();
//		containerLeft.setBackground(Color.RED);
		containerLeft.setPreferredSize(new Dimension(WIDTH/2,HEIGHT));
		containerLeft.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				if(clicked){
					containerLeft.repaint();
					Dot dot = new Dot(e.getX(), e.getY(), RADIUS,color);
					containerLeft.addDot(dot);
				}
			}
		});
		containerLeft.addMouseListener(new MouseListener() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
//	            System.out.print( e.getX()+" "+e.getY());
	        }

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Click");
				Dot dot = new Dot(e.getX(), e.getY(), RADIUS,color);
				containerLeft.addDot(dot);
				containerLeft.repaint();
				clicked = true;
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Release");

				clicked = false;
			}
			
	    });
	}

	private static void createRightContainer() {
		// TODO Auto-generated method stub
		containerRight = new JPanel(new GridLayout(4,1));
//		containerRight.setBackground(Color.GREEN);
		containerRight.setPreferredSize(new Dimension(WIDTH/2,HEIGHT));
		Dimension d = new Dimension(WIDTH/2,HEIGHT/4);
		label = new JLabel("Size: ");
		label.setPreferredSize(d);
		label.setOpaque(true);
		jSlide = new JSlider(JSlider.HORIZONTAL,0,100,20);
		jSlide.setPreferredSize(new Dimension(WIDTH/2-20,HEIGHT/4));
		jSlide.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				int frequency = jSlide.getValue();
				RADIUS = frequency;
			}
		});
		//Create the label table
		Hashtable labelTable = new Hashtable();
		labelTable.put( new Integer( 0 ), new JLabel("Small") );
		labelTable.put( new Integer( 100 ), new JLabel("Big") );
		jSlide.setLabelTable( labelTable );
		jSlide.setPaintLabels(true);
		int frequency = jSlide.getValue();
		RADIUS = frequency;
		
		
		JCheckBox box = new JCheckBox("Pen on/off");
//        box.setMnemonic(KeyEvent.VK_C);
		box.setPreferredSize(new Dimension(WIDTH/2-20,HEIGHT/4));
        box.setSelected(false);
        box.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				if(arg0.getStateChange() == ItemEvent.SELECTED){
					color = Color.BLACK;
					
				}else{
					color = Color.WHITE;
				}
			}
		});
//		jSelector.add(box);
		
        jAction = new JPanel(new GridLayout(1,2));
        JButton clear = new JButton("clear");
        clear.setPreferredSize(new Dimension(WIDTH/4,HEIGHT/4 ));
        JButton exit = new JButton("exit");
        exit.setPreferredSize(new Dimension(WIDTH/4,HEIGHT/4 ));
        clear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				containerLeft.clearScreen();
				containerLeft.repaint();
			}
		});
        
        exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mJFrame.dispatchEvent(new WindowEvent(mJFrame, WindowEvent.WINDOW_CLOSING));
			}
		});
        jAction.add(clear);
        jAction.add(exit);
        
		containerRight.add(label);
		containerRight.add(jSlide);
		containerRight.add(box);
		containerRight.add(jAction);
		
		
	}
	
	
	
}
