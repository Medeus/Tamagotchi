public class YodaGotchi extends Tamagotchi {
    public void eat() {
        hunger += 5;
        if (hunger == maxHunger) {
            System.out.println("I'm full!");
        }
        else if (hunger + 5 >= maxHunger) {
            hunger = maxHunger;
            System.out.println("I'm full!");
        }
    }

    public void fight() {}
}