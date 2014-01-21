import java.net.URL;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.Timer;
import java.io.*;
//import java.util.Timer;
import java.awt.*;
import java.awt.event.*;
import java.awt.Image;
import java.util.*;

public class TamaGUI extends JApplet implements ChangeListener{
    private LinkedList<JButton> buttonlist = new LinkedList<JButton>();
    private LinkedList<JLabel> statslist = new LinkedList<JLabel>();
    private LinkedList<HungerEnergyRect> barList = new LinkedList<HungerEnergyRect>();
    private static CollectionIcon ci = new CollectionIcon();

    private Tamagotchi tamagotchi = new YodaGotchi();
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
        statsMaker("Hunger: 20", new JLabel(ci));
        statsMaker("Energy: 20", new JLabel(ci));
        

        add(panelStatsMaker(), BorderLayout.NORTH);

        ImageIcon tamagotchiAvatar = new ImageIcon(location);

        final JPanel worldPanel = new JPanel();
        final JLabel imageLabel = new JLabel(tamagotchiAvatar);
        worldPanel.add(imageLabel);
        add(worldPanel, BorderLayout.CENTER);
        buttonMaker("eat", new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                tamagotchi.eat();
            }
        });
        
        buttonMaker("sleep", new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                tamagotchi.sleep();
            }
        });

        buttonMaker("fight", new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                tamagotchi.fight();
            }
        });

        add(panelButtonMaker(), BorderLayout.SOUTH);

        Timer imageUpdater = new Timer(500, new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (tamagotchi.getLifeState() == true) {
                    changeImage();
                    worldPanel.repaint();
                }
                
            }
        });

        imageUpdater.start();      
    }

    //Creates a button and adds an actionlistener to it.
    public void buttonMaker(String buttonName, ActionListener actionListener) {
        JButton button = new JButton(buttonName);

        button.addActionListener(actionListener);

        buttonlist.add(button);
    }

    /* Adds buttons from buttonlist to a panel.
    public JPanel panelMaker(T t, ArrayList<T> list) {
        JPanel panel = new JPanel(new GridLayout(1, list.size()));

        for (T t : list) {
            list.add(t);
        }

        return panel;
    }
    */

    //Adds buttons from buttonlist to a panel.
    public JPanel panelButtonMaker() {
        JPanel panel = new JPanel(new GridLayout(1, buttonlist.size()));

        for (JButton button : buttonlist) {
            panel.add(button);
        }

        return panel;
    }

    //Adds buttons from buttonlist to a panel.
    public JPanel panelStatsMaker() {
        JPanel panel = new JPanel(new GridLayout(1, statslist.size()));

        for (JLabel jlabel : statslist) {
            panel.add(jlabel);
        }

        return panel;
    }

    public void statsMaker(String statName, JLabel k) {
        JLabel jlabel = new JLabel(statName);

        statslist.add(jlabel);
        statslist.add(k);
    }

    // Not yet implemented. Will be taking care of updating stats. DO NOT REMOVE.
    public void stateChanged(ChangeEvent e) {

    }

    public void changeImage() {
        if (location.equals(getClass().getResource("Resources/YodaUp.jpg"))) {
            location = getClass().getResource("Resources/YodaDown.jpg");
        }
    }
}