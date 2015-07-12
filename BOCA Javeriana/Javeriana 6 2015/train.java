import java.util.Scanner;


public class train {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		long cap = scan.nextLong();
		long n = scan.nextLong();
		long current = 0; long stay = 0;
		boolean poss = true;
		for(int s = 0; s  < n; s++){
			long left = scan.nextLong();
			
			long enter = scan.nextLong();
			stay = scan.nextLong();
			long net = (current+enter)-left;
   		    poss = ( net >= 0 && cap >= net && !(cap-net > 0 && stay>0));
   		    if(s==0) if(left!=0) poss = false;
   		    if(!poss) break;
			current = net;
		}
		System.out.println(poss&&current==0&&stay==0?"possible":"impossible");
		
	}

}
