import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class IconFileUtil {
	public static boolean loadIconFromFile(File file,IconPanel iconPanel,String [] msg){
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			try {
				Color [][] c = (Color [][])in.readObject();
				iconPanel.setColors(c);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				msg[0] = "Error reading "+file.getName()+" as a valid icon.";
				in.close();
				return false;
			}
			in.close();
		} catch (IOException e) {
			msg[0] = "Cannot read "+file.getName()+".";
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static boolean saveIconToFile(File file,IconPanel iconPanel,String [] msg){
		return true;
	}
}
