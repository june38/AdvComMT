import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class IconMaker extends JFrame {
	
	PalettePanel palette = new PalettePanel();
	JInternalFrame paletteWindow = new JInternalFrame();
	IconMakerMenuBar menuBar;
	IconPanel iconPanel = new IconPanel(8,8,this);
	JLabel status = new JLabel("Ready");
	
	public IconMaker(){
		super("Icon Maker");
		this.setLayout(new BorderLayout());
		this.add(iconPanel,BorderLayout.CENTER);
		this.add(status,BorderLayout.SOUTH);
		palette.chooser.setColor(Color.BLACK);
		paletteWindow.add(palette);
		paletteWindow.setVisible(true);
		paletteWindow.setBounds(100, 100, 600,300);
		this.getLayeredPane().add(paletteWindow,JLayeredPane.PALETTE_LAYER);
			
		menuBar = new IconMakerMenuBar((JFrame)this);
		this.setJMenuBar(menuBar);
	}
	
	
	public boolean isConfirmedBeforeAction(String action){
		String [] options = {"Yes. I am sure.","No"};
		int n = JOptionPane.showOptionDialog(IconMaker.this,"There are unsaved changes. Are you sure you want to "+action+"?",
			    "Please confirm",
			    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,options,options[1]);
		return !(n==JOptionPane.NO_OPTION);
	}
	
	public static void createAndShowGUI(){
		IconMaker mainFrame = new IconMaker();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
        mainFrame.pack();
        mainFrame.setVisible(true);
	}
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
	}
	
	class IconMakerMenuBar extends JMenuBar{

		JFrame parent;
		
		JMenu fileMenu = new JMenu("File");
		JMenuItem loadMenuItem = new JMenuItem("Load");
		JMenuItem saveMenuItem = new JMenuItem("Save as");
		JMenuItem quitMenuItem = new JMenuItem("Quit");
		JCheckBox paletteVisible = new JCheckBox("Show color palette",true);
		
		public IconMakerMenuBar(JFrame parent){
			super();
			this.parent = parent;
			
			fileMenu.add(loadMenuItem);
			fileMenu.add(saveMenuItem);
			fileMenu.add(quitMenuItem);
			add(fileMenu);
			add(paletteVisible);
			
			loadMenuItem.setName("load");
			saveMenuItem.setName("save");
			quitMenuItem.setName("quit");
			paletteVisible.setName("palette toggle");
			
			MenubarHandler handler = new MenubarHandler(parent);
			loadMenuItem.addActionListener(handler);
			saveMenuItem.addActionListener(handler);
			quitMenuItem.addActionListener(handler);
			paletteVisible.addActionListener(handler);
		}
	}
	
	class MenubarHandler implements ActionListener{
		JFrame parent;
		
		public MenubarHandler(JFrame parent){
			super();
			this.parent = parent;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JComponent src = (JComponent)arg0.getSource();
			String name = src.getName();
			switch(name){
				case "load":
					if(!IconMaker.this.iconPanel.isNotSaved()||IconMaker.this.isConfirmedBeforeAction("load an icon")){
						JFileChooser chooser = new JFileChooser();
						if(chooser.showOpenDialog(IconMaker.this) == JFileChooser.APPROVE_OPTION){
							IconMaker parentIconMaker = (IconMaker)parent;
							String [] msg = new String[1];
							if(!IconFileUtil.loadIconFromFile(chooser.getSelectedFile(),parentIconMaker.iconPanel,msg)){
								JOptionPane.showMessageDialog(IconMaker.this,msg,"Error loading file", JOptionPane.ERROR_MESSAGE);
							}else{
								parentIconMaker.iconPanel.setNotSaved(false);
								parentIconMaker.validate();						
							}
						}
					}
					break;
				case "save":
					JFileChooser chooser = new JFileChooser();
					if(chooser.showSaveDialog(IconMaker.this) == JFileChooser.APPROVE_OPTION){
						String [] msg = new String[1];
						if(!IconFileUtil.saveIconToFile(chooser.getSelectedFile(),iconPanel,msg)){
							JOptionPane.showMessageDialog(IconMaker.this,msg,"Error saving file", JOptionPane.ERROR_MESSAGE);
						}else{
							IconMaker.this.iconPanel.setNotSaved(false);
							IconMaker.this.validate();
						}
					}
					break;
				case "quit":
					if(!IconMaker.this.iconPanel.isNotSaved()||IconMaker.this.isConfirmedBeforeAction("quit the program")){
						System.exit(0);
					}
					break;
				case "palette toggle":
					IconMaker.this.paletteWindow.setVisible(IconMaker.this.menuBar.paletteVisible.isSelected());
					break;
				default:
						
			}
		}
		
	}
}
