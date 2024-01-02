package ocean;

import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class OceanFacade {
    OceanCreatureFactory factory;
    Ocean ocean;
    Pane pane;
    BackgroundStrategy backgroundStrategy;
    TerrainStrategy terrainStrategy;

    public OceanFacade() {
        factory = new OceanCreatureFactory();
        ocean = Ocean.getInstance();
        pane = Ocean.getOceanPane();
    }

    public OceanCreature addOceanCreature(int choice) {
        OceanCreature newCreature = factory.create(choice);
        ocean.addOceanCreature(newCreature);

        return newCreature;
    }

    public Pane getOceanPane() {
        return pane;
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
