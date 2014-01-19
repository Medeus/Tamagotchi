public class YodaGotchi extends Tamagotchi {

    public void eat() {
        if (hunger == maxStomach) {
            System.out.println("I'm full!");
        }
        else if (hunger + 5 >= maxStomach) {
            hunger = maxStomach;
        }
        else {
            hunger += 5;
        }
    }

    public void fight() {}

    public boolean getIsSleeping() {
        if (isSleeping = true) {
            System.out.println("Sleeping");
        }
        else {
            System.out.println("I'm awake!");
        }
        return isSleeping;
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

    //used in TamaGUI for the yoda stuff
    public boolean getLifeState() {
        return isAlive;
    }
}