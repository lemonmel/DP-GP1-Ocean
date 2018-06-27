package fishtank;

import java.util.*;
import javafx.scene.layout.Pane;

public class FishTank {
   List<Fish> fishes = new ArrayList<>();
   Pane p;
   boolean suspended = false;

   public FishTank(Pane p) {
      this.p = p;
   }
   public void addFish(int fishtype) {
      Fish f = null;
      if (fishtype == 0)
         f = new AngelFish();
      else if (fishtype == 1)
         f = new MeanFish();
      else if (fishtype == 2)
         f = new Octopus();

      fishes.add(f);
      p.getChildren().add(f.getView());
   }

   public void startAnimation() {
      suspended = false;
   }

   public void stopAnimation() {
      suspended = true;
   }

   public void move(double height, double width) {
      if (!suspended) {
         for (Fish f : fishes) 
            f.move(height, width);
      }
   }
}
