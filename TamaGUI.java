import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class TamaGUI extends JApplet{

	public void init() {

		CarIcon carIcon = new CarIcon(100);
		final JPanel worldPanel = new JPanel();
		final JLabel carLabel = new JLabel(carIcon);
		final JTextField textLife = new JTextField("This field displays life");
		final JTextField textEnergy = new JTextField("This field displays energy");
		setLayout(new BorderLayout());
		add(worldPanel, BorderLayout.NORTH);
		worldPanel.add(carLabel);
		add(buttonAdder(), BorderLayout.SOUTH);

		
	}

	public JPanel buttonAdder() {
		final JButton eat = new JButton("Eat");
		final JButton sleep = new JButton("Sleep");
		final JButton fight = new JButton("Fight");
		final JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1,3));
		buttonPanel.add(eat);
		buttonPanel.add(sleep);
		buttonPanel.add(fight);
		return buttonPanel;
	}
}