package ocean;

import javafx.scene.image.Image;

public class Crab extends OceanCreature {
   private Image leftimage, rightimage;

   public Crab() {
      leftimage = new Image("images/crab.gif", fishWidth, fishHeight, true, true);
      rightimage = new Image("images/crab.gif", fishWidth, fishHeight, true, true);
   }

   public Image leftImage() {
      return leftimage;
   }

   public Image rightImage() {
      return rightimage;
   }

   @Override
   public void move(double tankheight, double tankwidth) {
      Image image = getImage();
      view.setImage(image);
      double x = moveXY(view.getX(), xspeed, xDirectionChangePct);
      if (legalMove(x, image.getWidth(), tankwidth)) {
         view.setX(x);
      } else {
         changeXdirection();
      }
   }
}
