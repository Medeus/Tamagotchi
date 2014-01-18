import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class CarIcon implements Icon {

	private int width;

	public CarIcon(int aWidth) {
		width = aWidth;
	}
	
	public int getIconWidth() {
		return width;
	}

	public int getIconHeight() {
		return width/2;
	}

	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g;
		Rectangle2D.Double body
			= new Rectangle2D.Double(x,y + width/6, width - 1, width/6);
		Ellipse2D.Double frontTire 
			= new Ellipse2D.Double(x + width/6, y + width/3, width/6, width/6);
		Ellipse2D.Double rearTire
			= new Ellipse2D.Double(x + width * 2/3, y + width/3, width/6, width/6);

		Point2D.Double r1
			= new Point2D.Double(x + width/6, y + width/6);
		Point2D.Double r2
			= new Point2D.Double(x + width/3, y);
		Point2D.Double r3
		 	= new Point2D.Double(x + width * 2/3, y);
		Point2D.Double r4
			= new Point2D.Double(x + width * 5/6, y + width / 6);

		Line2D.Double frontWindShield
			= new Line2D.Double(r1, r2);
		Line2D.Double roofTop
			= new Line2D.Double(r2, r3);
		Line2D.Double rearWindShield
			= new Line2D.Double(r3, r4);

		g2.fill(frontTire);
		g2.fill(rearTire);
		g2.setColor(Color.RED);
		g2.fill(body);
		g2.draw(frontWindShield);
		g2.draw(roofTop);
		g2.draw(rearWindShield);
	}
}