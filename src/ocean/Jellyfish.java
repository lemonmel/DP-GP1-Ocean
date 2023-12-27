package ocean;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.Map;

public class Jellyfish extends OceanCreature {
   private int floating = 0;
   private int stay = 0;

   private final static Map<Color, List<String>> colorImagePathMap = Map.of(
           Color.RED, List.of("images/crab-red.gif", "images/crab-red.gif"),
           Color.ORANGE, List.of("images/crab-orange.gif", "images/crab-orange.gif"),
           Color.YELLOW, List.of("images/crab-yellow.gif", "images/crab-yellow.gif"),
           Color.GREEN, List.of("images/crab-green.gif", "images/crab-green.gif"),
           Color.BLUE, List.of("images/crab-blue.gif", "images/crab-blue.gif"),
           Color.PURPLE, List.of("images/crab-purple.gif", "images/crab-purple.gif")
   );

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

   @Override
   public void changeColor(Color color) {
      addCurrentImagesToHistory();
      var path = colorImagePathMap.get(color);
      setImageByPath(path.getFirst(), path.getLast());
   }
}
