package ocean;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.Map;

public class Shark extends OceanCreature {

   private final static Map<Color, List<String>> colorImagePathMap = Map.of(
         Color.RED, List.of("images/sharkL-red.gif", "images/sharkR-red.gif"),
         Color.ORANGE, List.of("images/sharkL-orange.gif", "images/sharkR-orange.gif"),
         Color.GREEN, List.of("images/sharkL-green.gif", "images/sharkR-green.gif"),
         Color.BLUE, List.of("images/sharkL-blue.gif", "images/sharkR-blue.gif"),
         Color.PURPLE, List.of("images/sharkL.gif", "images/sharkR.gif"));

   public Shark() {
      size = 250;
      type = OceanCreatureType.Shark;
      leftimage = new Image("images/sharkL.gif", size, size, true, true);
      rightimage = new Image("images/sharkR.gif", size, size, true, true);
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
