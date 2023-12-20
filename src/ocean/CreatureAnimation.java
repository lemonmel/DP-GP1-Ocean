package ocean;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;

public class CreatureAnimation extends AnimationTimer {
   private Ocean ocean;
   private Pane contents;

   public CreatureAnimation(Ocean ocean, Pane p) {
      this.ocean = ocean;
      contents = p;
   }

   public void handle(long now) {
      double h = contents.getLayoutBounds().getHeight();
      double w = contents.getLayoutBounds().getWidth();
      ocean.move(h, w);
   }
}
