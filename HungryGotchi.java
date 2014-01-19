public class HungryGotchi extends Tamagotchi {
    public void eat() {
        if (hunger == maxHunger) {
            System.out.println("I'm full!");
        }
        else if (hunger + 5 >= maxHunger) {
            hunger = maxHunger;
        }
        else {
            hunger += 5;
        }
    }

    public void fight() {}
}