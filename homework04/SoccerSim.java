//if you sort the balls by distance from 1st ball, you only need to go through iteration once for the first ball
//ball ----ball----ball ---- balls
//asterisk ellipsis method
//if no collision between first and second ball, you can disregard the first ball as nothing else can collide with it.
import java.util.*;

public class SoccerSim {
	static Ball[] ballSack = null;
	public static int ball1;
	public static int ball2;
	public void handleMyBalls (String args[]) {
		System.out.println( "\n Initializing SoccerSim...\n\n");
		if (0 == args.length) {
			System.out.println("Usage: java SoccerSim <xPos> <yPos> <xSpeed> <ySpeed> [timeSlice]");
			System.exit(1);
		}

		if ((args.length < 4) || (args.length % 4 > 1)) {
			System.out.println("Sorry, invalid input. Please enter arguments in multiples of four, plus an optional timeSlice.");
			System.out.println("Usage: java SoccerSim <xPos> <yPos> <xSpeed> <ySpeed> [timeSlice]");
			System.exit(1);
		}
			ballSack = new Ball[args.length/4];
			if (args.length % 4 == 1) {
				if ((Double.parseDouble(args[args.length - 1])) <= 0.0 || (Double.parseDouble(args[args.length - 1]) > 1800.0)) {
					throw new IllegalArgumentException ("timeSlice is Invalid.");
				}
				Timer.timeSlice = Double.parseDouble(args[args.length - 1]);
			}
			for (int k = 0; k < args.length - 1; k += 4) {
				ballSack[k/4] = new Ball (Double.parseDouble(args[k]), Double.parseDouble(args[k + 1]),  Double.parseDouble(args[k + 2]),  Double.parseDouble(args[k + 3]));
			}
	}

	public void soccerToString() {
		System.out.println("--------------------------------------------------------------------------");
		for (int i = 0; i < ballSack.length; i++) {
			System.out.println("Ball Number: " + (i));
			System.out.println(ballSack[i].toString() + "\n");
		}
	}

	public static boolean isNotStopped() {
		int stopcount = 0;
		for (int i = 0; i <= ballSack.length - 1; i++) {
			if (ballSack[i].isStopped()) {
				stopcount++;
			}
		}
		return (stopcount != ballSack.length);
	}

	public static void updatePos () {
		for (int i = 0; i < ballSack.length; i++) {
			ballSack[i].getxPos();
			ballSack[i].getyPos();
		}
	}
	public static void updateVelocity () {
		for (int i = 0; i < ballSack.length; i++) {
			ballSack[i].updateSpeed();
		}
	}
	// public static void getDistance() {
	// 	//gets distances of each ball from the pole.
	// 	for (int i = 0; i < ballSack.length; i++) {
	// 		ballSack[i].distanceFromPole = ballSack[i].getDistance(0.0, 0.0);
	// 		if (ballSack[i].distanceFromPole <= Ball.diameter) {
	// 			SoccerSim.validCollision();
	// 			//this is broken, still needs to be implemented...
	// 		}
	// 	}
	// }
	public static boolean validCollision () {
		boolean collision = false;
		for (int i = 0; i <= ballSack.length - 2; i++) {
			for (int k = i + 1; k <= ballSack.length - 1; k++) {
				if(ballSack[i].getDistance(ballSack[k].xPos, ballSack[k].yPos) <= Ball.diameter) {
					if (ballSack[i].isinBounds() && ballSack[k].isinBounds()) {
						collision = true;
						ball1 = i;
						ball2 = k;
					}
				}
			}
		}
		return collision;
	}

	public static void main (String args[]) {
		SoccerSim soccerSim = new SoccerSim();
		Timer timer = new Timer();
		soccerSim.handleMyBalls(args);
		while (soccerSim.isNotStopped()) {
			System.out.println("\nTime: " + timer.timerToString());
			soccerSim.soccerToString();
			soccerSim.updateVelocity();
			soccerSim.updatePos();
			if(soccerSim.validCollision()) {
				System.out.println("\n\n  Collision Detected Between Ball " + ball1 + " and Ball " + ball2 + "\n\n");
				soccerSim.soccerToString();
				return;
			}
			timer.tick();
		}
		System.out.println("No Collision found.");



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
