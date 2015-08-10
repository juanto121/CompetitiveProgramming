import java.util.Scanner;


public class counting {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		while(s.hasNextLine()){
			int n= Integer.parseInt(s.nextLine());
			if(n<3){
				System.out.println(0);
			}else{
				int posibilidades= 0;
				int k=2;
				double x;
				do{
					x = (double)(n-(k*(k-1))/2)/(double)k;
					if(x%1==0 && x>0){
						posibilidades++;
					}
					k++;
				}while(x>0);
				System.out.println(posibilidades);		
			}				
		}
	}
}
