import java.util.*;
import java.lang.Math.*;

/* Name of the class has to be "Main" only if the class is public. */
class Main
{
	public static void main (String[] args) throws java.lang.Exception
	{
		Scanner scan = new Scanner(System.in);
		int a = Integer.parseInt(scan.nextLine());
		int b = Integer.parseInt(scan.nextLine());

		System.out.println(String.format("%d + %d = %d",a,b,a+b));
		System.out.println(String.format("%d - %d = %d",a,b,a-b));
		System.out.println(String.format("%d * %d = %d",a,b,a*b));
		System.out.println(String.format("%d / %d = %d",a,b,a/b));
        System.out.println(String.format("%d %% %d = %d",a,b,a%b));
        System.out.println(String.format("%d ^ %d = %d",a,b,(int)Math.pow(a/1.0,b/1.0) ));

	}
}