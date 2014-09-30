

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JColorChooser;
import javax.swing.JPanel;

public class PalettePanel extends JPanel {
	JColorChooser chooser = new JColorChooser();
	public PalettePanel(){
		setLayout(new BorderLayout());
		chooser.setPreviewPanel(new JPanel());
		chooser.setColor(Color.BLACK);
		add(chooser,BorderLayout.CENTER);
	}
}
