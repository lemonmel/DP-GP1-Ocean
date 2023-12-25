package ocean;

import javafx.scene.layout.Pane;

public class OceanFacade {
    OceanCreatureFactory factory;
    Ocean ocean;

    public OceanFacade() {
        factory = new OceanCreatureFactory();
        ocean = Ocean.getInstance();
    }

    public OceanCreature addOceanCreature(int choice) {
        OceanCreature newCreature = factory.create(choice);
        ocean.addOceanCreature(newCreature);

        return newCreature;
    }

    public Pane getOceanPane() {
        return ocean.getOceanPane();
    }

}
