package topic3;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class EventDemoApplet extends JApplet {
	
	JButton button = new JButton("Do not click");
	JTextArea textArea = new JTextArea();
	
	public EventDemoApplet(){
		this.setLayout(new BorderLayout());
		
		button.addActionListener(new MyButtonHandler());
		
		this.add(textArea,BorderLayout.CENTER);
		this.add(button,BorderLayout.SOUTH);
	}

	class MyButtonHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			textArea.append("I said \" DO NOT CLICK \"\n");
		}
		
	}
	
}
