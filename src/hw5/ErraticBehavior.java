/*
 * file: ErraticBehavior.java
 * author: garret patten
 * date: 03/12/18
 */

package hw5;

public class ErraticBehavior implements FishBehavior {
	private double xspeed = 5.0;
	private double yspeed = 4.0;
	private double xDirectionChangePct = 0.3; // the fish changes horizontal direction 0.1% of the time
	private double yDirectionChangePct = 0.7; // the fish changes vertical direction 0.5% of the time
	private double xinitialpos;
	private double yinitialpos;
	
	public void setupInitialPosition(){
		double x = (Aquarium.INIT_TANK_WD / (Math.random() * 10));
		double y = (Aquarium.INIT_TANK_HT / (Math.random() * 10));
		if(x < .75){
			x = .75;
		}
		else if(x > Aquarium.INIT_TANK_WD - .75){
			x = Aquarium.INIT_TANK_WD - .75;
		}
		if(y < .75){
			y = .75;
		}
		else if(y > Aquarium.INIT_TANK_HT - .75){
			y = Aquarium.INIT_TANK_HT - .75;
		}
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
