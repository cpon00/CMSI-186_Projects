import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
public class Collatz{
	public static void main (String[] args) {
		int count = 0;
		BrobInt value = new BrobInt(args[0]);
		while (value.compareTo(BrobInt.ONE) == 1) {
			System.out.println("Current Value:    " + value);
			if (value.remainder(BrobInt.TWO) != BrobInt.ZERO) {
				value = (value.multiply(BrobInt.THREE)).add(BrobInt.ONE);
			}else{
				value = value.divide(BrobInt.TWO);
			}
			count++;
		}
		System.out.println("Number of Steps:  " + count);
	}
}
