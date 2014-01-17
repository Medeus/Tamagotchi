import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class TamaGUI extends JApplet{

	public void init() {

		final JLabel values = new JLabel();
		final JLabel world = new JLabel();
		final JButton eat = new JButton("Eat");
		final JButton sleep = new JButton("Sleep");

		setLayout(new GridLayout(3,2));
		add(values);
		add(world);
		add(eat);
		add(sleep);
	}
}