import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class TamaGUI extends JApplet{

	private String[] buttons = {"Eat", "Sleep", "Fight"};

	public void init() {

		//CarIcon is temporary - Tamagotchis will be added shortly!
		CarIcon carIcon = new CarIcon(100);
		final JPanel worldPanel = new JPanel();
		final JLabel carLabel = new JLabel(carIcon);
		final JTextField textLife = new JTextField("This field displays life");
		final JTextField textEnergy = new JTextField("This field displays energy");
		setLayout(new BorderLayout());
		add(worldPanel, BorderLayout.NORTH);
		worldPanel.add(carLabel);
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