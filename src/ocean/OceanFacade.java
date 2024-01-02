package ocean;

import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class OceanFacade {
    Ocean ocean;
    Pane pane;
    OceanCreatureFactory factory;
    BackgroundStrategy backgroundStrategy;
    TerrainStrategy terrainStrategy;

    public OceanFacade() {
        factory = new OceanCreatureFactory();
        ocean = Ocean.getInstance();
        pane = Ocean.getOceanPane();
    }

    public Pane getOceanPane() {
        return pane;
    }

    public OceanCreature addOceanCreature(int choice) {
        OceanCreature newCreature = factory.create(choice);
        ocean.addOceanCreature(newCreature);

        return newCreature;
    }

    public void setDaytimeStrategy(Image backgroundImage) {
        backgroundStrategy = new Day(pane, backgroundImage);
        ocean.setBackgroundStrategy(backgroundStrategy);
        ocean.performBackground();
    }

    public void setNighttimeStrategy(Image backgroundImage) {
        backgroundStrategy = new Night(pane, backgroundImage);
        ocean.setBackgroundStrategy(backgroundStrategy);
        ocean.performBackground();
    }

    public void setSand() {
        terrainStrategy = new Sand(pane);
        ocean.setTerrainStrategy(terrainStrategy);
        ocean.performFloor();
    }

    public void setGrass() {
        terrainStrategy = new Grass(pane);
        ocean.setTerrainStrategy(terrainStrategy);
        ocean.performFloor();
    }

    public void setRock() {
        terrainStrategy = new Rock(pane);
        ocean.setTerrainStrategy(terrainStrategy);
        ocean.performFloor();
    }

    public void addOceanCreatureColor(ActionEvent e, Color color) {
        Command changeColorCommand = new ChangeColorCommand(ocean.getOceanCreatures(), e, color);
        changeColorCommand.execute();
    }

    public void undoOceanCreatureColor(ActionEvent e) {
        Command changeColorCommand = new UndoColorCommand(ocean.getOceanCreatures(), e);
        changeColorCommand.execute();
    }

    public void fishByRod() {
        FishingTemplate fishing = new FishingRod();
        fishing.performFishing();
    }

    public void fishByNet() {
        FishingTemplate fishing = new FishingNet();
        fishing.performFishing();
    }
}
