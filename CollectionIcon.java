import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;

public class CollectionIcon implements Icon {
	private ArrayList<Icon> iconList; 

	public CollectionIcon(){
		iconList = new ArrayList<Icon>();
		for (int i=0; i<10; i++) {
			HungerEnergyRect herRED = new HungerEnergyRect(10,10,Color.RED);
			HungerEnergyRect herSPACE = new HungerEnergyRect(10,2,Color.WHITE);
			iconList.add(herRED);
			iconList.add(herSPACE);
		}
	}

	public void addIcon(Icon ic){
		iconList.add(ic);
	}

	public int getIconHeight(){
		int ih = 0;

		for(Icon i : iconList){
			if(i.getIconHeight() > ih){
				ih = i.getIconHeight();
			}
		}
		return ih;
	}

	public int getIconWidth(){
		int iw = 0;

		for(Icon i : iconList){
			iw += i.getIconWidth();
		}
		return iw;
	}

	public void paintIcon(Component c, Graphics g, int x, int y){
		Graphics2D g2 = (Graphics2D) g;

		int totalIconWidth = x;
		for(Icon i : iconList){
			i.paintIcon(c, g2, totalIconWidth, y);
			totalIconWidth += i.getIconWidth();
		}
	}
}