public class /*classname*/ {
	/* initiate ตัวแปร */
	public /*classname*/(String title){
		super(title);
		/* example of creating panel */
		createOptionPanel();
		this.add(optionPanel, BorderLayout.EAST);
		createDrawingPanel();
		this.add(drawingPanel, BorderLayout.WEST);
		/* example ends */
	}
 




	public static void createAndShowGUI(){
		/*classname*/ frame = new  /*classname*/("/*name o windows*/");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500); // set the size of GUI
        frame.setBackground(new Color(128,128,128));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}
	public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
           public void run() {
               createAndShowGUI();
           }
       });
    }



}