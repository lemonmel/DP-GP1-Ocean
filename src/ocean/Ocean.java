package ocean;

import java.util.*;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Ocean {
   private static Ocean uniqueInstance = new Ocean();
   List<OceanCreature> oceanCreatures = new ArrayList<>();
   Pane pane = new Pane();
   BackgroundStrategy backgroundStrategy;
   TerrainStrategy terrainStrategy;

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

   public void performBackground() {
      backgroundStrategy.applyBackground();
   }

   public void setBackgroundStrategy(BackgroundStrategy bs) {
      backgroundStrategy = bs;
   }

   public void performFloor() {
      terrainStrategy.applyFloor(getCurrentBackgroundImage());
   }

   public void setTerrainStrategy(TerrainStrategy ts) {
      terrainStrategy = ts;
   }

   public ImageView getCurrentBackgroundImage() {
      return backgroundStrategy.getBackgroundImage();
   }
}
