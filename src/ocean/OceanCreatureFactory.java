package ocean;

public class OceanCreatureFactory {
    public OceanCreature create(int choice) {
        OceanCreature oc;
        if (choice == 1) {
            oc = new PufferFish();
        } else if (choice == 2) {
            oc = new Crab();
        } else {
            oc = new Jellyfish();
        }
        return oc;
    }
}
