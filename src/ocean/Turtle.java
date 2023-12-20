package ocean;

import javafx.scene.image.Image;

public class Turtle extends OceanCreature {

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
      if (legalMove(x, image.getWidth(), tankwidth)) {
         view.setX(x);
      } else {
         changeXdirection();
      }
      if (legalMove(y, image.getHeight(), tankheight)) {
         view.setY(y);
      } else {
         changeYdirection();
      }
   }
}
