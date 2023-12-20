package ocean;

import java.util.*;
import javafx.scene.layout.Pane;

public class Ocean {
   private static Ocean uniqueInstance = new Ocean();
   List<OceanCreature> oceanCreatures = new ArrayList<>();
   Pane pane = new Pane();

   private Ocean() {
   }

   public static Ocean getInstance() {
      return uniqueInstance;
   }

   public Pane getOceanPane() {
      return pane;
   }

   public void addOceanCreature(OceanCreature oc) {
      oceanCreatures.add(oc);
      pane.getChildren().add(oc.getView());
   }

   public void move(double height, double width) {
      for (OceanCreature oc : oceanCreatures) {
         oc.move(height, width);
      }
   }
}
