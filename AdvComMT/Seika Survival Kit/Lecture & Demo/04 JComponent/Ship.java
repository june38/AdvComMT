package topic4;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.KeyStroke;

public class Ship extends JLabel {
	private double x = 0.5;
	public Ship(){
		super(new ImageIcon("spaceshipapp/ship.png"));
		InputMap inputMap = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(KeyStroke.getKeyStroke("LEFT"),"moveLeft");
		inputMap.put(KeyStroke.getKeyStroke("RIGHT"),"moveRight");
		ActionMap actionMap = this.getActionMap();
		actionMap.put("moveLeft", new MoveAction("left"));
		actionMap.put("moveRight", new MoveAction("right"));		
	}
	public void draw(Dimension d){
		this.setBounds((int)(d.width*x-50),d.height-250,100,100);
	}
	class MoveAction extends AbstractAction{
		String direction;
		public MoveAction(String direction){
			this.direction = direction;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if(direction.equals("left")){
				x -= 0.01;
			}else if(direction.equals("right")){
				x += 0.01;
			}
		}
	}
}
