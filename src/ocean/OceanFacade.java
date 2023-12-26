package ocean;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class OceanFacade {
    OceanCreatureFactory factory;
    Ocean ocean;
    BackgroundStrategy backgroundStrategy;
    TerrainStrategy terrainStrategy;

    public OceanFacade() {
        factory = new OceanCreatureFactory();
        ocean = Ocean.getInstance();
    }

    public void addOceanCreature(int choice) {
        OceanCreature newCreature = factory.create(choice);
        ocean.addOceanCreature(newCreature);
    }

    public Pane getOceanPane() {
        return ocean.getOceanPane();
    }

    public void setDaytimeStrategy(Pane oceanPane, Image backgroundImage) {
        backgroundStrategy = new Day(oceanPane, backgroundImage);
        ocean.setBackgroundStrategy(backgroundStrategy);
        ocean.performBackground();
    }

    public void setNighttimeStrategy(Pane oceanPane, Image backgroundImage) {
        backgroundStrategy = new Night(oceanPane, backgroundImage);
        ocean.setBackgroundStrategy(backgroundStrategy);
        ocean.performBackground();
    }

    public void setSand(Pane oceanPane) {
        terrainStrategy = new Sand(oceanPane);
        ocean.setTerrainStrategy(terrainStrategy);
        ocean.performFloor();
    }

    public void setGrass(Pane oceanPane) {
        terrainStrategy = new Grass(oceanPane);
        ocean.setTerrainStrategy(terrainStrategy);
        ocean.performFloor();
    }

    public void setRock(Pane oceanPane) {
        terrainStrategy = new Rock(oceanPane);
        ocean.setTerrainStrategy(terrainStrategy);
        ocean.performFloor();
    }
}
