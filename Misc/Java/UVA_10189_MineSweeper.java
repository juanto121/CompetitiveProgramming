import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class UVA_10189_MineSweeper {
	public static void main(String[] args) {
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		String desc = null;
		int cases = 1;
		try{
		while( (desc = scan.readLine()) != null){
			
			StringTokenizer str = new StringTokenizer(desc);
			
			int rows = Integer.parseInt(str.nextToken());
			int cols = Integer.parseInt(str.nextToken());
			
			if(rows==0 && cols==0)break;
			
			char table[][] = new char[rows][cols];
			int rest[][] = new int[rows][cols];
			
			for(int i = 0; i < rows ; i++){
				String row = scan.readLine();
				for(int j = 0; j < cols; j++){
					table[i][j] =  row.charAt(j);
				}
			}
			
			for(int i = 0; i < rows ; i++){
				for(int j = 0; j < cols; j++){
					
					if(table[i][j]=='*'){
						
						rest[i][j] = Integer.MIN_VALUE;
						
						int startx = j-1<0?0:j-1;
						int endx = j+1>=cols?j:j+1;
						int starty = i-1<0?0:i-1;
						int endy = i+1>=rows?i:i+1;
						
						for(int k = startx; k <= endx; k++){
							for(int r = starty; r <= endy; r++){
								rest[r][k] += 1;
							}
						}						
					}
					
				}
			}
			
			int i,j;
			if(cases != 1) System.out.println();
			System.out.println("Field #" + cases + ":");
			for(i=0;i<rows;i++){
				for(j=0;j<cols;j++){
					if(rest[i][j]>=0) System.out.print(rest[i][j]);
					else System.out.print("*");
				}
				System.out.println();
			}
			
			
		}
		
		}catch(IOException e){}
		
	}
}