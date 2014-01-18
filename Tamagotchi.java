import java.util.Date;

public abstract class Tamagotchi{
    protected boolean isAlive = true;
    protected boolean isSleeping = false;
    protected int hunger = 10;
    protected int energy = 10;
    // What the Tamagochis stommach can hold
    protected int maxStomach = 20;         
    // the Tamagochis maximum energi
    protected int maxEnergy = 40;         
    private Date date = new Date();

    /*
    /If hunger is 0, alive is set to false
    /If minutes of the hour is 00, 15,30 or 45 hunger and energy decreases.
    */
    public void live() {
        if (getHunger() == 0) {
            setAlive(false);
            System.out.println(this.getClass().getName() + " is now a ghost!");
        }
        if(date.getMinutes() == 00 || date.getMinutes() == 15 || date.getMinutes() == 30 || date.getMinutes() == 45) {
            hunger--;                      // decreases hunger
            energy--;                      // decreases Energy
        }
    }

    public void sleep() {
        if (getEnergy() == 20) {
            System.out.println("I'm not tired!");
        }
        else {
          setSleeping(true);
        }
    }

    public abstract void eat();

    public abstract void fight();

    public abstract int getHunger();

    public abstract int getEnergy();

    public abstract boolean getIsSleeping();

    public abstract boolean getIsAlive();

    public abstract void setSleeping(boolean state);

    public abstract void setAlive(boolean state);
}