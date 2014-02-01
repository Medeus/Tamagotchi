import java.util.Date;
import javax.swing.Timer;
import java.awt.event.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public abstract class Tamagotchi{
    protected LifeState lifeState = LifeState.ALIVE;
    protected SleepState sleepState = SleepState.AWAKE;
    protected boolean stomachState;   

    protected int hunger = 10;
    protected int energy = 10;
    protected int hungerDecrease = 4;
    protected int energyDecrease = 4;
    protected int hungerIncrease = 2;
    protected int energyIncrease = 1;
    protected int maxHunger = 20;
    protected int maxEnergy = 20;  

    protected Date now = new Date();
    protected Date tempDate;
    protected ChangeListener listener;

    protected BufferedImage firstPosition;
    protected BufferedImage secondPosition;
    protected BufferedImage asleep;
    protected BufferedImage dead;
    protected BufferedImage currentAvatar;
    protected Timer avatarSwitcher;

    //@Para Images should be named after the formular: class name + state + .jpg . Where state also includes "Up" and "Down".
    public Tamagotchi(){
        try {
            firstPosition = ImageIO.read(new File("Resources/Images/" + getClass().getName() + "Up.jpg"));
            secondPosition = ImageIO.read(new File("Resources/Images/" +  getClass().getName() + "Down.jpg"));
            asleep = ImageIO.read(new File("Resources/Images/" + getClass().getName() + "Sleep.jpg"));
            dead = ImageIO.read(new File("Resources/Images/" + getClass().getName() + "Dead.jpg"));
        } 
        catch (IOException e) {
            System.out.println("Avatars not found");
        }

        currentAvatar = firstPosition;

        avatarSwitcher = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (currentAvatar.getSource() != firstPosition.getSource()) {
                    currentAvatar = firstPosition;
                }
                else if (currentAvatar.getSource() != secondPosition.getSource()) {
                    currentAvatar = secondPosition;
                } 
            }
        });

        avatarSwitcher.start();
    }    

    public ImageIcon getAvatar(){
        return new ImageIcon(currentAvatar);
    }

    public String getLifeState() {
        if (lifeState == LifeState.DEAD) {
            return "Dead";
        }
        else {
            return "Alive";
        }
    }

    public String getSleepState() {
        if (sleepState == SleepState.ASLEEP) {
            return "Sleeping";
        }
        else {
            return "Awake";
        }
    }

    public boolean getStomachState() {
        return stomachState;
    }

    public int getHunger() {
        return hunger;
    }

    public int getEnergy() {
        return energy;
    }

    public void addChangeListener(ChangeListener listener) {
        this.listener = listener;
    }

    protected void change() {
        listener.stateChanged(new ChangeEvent(this));
    }

    public void start() {
        Timer dateUpdater = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                tempDate = now;
                now = new Date();
            }
        });

        dateUpdater.setInitialDelay(0);
        dateUpdater.start();

        final Timer tamagotchiRunner = new Timer(1000, null);

        tamagotchiRunner.setInitialDelay(0);
        tamagotchiRunner.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (lifeState == LifeState.DEAD) {
                    tamagotchiRunner.stop();
                }
                else if(now.getMinutes() - tempDate.getMinutes() == 1) {
                    live();
                }
            }
        });
        
        tamagotchiRunner.start();
    }

    /*
    /If hunger is 0, alive is set to false
    /If minutes of the hour is 00, 15, 30 or 45 hunger and energy decreases.
    */
    public void live() {
        if (hunger <= 0) {
            lifeState = LifeState.DEAD;
            avatarSwitcher.stop();
            currentAvatar = dead;
            change();
            return;
        }
        else if (energy <= 0) {
            sleepState = SleepState.ASLEEP;
            avatarSwitcher.stop();
            currentAvatar = asleep;
            change();

        }

        if (now.getMinutes() == 00 || now.getMinutes() == 05 || now.getMinutes() == 10 || now.getMinutes() == 15 ||
            now.getMinutes() == 20 || now.getMinutes() == 25 || now.getMinutes() == 30 || now.getMinutes() == 35 ||
            now.getMinutes() == 40 || now.getMinutes() == 45 || now.getMinutes() == 50 || now.getMinutes() == 55) {
            if (sleepState == SleepState.AWAKE){
                hunger -= hungerDecrease;                      
                energy -= energyDecrease;
                stomachState = false;
                change();
            }
            else if (sleepState == SleepState.ASLEEP && energy >= maxEnergy) {
                sleepState = SleepState.AWAKE;
                avatarSwitcher.start();
                energy = maxEnergy;
                change();
            }
            else if (sleepState == SleepState.ASLEEP) {
                hunger -= hungerDecrease; 
                energy += energyIncrease;
                stomachState = false;
                change();
            }
        }
    }

    public void sleep() {
        if (energy >= maxEnergy) {
            energy = maxEnergy;
            wake();
        }
        else {
            sleepState = SleepState.ASLEEP;
            avatarSwitcher.stop();
            currentAvatar = asleep;
            change();
        }
    }

    public void wake() {
        sleepState = SleepState.AWAKE;
        avatarSwitcher.start();
        change();
    }

    // Needs to call "change()" in it's implementation.
    public abstract void eat();

    // Needs to call "change()" in it's implementation.
    public abstract void fight();
}