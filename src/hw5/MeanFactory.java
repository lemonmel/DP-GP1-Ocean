/*
 * MeanFactory.java
 * author: garret patten
 * date:03/12/18
 */

package hw5;

public class MeanFactory extends FishFactory{
	
	public MeanFactory(){}
	
	public Fish create(){
		Fish f = new Fish(new ErraticBehavior());
		f.setType(new MeanFish());
		return f;
	}
}
