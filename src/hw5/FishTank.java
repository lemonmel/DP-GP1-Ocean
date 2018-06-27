/*
 * file: FishTank.java
 * author: garret patten
 * date: 03/11/18
 */

package hw5;

import java.util.*;

import fishtank.Fish;
import javafx.scene.layout.Pane;

public class FishTank {
   FishFactory[] fishFactories = 
		   {new AngelFactory(),
			new MeanFactory(),
			new OctopusFactory(),
			new DolphinFactory()};
   List<hw5.Fish> fishes = new ArrayList<>();
   Pane p;
   boolean suspended = false;

   public FishTank(Pane p) {
      this.p = p;
   }
   public void addFish(int fishtype) {
      hw5.Fish f = fishFactories[fishtype].create();
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
         for (hw5.Fish f : fishes) 
            f.move(height, width);
      }
   }
}
