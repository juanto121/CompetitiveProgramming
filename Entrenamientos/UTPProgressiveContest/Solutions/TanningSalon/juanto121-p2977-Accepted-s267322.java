import java.util.Scanner;

public class Main{

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String line[] = scan.nextLine().split(" ");
		boolean inTanning[] = new boolean[30];
		while(line.length == 2){
			int salons = Integer.parseInt(line[0]);
			
			String customers = line[1];
			int walked = 0;
			
			for(int i = 0; i < customers.length(); i++){
				if( inTanning[(customers.charAt(i)-'A')] ){
					salons++;
					inTanning[(customers.charAt(i)-'A')] = false;
				}else{
					if( salons-1 >= 0){
						salons--;
						inTanning[(customers.charAt(i)-'A')] = true;
					}else{
						walked++;
					}
						
				}
			}
			if(walked == 0){
				System.out.println("All customers tanned successfully.");
			}else{
				System.out.println(String.format("%d customer(s) walked away.", walked/2));
			}
			

			line = scan.nextLine().split(" ");
			
		}
		
	}

}
