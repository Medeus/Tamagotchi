import java.awt.Image;
import java.net.URL;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class TamaGUI extends JApplet{

	private String[] buttons = {"Eat", "Sleep", "Fight"};

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
		URL tamagotchiPIC = getClass().getResource("Figurer/YodaDead.JPG");
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