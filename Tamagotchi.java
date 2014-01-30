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
    protected boolean lifeState = true;
    protected boolean isSleeping = false;
    protected int hunger = 10;
    protected int energy = 10;
    protected int hungerDecrease = 2;
    protected int energyDecrease = 2;
    protected int hungerIncrease = 2;
    protected int energyIncrease = 2;
    protected int maxHunger = 20;         
    protected int maxEnergy = 40;         
    protected Date now = new Date();
    protected Date tempDate;
    protected ChangeListener listener;
    protected BufferedImage firstPosition;
    protected BufferedImage secondPosition;
    protected BufferedImage asleep;
    protected BufferedImage dead;
    protected BufferedImage currentAvatar;
    protected Timer avatarSwitcher;

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

    public Boolean getLifeState() {
        return lifeState;
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
                if (lifeState == false) {
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
            lifeState = false;
            avatarSwitcher.stop();
            currentAvatar = dead;
            return;
        }
        else if (energy <= 0) {
            isSleeping = true;
            avatarSwitcher.stop();
            currentAvatar = asleep;

        }

        if (now.getMinutes() == 00 || now.getMinutes() == 15 || now.getMinutes() == 30 || now.getMinutes() == 45) {
            if (isSleeping == false){
                hunger -= hungerDecrease;                      
                energy -= energyDecrease;
                change();
            }
            else if (isSleeping == true && energy >= maxEnergy) {
                isSleeping = false;
                avatarSwitcher.start();
                energy = maxEnergy;
                change();
            }
            else if (isSleeping == true) {
                hunger -= hungerDecrease; 
                energy += energyIncrease;
                change();
            }
        }
    }

    public void sleep() {
        if (energy >= maxEnergy) {
            energy = maxEnergy;
        }
        else {
            isSleeping = true;
            avatarSwitcher.stop();
            currentAvatar = asleep;
        }
    }

    // Needs to call "change()" in it's implementation.
    public abstract void eat();

    // Needs to call "change()" in it's implementation.
    public abstract void fight();
}