import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class TamaGUI extends JApplet{

	public void init() {

		final JPanel worldPanel = new JPanel();
		final JLabel values = new JLabel("something");
		final JLabel world = new JLabel("Life");
		final JTextField textLife = new JTextField("This field displays life");
		
		final JTextField textEnergy = new JTextField("This field displays energy");
		

		add(buttonAdder());
		add(values);
		add(world);
		setLayout(new GridLayout(1,4));
		add(textLife);
		add(textEnergy);
	}

	public JPanel buttonAdder() {
		final JButton eat = new JButton("Energy");
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