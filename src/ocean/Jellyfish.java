package ocean;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.Map;

public class Jellyfish extends OceanCreature {
   private int floating = 0;
   private int stay = 0;

   private final static Map<Color, List<String>> colorImagePathMap = Map.of(
         Color.RED, List.of("images/jellyfish-red.gif", "images/jellyfish-red.gif"),
         Color.ORANGE, List.of("images/jellyfish-orange.gif", "images/jellyfish-orange.gif"),
         Color.GREEN, List.of("images/jellyfish-green.gif", "images/jellyfish-green.gif"),
         Color.BLUE, List.of("images/jellyfish-blue.gif", "images/jellyfish-blue.gif"),
         Color.PURPLE, List.of("images/jellyfish.gif", "images/jellyfish.gif"));

   public Jellyfish() {
      size = 100;
      type = OceanCreatureType.Jellyfish;
      leftimage = new Image("images/jellyfish.gif", size, size, true, true);
      rightimage = leftimage;
   }

   @Override
   public void move(double tankheight, double tankwidth) {
      Image image = getImage();
      view.setImage(image);
      if (floating < 350) {
         floating += 1;
         double x = moveXY(view.getX(), xspeed, xDirectionChangePct);
         double y = moveXY(view.getY(), yspeed, yDirectionChangePct);
         if (legalXMove(x, image.getWidth(), tankwidth)) {
            view.setX(x);
         } else {
            changeXdirection();
         }
         if (legalYMove(y, image.getHeight(), tankheight)) {
            view.setY(y);
         } else {
            changeYdirection();
         }
      } else {
         stay += 1;
         if (stay >= 100) { // if rest enough then continue walking
            floating = 0;
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
