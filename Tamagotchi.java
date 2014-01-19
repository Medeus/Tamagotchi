import java.util.Date;

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
    private Date date = new Date();

    /*
    /If hunger is 0, alive is set to false
    /If minutes of the hour is 00, 15, 30 or 45 hunger and energy decreases.
    */
    public void live() {
        if (hunger <= 0) {
            lifeState = false;
            System.out.println(this.getClass().getName() + " is now a ghost!");
        }
        if (isSleeping == false) {
            if(date.getMinutes() == 00 || date.getMinutes() == 15 || date.getMinutes() == 30 || date.getMinutes() == 45) {
                hunger -= hungerDecrease;                      
                energy -= energyDecrease;                      
            }
        }
        else if (isSleeping == true && energy >= maxEnergy) {
            isSleeping = false;
            energy = maxEnergy;
        }
        else if(date.getMinutes() == 00 || date.getMinutes() == 15 || date.getMinutes() == 30 || date.getMinutes() == 45) {
            hunger -= hungerDecrease;                      
            energy += energyIncrease;                      
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

    public Boolean getLifeState() {
        return lifeState;
    }

    public abstract void eat();

    public abstract void fight();
}