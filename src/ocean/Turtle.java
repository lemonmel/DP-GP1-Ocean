package ocean;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.Map;

public class Turtle extends OceanCreature {

   private final static Map<Color, List<String>> colorImagePathMap = Map.of(
           Color.RED, List.of("images/crab-red.gif", "images/crab-red.gif"),
           Color.ORANGE, List.of("images/crab-orange.gif", "images/crab-orange.gif"),
           Color.YELLOW, List.of("images/crab-yellow.gif", "images/crab-yellow.gif"),
           Color.GREEN, List.of("images/crab-green.gif", "images/crab-green.gif"),
           Color.BLUE, List.of("images/crab-blue.gif", "images/crab-blue.gif"),
           Color.PURPLE, List.of("images/crab-purple.gif", "images/crab-purple.gif")
   );

   public Turtle() {
      fishSize = 200;
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
      setImageByPath(path.getFirst(), path.getLast());
   }
}
