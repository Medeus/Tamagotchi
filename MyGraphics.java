import java.net.URL;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.Timer;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Image;
import java.util.*;
import javax.imageio.*;
import java.net.*;
import java.applet.*;

public class MyGraphics extends JComponent {
	private Image tamagotchi;

	public MyGraphics(Image i) {
		Image tamagotchi = i;
	}

	public void paintComponent(Graphics g) {
		g.drawImage(tamagotchi, 20, 120, tamagotchi.getHeight(this), tamagotchi.getWidth(this), this);
	}
}
