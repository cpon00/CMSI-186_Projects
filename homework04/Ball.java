public class Ball {
	private final double radius = 4.45;
	private final double weight = 1.0;
	private final double friction = 0.99;
	private double seconds = 0.0;
	private double timeSlice = 1.0;
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

	public static double validatePositionArgs() {
// validate position arg should actually be in the SoccerSim?
	}
	//could possibly return arrays for position and speed.
	//first pos would be horizontal, second would be vertical

	public static double[] getPos() {
		//there is an initial horizontal position. must test if seconds is not 0.
		return (this.xPos, this.yPos);
		//return x
		//these need to be announced at the beginning and at every tick.
	}
	// public static getVerticalPos() {
	// 	//return y
	// 	//these need to be announced at the beginning and at every tick.
	// }
	//will also need to alert velocity of balls in set at every tick; where is it,
	//how fast is it going, in what direction?
	public static double[] getSpeed() {
		//for every second this needs to be reduced by 1%.
		//there is an initial horizontal speed. must test if seconds = 0;
		xSpeed = xSpeed * Math.pow(friction, seconds);
		if (seconds == 0) {
			horizontalSpeed = //whatever argument is horizontal speed.
		}else{
			//for every second we need to reduce the speed by 1%.
			//this means at one second, speed is reduced by 1%.
			//at two seconds speed is equal to the speed reduced by 1% then also reduced by another 1%.

			for (let i = 0; i <= seconds; i++) {
				totalHorizontalSpeed = horizontalSpeed * i;
				horizontalSpeed = horizontalspeed * friction;
				//friction is just (friction)^however many seconds have passed.



				//if my speed is 10m/s i multiply it by 1 second then reduce the speed by 1%.
				//if seconds = 2, i have the horizontal speed already reduced by 1%, plus another
			}
		}



	}
	public static getVerticalSpeed() {

	}

	//there is also a pole that we need to announce?

	//simulation will occur until all balls go off the field or all balls come to rest.
	//if no collision, alert no collision possible.

//need to make a method that changes the ball (x, y) every tick
// by the speed specified.
//this value needs to change by 1% less every tick.


}
