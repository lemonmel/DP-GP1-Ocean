/*
 * file: DolphinBehavior.java
 * author: garret patten
 * date: 03/12/18
 */

package hw5;

public class DolphinBehavior implements FishBehavior {
	private double xspeed = 3.0;
	private double yspeed = 3.0;
	private double xDirectionChangePct = 0.1; // the fish changes horizontal direction 0.1% of the time
	private double yDirectionChangePct = 0.9; // the fish changes vertical direction 0.5% of the time
	private double xinitialpos;
	private double yinitialpos;
	
	public void setupInitialPosition(){
		xinitialpos = (Aquarium.INIT_TANK_WD / 3);
		yinitialpos = (.75);
	}
	
	public double getxinitialpos(){
		return xinitialpos;
	}
	
	public double getyinitialpos(){
		return yinitialpos;
	}
	
	public double getXSpeed(){
		return xspeed;
	}
	
	public double getYSpeed(){
		return yspeed;
	}
	
	public double getXPer(){
		return xDirectionChangePct;
	}
	
	public double getYPer(){
		return yDirectionChangePct;
	}
	
	public void setXSpeed(double x){
		xspeed = x;
	}
	
	public void setYSpeed(double y){
		yspeed = y;
	}
}
