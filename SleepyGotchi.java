public class SleepyGotchi extends Tamagotchi {
    public void eat() {
        if (hunger + hungerIncrease <= maxHunger){
            hunger += hungerIncrease;
        }
        else {
            hunger = maxHunger;
            stomachState = true;  
        }
        change();
    }

    public void fight() {}
}