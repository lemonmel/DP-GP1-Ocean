/*
 * file: Fish.java
 * author: sciore, patten
 * date: 03/11/18
 */

package hw5;

import javafx.scene.image.*;

public class Fish {	
	private ImageView view = new ImageView(); // holds the image and current position
	private FishType type;
	private FishBehavior behavior;
	
	public Fish(FishBehavior fishbehavior) {
		behavior = fishbehavior;
		behavior.setupInitialPosition();
		view.setX(behavior.getxinitialpos());  // the initial fish location
		view.setY(behavior.getyinitialpos());
	}
	
	public void move(double tankheight, double tankwidth) {
      Image image = getImage();
	  view.setImage(image);
	  double x = moveXY(view.getX(), behavior.getXSpeed(), behavior.getXPer());
	  double y = moveXY(view.getY(), behavior.getYSpeed(), behavior.getYPer());
	  if (legalMove(x, image.getWidth(), tankwidth))
		  view.setX(x);
	  else
		 changeXdirection();
      if (legalMove(y, image.getHeight(), tankheight))
         view.setY(y);
      else
         changeYdirection();
	}
	
	public ImageView getView() {
	   return view;
	}
	
	private Image getImage() {
		return movesRight() ? type.rightImage() : type.leftImage();
	}

	private double moveXY(double pos, double speed, double pct) {
	   if (changesDirection(pct) ) 
			return -1;
		else
			return pos + speed;
	}
	 
   private boolean legalMove(double pos, double size, double limit) {
      return (pos >=0) && (pos + size <= limit);
   }
   
   private void changeXdirection() {
      behavior.setXSpeed(-behavior.getXSpeed());
   }
   
   private void changeYdirection() {
      behavior.setYSpeed(-behavior.getYSpeed());
   }
   
	private boolean movesRight() {
		return behavior.getXSpeed() >= 0;
	}
	
	private boolean changesDirection(double frequency) {
		return Math.random() * 100 < frequency;
	}
	
	public void setType(FishType fishtype){
		this.type = fishtype;
	}
}
