package topic5;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JList;

public class LaunchYouTubeAction extends AbstractAction{
	JList<CustomEntryPanel> resultList;
	public LaunchYouTubeAction(JList<CustomEntryPanel> resultList){
		this.resultList = resultList;
	}
	public void actionPerformed(ActionEvent e) {
		String url = resultList.getSelectedValue().getUrl();
		try {
			Desktop.getDesktop().browse(new URI(url));
		} catch (IOException | URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
