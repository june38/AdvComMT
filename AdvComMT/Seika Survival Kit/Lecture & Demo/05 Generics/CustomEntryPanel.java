package topic5;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import com.google.gdata.data.media.mediarss.MediaThumbnail;
import com.google.gdata.data.youtube.VideoEntry;
import com.google.gdata.data.youtube.YouTubeMediaGroup;

public class CustomEntryPanel extends JPanel {
	JLabel titleLabel;
	JLabel thumbLabel;
	String entryUrl;
	public CustomEntryPanel(VideoEntry videoEntry){
		titleLabel = new JLabel(videoEntry.getTitle().getPlainText());
		
		YouTubeMediaGroup mediaGroup = videoEntry.getMediaGroup();
		entryUrl = mediaGroup.getPlayer().getUrl();
		
		List<MediaThumbnail> thumbList = mediaGroup.getThumbnails();
		String thumbLabelHtml = "<html>";
		thumbLabelHtml += "<img src=\""+thumbList.get(0).getUrl()+"\">";
		thumbLabelHtml += "</html>";
		thumbLabel = new JLabel(thumbLabelHtml);
		
		setLayout(new BorderLayout());
		add(titleLabel,BorderLayout.CENTER);
		add(thumbLabel,BorderLayout.WEST);
		
		setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
	}
	public void setForeground(Color c){
		if(titleLabel!=null){
			titleLabel.setForeground(c);
		}
	}
	public String getUrl(){
		return entryUrl;
	}
}
