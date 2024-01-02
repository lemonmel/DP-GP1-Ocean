package ocean;

import javafx.event.ActionEvent;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class ChangeColorCommand implements Command {

    private List<OceanCreature> oceanCreatures;
    private ActionEvent event;
    private Color color;

    public ChangeColorCommand(List<OceanCreature> oceanCreatures, ActionEvent event, Color color) {
        this.oceanCreatures = oceanCreatures;
        this.event = event;
        this.color = color;
    }

    @Override
    public void execute() {
        var selectedColorMenuItem = (MenuItem) this.event.getSource();
        var oceanCreatureMenu = selectedColorMenuItem.getParentMenu().getParentMenu();
        List<OceanCreature> oceanCreatures = switch (oceanCreatureMenu.getText()) {
            case "Anchovy" -> getManyOceanCreaturesOfType(OceanCreatureType.Anchovy, this.oceanCreatures);
            case "Crab" -> getManyOceanCreaturesOfType(OceanCreatureType.Crab, this.oceanCreatures);
            case "Mackeral" -> getManyOceanCreaturesOfType(OceanCreatureType.Mackerel, this.oceanCreatures);
            case "Jellyfish" -> getManyOceanCreaturesOfType(OceanCreatureType.Jellyfish, this.oceanCreatures);
            case "Shark" -> getManyOceanCreaturesOfType(OceanCreatureType.Shark, this.oceanCreatures);
            case "Turtle" -> getManyOceanCreaturesOfType(OceanCreatureType.Turtle, this.oceanCreatures);
            default -> throw new IllegalStateException("Unexpected value: " + oceanCreatureMenu.getText());
        };

        for (OceanCreature oceanCreature : oceanCreatures) {
            oceanCreature.changeColor(color);
        }
    }

    private static List<OceanCreature> getManyOceanCreaturesOfType(OceanCreatureType type,
            List<OceanCreature> oceanCreatures) {
        var fishes = new ArrayList<OceanCreature>();
        for (OceanCreature oceanCreature : oceanCreatures) {
            if (oceanCreature.type == type) {
                fishes.add(oceanCreature);
            }
        }

        return fishes;
    }
}
