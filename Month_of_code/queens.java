import java.util.Scanner;


public class queens {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while(scan.hasNextLine()){
		int rows[] = new int[8];
		int cols[] = new int[8];
		int diagp[] = new int[15];
		int diagr[] = new int[15];
		
		int queens = 0;
		
		for(int row = 0 ; row < 8; row ++){
			String srow = scan.nextLine();
			for(int col = 0; col < 8; col++){
				if(srow.charAt(col) == '*'){
					queens++;
					rows[row]++;
					cols[col]++;
					diagr[row+col]++;
					diagp[7-row+col]++;
				}
			}
		}	
		boolean valid = true;
		for(int i = 0,j=0; i < 15; i++){
			if(rows[j]>1){
				valid= false; break;
			}
			if(cols[j]>1){
				valid = false;break;
			}
			if(diagp[i]>1){
				valid = false;break;
			}
			if(diagr[i]>1){
				valid = false;break;
			}
			if(j<7)j++;
		}
		
		if(valid && queens==8)
			System.out.println("valid");
		else
			System.out.println("invalid");
		
		break;
		}
	}

}
