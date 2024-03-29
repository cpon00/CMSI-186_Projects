import java.util.*;
public class Timer {
	public static double timeSlice;
	public static double seconds;
	private static int hoursString;
	private static int minutesString;
	private static double secondsString;
	private static double precisionString;
	public Timer() {
		this.timeSlice = 1.0;
		this.seconds = 0;
	}

	public double tick() {
		seconds += timeSlice;
		return seconds;
	}
	public static String timerToString() {
        //take total number of seconds value
        //create another variable that is equal to current total number of seconds
        //from the total seconds, subtract hours and subtract minutes to get seconds mod 60.
        double secondsForString = seconds;
        //i have the total amount of seconds stored as secondsForString.
        hoursString = (int)(secondsForString / 3600);
        //i want to take the total number of seconds divided by 3600 to get the total number of hours.
        minutesString = (int)((secondsForString - hoursString * 3600)/60);
        //total seconds - hours, converted to minutes
        //the minutes string should take the difference between the total number of seconds and the total amount of time in
        //the hour and divide it by 60.
        secondsString = secondsForString - (hoursString * 3600 + minutesString * 60);
        //the seconds string should be total seconds - hours - minutes.
        return ((int)hoursString + ":" + String.format("%02d", (int)minutesString) + ":" + secondsString);
    }

}
//timeSlice, totalSeconds,
//Constructor(), toString, tick, stop, main()
