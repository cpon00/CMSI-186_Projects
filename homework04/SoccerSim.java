//if you sort the balls by distance from 1st ball, you only need to go through iteration once for the first ball
//ball ----ball----ball ---- balls
//asterisk ellipsis method
//if no collision between first and second ball, you can disregard the first ball as nothing else can collide with it.
import java.util.*;

public class SoccerSim {
	private static Ball[] ballSack = null;
	private static double[] distances = null;
	private static int ball1;
	private static int ball2;
	//need to initialize flagpole as well.
	//also need to change timeSlice to
	public void handleMyBalls (String args[]) {
		System.out.println( "\n Initializing SoccerSim...\n\n");
		if (0 == args.length) {
			System.out.println("Sorry, enter arguments in sets of four, plus an optional timeSlice argument.");
			System.out.println("Usage: java SoccerSim <xPos> <yPos> <xSpeed> <ySpeed> [timeSlice]");
			System.exit(1);
		}
		try {
			ballSack = new Ball[(int)(Math.floor(args.length/4.0))];
			if (args.length % 4 == 1) {
				//timeSLice = args[args.length - 1];
				Timer.timeSlice = Double.parseDouble(args[args.length - 1]);
			}

			for (int i = 0; i < ballSack.length; i++) {
				for (int k = 0; k < args.length; k += 4) {
					ballSack[i] = new Ball (Double.parseDouble(args[k]), Double.parseDouble(args[k + 1]),  Double.parseDouble(args[k + 2]),  Double.parseDouble(args[k + 3]));
					System.out.println(ballSack[i].toString() + "\n\n");
				}
			}
		}catch (ArrayIndexOutOfBoundsException e) {
			System.out.println ("Invalid Input. Must input in sets of four, with optional argument for timeSlice.");

			return;
		}
	}
	public static boolean isStillRunning() {
		return (SoccerSim.isNotOffCourse() && SoccerSim.isNotStopped());
	}

	public String toString() {
		String result = "";
		for (int i = 0; i < ballSack.length; i++) {
			result += ballSack[i].toString();
		}
		return result;
	}
	//maybe a method called move()
	//for every ball in ball array, updateSpeed, then getxandyPos.
	public static boolean isNotStopped () {
		for (int i = 0; i < ballSack.length;i++) {
			if (ballSack[i].isStopped()) {
				return false;
			}
		}
		return true;
	}
	public static boolean isNotOffCourse () {
		for (int i = 0; i< ballSack.length;i++) {
			if (ballSack[i].isOffCourse()) {
				return false;
			}
		}
		return true;
	}
	public static void updatePos () {
		for (int i = 0; i < ballSack.length;i++) {
			ballSack[i].getxPos();
			ballSack[i].getyPos();
		}
	}
	public static void updateVelocity () {
		for (int i = 0; i < ballSack.length;i++) {
			ballSack[i].updateSpeed();
		}
	}
	public static void getDistance() {
		for (int i = 0; i < ballSack.length; i++) {
			ballSack[i].distanceFromPole = ballSack[i].getDistance(0.0, 0.0);
			//gets distances of each ball from the pole.
		}

		//sorted from least to greatest.
		//need to use comparators to compare properties of objects.
	}
	private static class DistanceComparison implements Comparator<Ball> {
		public int compare(Ball ball1, Ball ball2) {
			return ball1.getDistance(0,0).compareTo(ball2.getDistance(0,0));
		}
	}



	public static boolean validCollision () {
		Arrays.sort(ballSack, new DistanceComparison());
		//sorts the array of balls by their distance from the pole.
		//edge case: if two equal distances, what happens?
		for (int i = 0; i <= ballSack.length; i++) {
			if (Math.abs(ballSack[i].distanceFromPole - ballSack[i + 1].distanceFromPole) <= Ball.diameter) {
				//return true if distance between balls is less than the diameter.
				 if (ballSack[i].getDistance(ballSack[i + 1].getxPos(), ballSack[i + 1].getyPos()) <= Ball.diameter)  {
					 System.out.println(ballSack[i].toString());
					 System.out.println(ballSack[i].toString());
					 return true;
				 }
			}
		}
		return false;
	}



	public static void main (String args[]) {
		SoccerSim soccerSim = new SoccerSim();
		soccerSim.handleMyBalls(args);
		Timer timer = new Timer();
		System.out.println("Timer seconds: " + timer.seconds);
		System.out.println("1xpos: " + ballSack[0].toString());

		System.out.println("2yPos: " + ballSack[1].toString());
		System.out.println(ballSack.length);
		//for every ball, test if Ball[i].isStopped
		//if (Ball[i].isStopped) {
		// return;
		//}
		//i want to say while Balls are not stopped and also on Course, run the method.
		//while this method is true && while that method is true, run the thing.
		while (soccerSim.isStillRunning()) {
			soccerSim.toString();
			soccerSim.updatePos();
			soccerSim.updateVelocity();
			soccerSim.getDistance();
			if(soccerSim.validCollision()) {
				System.out.println("Collision Detected...");
				return;
			}
			timer.tick();

		}



		//get Pos of every ball
		//compare disntance to pole
		//then distance is less than diameter; test for exact ball contact
		//if collide, then stop sim.

		//need to track if the Ball is OffCourse; if is, then stop simulation.
		//need to track if Ball is in motion; if all balls stop, then stop simulation.
		//for every timeSlice, we need to check the distance between every ball and the pole.
		//if the distance is within 8.9, then we need to check the distance between those two balls, or three balls.

		//i must call updateSpeed before getyPos and getxPos, then call toString to get every ball.
		//then, must determine distance from every ball to pole.

		//every tick, track isStopped and isOffCourse for every ball.
		//

	}


}
//need to initialize size of field; xsize and ysize


//Timer:

//create new instances of ball
//args.length needs to be either divisible by four, or args.length-1 mod 4 needs to equal one to be a valid input.



//if getDistance is less than 8.9 between any ball, they have collided.
//sort the array of balls by distance from the first ball.
//if the distance between the first and second ball is not
