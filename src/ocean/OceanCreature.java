package ocean;

import javafx.scene.image.*;

public abstract class OceanCreature {
	double xspeed = 2.0;
	double yspeed = 1.0;
	double xDirectionChangePct = 0.1; // the fish changes horizontal direction 0.1% of the time
	double yDirectionChangePct = 0.5; // the fish changes vertical direction 0.5% of the time
	double fishWidth = 100; // Adjust the width of the fish
	double fishHeight = 100; // Adjust the height of the fish
	ImageView view = new ImageView(); // holds the image and current position

	public OceanCreature() {
		view.setX(MyOceanApp.INIT_TANK_WD / 3); // the initial fish location
		view.setY(MyOceanApp.INIT_TANK_HT / 3);
	}

	public abstract void move(double tankheight, double tankwidth);

	public ImageView getView() {
		return view;
	}

	public Image getImage() {
		return movesRight() ? rightImage() : leftImage();
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

	private boolean movesRight() {
		return xspeed >= 0;
	}

	private boolean changesDirection(double frequency) {
		return Math.random() * 100 < frequency;
	}

	protected abstract Image rightImage();

	protected abstract Image leftImage();
}
