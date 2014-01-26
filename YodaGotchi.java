public class YodaGotchi extends Tamagotchi {
    public void eat() {
        if (hunger >= maxHunger) {
            hunger = maxHunger;  
        }
        else {
            hunger += 5;
        }
        change();
        System.out.println(hunger);
    }

    public void fight() {}
}