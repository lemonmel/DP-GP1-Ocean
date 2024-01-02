package ocean;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class FishingNet extends FishingTemplate {

    @Override
    void setFishingObject(double x, double y) {
        fishingEquip = new Circle(x, y, 40);
        fishingEquip.setFill(Color.CORNFLOWERBLUE); // Set the fill color of the fishing rod
        fishingEquip.setScaleY(0.3);

        // Add the fishing rod circle and line to the Pane
        p.getChildren().add(fishingEquip);
    }

    @Override
    boolean checkCaught() {
        ArrayList<OceanCreatureType> types = new ArrayList<>();
        types.add(OceanCreatureType.Jellyfish);
        types.add(OceanCreatureType.Crab);

        List<OceanCreature> oceanCreatures = Ocean.getInstance().getOceanCreatures();
        for (OceanCreature oc : oceanCreatures) {
            if (types.contains(oc.type)) {
                caughtOceanCreature = oc;
                return true;
            }
        }
        return false;
    }

}
