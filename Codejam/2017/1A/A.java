import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class A {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int cases = Integer.parseInt(scan.nextLine());
		
		for(int t = 0 ; t < cases; t++){
			String desc[] = scan.nextLine().split(" ");
			int r = Integer.parseInt(desc[0]);
			int c = Integer.parseInt(desc[1]);
			String lineS[] = new String[r];
			StringBuilder stb = new StringBuilder();
			String res[][] = new String[r][c];
						
			for(int j = 0; j < r; j++){
				String line = scan.nextLine();
				lineS[j] = line;
				String lastSeen = "?";
				String current;
				for(int k = 0; k < c; k++){
					current = line.charAt(k)+"";

					if(!lastSeen.equals("?")){
						if(current.equals("?")){
							res[j][k] = lastSeen;
						}else{
							lastSeen = current;
						}
					}
					if(!current.equals("?")){
						lastSeen = current;
						res[j][k] = current;
					}
				}
				for(int k = c-1; k>= 0; k--){
					current = line.charAt(k)+"";

					if(!lastSeen.equals("?")){
						if(current.equals("?")){
							res[j][k] = lastSeen;
						}else{
							lastSeen = current;
						}
					}
					if(!current.equals("?")){
						lastSeen = current;
						res[j][k] = current;
					}
				}
			}
			
			for(int p = 0; p < r; p++){
				StringBuilder cline = new StringBuilder();
				for(int e = 0; e < c; e++){
					cline.append(res[p][e]==null?"?":res[p][e]);
				}
				lineS[p] = cline.toString();
			}
			
			for(int j = 0; j < c; j++){
				
				String lastSeen = "?";
				String current;
				for(int k = 0; k < r; k++){
					String line = lineS[k];
					current = line.charAt(j)+"";

					if(!lastSeen.equals("?")){
						if(current.equals("?")){
							res[k][j] = lastSeen;
						}else{
							lastSeen = current;
						}
					}
					if(!current.equals("?")){
						lastSeen = current;
					}
				}
				for(int k = r-1; k >= 0; k--){
					String line = lineS[k];
					current = line.charAt(j)+"";

					if(!lastSeen.equals("?")){
						if(current.equals("?")){
							res[k][j] = lastSeen;
						}else{
							lastSeen = current;
						}
					}
					if(!current.equals("?")){
						lastSeen = current;
					}
				}
			}
			
			StringBuilder reS = new StringBuilder();
			for(int i = 0; i < r; i++){
				for(int j = 0; j < c; j++){
					reS.append(res[i][j]);
				}
				reS.append(String.format("%n"));
			}
			if(t == 0)
				System.out.print(String.format("Case #%d:%n%s", t+1, reS));
			else
				System.out.print(String.format("Case #%d:%n%s", t+1, reS));
		}
	}
	

}
