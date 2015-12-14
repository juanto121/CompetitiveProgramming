import java.util.Scanner;


public class simonsays {


	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = Integer.parseInt(scan.nextLine());
		for(int N = 0; N < n; N++){
			String line = scan.nextLine();
			String instruction[] = line.split(" ");
			if(instruction[0].equals("Simon")){
				if(instruction[1].equals("says")){
					System.out.println(String.format(" %s", line.substring(11)));
				}
			}
		}
	}

}
