package ocean;

public class OceanCreatureFactory {
    public OceanCreature create(int choice) {
        OceanCreature oc;
        if (choice == 1) {
            oc = new Mackeral();
        } else if (choice == 2) {
            oc = new Crab();
        } else if (choice == 3) {
            oc = new Jellyfish();
        } else if (choice == 4) {
            oc = new Anchovy();
        } else if (choice == 5) {
            oc = new Turtle();
        } else {
            oc = new Shark();
        }
        return oc;
    }
}
