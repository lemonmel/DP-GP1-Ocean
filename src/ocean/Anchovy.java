package ocean;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.Map;

public class Anchovy extends OceanCreature {

   private final static Map<Color, String> colorImagePathMap = Map.of(
           Color.RED, "images/crab-red.gif",
           Color.ORANGE, "images/crab-orange.gif",
           Color.YELLOW, "images/crab-yellow.gif",
           Color.GREEN, "images/crab-green.gif",
           Color.BLUE, "images/crab-blue.gif",
           Color.PURPLE, "images/crab-purple.gif"
   );

   public Anchovy() {
      xspeed = 0.8;
      yspeed = 0.8;
      fishSize = 200;
      leftimage = new Image("images/anchovyL.gif", fishSize, fishSize, true, true);
      rightimage = new Image("images/anchovyR.gif", fishSize, fishSize, true, true);
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
      setImageByPath(path);
   }
}
