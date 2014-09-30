package topic4;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class SpaceShipApp extends JFrame {
	private StarField field = new StarField();
	private Ship ship = new Ship();
	public SpaceShipApp(){
		super();
		setLayout(new BorderLayout());
		add(field,BorderLayout.CENTER);
		field.addShip(ship);
	}
	
	private static void createGui(){
		SpaceShipApp frame = new SpaceShipApp();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		frame.pack();
		frame.setVisible(true);
	}
	public static void main(String [] args){
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				createGui();
			}
		});
	}
}
