import java.util.Scanner;


public class DIV2_310_B_FakeNums {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = Integer.parseInt(scan.nextLine());
		String g[] = scan.nextLine().split(" ");
		int firstGear = Integer.parseInt(g[0]);
		int steps = (n-firstGear)%n;
		boolean fake = false;
		int neg = -1;
		for(int i = 0; i < n; i++){
			neg *= -1;
			int currentGear = Integer.parseInt(g[i]);
			int cstep = currentGear + steps*neg;
			
			if(cstep<0)
				cstep+=n;
						
			if( (cstep)%n != i){
				fake = true;
				break;
			}
		}
		if(fake){
			System.out.println("No");
		}else{
			System.out.println("Yes");
		}
	}

}
