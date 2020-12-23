package Client;
import java.awt.Image;

import javax.swing.ImageIcon;

public class LabelImage {
	
	public static ImageIcon Labelimage(String st, int h, int w) {
		//이미지경로, 높이, 너비
		ImageIcon icon = new ImageIcon(st);
		Image iicon = icon.getImage();
		Image iicon1 = iicon.getScaledInstance(h, w, Image.SCALE_DEFAULT);
		ImageIcon icon1 = new ImageIcon(iicon1);
		return icon1;
		
	}
	
}


