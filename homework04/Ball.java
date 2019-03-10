public class Ball {
	public final static double diameter = 8.9/12;
	private final static double weight = 1.0; //why is this necessary to know?
	private final static double friction = 0.99;
	private double xPos = 0;
	private double yPos = 0;
	private double xSpeed = 0;
	private double ySpeed = 0;

	//maybe we reuse clock class in timer.java?

	//ball class will report details of ball: speed, pos, etc.
	//handle multiple balls in SoccerSim.java

	//not tracking course, but tracking position at every time slice.
	//perhaps they collide within the timeSlice, but because we check every time slice,
	//its ok for us to miss those collisions.

	public Ball (double xPos, double yPos, double xSpeed, double ySpeed) {
		this.xPos = (xPos);
		this.yPos = (yPos);
		this.xSpeed = (xSpeed);
		this.ySpeed = (ySpeed);
	}
	// public double tick() {
	// 	seconds += timeSlice;
	// 	return seconds;
	// }
	//need to verify all arguments, make sure theyre all numbers.
	//also need to verify if enough arguments. :invalid arguments, must be sets of four.
	//if args.length is an odd number, maybe we have an optional argument such as timeSLice.
	//if args.length - 1 % 4 does not equal zero, we dont have enough arguments.
	//must mean wrong number of arguments or no specified timeSlice.

//	public static double validatePositionArgs() {
// validate position arg should actually be in the SoccerSim?
// 	}
	//could possibly return arrays for position and speed.
	//first pos would be horizontal, second would be vertical

	public void getxPos() {
		for (int i = 1; i <= Timer.seconds; i++) {
			xPos += xSpeed * Math.pow(friction, i);
		}
		//there is an initial horizontal position. must test if seconds is not 0.

		//return x
		//these need to be announced at the beginning and at every tick.
	}
	public void getyPos() {
		for (int i = 1; i <= Timer.seconds; i++) {
			yPos += ySpeed * Math.pow(friction, i);
		}

	}
	// public static getVerticalPos() {
	// 	//return y
	// 	//these need to be announced at the beginning and at every tick.
	// }
	//will also need to alert velocity of balls in set at every tick; where is it,
	//how fast is it going, in what direction?
	public void updateSpeed() {
		//for every second this needs to be reduced by 1%.
		//there is an initial horizontal speed. must test if seconds = 0;
		xSpeed = xSpeed * Math.pow(friction, Timer.seconds);
		ySpeed = ySpeed * Math.pow(friction, Timer.seconds);

	}
	// i must call updateSpeed before getting the position.
	//whatever argument is horizontal speed.
	//for every second we need to reduce the speed by 1%.
	//this means at one second, speed is reduced by 1%.
	//at two seconds speed is equal to the speed reduced by 1% then also reduced by another 1%.
	//friction is just (friction)^however many seconds have passed.
	//if my speed is 10m/s i multiply it by 1 second then reduce the speed by 1%.
	//if seconds = 2, i have the horizontal speed already reduced by 1%, plus another


	//the getDistance method first must be used to compare to the pole, then used to compare to the ball with a possible collision.

	public double getDistance(double otherX, double otherY) {
		double ac = Math.abs (this.yPos - otherY);
		double cb = Math.abs (this.xPos - otherX);
		//return Math.sqrt((yPos - otherY) * (yPos - otherY) + (xPos - otherX) * (xPos - otherX));
		return Math.hypot(ac, cb);
	}
	public boolean isStopped() {
		//stopped is considered as 1/12 fps.
		//Ball[i].isStopped()?
		return (this.xSpeed <= 1/12 && this.ySpeed <= 1/12);
	}
	public boolean isOffCourse() {
		return (this.xPos > 100 || this.xPos < -100 || this.yPos > 100 || this.yPos < -100);//if ball pos is greater than or less than bounds of course, and ypos too.
	}
	public String toString() {
		return ("X: " + this.xPos + "\nY: " + this.yPos + "\nX-Speed: " + this.xSpeed + "\n Y-Speed: " + this.ySpeed);
	}

	public static void main (String [] args) {
		Ball ball1 = new Ball(3.0, 4.0, 1.0, 1.0);
		ball1.getxPos();
		ball1.getyPos();
		System.out.println("XPos: " + ball1.xPos + ", " + "YPos: " + ball1.yPos);
		System.out.println("XSpeed: " + ball1.xSpeed + ", " + "YSpeed: " + ball1.ySpeed);
		Ball ball2 = new Ball (0.0, 0.0, 0.0, 0.0);
		System.out.println("XPos: " + ball2.xPos + ", " + "YPos: " + ball2.yPos);
		System.out.println("XSpeed: " + ball2.xSpeed + ", " + "YSpeed: " + ball2.ySpeed);
		System.out.println("Distance: " + ball1.getDistance(ball2.xPos, ball2.yPos));
		System.out.println(ball1.isStopped());
		System.out.println(ball2.isStopped());
		Timer.seconds = 10.0;
		ball1.getxPos();
		ball1.getyPos();
		ball1.updateSpeed();
		ball2.updateSpeed();
		System.out.println("New Pos: " + ball1.xPos + ", " + ball1.yPos);
		System.out.println(ball1.toString());
		System.out.println(ball2.toString());
		System.out.println(1/12);

	}

	//move, updateVelocity, isinmotion, get location, get speed , to String, main, isinBounds,


	//there is also a pole that we need to announce?

	//simulation will occur until all balls go off the field or all balls come to rest.
	//if no collision, alert no collision possible.

//need to make a method that changes the ball (x, y) every tick
// by the speed specified.
//this value needs to change by 1% less every tick.


}
