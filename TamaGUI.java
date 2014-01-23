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

public class TamaGUI extends JApplet implements ChangeListener{
    private LinkedList<JButton> buttonlist = new LinkedList<JButton>();
    private LinkedList<JPanel> panellist = new LinkedList<JPanel>();
    private LinkedList<JLabel> statsnamelist = new LinkedList<JLabel>();
    private LinkedList<JLabel> statsnumberlist = new LinkedList<JLabel>();
    private static CollectionIcon statsboxes = new CollectionIcon();
    private Tamagotchi tamagotchi = new YodaGotchi();

    private URL resourceLocation = getClass().getResource("Resources/");

    private Image yodaDown;     //skal smides ud i de enkelt Gotchi´s
    private Image yodaUp;
    private Image yoda;

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
        statsNameLabelMaker("Hunger");
        statsNumberLabelMaker(tamagotchi.getHunger());
        statsNameLabelMaker("Energy");
        statsNumberLabelMaker(tamagotchi.getEnergy());

        add(panelStatsMaker(), BorderLayout.NORTH);

        // skal på en eller anden måde også smide ud i de enkelte gotchi's
        try {
            URL imageLocation = new URL(resourceLocation, "YodaDown.jpg");
            yodaDown = ImageIO.read(imageLocation);

            imageLocation = new URL(resourceLocation, "YodaUp.jpg");
            yodaUp = ImageIO.read(imageLocation);

            yoda = yodaUp;
        }
        catch (IOException e) {
        }

        MyGraphics graphics = new MyGraphics(yoda);

        //ImageIcon tamagotchiAvatar = new ImageIcon(location);

        final JPanel worldPanel = new JPanel();
        worldPanel.add(graphics);
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
            
                changeImage();
                repaint();
                
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

    //Adds buttons from buttonlist to a panel.
    public JPanel panelButtonMaker() {
        JPanel panel = new JPanel(new GridLayout(1, buttonlist.size()));

        for (JButton button : buttonlist) {
            panel.add(button);
        }

        return panel;
    }

    public void statsNameLabelMaker(String labelContent) {
        JLabel jlabel = new JLabel(labelContent);
        statsnamelist.add(jlabel);
    }

    public void statsNumberLabelMaker(int labelContent) {
        JLabel jlabel = new JLabel(Integer.toString(labelContent));
        statsnumberlist.add(jlabel);
    }

    public JLabel statsBoxesLabelMaker(CollectionIcon labelContent) {
        return new JLabel(labelContent);
    }

    //Adds stats labels to panels and adds them to the applet.
    public JPanel panelStatsMaker() {
        JPanel mainpanel = new JPanel();

        for (int i=0; i < statsnamelist.size(); i++) {
            JPanel panel = new JPanel();
            panel.add(statsnamelist.get(i));
            panel.add(statsnumberlist.get(i));
            panel.add(statsBoxesLabelMaker(statsboxes));
            mainpanel.add(panel);
        }

        return mainpanel;
    }

    // Not yet implemented. Will be taking care of updating stats. DO NOT REMOVE.
    public void stateChanged(ChangeEvent e) {
        for (JLabel jlabel : statsnumberlist) {

        }
    }

    public void changeImage() {
        if (yoda == yodaUp) {
            yoda = yodaDown;
        }
        else {
            yoda = yodaUp;
        }
        this.repaint();
    }

    public void paint(Graphics g) {
        g.drawImage(yoda, 20, 40, yoda.getHeight(this), yoda.getWidth(this), this);
    }
}
