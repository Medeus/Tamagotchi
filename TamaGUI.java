import java.net.URL;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.Timer;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Image;
import java.util.*;

public class TamaGUI extends JApplet implements ChangeListener{
    private LinkedList<JButton> buttonlist = new LinkedList<JButton>();
    private LinkedList<JPanel> panellist = new LinkedList<JPanel>();
    private LinkedList<JLabel> statsnamelist = new LinkedList<JLabel>();
    private LinkedList<JLabel> statsnumberlist = new LinkedList<JLabel>();
    private LinkedList<JLabel> statsBarList = new LinkedList<JLabel>();
    private Tamagotchi tamagotchi = new YodaGotchi();

    private URL location = getClass().getResource("Resources/Images/YodaUp.jpg");

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

        tamagotchi.addChangeListener(this);

        setLayout(new BorderLayout());
        statsNameLabelMaker("Hunger");
        statsNumberLabelMaker(tamagotchi.getHunger());
        statsBoxesLabelMaker();
        statsNameLabelMaker("Energy");
        statsNumberLabelMaker(tamagotchi.getEnergy());
        statsBoxesLabelMaker();

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
        
        // Not yet implemented. Will take care of the life of the tamagotchi.
        
        Timer live = new Timer(500, new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                tamagotchi.live();
                
            }
        });
        

        /*
        Timer imageUpdater = new Timer(500, new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (tamagotchi.getLifeState() == true) {
                    changeImage();
                    worldPanel.repaint();
                }
                
            }
        });
        
        imageUpdater.start();
        */      
    }

    //Creates a button and adds an actionlistener to it.
    private void buttonMaker(String buttonName, ActionListener actionListener) {
        JButton button = new JButton(buttonName);

        button.addActionListener(actionListener);

        buttonlist.add(button);
    }

    //Adds buttons from buttonlist to a panel.
    private JPanel panelButtonMaker() {
        JPanel panel = new JPanel(new GridLayout(1, buttonlist.size()));

        for (JButton button : buttonlist) {
            panel.add(button);
        }

        return panel;
    }

    private void statsNameLabelMaker(String labelContent) {
        JLabel jlabel = new JLabel(labelContent);
        statsnamelist.add(jlabel);
    }

    private void statsNumberLabelMaker(int labelContent) {
        JLabel jlabel = new JLabel(Integer.toString(labelContent));
        statsnumberlist.add(jlabel);
    }

    private void statsBoxesLabelMaker() {
        JLabel statsBar = new JLabel(new CollectionIcon());

        statsBarList.add(statsBar);
    }

    //Adds stats labels to panels and adds them to the applet.
    private JPanel panelStatsMaker() {
        JPanel mainpanel = new JPanel();

        for (int i=0; i < statsnamelist.size(); i++) {
            JPanel panel = new JPanel();
            panel.add(statsnamelist.get(i));
            panel.add(statsnumberlist.get(i));
            panel.add(statsBarList.get(i));
            mainpanel.add(panel);
        }

        return mainpanel;
    }

    public void stateChanged(ChangeEvent e) {
        statsnumberlist.get(0).setText(Integer.toString(tamagotchi.getHunger()));
        statsnumberlist.get(1).setText(Integer.toString(tamagotchi.getEnergy()));
    }

    public void changeImage() {
        if (location.equals(getClass().getResource("Resources/Figurer/YodaUp.jpg"))) {
            location = getClass().getResource("Resources/Figurer/YodaDown.jpg");
        }
    }
}