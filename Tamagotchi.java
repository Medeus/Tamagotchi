import java.util.Date;
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
    protected Date date = new Date();
    protected ChangeListener listener;

    public void addChangeListener(ChangeListener listener) {
        this.listener = listener;
    }

    private void change() {
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
        }
        if (isSleeping == false) {
            if(date.getMinutes() == 00 || date.getMinutes() == 15 || date.getMinutes() == 30 || date.getMinutes() == 45) {
                hunger -= hungerDecrease;                      
                energy -= energyDecrease;
                change();                      
            }
        }
        else if (isSleeping == true && energy >= maxEnergy) {
            isSleeping = false;
            energy = maxEnergy;
            change();
        }
        else if(date.getMinutes() == 00 || date.getMinutes() == 15 || date.getMinutes() == 30 || date.getMinutes() == 45) {
            hunger -= hungerDecrease;                      
            energy += energyIncrease;
            change();                      
        }
    }

    public void sleep() {
        if (energy >= maxEnergy) {
            System.out.println("I'm not tired!");
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