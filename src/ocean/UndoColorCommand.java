package ocean;

import javafx.event.ActionEvent;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class UndoColorCommand implements Command{

    private List<OceanCreature> oceanCreatures;
    private ActionEvent event;

    public UndoColorCommand(List<OceanCreature> oceanCreatures, ActionEvent event) {
        this.oceanCreatures = oceanCreatures;
        this.event = event;
    }

    @Override
    public void execute() {
        var selectedColorMenuItem = (MenuItem) this.event.getSource();
        var oceanCreatureMenu = selectedColorMenuItem.getParentMenu();
        List<OceanCreature> oceanCreatures = switch (oceanCreatureMenu.getText()) {
            case "Anchovy" -> getManyOceanCreaturesOfType(OceanCreatureType.Anchovy, this.oceanCreatures);
            case "Crab" -> getManyOceanCreaturesOfType(OceanCreatureType.Crab, this.oceanCreatures);
            case "Mackeral" -> getManyOceanCreaturesOfType(OceanCreatureType.Fish, this.oceanCreatures);
            case "Jellyfish" -> getManyOceanCreaturesOfType(OceanCreatureType.JellyFish, this.oceanCreatures);
            case "Shark" -> getManyOceanCreaturesOfType(OceanCreatureType.Shark, this.oceanCreatures);
            case "Turtle" -> getManyOceanCreaturesOfType(OceanCreatureType.Turtle, this.oceanCreatures);
            default -> throw new IllegalStateException("Unexpected value: " + oceanCreatureMenu.getText());
        };

        for (OceanCreature oceanCreature : oceanCreatures) {
            oceanCreature.undoColor();
        }
    }

    private static List<OceanCreature> getManyOceanCreaturesOfType(OceanCreatureType type, List<OceanCreature> oceanCreatures) {
        var fishes = new ArrayList<OceanCreature>();
        for (OceanCreature oceanCreature : oceanCreatures) {
            if (oceanCreature.type == type) {
                fishes.add(oceanCreature);
            }
        }

        return fishes;
    }
}
