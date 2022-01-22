import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.FileChooser;

import java.io.*;

public class EggyPeggy extends Application 
{
	@Override
	public void start(Stage primaryStage) throws IOException 
	{
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select File");
		
		/* showOpenDialog shows a new file open dialog in which one file can be selected.
		 * Returns a File object representing the user's chosen file or null if no file was chosen.*/
		File inFile = fileChooser.showOpenDialog(primaryStage);
		File outFile = fileChooser.showOpenDialog(primaryStage);
		
		BufferedWriter bufWriter = new BufferedWriter(new FileWriter(outFile));
		BufferedReader bufReader = new BufferedReader(new FileReader(inFile));

		String s = bufReader.readLine(); // read a line
		while (s != null) // null = end of the file
		{
			for (int i = 0; i < s.length(); i++)
			{
				char c = s.charAt(i);
				if (isVowel(c)) 
				{
					bufWriter.write("egg" + c);
				} 
				else 
				{
					bufWriter.write("" + c);
				}
			} // end for = end of a read line
			
			// when the end of a line has been reached, add \n = go to the next line
			bufWriter.write("\n");
			
			// then read it (the next line) (we're still inside the while loop)
			s = bufReader.readLine();
			
		} // end while (s == null, end of the file)
		
		// close the files after we're done (VERY IMPORTANT)
		bufReader.close();
		bufWriter.close();
	}
	
	private static boolean isVowel(char c) 
	{
		boolean flag = false;
		if (c == 'a' || c == 'A' || c == 'e' || c == 'E' || c == 'i'
				|| c == 'I' || c == 'o' || c == 'O' || c == 'u' || c == 'U')
			flag = true;
		return flag;
	}
	
	public static void main(String[] args) 
	{
		Application.launch(args);
	}
}