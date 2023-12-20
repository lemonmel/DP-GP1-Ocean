package ocean;

import javafx.scene.image.*;

public abstract class OceanCreature {
	double xspeed = 0.5;
	double yspeed = 0.5;
	double xDirectionChangePct = 0.1; // the fish changes horizontal direction 0.1% of the time
	double yDirectionChangePct = 0.4; // the fish changes vertical direction 0.4% of the time
	Image leftimage, rightimage;
	double fishSize;
	ImageView view = new ImageView(); // holds the image and current position

	public OceanCreature() {
		view.setX(MyOceanApp.INIT_TANK_WD / 10); // the initial fish location
		view.setY(MyOceanApp.INIT_TANK_HT / 4);
	}

	public abstract void move(double tankheight, double tankwidth);

	public ImageView getView() {
		return view;
	}

	public Image getImage() {
		return xspeed >= 0 ? rightimage : leftimage;
	}

	public double moveXY(double pos, double speed, double pct) {
		if (changesDirection(pct))
			return -1;
		else
			return pos + speed;
	}

	public boolean legalMove(double pos, double size, double limit) {
		return (pos >= 0) && (pos + size <= limit);
	}

	public void changeXdirection() {
		xspeed = -xspeed;
	}

	public void changeYdirection() {
		yspeed = -yspeed;
	}

	private boolean changesDirection(double frequency) {
		return Math.random() * 100 < frequency;
	}
}
