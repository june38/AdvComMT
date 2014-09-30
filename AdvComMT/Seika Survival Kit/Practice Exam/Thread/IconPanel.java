import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JPanel;


public class IconPanel extends JPanel{

	int w,h;
	JPanel innerPanel = new JPanel();
	boolean notSaved;
	
	public IconPanel(int w, int h, IconMaker parent){
		super();
		this.w = w;
		this.h = h;		
		this.setBackground(Color.DARK_GRAY);
		this.setLayout(null);
		innerPanel.setLayout(new GridLayout(w,h,0,0));
		for(int x=0;x<w;x++){
			for(int y=0;y<h;y++){
				innerPanel.add(new PixelPatch(Color.WHITE));
			}
		}
		this.add(innerPanel);
		this.setNotSaved(false);
	}
	
	public void setNotSaved(boolean b){
		notSaved = b;
	}
	
	public boolean isNotSaved(){
		return notSaved;
	}
	
	public Color [][] getColors(){
		Color [][] colors = new Color[this.w][this.h];
		for(int x=0;x<this.w;x++){
			for(int y=0;y<this.h;y++){
				PixelPatch p = (PixelPatch)innerPanel.getComponent(this.w*x+y);
				colors[x][y] = p.getPatchColor();
			}
		}
		return colors;
	}
	
	public void setColors(Color [][] colors){
		for(int x=0;x<this.w;x++){
			for(int y=0;y<this.h;y++){
				PixelPatch p = (PixelPatch)innerPanel.getComponent(this.w*x+y);
				p.setPatchColor(colors[x][y]);
			}
		}
	}
	
	public void paintComponent(Graphics g){
		Dimension d = this.getSize();
		innerPanel.setBounds(d.width/2-200,d.height/2-200,400,400);
		super.paintComponent(g);
	}
	
	class PixelPatch extends JPanel{
		private Color c;
		public PixelPatch(Color c){
			this.setOpaque(true);
			this.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
			setPatchColor(c);
			this.addMouseListener(new PixelClickHandler());
		}
		public void setPatchColor(Color c){
			this.c = c;
			this.setBackground(c);
		}
		public Color getPatchColor(){
			return this.c;
		}
	}
	
	class PixelClickHandler extends MouseAdapter{
		public void mouseReleased(MouseEvent me){
			PixelPatch src = (PixelPatch)me.getSource();
			IconMaker parent = (IconMaker)(IconPanel.this.getParent().getParent().getParent().getParent());
			Color newColor = parent.palette.chooser.getColor();
			if(!src.getPatchColor().equals(newColor)){
				IconPanel.this.setNotSaved(true);
				src.setPatchColor(parent.palette.chooser.getColor());
			}
		}
	}
}
