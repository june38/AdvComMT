package topic3;

import java.awt.GridLayout;

import javax.swing.JApplet;
import javax.swing.JColorChooser;
import javax.swing.JSlider;

public class Containment extends JApplet {
	public Containment(){
		this.setLayout(new GridLayout(2,2,5,5));
		add(new TestFlowLayoutApplet());
		add(new BorderLayoutApplet());
		add(new JSlider());
		add(new JColorChooser());
	}
}
