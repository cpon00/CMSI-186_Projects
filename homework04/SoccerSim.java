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

	public static boolean isStopped() {
		boolean stopcount = true;
		for (int i = 0; i <= ballSack.length - 1; i++) {
			stopcount =  (stopcount && (ballSack[i].isStopped() || !ballSack[i].isInBounds()));
		}
		return (stopcount);
	}

	public static boolean allOutOfBounds() {
		boolean outsidebounds = true;
		for (int i = 0; i <= ballSack.length - 1; i++) {
			outsidebounds = (outsidebounds && (!ballSack[i].isInBounds() || ballSack[i].isStopped()));
		}
		return outsidebounds;
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

	public static boolean validCollision () {
		boolean collision = false;
		for (int i = 0; i <= ballSack.length - 2; i++) {
			for (int k = i + 1; k <= ballSack.length - 1; k++) {
				if(ballSack[i].getDistance(ballSack[k].xPos, ballSack[k].yPos) <= Ball.diameter) {
					if (ballSack[i].isInBounds() && ballSack[k].isInBounds()) {
						collision = true;
						ball1 = i;
						ball2 = k;
					}
				}
			}
		}
		return collision;
	}

	public static boolean poleCollision () {
		boolean pole = false;
		for (int i = 0; i <= ballSack.length - 1; i++) {
			if (ballSack[i].getDistance(1000.0, 1000.0) <= Ball.diameter) {
				pole = true;
				ball1 = i;
			}
		}
		return pole;
	}

	public void soccerToString() {
			System.out.println("--------------------------------------------------------------------------");
			for (int i = 0; i < ballSack.length; i++) {
				System.out.println("Ball Number: " + (i));
				System.out.println(ballSack[i].toString() + "\n");
			}
		}

	public static void main (String args[]) {
		SoccerSim soccerSim = new SoccerSim();
		Timer timer = new Timer();
		soccerSim.handleMyBalls(args);
		do {
			System.out.println("\nTime: " + timer.timerToString());
			if(soccerSim.validCollision()) {
				System.out.println("\n Collision Detected Between Ball " + ball1 + " and Ball " + ball2 + "\n" + "at Time: " + timer.timerToString());
				soccerSim.soccerToString();
				return;
			}
			if (soccerSim.poleCollision()) {
				System.out.println("\n\n Collision Detected Between Ball " + ball1 + " and Pole at (0,0). " );
				soccerSim.soccerToString();
				return;
			}
			soccerSim.soccerToString();
			if (Timer.timeSlice != 1) {
				soccerSim.updateVelocity();
				soccerSim.updatePos();
			}else{
				soccerSim.updatePos();
				soccerSim.updateVelocity();
			}


			timer.tick();
		} while (!soccerSim.isStopped() && !soccerSim.allOutOfBounds());
		System.out.println("No Collision found.");
		if (soccerSim.allOutOfBounds() || soccerSim.isStopped()) {
			System.out.println("\nAll balls are invalid.");
		}
	}
}
