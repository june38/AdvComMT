package topic4;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

public class MenuDemo extends JFrame {
	public MenuDemo(){
		super("Demo of JMenuItem");
		createMenuBar();
	}
	
	private void createMenuBar(){
		JMenuBar menuBar = new JMenuBar();
		
		JMenu menuFile = new JMenu("File");
		
		JMenuItem itemLoad = new JMenuItem("Load");
		itemLoad.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_L, ActionEvent.CTRL_MASK));
		itemLoad.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				int returnVal = chooser.showOpenDialog(MenuDemo.this);
				// This demo does nothing with returnVal
			}		
		});
		menuFile.add(itemLoad);
		
		JMenuItem itemQuit = new JMenuItem("Quit");
		itemQuit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int choice = JOptionPane.showConfirmDialog(
						MenuDemo.this,
						"Are you sure?",
						"Please confirm",
						JOptionPane.YES_NO_OPTION);
				if(choice==JOptionPane.YES_OPTION){
					System.exit(0);
				}
			}		
		});
		menuFile.add(itemQuit);
		
		JMenuItem itemAbout = new JMenuItem("About");
		itemAbout.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(
						MenuDemo.this,
						"This Demo aims at showing how to use Menus and Dialogs" ,
						"About this Demo",
						JOptionPane.PLAIN_MESSAGE,
						new ImageIcon("icon_2143101.png"));
			}		
		});
		
		
		menuBar.add(menuFile);
		menuBar.add(itemAbout);
		this.setJMenuBar(menuBar);
	}
	
	private static void createGUI(){
		MenuDemo frame = new MenuDemo();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(400,400));
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String [] args){
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				createGUI();
			}	
		});
	}
}
