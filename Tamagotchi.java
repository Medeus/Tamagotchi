import java.util.Date;
import javax.swing.Timer;
import java.awt.event.*;
import javax.swing.event.*;

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

    public void addChangeListener(ChangeListener listener) {
        this.listener = listener;
    }

    protected void change() {
        listener.stateChanged(new ChangeEvent(this));
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

    /*
    /If hunger is 0, alive is set to false
    /If minutes of the hour is 00, 15, 30 or 45 hunger and energy decreases.
    */
    public void live() {
        if (hunger <= 0) {
            lifeState = false;
            return;
        }
        else if (energy <= 0) {
            isSleeping = true;
        }

        if (now.getMinutes() == 00 || now.getMinutes() == 15 || now.getMinutes() == 27 || now.getMinutes() == 28) {
            if (isSleeping == false){
                hunger -= hungerDecrease;                      
                energy -= energyDecrease;
                change();
            }
            else if (isSleeping == true && energy >= maxEnergy) {
                isSleeping = false;
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
        }
    }

    // Needs to call "change()" in it's implementation.
    public abstract void eat();

    // Needs to call "change()" in it's implementation.
    public abstract void fight();
}