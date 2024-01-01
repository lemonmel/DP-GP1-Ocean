package ocean;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.Map;

public class Turtle extends OceanCreature {

   private final static Map<Color, List<String>> colorImagePathMap = Map.of(
           Color.RED, List.of("images/turtleL-red.gif", "images/turtleR-red.gif"),
           Color.ORANGE, List.of("images/turtleL-orange.gif", "images/turtleR-orange.gif"),
           Color.GREEN, List.of("images/turtleL.gif", "images/turtleR.gif"),
           Color.BLUE, List.of("images/turtleL-blue.gif", "images/turtleR-blue.gif"),
           Color.PURPLE, List.of("images/turtleL-purple.gif", "images/turtleR-purple.gif")
   );

   public Turtle() {
      fishSize = 200;
      type = OceanCreatureType.Turtle;
      leftimage = new Image("images/turtleL.gif", fishSize, fishSize, true, true);
      rightimage = new Image("images/turtleR.gif", fishSize, fishSize, true, true);
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
