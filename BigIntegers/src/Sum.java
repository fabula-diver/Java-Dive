import java.util.*;
import java.io.*;

public class Sum 
{
	// the program can sum numbers with 25 digits at most
	public static final int DIGITS = 25;

	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner input = new Scanner(new File("sum.txt")); // open the file, requires throws FileNotFoundException
		processFile(input);
	} // end main
	
	/* Reads and processes the input file; then reports the number of lines processed */
	public static void processFile(Scanner input) 
	{
		int lines = 0;
		while (input.hasNextLine()) 
		{
			String text = input.nextLine(); // grab the next line
			Scanner data = new Scanner(text); // break input (a line) into tokens, with whitespace as the delimiter
			processLine(data);
			lines++;
		}
		
		System.out.println();
		System.out.println("Total lines = " + lines);
	}
	
	/* Processes the lines read from the input file. Will be called by processFile */
	public static void processLine(Scanner data) 
	{
		int[] result = new int[DIGITS];
		String next = data.next(); // return a string token in the Scanner object (whitespace as delimiter)
		transfer(next, result); // store String number into array
		System.out.print(next); // print out the first number of the addition prompt; next is String, so can be printed just fine
		while(data.hasNext()) // if there's another number in the Scanner object after data.next()?
		{
			next = data.next(); // grab the next String number
			int[] number = new int[DIGITS]; // create a second array to store it
			transfer(next, number); // put it into the newly created array
			addTo(result, number); // find the sum. Result stores in result (because if there's still another number, the number array will be used)
			System.out.print(" + " + next); // print out the next number in the addition prompt
		}
		System.out.print(" = ");
		print(result);
		System.out.println();
	}
	/* Transfers a String number into an array */
	public static void transfer(String data, int[] digits) 
	{
		int i = data.length() - 1; // String is like an array; get the last index
		int j = DIGITS - 1; // get the last index of the 25-element array
		while (i >= 0) // we're talking about indices (not the number of times), so =0 must be included
		{
			digits[j] = Character.getNumericValue(data.charAt(i)); // extract a character from String; then turn it into int and put in the array. Start from the last digit
			i--; // the process works backward from last to start
			j--; // the process works backward from last to start
		}
	}
	
	/* Calculates the sum and stores the resukt in the sum array */
	public static void addTo(int[] sum, int[] number) 
	{
		int carry = 0;
		for (int i = DIGITS - 1; i >= 0; i--) // start from last position
		{
			int next = sum[i] + number[i] + carry;
			sum[i] = next % 10; // store the last digit of next in the sum array (for example, 12 % 10 = 2, store 2)
			carry = next / 10; // the carry digit should be the first number (for example, 12 / 10 = 1, store 1)
		}
		if (carry > 0) // at the end of the process, if the carray digit =/= 0, then 25-element array is not enough to store the result -> overflow!
			throw new RuntimeException("overflow");
	}
	
	/* Prints the result */
    public static void print(int[] digits) 
    {
        int start = 0;
        while (start < DIGITS - 1 && digits[start] == 0)
            start++;
        for (int i = start; i < DIGITS; i++)
            System.out.print(digits[i]);
    }
} // end Sum
