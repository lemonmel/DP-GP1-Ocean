/*
 * file: DolphinFactory.java
 * author: garret patten
 * date: 03/12/18
 */

package hw5;

public class DolphinFactory extends FishFactory{
	
	public DolphinFactory(){}
	
	public Fish create(){
		Fish f = new Fish(new DolphinBehavior());
		f.setType(new Dolphin());
		return f;
	}
}
