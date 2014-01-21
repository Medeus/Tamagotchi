import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class HungerEnergyRect implements Icon {
	private Color color;
	private int iconHeight;
	private int iconWidth;

	public HungerEnergyRect(int height, int width, Color color) {
		iconHeight = height;
		iconWidth = width;
		this.color = color;
	}

	public void paintIcon(Component c, Graphics g, int x, int y) {
		g.setColor(color);
		g.fillRect(x, y, iconWidth, iconHeight);
	}

	public int getIconHeight() {
		return iconHeight;
	}

	public int getIconWidth() {
		return iconWidth;
	}
}