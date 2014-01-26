public class YodaGotchi extends Tamagotchi {
    public void eat() {
        if (hunger >= maxHunger) {
            hunger = maxHunger;  
        }
        else {
            hunger += 5;
        }
        change();
    }

    public void fight() {}
}