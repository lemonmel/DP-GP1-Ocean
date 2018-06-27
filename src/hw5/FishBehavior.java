/*
 * file: FishBehavior.java
 * author: garret patten
 * date: 03/11/18
 */

package hw5;

public interface FishBehavior {
	public void setupInitialPosition();
	public double getxinitialpos();
	public double getyinitialpos();
	public double getXSpeed();
	public double getYSpeed();
	public double getXPer();
	public double getYPer();
	public void setXSpeed(double x);
	public void setYSpeed(double x);
}
