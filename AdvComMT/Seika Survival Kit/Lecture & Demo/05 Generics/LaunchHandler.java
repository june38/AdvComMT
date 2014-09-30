package topic5;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LaunchHandler implements ActionListener {
	String url;
	public LaunchHandler(String url){
		this.url = url;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println(url);
	}

}
