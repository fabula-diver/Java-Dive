// Joyce's Chapter 13, Game Zone 3
import javax.swing.JOptionPane;
import java.nio.file.*;
import java.io.*;

public class Login 
{
	public static void main(String[] args) 
	{
		Path file = Paths.get("GamePlayers.txt");
		try 
		{
			//Files.exists(file, null); // not sure why null is needed, removing it makes the prog ignore the catch clause. 
						    // Probably because without null, the statement returns true or false, which is useless without an if statement.
			file.getFileSystem().provider().checkAccess(file); // checkAccess() is not possible. Joyce Farrell was wrong!
		} catch(Exception e) {
			System.out.println("File cannot be used for this application.");
			System.exit(1); // 1 = unsuccessful termination
		}
		String userId;
		String password;
		String s;
		String delim = ",";
		String[] array = new String[2];
		boolean okToGo = false;
		
		userId = JOptionPane.showInputDialog(null, "Enter your user ID");
		password = JOptionPane.showInputDialog(null, "Enter your password");
		
		try 
		{
			InputStream input = new BufferedInputStream(Files.newInputStream(file));
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			s = reader.readLine();
			
			while(s != null) 
			{
				array = s.split(delim);
				if(array[0].equals(userId) && array[1].equals(password)) 
				{
					okToGo = true;
					break; // break out of the while loop early if id and password have been found
				}
				s = reader.readLine(); // continue to read the next line if username and password are not correct. Without this line, the while loop runs forever.
			}
			reader.close();
		} catch(Exception e) { // Exception e is way too general. Hopefully we can improve it in the future.
			System.out.println("Message:" + e);
		}
		if(!okToGo)
			JOptionPane.showMessageDialog(null, "Sorry - Your ID or password was invalid.");
		else
			JOptionPane.showMessageDialog(null, "Log in successfully!");
	}
}
