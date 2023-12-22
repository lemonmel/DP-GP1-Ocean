package ocean;

import javafx.scene.image.Image;

public class Crab extends OceanCreature {
   private int walking = 0;
   private int stay = 0;

   public Crab() {
      xspeed = 0.8;
      fishSize = 100;
      leftimage = new Image("images/crab.gif", fishSize, fishSize, true, true);
      rightimage = leftimage;
      view.setY(Ocean.getInstance().getOceanPane().getLayoutBounds().getHeight() - leftimage.getHeight());
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
      if (walking < 500) {
         double x = moveXY(view.getX(), xspeed, xDirectionChangePct);
         if (legalXMove(x, image.getWidth(), tankwidth)) {
            walking += 1;
            view.setX(x);
         } else {
            changeXdirection();
         }
      } else {
         stay += 1;
         if (stay >= 100) { // if rest enough then continue walking
            walking = 0;
            stay = 0;
         }
      }
   }
}
