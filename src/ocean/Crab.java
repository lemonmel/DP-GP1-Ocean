package ocean;

import java.util.List;
import java.util.Map;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Crab extends OceanCreature {
   private int walking = 0;
   private int stay = 0;

   private final static Map<Color, List<String>> colorImagePathMap = Map.of(
         Color.RED, List.of("images/crab.gif", "images/crab.gif"),
         Color.ORANGE, List.of("images/crab-orange.gif", "images/crab-orange.gif"),
         Color.GREEN, List.of("images/crab-green.gif", "images/crab-green.gif"),
         Color.BLUE, List.of("images/crab-blue.gif", "images/crab-blue.gif"),
         Color.PURPLE, List.of("images/crab-purple.gif", "images/crab-purple.gif"));

   public Crab() {
      xspeed = 0.8;
      fishSize = 100;
      type = OceanCreatureType.Crab;
      leftimage = new Image("images/crab.gif", fishSize, fishSize, true, true);
      rightimage = leftimage;
      view.setY(Ocean.getOceanPane().getLayoutBounds().getHeight() - leftimage.getHeight() * 1.2);
   }

   @Override
   public void move(double tankheight, double tankwidth) {
      Image image = getImage();
      view.setImage(image);
      if (walking < 500) {
         double x = moveXY(view.getX(), xspeed, xDirectionChangePct);
         if (legalXMove(x, image.getWidth(), tankwidth)) {
            walking += 1;
            view.setX(x);
         } else {
            changeXdirection();
         }
      } else {
         stay += 1;
         if (stay >= 100) { // if rest enough then continue walking
            walking = 0;
            stay = 0;
         }
      }
   }

   @Override
   public void changeColor(Color color) {
      addCurrentImagesToHistory();
      var path = colorImagePathMap.get(color);
      setImageByPath(path.get(0), path.get(1));
   }
}
