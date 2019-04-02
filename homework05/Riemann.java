/**
randomized estimation to determine the area under a curve
programs for integrating polynomials
computes definite integrals using Riemann integration
handle polynomials to the nth degree
proper argument handling
proper way to invoke program: java Riemann args[0] args[1] args [2]... args[k] <lowerbound> <upperbound> <percentage>
args[0] : name of function type
args[1] - args[k] : specify the coefficients of the polynomials
lowerbound and upperbound : specify the lower and upper bounds of x, i.e. the range within which the integral shoud be evaluated
percentage : indicates when the program should halt - the program should halt when two successive approxiamtions are within a percentage of each other.
no optional arguments for this assignment - all arguments must be inputted.
**/

/**
functions i need to handle:

polynomials (poly)
sin
logarithms (log)
exponents (exp)
square root (sqrt)

**/

/**
Argument handling:

if (args[args.length - 1].contains("%")) {
use substring method : (args[args.length - 1].length() - 2) to return the length of the substring
Double.parseDouble(substring (0, (args[args.length - 1].length() - 2))
^^
if the percentage specified is 10.0%, parses double of the last argument with length 4 - 2... which means stops at location 2 in the substring... at 10.(0)%
}

^^^
then Double.parseDouble for that number to return the double of the last number value if percentage specified.

**/

/**

how do we know when to stop the program?
what does the percentage do?

-- if the current value that you have divided by the last number you recorded is smaller than (percentage), its close enough.

**/
import java.util.*;
public class Riemann {
	public static double percentage;
	public static double[] coefficients;
	public static double lowerBound;
	public static double upperBound;
	public static double yValue;
	public static double currentArea;
	public static double previousArea;
	public static double xValue;
	public static double height;
	public static double width;
	public static double numberOfRectangles;

	public Riemann () {
		this.percentage = 1;
		this.numberOfRectangles = 1.0;
	}

