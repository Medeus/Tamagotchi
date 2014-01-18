public class SleepyGotchi extends Tamagotchi {
    private boolean isAlive = true;
    private boolean isSleeping = false;
    private int hunger = 20;
    private int energy = 10;

    public void eat() {
        if (hunger == 20) {
            System.out.println("I'm full!");
        }
        else {
            hunger += 5;
        }
    }

    public void fight() {}

    public int getHunger() {
        return hunger;
    }

    public int getEnergy() {
        return energy;
    }

    public boolean getIsSleeping() {
        if (isSleeping = true) {
            System.out.println("Sleeping");
        }
        else {
            System.out.println("I'm awake!");
        }
        return isSleeping;
    }

    public boolean getIsAlive() {
        return isAlive;
    }

    public void setSleeping(boolean state) {
        if (state = true) {
            isSleeping = true;
        }
        else if (state = false) {
            isSleeping = false;
        }
    }

    public void setAlive(boolean state) {
        if (state = true) {
            isAlive = true;
        }
        else if (state = false) {
            isAlive = false;
        }
    }
}