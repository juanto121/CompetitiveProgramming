import java.util.Scanner;
class DemeritPoints{
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);
		int cases = Integer.parseInt(scan.nextLine());
		int year, month, day;

		for(int i = 0; i < cases; i++){
			scan.nextLine();//blank line.
			String issue = scan.nextLine();
			year = Integer.parseInt(issue.substring(0,4));
			month = Integer.parseInt(issue.substring(4,6));
			day = Integer.parseInt(issue.substring(6,8));
			System.out.println(year+" "+month+" "+day);
			
		}
	}
}