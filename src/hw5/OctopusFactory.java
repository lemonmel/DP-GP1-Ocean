/*
 * file: OctopusFactory.java
 * author: garret patten
 * 03/11/18
 */

package hw5;

public class OctopusFactory extends FishFactory{

	public OctopusFactory(){}
	
	public Fish create(){
		Fish f = new Fish(new BottomFeedingBehavior());
		f.setType(new Octopus());
		return f;
	}
}
