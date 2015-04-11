import java.util.Scanner;


public class camelTrading {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int cases = Integer.parseInt(scan.nextLine());
		for(int t = 0;t < cases; t ++){
			String expresion = scan.nextLine();
			String sumsplit[] = expresion.split("\\+");
			
			long max = 0;
			for(int i = 0; i < sumsplit.length; i++){
				max += solveprod(sumsplit[i]);
			}
			

			
			String prodsplit[] = expresion.split("\\*");
			
			long min = 1;
			for(int i = 0; i < prodsplit.length; i++){
				min *= solvesum(prodsplit[i]);
			}
			
			System.out.println(String.format("The maximum and minimum are %d and %d.", min, max));
			
		}
	}
	
	public static long solvesum(String exp){
		int len = exp.length();
		if(len<3){
			if(exp.isEmpty())return 1;
			return Long.parseLong(exp);
		}else{
			int index = exp.indexOf('+');
			String left = exp.substring(0, index);
			String right = exp.substring(index+1,len);
			
			return solvesum(left)+solvesum(right);
		}
	}
	
	public static long solveprod(String exp){
		int len = exp.length();
		
		if(len<3){
			if(exp.isEmpty())return 1;
			return Long.parseLong(exp);
		}else{
			int index = exp.indexOf('*');
			String left = exp.substring(0, index);
			String right = exp.substring(index+1,len);
			return solveprod(left)*solveprod(right);
		}
	}

}
