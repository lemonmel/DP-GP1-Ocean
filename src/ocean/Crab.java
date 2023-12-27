package ocean;

import java.util.Map;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Crab extends OceanCreature {
   private int walking = 0;
   private int stay = 0;

   private final static Map<Color, String> colorImagePathMap = Map.of(
      Color.RED, "images/crab-red.gif",
      Color.ORANGE, "images/crab-orange.gif",
      Color.YELLOW, "images/crab-yellow.gif",
      Color.GREEN, "images/crab-green.gif",
      Color.BLUE, "images/crab-blue.gif",
      Color.PURPLE, "images/crab-purple.gif"
   );

   public Crab() {
      xspeed = 0.8;
      fishSize = 100;
      type = OceanCreatureType.Crab;
      leftimage = new Image("images/crab.gif", fishSize, fishSize, true, true);
      rightimage = leftimage;
      view.setY(Ocean.getInstance().getOceanPane().getLayoutBounds().getHeight() - leftimage.getHeight() * 1.2);
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

   @Override
   public void changeColor(Color color) {
      addCurrentImagesToHistory();
      var path = colorImagePathMap.get(color);
      setImageByPath(path);
   }
}
