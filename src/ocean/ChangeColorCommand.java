package ocean;

import javafx.event.ActionEvent;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class ChangeColorCommand implements Command{

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
        switch (oceanCreatureMenu.getText()) {
            case "Crab" -> {
                var crabs = getManyOceanCreaturesOfType(OceanCreatureType.Crab, this.oceanCreatures);
                for (var newCrab : crabs) {
                    ((Crab) newCrab).ChangeColor(this.color);
                }
            }
            default -> throw new IllegalStateException("Unexpected value: " + oceanCreatureMenu.getText());
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
