public class HungryGotchi extends Tamagotchi {

    public void eat() {
        if (hunger == 20) {
            System.out.println("I'm full!");
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
}