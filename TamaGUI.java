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
    private JLabel lifeState;
    private JLabel sleepState;
    private Tamagotchi tamagotchi = new YodaGotchi();

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
        tamagotchi.start();

        setLayout(new BorderLayout());
        statsNameLabelMaker("Hunger");
        statsNumberLabelMaker(tamagotchi.getHunger());
        statsBoxesLabelMaker();
        statsNameLabelMaker("Energy");
        statsNumberLabelMaker(tamagotchi.getEnergy());
        statsBoxesLabelMaker();

        JLabel stateName1 = new JLabel("Life State: ");
        lifeState = new JLabel(tamagotchi.getLifeState());
        JPanel statePanel1 = new JPanel();
        statePanel1.add(stateName1);
		statePanel1.add(lifeState);

        JLabel stateName2 = new JLabel("Life State: ");
        sleepState = new JLabel(tamagotchi.getSleepState());
        JPanel statePanel2 = new JPanel();
        statePanel2.add(stateName2);
		statePanel2.add(sleepState);


        final JPanel container = new JPanel(new GridLayout(2,0));
        panelStatsMaker(container);
        container.add(statePanel1);
        container.add(statePanel2);

        add(container, BorderLayout.NORTH);

        final JTextArea textArea = new JTextArea(8, 16);
        JScrollPane textScrollPane = new JScrollPane(textArea);
        textArea.setEditable(false);
        textScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);


        final JPanel worldPanel = new JPanel();
        final JLabel imageLabel = new JLabel(tamagotchi.getAvatar());
        worldPanel.add(imageLabel);
        add(worldPanel, BorderLayout.CENTER);

        Timer imageUpdater = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                imageLabel.setIcon(tamagotchi.getAvatar());
            }
        });

        imageUpdater.start();
        
        buttonMaker("eat", new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                tamagotchi.eat();
                if (tamagotchi.getStomachState() == true) {
                    String currentText = textArea.getText();
                    textArea.setText(currentText + "\n" + "I'm full!");
                }
            }
        });
        
        buttonMaker("sleep", new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (tamagotchi.getSleepState().equals("Awake")){
                	tamagotchi.sleep();
                	buttonlist.get(1).setText("wake");
                }
                else if (tamagotchi.getSleepState().equals("Sleeping")){
                	tamagotchi.wake();
                	buttonlist.get(1).setText("sleep");
                }
            }
        });

        buttonMaker("fight", new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                tamagotchi.fight();
            }
        });

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));
        southPanel.add(textScrollPane);
        southPanel.add(panelButtonMaker());


        add(southPanel, BorderLayout.SOUTH);
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
    private void panelStatsMaker(JPanel south) {
        for (int i=0; i < statsnamelist.size(); i++) {
            JPanel panel = new JPanel();
            panel.add(statsnamelist.get(i));
            panel.add(statsnumberlist.get(i));
            panel.add(statsBarList.get(i));
            south.add(panel);
        }
    }

    public void stateChanged(ChangeEvent e) {
        statsnumberlist.get(0).setText(Integer.toString(tamagotchi.getHunger()));
        statsnumberlist.get(1).setText(Integer.toString(tamagotchi.getEnergy()));
        lifeState.setText(tamagotchi.getLifeState());
        sleepState.setText(tamagotchi.getSleepState());
    }
}