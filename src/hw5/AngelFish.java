/*
 * file: AngelFish.java
 * author: garret patten
 * date: 03/11/18
 */

package hw5;

import javafx.scene.image.Image;

public class AngelFish implements FishType{
	private Image leftimage, rightimage;
	
	public AngelFish(){
		leftimage  = new Image("images/Langelfish.gif");
		rightimage = new Image("images/Rangelfish.gif");
	}

	public String getType(){
		return "Angel";
	}
	
	public Image leftImage(){
		return leftimage;
	}
	
	public Image rightImage(){
		return rightimage;
	}
}
