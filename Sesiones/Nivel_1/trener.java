import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;


public class trener {

	public static void main(String[] args) {
		 Scanner scan = new Scanner(System.in);
		 int mp[] = new int[26];
		 while(scan.hasNextLine()){
			 Arrays.fill(mp, 0);
			 int N = Integer.parseInt(scan.nextLine());
			 for(int i = 0; i < N ; i++){
				 String name = scan.nextLine();
				 int pos = name.charAt(0)-'a';
				 mp[pos] ++;				 
			 }
			 
			 boolean any = false;			 
			 for(int i = 0; i < 26; i++){
				 if(mp[i] >= 5){
					 System.out.print((char)(i+'a'));
					 any = true;
				 }
			 }
			 if(!any) System.out.print("PREDAJA");
			 System.out.print("\n");
		 }
		 

	}

}
