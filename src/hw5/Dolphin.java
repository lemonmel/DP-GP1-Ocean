/*
 * file: Dolphin.java
 * author: garret patten
 * date: 03/12/18
 */

package hw5;

import javafx.scene.image.Image;

public class Dolphin implements FishType {
	private Image leftimage, rightimage;
	
	public Dolphin(){
	      leftimage  = new Image("images/Ldolphin.gif");
	      rightimage = new Image("images/Rdolphin.gif");
	}
	
	public String getType(){
		return "Dolphin";
	}
	
	public Image leftImage(){
		return leftimage;
	}
	
	public Image rightImage(){
		return rightimage;
	}
}
