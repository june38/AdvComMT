package topic5;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import com.google.gdata.data.youtube.VideoEntry;
import com.google.gdata.data.youtube.VideoFeed;
import com.google.gdata.util.ServiceException;


public class YouTubeList extends JFrame {
	JTextField searchBox;
	JList<CustomEntryPanel> resultList;
	VideoFeed resultFeed;
	final String INITIAL_SEARCH = "ICE";
	public YouTubeList(){
		super("Java YouTube Search");
		setGUI();
	}
	private void setGUI(){
		setLayout(new BorderLayout());
		
		JPanel searchPanel = new JPanel();
		searchBox = new JTextField(INITIAL_SEARCH);
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String keyword = searchBox.getText().trim();
				if(keyword!=""){
					try {
						resultFeed = YouTubeSearch.search(keyword);
						showResult(resultFeed);
					} catch (IOException | ServiceException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		searchPanel.setLayout(new BorderLayout());
		searchPanel.add(searchBox,BorderLayout.CENTER);
		searchPanel.add(searchButton,BorderLayout.EAST);
		this.add(searchPanel,BorderLayout.NORTH);
		
		resultList = new JList();
		resultList.setCellRenderer(new MyCellRenderer());
		JScrollPane scrollPane = new JScrollPane(resultList);
		resultList.getInputMap().put(KeyStroke.getKeyStroke("ENTER"),"launchYouTube");
		resultList.getActionMap().put("launchYouTube",new LaunchYouTubeAction(resultList));
		this.add(scrollPane,BorderLayout.CENTER);
		
	}
	private void showResult(VideoFeed resultFeed){
		DefaultListModel<CustomEntryPanel> listModel = new DefaultListModel<CustomEntryPanel>();
		for(VideoEntry videoEntry : resultFeed.getEntries() ) {
		    listModel.addElement(new CustomEntryPanel(videoEntry));
		}
		resultList.setModel(listModel);
	}
	public static void createAndShowGUI(){
		YouTubeList frame = new YouTubeList();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(600,600));
		frame.pack();
		frame.setVisible(true);
	}
	public static void main(String [] args){
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				createAndShowGUI();
			}	
		});
	}
}
