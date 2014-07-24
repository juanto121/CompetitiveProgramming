import java.util.Scanner;
 class LifeUniverse {

    public static void main(String args[])
    {
    	int a;
    	Scanner scan=new Scanner(System.in);
        while(scan.hasNext())
        {
        a=scan.nextInt();
        if(a==42)
        	System.exit(0);
        else
        System.out.println (a);
        
        }
    }
    
    
}
