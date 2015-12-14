
import java.util.Arrays;
import java.util.Scanner;

class Main {

	public static void main(String[] args) {
		  Scanner scan = new Scanner(System.in);
		  
		  while(scan.hasNextLine()){
			  String []inp = scan.nextLine().split(" ");
			  int solvedTeam[][] = new int[101][13];
			  int lb[][] = new int[12][2];
			  for(int k =0 ; k < 12; k++) Arrays.fill(lb[k], -1);
			  for(int u = 0; u < 100; u++)Arrays.fill(solvedTeam[u],-1);
			  int problems = Integer.parseInt(inp[0]);
			  int teams = Integer.parseInt(inp[1]);
			  int subm = Integer.parseInt(inp[2]);
			  for(int i = 0 ; i < subm; i++){
				  String submi[] = scan.nextLine().split(" ");
				  int pos = submi[2].charAt(0)-'A';
				  int id = Integer.parseInt(submi[1]);
				  if (submi[3].equals("Yes") && solvedTeam[id][pos] == -1){
					  lb[pos][0] = Integer.parseInt(submi[0]);
					  lb[pos][1] = Integer.parseInt(submi[1]);
					  solvedTeam[id][pos] = 1;
				  }
			  }
			  for(int i = 0; i < problems;i++){
				  if(lb[i][0] == -1){
					  System.out.println(String.format("%c - -", (char)(i+'A')));
				  }else{
					  System.out.println(String.format("%c %d %d", (char)(i+'A'),lb[i][0],lb[i][1]));
				  }
			  }
		  }

	}

}
