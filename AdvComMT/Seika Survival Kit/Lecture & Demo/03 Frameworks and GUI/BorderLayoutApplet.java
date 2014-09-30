package topic3;

import java.awt.BorderLayout;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class BorderLayoutApplet extends JApplet {
	public BorderLayoutApplet(){
		this.setLayout(new BorderLayout());
		this.add(new JButton("UP"),BorderLayout.NORTH);
		this.add(new JButton("RIGHT"),BorderLayout.EAST);
		this.add(new JButton("LEFT"),BorderLayout.WEST);
		this.add(new JButton("DOWN"),BorderLayout.SOUTH);
		this.add(new JTextArea("Advanced Computer Programming"),BorderLayout.CENTER);
	}
}
