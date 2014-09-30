
import java.awt.*;
import java.applet.Applet;
public class Planet extends Applet{
	public enum Planet{
		Mercury("mercury.jpg",1), 
		Venus("venus.jpg",2), 
		Earth("earth.jpg",3), 
		Jupiter("jupiter.jpg",4), 
		Saturn("saturn.jpg",5);
		
		private String path;
		private int order;
		
		private Planet(String path, int order){
			this.path = path;
			this.order = order;
		}
		
		
		public Planet next(){ 
			switch( this.order ){ 
				case 1: return Venus; 
				case 2: return Earth; 
				case 3: return Jupiter; 
				case 4: return Saturn; 
				case 5: return Mercury; 
				default: return null; 
			} 
		}
		
		public static Planet getRandom(){
			switch( (int)((Math.random()*5)+1) ){
			case 1: return Mercury;
			case 2: return Venus;
			case 3: return Earth;
			case 4: return Jupiter;
			case 5: return Saturn;
			default: return null;
			}
		}	
	};
	
	
	Image picture;
	Planet planet = Planet.getRandom();
	
	public void init(){
		setSize(500,500);
		picture = getImage(getDocumentBase(),planet.path);
	}
	
	public void paint(Graphics g){ 
		g.drawImage(picture, 0, 30, this); 
		g.drawString(planet.toString(), 10, 20); 
		planet = planet.next(); 
		picture = getImage(getDocumentBase(),planet.path); 
	}
}
