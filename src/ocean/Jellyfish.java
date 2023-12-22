package ocean;

import javafx.scene.image.Image;

public class Jellyfish extends OceanCreature {
   private int floating = 0;
   private int stay = 0;

   public Jellyfish() {
      fishSize = 100;
      leftimage = new Image("images/jellyfish.gif", fishSize, fishSize, true, true);
      rightimage = leftimage;
   }

   public void setLeftImage(Image leftimage){
		this.leftimage = leftimage;
	}

	public void setRightImage(Image rightimage){
		this.rightimage = rightimage;
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
}
