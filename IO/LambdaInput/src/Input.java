import java.io.*;

public class Input 
{

	public static void main(String[] args) 
	{
		char choice;
		
		char character;
		String text;
		double doubleNum;
		float floatNum;
		int integer;
		long longNum;
		
		System.out.println("This demonstrates Input class methods");
		
		do 
		{
			System.out.print("\nSelect one of these options:"
					+ "\n\tt: readLine()\t reads a line of (t)ext"
					+ "\n\tc: readChar()\t reads a single (c)haracter"
					+ "\n\td: readDouble()\t reads a (d)ouble number"
					+ "\n\tf: readFloat()\t reads a (f)loat number"
					+ "\n\ti: readInt()\t reads an (i)nt number"
					+ "\n\tl: readLong()\t reads a (l)ong number"
					+ "\n\tq: quit"
					+ "\nEnter t, c, d, f, i, l, or q: ");
			choice = Input.readChar();
			
			switch(choice) 
			{
			case 't' -> 
			{
				System.out.print("Enter a line of text: ");
				text = Input.readLine();
				System.out.println("text = " + text);
			}
			case 'c' ->
			{
				System.out.print("Enter a character: ");
				character = Input.readChar();
				System.out.println("character = " + character);
			}
			case 'd' -> 
			{
				System.out.print("Enter a double number: ");
				doubleNum = Input.readDouble();
				System.out.println("double number = " + doubleNum);
			}
			case 'f' ->
			{
				System.out.print("Enter a float number: ");
				floatNum = Input.readFloat();
				System.out.println("float number = " + floatNum);
			}
			case 'i' -> 
			{
				System.out.print("Enter an integer: ");
				integer = Input.readInt();
				System.out.println("integer number = " + integer);
			}
			case 'l' ->
			{
				System.out.print("Enter a long number: ");
				longNum = Input.readLong();
				System.out.println("long number = " + longNum);
			}
			case 'q' -> 
			{
				System.out.println("Bye!");
				System.exit(1);
			}
			default -> System.out.println("Invalud entry!");
			}
		} while (choice != 'q');
	} // end main
	
	/* This method reads a line of text and returns a string */
	public static String readLine() 
	{
		String string = "";
		try 
		{
			string = (new BufferedReader(new InputStreamReader(System.in))).readLine();
		}
		catch (IOException e) 
		{
			System.out.println(e.getMessage());
			System.exit(0);
		}
		
		return string;
	} // end readLine
	
	/* This method reads a single character and returns a char 
	 * Pressing enter returns the \n character */
	public static char readChar() 
	{
		char character = ' '; // <space>; can't leave it empty
		String string = readLine();
		
		if (string.length() == 1) 
		{
			character = string.charAt(0);
		}
		else if (string.length() == 0) 
		{
			character = '\n';
		}
		else 
		{
			System.out.print("Invalid entry - expecting a single character.\n");
			System.exit(0);
		}
		
		return character;
	} // end readChar
	
	/* This method reads a number and returns a double value */
	public static double readDouble() 
	{
		double num = 0;
		String string = readLine();
		
		try 
		{
			num = Double.parseDouble(string.trim());
		}
		catch (NumberFormatException nfe) 
		{
			System.out.print("Invalid entry - expecting a double.\n");
			System.exit(0);
		}
		
		return num;
	} // end readDouble
	
	/* This method reads a number and returns a float value */
	public static float readFloat() 
	{
		float num = 0;
		String string = readLine();
		
		try 
		{
			num = Float.parseFloat(string.trim());
		}
		catch (NumberFormatException nfe) 
		{
			System.out.print("Invalid entry - expecting a float.\n");
			System.exit(0);
		}
		
		return num;
	} // end readFloat
	
	/* This method reads a number and returns an int value */
	public static int readInt() 
	{
		int num = 0;
		String string = readLine();
		string = getZeroFractionRemoved(string.trim());
		
		try 
		{
			num = Integer.parseInt(string.trim());
		}
		catch (NumberFormatException nfe) 
		{
			System.out.print("Invalid entry - expecting an int.\n");
			System.exit(0);
		}
		
		return num;
	} // end readInt

	/* This method reads a number and returns a long value */
	public static long readLong() 
	{
		long num = 0;
		String string = readLine();
		string = getZeroFractionRemoved(string.trim());
		
		try 
		{
			num = Long.parseLong(string.trim());
		}
		catch (NumberFormatException nfe) 
		{
			System.out.print("Invalid entry - expecting a long.\n");
			System.exit(0);
		}
		
		return num;
	} // end readLong
	
	/* If the given string contains a zero fraction, remove it and return the result
	 * Otherwise, return the original string */
	private static String getZeroFractionRemoved(String string) 
	{
		int dotIndex; 	// position of decimal point in the string
		int i;		// current position
		int lastIndex;	// position of last character within string
		
		dotIndex = string.indexOf("."); // -1 means . is not in the string
		if (dotIndex != -1) 
		{
			// check for all 0's to the right of the dot
			i = dotIndex + 1; // the index of the first 0 (to the right of the .)
			lastIndex = string.length() - 1; // index of the last element in the string
			while ((i <= lastIndex) && (string.charAt(i) == '0')) 
			{
				i++; // get the index of the farthest 0 to the right of the .
				     // if all digits to the right of the . are 0, then eventually i > lastIndex
			}
			
			// if all chars to the right of the . are 0's, remove fraction
			// if not, keep the fraction
			if (i > lastIndex)
				string = string.substring(0, dotIndex); // dotIndex exclusive
		}
		
		return string;
	} // end getZeroFractionRemoved
	
} // end Input
