import java.net.URL;
import javax.swing.*;
import javax.swing.event.*;
//import javax.swing.Timer; skal måske bruges
import java.util.Timer;
import java.awt.*;
import java.awt.event.*;
import java.awt.Image;
import java.util.*;

public class TamaGUI extends JApplet implements ChangeListener{
    private ArrayList<JButton> buttonlist = new ArrayList<JButton>();
    private Tamagotchi tamagotchi = new YodaGotchi();
    private final Timer imageTimer = new Timer();
    private URL location = getClass().getResource("Resources/YodaUp.jpg");

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

        setLayout(new BorderLayout());

		imageTimer.schedule( new TimerTask() {
			public void run() {
				if (tamagotchi.getLifeState() == true) {
				    changeImage();
				}
			}
		}, 0, 1000 );

        Icon tamagotchiAvatar = new ImageIcon(location);

        final JPanel worldPanel = new JPanel();
        final JLabel imageLabel = new JLabel(tamagotchiAvatar);
        worldPanel.add(imageLabel);
        add(worldPanel, BorderLayout.NORTH);

        buttonMaker("eat", new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                tamagotchi.eat();
            }
        });
        
        buttonMaker("sleep", new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                tamagotchi.eat();
            }
        });

        buttonMaker("fight", new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                tamagotchi.sleep();
            }
        });

        add(panelMaker(), BorderLayout.SOUTH);	
	}

    //Creates a button and adds an actionlistener to it.
    public void buttonMaker(String buttonName, ActionListener actionListener) {
        JButton button = new JButton(buttonName);

        button.addActionListener(actionListener);

        buttonlist.add(button);
    }

    //Adds buttons from buttonlist to a panel.
    public JPanel panelMaker() {
        JPanel buttonPanel = new JPanel(new GridLayout(1,buttonlist.size()));

        for (JButton button : buttonlist) {
            buttonPanel.add(button);
        }

        return buttonPanel;
    }

    // Not yet implemented. Will be taking care of updating stats. DO NOT REMOVE.
    public void stateChanged(ChangeEvent e) {

    }

    public void changeImage() {
        if (location.equals(getClass().getResource("Figurer/YodaUp.JPG"))) {
            location = getClass().getResource("Figurer/YodaDown.JPG");
            repaint();
        }
        else {
            location = getClass().getResource("Figurer/YodaDown.JPG");
            repaint();
        }
    }
}