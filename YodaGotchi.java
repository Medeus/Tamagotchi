public class YodaGotchi extends Tamagotchi {
    public void eat() {
        if (hunger + 5 >= maxHunger) {
            hunger = maxHunger;
            System.out.println("I'm full!");
        }
        else {
            hunger += 5;
        }
    }

    public void fight() {}
}