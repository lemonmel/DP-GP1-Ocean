package ocean;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;

public class CreatureAnimation extends AnimationTimer {
   private Ocean tank;
   private Pane contents;

   public CreatureAnimation(Ocean tank, Pane p) {
      this.tank = tank;
      contents = p;
   }

   public void handle(long now) {
      double h = contents.getLayoutBounds().getHeight();
      double w = contents.getLayoutBounds().getWidth();
      tank.move(h, w);
   }
}