    public void argumentHandler (String[] args) {
		if (args[args.length - 1].contains("%")) {
			try {
			percentage = Double.parseDouble(args[args.length - 1].substring(0, args[args.length - 1].length() - 1));
		    }catch (Exception e) {
				throw new IllegalArgumentException ("Percentage value is invalid. Try again.");
		    }
			if (percentage <= 0) {
				throw new IllegalArgumentException ("Percentage value is invalid. Try again.");
			}
			try{
				coefficients = new double[args.length - 4];
			}catch (Exception e) {
				throw new IllegalArgumentException ("Invalid number of arguments for percentage specified. Try again.");
			}
			if (coefficients.length == 0) {
				throw new IllegalArgumentException ("Must enter coefficient arguments. For example: if trying to find sin(x), enter: java Riemann sin 0 1");
			}
			//BY DEFAULT, THERE ARE THREE ARGUMENTS WE IGNORE; THE FUNCTION TYPE, AND THE UPPER AND LOWER BOUNDS.
			//IF PERCENTAGE SPECIFIED, NEED TO IGNORE AN EXTRA ARGUMENT.
			try {
				lowerBound = Double.parseDouble(args[args.length - 3]);
				upperBound = Double.parseDouble(args[args.length - 2]);
				for (int i = 1; i < args.length - 3; i++) {
					coefficients[i - 1] = Double.parseDouble(args[i]);
					//NOW, COEFFICIENTS HOLDS THE ARGUMENTS NECESSARY TO BUILD THE FUNCTION.
					//ARGUMENT LENGTH - 3 REPRESENTS TOTAL ARGS MINUS THE PERCENTAGE VALUE MINUS THE UPPER AND LOWER BOUNDS; STARTS AT 1 TO NULLIFY ARGS[0].
				}
			}catch (Exception e) {
				throw new IllegalArgumentException ("Arguments with percentage specified are invalid. Try again.");
			}
			// if (lowerBound > upperBound) {
			// 	throw new IllegalArgumentException ("Upperbound must be greater than lowerBound");
			// }
		}else{
			try {
				coefficients = new double[args.length - 3];
			} catch (Exception e) {
				throw new IllegalArgumentException ("Invalid number of arguments for default percentage.");
			}
			if (coefficients.length == 0) {
				throw new IllegalArgumentException ("Must enter coefficient arguments. For example: if trying to find sin(x), enter: java Riemann sin 0 1");
			}
			try {
				//BY DEFAULT THERE ARE 3 ARGUMENTS WE HAVE TO IGNORE.
				lowerBound = Double.parseDouble(args[args.length - 2]);
				upperBound = Double.parseDouble(args[args.length - 1]);
				for (int i = 1; i < args.length - 2; i++) {
					coefficients[i - 1] = Double.parseDouble(args[i]);
					//NOW, COEFFICIENTS HOLDS THE ARGUMENTS NECESSARY TO BUILD THE FUNCTION.
					//AS PERCENTAGE VALUE IS NOT SPECIFIED, ARGUMENT LENGTH - 2 REPRESENTS TOTAL ARGS MINUS THE LOWER AND UPPER BOUNDS; STARTS AT 1 TO NULLIFY ARGS[0].
				}
			}catch (Exception e) {
				throw new IllegalArgumentException ("Arguments with default percentage are invalid. Try again.");
			}
			if (lowerBound > upperBound) {
				throw new IllegalArgumentException ("Upperbound must be greater than lowerBound");
			}
		}

		switch(args[0]) {
			case "poly" :
			    Riemann.poly();
			    break;
			case "sin" :
			    Riemann.sin();
			    break;
			case "cos" :
				Riemann.cos();
				break;
			case "tan" :
				Riemann.tan();
				break;
			case "csc" :
				Riemann.csc();
				break;
			case "sec" :
				Riemann.sec();
				break;
			case "cot" :
				Riemann.cot();
				break;
			case "log" :
			    Riemann.log();
			    break;
			case "exp" :
			    Riemann.exp();
			    break;
			case "sqrt" :
			    Riemann.sqrt();
			    break;
			default:
			    throw new IllegalArgumentException ("This type of function is not supported by the program.");
				//if not a valid type, should break the statement.
		}
	}

/**
 *Calculating area is simple.
 Step 1: Know the equation and the bounds.
 Step 2: Find the distance between both bounds.
 Step 3: Know how many rectangles you want to make.
 Step 4: Find the locations of where those rectangles should start. Midpoint? Width of rectangle divided by 2.
 Step 5: Find the y value at that location.
 Step 6: Multiply that yValue by the width of the rectangle to find area of one rectangle.
 Step 7: Add up all of the areas of the rectangles to find the approximate area.
 Step 8: Increase the number of rectangles until current divided by previous is less than the percent value.
 */

	public static double poly () {
		width = Math.sqrt(Math.pow(upperBound - lowerBound, 2));
		do {
			previousArea = currentArea;
			currentArea = 0;
			double xValue = (lowerBound + (width/numberOfRectangles * 0.5));

			//should set previousArea to 0.
			for (double i = xValue; i < upperBound; i += width/numberOfRectangles) {
				//i represents the xValue. now, we find the yValue.
				for (int k = 0; k < coefficients.length; k++) {
					height += coefficients[k] * Math.pow(i, k);
				}
				currentArea += height * width/numberOfRectangles;
				height = 0;
				//now we have found the area of one rectangle.
				//will iterate through until currentArea represents all areas of all rectangles.
			}
			numberOfRectangles += 1.0;
		}while (Math.abs(currentArea/previousArea) == 0 || Math.abs((currentArea - previousArea)/currentArea) >= percentage);
		return (currentArea);
	}

