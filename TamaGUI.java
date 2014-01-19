import java.awt.Image;
import java.net.URL;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.Timer;

public class TamaGUI extends JApplet{

	private String[] buttons = {"Eat", "Sleep", "Fight"};
	private URL tamagotchiPIC = getClass().getResource("Figurer/YodaUp.JPG");
	private YodaGotchi yoda = new YodaGotchi();
	//private final Timer imageTimer = new Timer();		//Til billedskift hvert sekund

	public void init() {
		try {
            UIManager.setLookAndFeel(
            UIManager.getSystemLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException e) {
            System.err.println("Can't use the specified look and feel (System LookAndFeel) on this platform.");
            System.err.println("Using the default look and feel.");
        }
        catch (ClassNotFoundException e) {
            System.err.println("Couldn't find class for specified look and feel: System LookAndFeel");
            System.err.println("Did you include the L&F library in the class path?");
            System.err.println("Using the default look and feel.");
        }
        catch (Exception e) {
            System.err.println("Couldn't get specified look and feel (System LookAndFeel);, for some reason.");
            System.err.println("Using the default look and feel.");
            e.printStackTrace();
        }

		Image image;
		/*
		Denne kode skulle hjælpe med at lave et billed skift hvert sekend, men er for træt til at finde fejlen!

		imageTimer.schedule( new TimerTask() {
			public void run() {
				if (yoda.getLifeState() == true) {
					if (tamagotchiPIC == getClass().getResource("Figurer/YodaUp.JPG")) {
						tamagotchiPIC = getClass().getResource("Figurer/YodaDown.JPG");
						repaint();
					}
					else {
						getClass().getResource("Figurer/YodaDown.JPG");
						repaint();
					}
				}
			}
		}, 0, 1000 );
		*/
		final JPanel worldPanel = new JPanel();
		final JLabel imageLabel = new JLabel(new ImageIcon(tamagotchiPIC));
		setLayout(new BorderLayout());
		add(worldPanel, BorderLayout.NORTH);
		worldPanel.add(imageLabel);
		add(buttonMaker(), BorderLayout.SOUTH);

		
	}
	//This method uses a FOR-EACH-LOOP to create and add buttons from StringArray buttons
	public JPanel buttonMaker() {
		final JPanel buttonPanel = new JPanel(new GridLayout(1,buttons.length));
		for(String s : buttons) {
			JButton btn = new JButton(s);
			buttonPanel.add(btn);
		}
		return buttonPanel;
	}
}