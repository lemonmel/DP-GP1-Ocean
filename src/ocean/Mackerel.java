package ocean;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.Map;

public class Mackerel extends OceanCreature {

	private final static Map<Color, List<String>> colorImagePathMap = Map.of(
			Color.RED, List.of("images/mackeralL-red.gif", "images/mackeralR-red.gif"),
			Color.ORANGE, List.of("images/mackeralL-orange.gif", "images/mackeralR-orange.gif"),
			Color.GREEN, List.of("images/mackeralL-green.gif", "images/mackeralR-green.gif"),
			Color.BLUE, List.of("images/mackeralL.gif", "images/mackeralR.gif"),
			Color.PURPLE, List.of("images/mackeralL-purple.gif", "images/mackeralR-purple.gif"));

	public Mackerel() {
		fishSize = 180;
		type = OceanCreatureType.Mackerel;
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

	@Override
	public void changeColor(Color color) {
		addCurrentImagesToHistory();
		var path = colorImagePathMap.get(color);
		setImageByPath(path.get(0), path.get(1));
	}
}
