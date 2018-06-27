/*
 * file: Octopus.java
 * author: garret patten
 * date: 03/11/18
 */

package hw5;

import javafx.scene.image.Image;

public class Octopus implements FishType {
	private Image leftimage, rightimage;
	
	public Octopus(){
	      leftimage  = new Image("images/Loctopus.gif");
	      rightimage = new Image("images/Roctopus.gif");
	}
	
	public String getType(){
		return "Octopus";
	}
	
	public Image leftImage(){
		return leftimage;
	}
	
	public Image rightImage(){
		return rightimage;
	}
}

