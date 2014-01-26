public class HungryGotchi extends Tamagotchi {
    public void eat() {
        if (hunger >= maxHunger) {
            hunger = maxHunger;
        }
        else {
            hunger += 5;
        }
    }

    public void fight() {}
}