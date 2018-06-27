package fishtank;

import javafx.scene.image.*;

public abstract class Fish {
	private double xspeed = 2.0;
	private double yspeed = 1.0;
	private double xDirectionChangePct = 0.1; // the fish changes horizontal direction 0.1% of the time
	private double yDirectionChangePct = 0.5; // the fish changes vertical direction 0.5% of the time	
	private ImageView view = new ImageView(); // holds the image and current position
	
	public Fish() {
	   view.setX(Aquarium.INIT_TANK_WD / 3);  // the initial fish location
	   view.setY(Aquarium.INIT_TANK_HT / 3);
	}
	
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
	
	public ImageView getView() {
	   return view;
	}
	
	private Image getImage() {
		return movesRight() ? rightImage() : leftImage();
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
      xspeed = -xspeed;
   }
   
   private void changeYdirection() {
      yspeed = -yspeed;
   }
   
	private boolean movesRight() {
		return xspeed >= 0;
	}
	
	private boolean changesDirection(double frequency) {
		return Math.random() * 100 < frequency;
	}
	
	protected abstract Image rightImage();
	protected abstract Image leftImage();
}
