public abstract class Tamagotchi{
    public void live() {
        if (getHunger() == 0) {
            setAlive(false);
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