	public static double sin () {
		width = Math.sqrt(Math.pow(upperBound - lowerBound, 2));
		do {
			previousArea = currentArea;
			currentArea = 0;

			//should set previousArea to 0.
			for (double i = (lowerBound + (width/numberOfRectangles/2.0)); i < upperBound; i += width/numberOfRectangles) {
				//i represents the xValue. now, we find the yValue.
				for (int k = 0; k < coefficients.length; k++) {
					height += coefficients[k] * Math.pow(i, k);
				}
				currentArea += Math.sin(height) * width/numberOfRectangles;
				height = 0;
				//now we have found the area of one rectangle.
				//will iterate through until currentArea represents all areas of all rectangles.
			}
			numberOfRectangles += 1.0;
		}while (Math.abs(currentArea/previousArea) == 0 || Math.abs((currentArea - previousArea)/currentArea) >= percentage);
		return (currentArea);
	}

	public static double cos () {
		width = Math.sqrt(Math.pow(upperBound - lowerBound, 2));
		do {
			previousArea = currentArea;
			currentArea = 0;
			//should set previousArea to 0.
			for (double i = (lowerBound + (width/numberOfRectangles/2.0)); i < upperBound; i += width/numberOfRectangles) {
				//i represents the xValue. now, we find the yValue.
				for (int k = 0; k < coefficients.length; k++) {
					height += coefficients[k] * Math.pow(i, k);
				}

				currentArea += Math.cos(height) * width/numberOfRectangles;
				height = 0;
				//now we have found the area of one rectangle.
				//will iterate through until currentArea represents all areas of all rectangles.
			}
			numberOfRectangles += 1.0;
		}while (Math.abs(currentArea/previousArea) == 0 || Math.abs((currentArea - previousArea)/currentArea) >= percentage);
		return (currentArea);
	}

	public static double tan () {
		width = Math.sqrt(Math.pow(upperBound - lowerBound, 2));
		do {
			previousArea = currentArea;
			currentArea = 0;

			//should set previousArea to 0.
			for (double i = (lowerBound + (width/numberOfRectangles/2.0)); i < upperBound; i += width/numberOfRectangles) {
				//i represents the xValue. now, we find the yValue.
				for (int k = 0; k < coefficients.length; k++) {
					height += coefficients[k] * Math.pow(i, k);
				}
				currentArea += (Math.sin(height)/Math.cos(height)) * width/numberOfRectangles;
				height = 0;
				//now we have found the area of one rectangle.
				//will iterate through until currentArea represents all areas of all rectangles.
			}
			numberOfRectangles += 1.0;
		}while (Math.abs(currentArea/previousArea) == 0 || Math.abs((currentArea - previousArea)/currentArea) >= percentage);
		return (currentArea);
	}

	public static double csc () {
		width = Math.sqrt(Math.pow(upperBound - lowerBound, 2));
		do {
			previousArea = currentArea;
			currentArea = 0;

			//should set previousArea to 0.
			for (double i = (lowerBound + (width/numberOfRectangles/2.0)); i < upperBound; i += width/numberOfRectangles) {
				//i represents the xValue. now, we find the yValue.
				for (int k = 0; k < coefficients.length; k++) {
					height += coefficients[k] * Math.pow(i, k);
				}
				currentArea += (1.0/Math.sin(height)) * width/numberOfRectangles;
				height = 0;
				//now we have found the area of one rectangle.
				//will iterate through until currentArea represents all areas of all rectangles.
			}
			numberOfRectangles += 1.0;
		}while (Math.abs(currentArea/previousArea) == 0 || Math.abs((currentArea - previousArea)/currentArea) >= percentage);
		return (currentArea);
	}

	public static double sec () {
		width = Math.sqrt(Math.pow(upperBound - lowerBound, 2));
		do {
			previousArea = currentArea;
			currentArea = 0;

			//should set previousArea to 0.
			for (double i = (lowerBound + (width/numberOfRectangles/2.0)); i < upperBound; i += width/numberOfRectangles) {
				//i represents the xValue. now, we find the yValue.
				for (int k = 0; k < coefficients.length; k++) {
					height += coefficients[k] * Math.pow(i, k);
				}

				currentArea += (1/Math.cos(height)) * width/numberOfRectangles;
				height = 0;
				//now we have found the area of one rectangle.
				//will iterate through until currentArea represents all areas of all rectangles.
			}
			numberOfRectangles += 1.0;
		}while (Math.abs(currentArea/previousArea) == 0 || Math.abs((currentArea - previousArea)/currentArea) >= percentage);
		return (currentArea);
	}

