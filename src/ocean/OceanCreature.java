package ocean;

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
	double size;
	ImageView view = new ImageView(); // holds the image and current position
	OceanCreatureType type;

	Stack<List<String>> imagePathsHistory = new Stack<>();

	public OceanCreature() {
		view.setX(Ocean.getOceanPane().getWidth() / 10); // the initial fish location
		view.setY(Ocean.getOceanPane().getHeight() / 2);
	}

	public ImageView getView() {
		return view;
	}

	public Image getImage() {
		return xspeed >= 0 ? rightimage : leftimage;
	}

	public void setImageByPath(String leftImagePath, String rightImagePath) {
		this.leftimage = createImageByPath(leftImagePath);
		this.rightimage = createImageByPath(rightImagePath);
	}

	public Image createImageByPath(String path) {
		return new Image(path, this.size, this.size, true, true);
	}

	public abstract void move(double tankheight, double tankwidth);

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
		return (pos >= limit * 0.4) && (pos + size) <= limit;
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

	public abstract void changeColor(Color color);

	public void undoColor() {
		if (imagePathsHistory.isEmpty()) {
			return;
		}

		var lastImagePaths = imagePathsHistory.pop();
		setImageByPath(lastImagePaths.get(0), lastImagePaths.get(1));
	}

	public void addCurrentImagesToHistory() {
		List<String> imagePaths = new ArrayList<>();
		imagePaths.add(leftimage.getUrl());
		imagePaths.add(rightimage.getUrl());
		imagePathsHistory.add(imagePaths);
	}
}
