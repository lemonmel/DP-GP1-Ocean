package ocean;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public abstract class OceanCreature {
	double xspeed = 0.5;
	double yspeed = 0.5;
	double xDirectionChangePct = 0.1; // the fish changes horizontal direction 0.1% of the time
	double yDirectionChangePct = 0.4; // the fish changes vertical direction 0.4% of the time
	Image leftimage, rightimage;
	double fishSize;
	ImageView view = new ImageView(); // holds the image and current position
	OceanCreatureType type;

	Stack<List<String>> imagePathsHistory = new Stack<>();

	public OceanCreature() {
		view.setX(MyOceanApp.INIT_TANK_WD / 10); // the initial fish location
		view.setY(MyOceanApp.INIT_TANK_HT / 2);
	}

	public abstract void move(double tankheight, double tankwidth);

	public ImageView getView() {
		return view;
	}

	public Image getImage() {
		return xspeed >= 0 ? rightimage : leftimage;
	}

	public void setImageByPath(String path) {
		var image = createImageByPath(path);
		this.leftimage = image;
		this.rightimage = image;
	}

	public void setImageByPath(String leftImagePath, String rightImagePath) {
		this.leftimage = createImageByPath(leftImagePath);
		this.rightimage = createImageByPath(rightImagePath);
	}

	public Image createImageByPath(String path) {
		return new Image(path, this.fishSize, this.fishSize, true, true);
	}

	public double moveXY(double pos, double speed, double pct) {
		if (changesDirection(pct))
			return -1;
		else
			return pos + speed;
	}

	public boolean legalXMove(double pos, double size, double limit) {
		return (pos >= 0) && (pos + size <= limit);
	}

	public boolean legalYMove(double pos, double size, double limit) {
		return (pos >= 0) && (pos + size <= limit && pos + size >= limit * 0.65);
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

	public void changeHue(double hue) {
		ColorAdjust colorAdjust = new ColorAdjust();
		colorAdjust.setHue(hue);
		view.setEffect(colorAdjust);
	}

	public abstract void changeColor(Color color);

	public void undoColor() {
		var lastImagePaths = imagePathsHistory.pop();
		setImageByPath(lastImagePaths.getFirst(), lastImagePaths.getLast());
	}

	public void addCurrentImagesToHistory() {
		List<String> imagePaths = new ArrayList<>();
		imagePaths.add(leftimage.getUrl());
		imagePaths.add(rightimage.getUrl());
		imagePathsHistory.add(imagePaths);
	}
}
