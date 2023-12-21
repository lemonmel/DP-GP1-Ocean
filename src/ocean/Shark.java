package ocean;

import javafx.scene.image.Image;

public class Shark extends OceanCreature {

   public Shark() {
      fishSize = 250;
      leftimage = new Image("images/sharkL.gif", fishSize, fishSize, true, true);
      rightimage = new Image("images/sharkR.gif", fishSize, fishSize, true, true);
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
}
