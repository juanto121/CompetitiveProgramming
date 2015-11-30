import java.util.Scanner;


public class conundrum {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		String text = scan.nextLine();
		int len = text.length();
		String per = "PER";
		int days = 0;
		for(int i = 0; i < len; i++){
			if(text.charAt(i)!=per.charAt(i%3)) days++;
		}
		
		System.out.println(days);
	}

}
