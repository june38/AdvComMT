package topic3;

import javax.swing.JApplet;
import javax.swing.JButton;

public class TestFlowLayoutApplet extends JApplet {
	private JButton [] buttons = new JButton[10]; 
	public TestFlowLayoutApplet(){
		this.setLayout(new java.awt.FlowLayout());
		for(int i=0;i<buttons.length;i++){
			buttons[i] = new JButton("Action "+i);
			this.add(buttons[i]);
		}
	}
}
