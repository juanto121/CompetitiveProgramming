import java.util.Scanner;


public class prophunt {

	public static void main(String[] args) {
		Scanner scan  = new Scanner(System.in);
		int props = scan.nextInt();
		int hunters = scan.nextInt();
		int objects = scan.nextInt();
		
		if( hunters > (objects-props) ){
			System.out.println("Hunters win!");
		}else{
			System.out.println("Props win!");
		}

	}

}
