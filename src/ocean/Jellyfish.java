package ocean;

import javafx.scene.image.Image;

public class Jellyfish extends OceanCreature {
   private Image leftimage, rightimage;

   public Jellyfish() {
      leftimage = new Image("images/jellyfish.gif", 100, 100, true, true);
      rightimage = new Image("images/jellyfish.gif", 100, 100, true, true);
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
      double y = moveXY(view.getY(), yspeed, yDirectionChangePct);
      if (legalMove(x, image.getWidth(), tankwidth))
         view.setX(x);
      else
         changeXdirection();
      if (legalMove(y, image.getHeight(), tankheight))
         view.setY(y);
      else
         changeYdirection();
   }
}
