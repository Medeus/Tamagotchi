public class YodaGotchi extends Tamagotchi {
    public void eat() {
        if (hunger >= maxHunger) {
            hunger = maxHunger;
            isFull = true;  
        }
        else {
            hunger += 5;
            isFull = false;
        }
        change();
    }

    public void fight() {}
}