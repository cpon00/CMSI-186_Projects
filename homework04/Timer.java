public class Timer {
	public static double timeSlice = 1.0;
	public static double seconds;
	private double hoursString;
	private double minutesString;
	private double secondsString;
	public Timer() {
		//this means i have to initialize Timer after SoccerSim and handleMyBalls
		//this also means that I need to set the Ball.java timeSlice to the Timer.java timeSlice
		//initialize Ball.java before
	}
	public double tick() {
		seconds += timeSlice;

		return seconds;
	}
	public String toString() {
        //take total number of seconds value
        //create another variable that is equal to current total number of seconds
        //from the total seconds, subtract hours and subtract minutes to get seconds mod 60.
        double secondsForString = seconds;
        //i have the total amount of seconds stored as secondsForString.
        hoursString = (int)Math.floor(secondsForString / 3600);
        //i want to take the total number of seconds divided by 3600 to get the total number of hours.
        minutesString = (int)Math.floor((secondsForString - hoursString * 3600)/60);
        //total seconds - hours, converted to minutes
        //the minutes string should take the difference between the total number of seconds and the total amount of time in
        //the hour and divide it by 60.
        secondsString = (int)secondsForString - (hoursString * 3600 + minutesString * 60);
        //the seconds string should be total seconds - hours - minutes.
        return hoursString + ":" + String.format("%02d", minutesString) + ":" + String.format("%02d", secondsString);
    }

}
//timeSlice, totalSeconds,
//Constructor(), toString, tick, stop, main()
