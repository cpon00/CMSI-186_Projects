public class Ball {
	public final static double diameter = 8.9/12;
	private final static double weight = 1.0; //why is this necessary to know?
	private final static double friction = 0.99;
	public double xPos = 0;
	public double yPos = 0;
	public double xSpeed = 0;
	public double ySpeed = 0;
	public double distanceFromPole = 0;

	//maybe we reuse clock class in timer.java?

	//ball class will report details of ball: speed, pos, etc.
	//handle multiple balls in SoccerSim.java

	//not tracking course, but tracking position at every time slice.
	//perhaps they collide within the timeSlice, but because we check every time slice,
	//its ok for us to miss those collisions.

	public Ball (double xPos, double yPos, double xSpeed, double ySpeed) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
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

	public double getxPos() {
		double i = Timer.seconds;
		xPos += xSpeed * Math.pow(friction, i);

		return xPos;
		//there is an initial horizontal position. must test if seconds is not 0.

		//return x
		//these need to be announced at the beginning and at every tick.
	}
	public double getyPos() {
		double i = Timer.seconds;
		yPos += ySpeed * Math.pow(friction, i);

		return yPos;
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

	//the getDistance method first must be used to compare to the pole, then used to compare to the ball with a possible collision.

	public Double getDistance(double otherX, double otherY) {
		double ac = Math.abs (this.yPos - otherY);
		double cb = Math.abs (this.xPos - otherX);
		//return Math.sqrt((yPos - otherY) * (yPos - otherY) + (xPos - otherX) * (xPos - otherX));
		return Math.hypot(ac, cb);
	}

	public boolean isStopped() {
		//stopped is considered as 1/12 fps.
		//Ball[i].isStopped()?
		return (Math.abs(this.xSpeed) <= 1/12 && Math.abs(this.ySpeed) <= 1/12);
	}

	public String toString() {
		return ("X-Pos: " + this.xPos + "\nY-Pos: " + this.yPos + "\nX-Speed: " + this.xSpeed + "\nY-Speed: " + this.ySpeed);
	}


	public static void main (String [] args) {
		Ball ball1 = new Ball(3.0, 4.0, 1.0, 1.0);
		System.out.println("XPos: " + ball1.xPos + ", " + "YPos: " + ball1.yPos);
		System.out.println("XSpeed: " + ball1.xSpeed + ", " + "YSpeed: " + ball1.ySpeed);
		Ball ball2 = new Ball (0.0, 0.0, 0.0, 0.0);
		Ball ball3 = new Ball (-1000.1, 1000, 0, 0);
		System.out.println("XPos: " + ball2.xPos + ", " + "YPos: " + ball2.yPos);
		System.out.println("XSpeed: " + ball2.xSpeed + ", " + "YSpeed: " + ball2.ySpeed);
		System.out.println("Distance: " + ball1.getDistance(ball2.getxPos(), ball2.getyPos()));
		System.out.println(ball1.isStopped());
		System.out.println(ball2.isStopped());
		Timer.seconds = 100.0;
		ball1.getxPos();
		ball1.getyPos();
		ball1.updateSpeed();
		ball2.updateSpeed();
		System.out.println("New Pos: " + ball1.xPos + ", " + ball1.yPos);
		System.out.println(ball1.toString());
		System.out.println(ball2.toString());

	}

	//move, updateVelocity, isinmotion, get location, get speed , to String, main, isinBounds,


	//there is also a pole that we need to announce?

	//simulation will occur until all balls go off the field or all balls come to rest.
	//if no collision, alert no collision possible.

//need to make a method that changes the ball (x, y) every tick
// by the speed specified.
//this value needs to change by 1% less every tick.


}
