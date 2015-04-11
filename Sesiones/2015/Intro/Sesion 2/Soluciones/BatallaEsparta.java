
import java.util.*;
import java.lang.Math.*;

/* Name of the class has to be "Main" only if the class is public. */
class Main
{
	public static void main (String[] args) throws java.lang.Exception
	{
		Scanner scan = new Scanner(System.in);
		String spartans[] = scan.nextLine().split(" ");
		
		for(int i = 0; i < spartans.length; i++){
			System.out.println(Integer.parseInt(spartans[i])*2);
		}

	}
}