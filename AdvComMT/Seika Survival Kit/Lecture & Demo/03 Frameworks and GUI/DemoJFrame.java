package topic3;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DemoJFrame extends JFrame{
	public DemoJFrame(String title){
		super(title);
	}
	private static void createAndShowGUI() {
        DemoJFrame frame = new DemoJFrame("A Demo of a JFrame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel iceLabel = new JLabel(new ImageIcon("icon_2143101.png"));
        iceLabel.setPreferredSize(new Dimension(300,300));
        frame.add(iceLabel);
        
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
