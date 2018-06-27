/*
 * file: AngelFactory.java
 * author: garret patten
 * 03/11/18
 */

package hw5;

public class AngelFactory extends FishFactory{
	
	public AngelFactory(){}
	
	public Fish create(){
		Fish f = new Fish(new NormalBehavior());
		f.setType(new AngelFish());
		return f;
	}
}
