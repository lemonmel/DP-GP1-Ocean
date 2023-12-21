package ocean;

import javafx.scene.image.Image;

public class Fish extends OceanCreature {

	public Fish() {
		fishSize = 180;
		leftimage = new Image("images/mackeralL.gif", fishSize, fishSize, true, true);
		rightimage = new Image("images/mackeralR.gif", fishSize, fishSize, true, true);
	}

	@Override
	public void move(double tankheight, double tankwidth) {
		Image image = getImage();
		view.setImage(image);
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
	}
}
