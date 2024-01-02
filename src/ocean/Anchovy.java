package ocean;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.Map;

public class Anchovy extends OceanCreature {

   private final static Map<Color, List<String>> colorImagePathMap = Map.of(
         Color.RED, List.of("images/anchovyL-red.gif", "images/anchovyR-red.gif"),
         Color.ORANGE, List.of("images/anchovyL-orange.gif", "images/anchovyR-orange.gif"),
         Color.GREEN, List.of("images/anchovyL-green.gif", "images/anchovyR-green.gif"),
         Color.BLUE, List.of("images/anchovyL.gif", "images/anchovyR.gif"),
         Color.PURPLE, List.of("images/anchovyL-purple.gif", "images/anchovyR-purple.gif"));

   public Anchovy() {
      xspeed = 0.8;
      yspeed = 0.8;
      size = 200;
      type = OceanCreatureType.Anchovy;
      leftimage = new Image("images/anchovyL.gif", size, size, true, true);
      rightimage = new Image("images/anchovyR.gif", size, size, true, true);
   }

   @Override
   public void move(double tankheight, double tankwidth) {
      Image image = getImage();
      view.setImage(image);
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
   }

   @Override
   public void changeColor(Color color) {
      addCurrentImagesToHistory();
      var path = colorImagePathMap.get(color);
      setImageByPath(path.get(0), path.get(1));
   }
}