	public static double cot () {
		width = Math.sqrt(Math.pow(upperBound - lowerBound, 2));
		do {
			previousArea = currentArea;
			currentArea = 0;

			//should set previousArea to 0.
			for (double i = (lowerBound + (width/numberOfRectangles/2.0)); i < upperBound; i += width/numberOfRectangles) {
				//i represents the xValue. now, we find the yValue.
				for (int k = 0; k < coefficients.length; k++) {
					height += coefficients[k] * Math.pow(i, k);
				}
				currentArea += (Math.cos(height)/Math.sin(height)) * width/numberOfRectangles;
				height = 0;
				//now we have found the area of one rectangle.
				//will iterate through until currentArea represents all areas of all rectangles.
			}
			numberOfRectangles += 1.0;
		}while (Math.abs(currentArea/previousArea) == 0 || Math.abs((currentArea - previousArea)/currentArea) >= percentage);
		return (currentArea);
	}

	public static double log () {
		width = Math.sqrt(Math.pow(upperBound - lowerBound, 2));
		do {
			previousArea = currentArea;
			currentArea = 0;

			//should set previousArea to 0.
			for (double i = (lowerBound + (width/numberOfRectangles/2.0)); i < upperBound; i += width/numberOfRectangles) {
				//i represents the xValue. now, we find the yValue.
				for (int k = 0; k < coefficients.length; k++) {
					height += coefficients[k] * Math.pow(i, k);
				}
				currentArea += Math.log(height) * width/numberOfRectangles;
				height = 0;
				//now we have found the area of one rectangle.
				//will iterate through until currentArea represents all areas of all rectangles.
			}
			numberOfRectangles += 1.0;
		}while (Math.abs(currentArea/previousArea) == 0 || Math.abs((currentArea - previousArea)/currentArea) >= percentage);
		return (currentArea);
	}

	public static double exp () {
		width = Math.sqrt(Math.pow(upperBound - lowerBound, 2));
		do {
			previousArea = currentArea;
			currentArea = 0;

			//should set previousArea to 0.
			for (double i = (lowerBound + (width/numberOfRectangles/2.0)); i < upperBound; i += width/numberOfRectangles) {
				//i represents the xValue. now, we find the yValue.
				for (int k = 0; k < coefficients.length; k++) {
					height += coefficients[k] * Math.pow(i, k);
				}
				currentArea += Math.exp(height) * width/numberOfRectangles;
				height = 0;
				//now we have found the area of one rectangle.
				//will iterate through until currentArea represents all areas of all rectangles.
			}
			numberOfRectangles += 1.0;
		}while (Math.abs(currentArea/previousArea) == 0 || Math.abs((currentArea - previousArea)/currentArea) >= percentage);
		return (currentArea);
	}

	public static double sqrt () {
		width = Math.sqrt(Math.pow(upperBound - lowerBound, 2));
		do {
			previousArea = currentArea;
			currentArea = 0;

			//should set previousArea to 0.
			for (double i = (lowerBound + (width/numberOfRectangles/2.0)); i < upperBound; i += width/numberOfRectangles) {
				//i represents the xValue. now, we find the yValue.
				for (int k = 0; k < coefficients.length; k++) {
					height += coefficients[k] * Math.pow(i, k);
				}
				currentArea += Math.sqrt(Math.abs(height)) * width/numberOfRectangles;
				height = 0;
				//now we have found the area of one rectangle.
				//will iterate through until currentArea represents all areas of all rectangles.
			}
			numberOfRectangles += 1.0;
		}while (Math.abs(currentArea/previousArea) == 0 || Math.abs((currentArea - previousArea)/currentArea) >= percentage);
		return (currentArea);
	}
	public static void main (String[] args) {
		Riemann riemann = new Riemann();
		riemann.argumentHandler(args);
		System.out.println(currentArea);
	}

}
