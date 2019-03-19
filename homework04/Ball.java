public class Ball {
	public final static double diameter = 8.9/12;
	private final static double weight = 1.0; //why is this necessary to know?
	private final static double friction = 0.99;
	public double xPos = 0;
	public double yPos = 0;
	public double xSpeed = 0;
	public double ySpeed = 0;
	public double distanceFromPole = 0;
	public double stopSpeed = 1.0;

	public Ball (double xPos, double yPos, double xSpeed, double ySpeed) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
	}

	public double getxPos() {
		xPos += xSpeed * Timer.timeSlice;
		return xPos;
	}

	public double getyPos() {
		yPos += ySpeed * Timer.timeSlice;
		return yPos;
	}

	public void updateSpeed() {
		xSpeed = xSpeed * Math.pow(friction, Timer.timeSlice);
		ySpeed = ySpeed * Math.pow(friction, Timer.timeSlice);
	}

	public Double getDistance(double otherX, double otherY) {
		double ac = (this.yPos - otherY);
		double cb = (this.xPos - otherX);
		return Math.hypot(ac, cb);
	}


	public boolean isStopped() {
		return (Math.abs(xSpeed * 12) <= stopSpeed && Math.abs(ySpeed * 12) <= stopSpeed);
	}

	public boolean isinBounds() {
		System.out.println(xPos);
		System.out.println(yPos);
		return (xPos < 1000.0 && xPos > -1000.0 && yPos < 1000.0 && yPos > -1000.0);
	}

	public String toString() {
		return ("X-Pos: " + this.xPos + "\nY-Pos: " + this.yPos + "\nX-Speed: " + this.xSpeed + "\nY-Speed: " + this.ySpeed);
	}

	public static void main (String [] args) {
		Ball ball1 = new Ball(2.0, 3.0, 10.000E-10, 0);
		System.out.println("XPos: " + ball1.xPos + ", " + "YPos: " + ball1.yPos);
		System.out.println("XSpeed: " + ball1.xSpeed + ", " + "YSpeed: " + ball1.ySpeed);
		Ball ball2 = new Ball (-1.0, -1.0, 0.0, 0.0);
		Ball ball3 = new Ball (-1000.1, 1000, 0, 0);
		System.out.println("XPos: " + ball2.xPos + ", " + "YPos: " + ball2.yPos);
		System.out.println("XSpeed: " + ball2.xSpeed + ", " + "YSpeed: " + ball2.ySpeed);
		System.out.println("Distance: " + ball1.getDistance(ball2.getxPos(), ball2.getyPos()));
		System.out.println(ball1.isStopped());
		System.out.println(ball2.isStopped());
		System.out.println(ball3.isinBounds());
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
