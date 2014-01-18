public class Driver {
    public static void main(String[] args) {
        Tamagotchi hungry = new HungryGotchi();
        Tamagotchi sleepy = new SleepyGotchi();

        sleepy.sleep(); 

        sleepy.getIsSleeping();
        hungry.getIsSleeping();
    }
}