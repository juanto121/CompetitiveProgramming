import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;


public class ambitious {
	static int max = 1001;
	
	public static void main(String[] args) {
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		try{
		while(true){
			
			int n = Integer.parseInt(scan.readLine());
			int stores[][] = new int[n][n];
			if(n == 0) break;
			for(int i = 0; i < n; i++){
				String st[] = scan.readLine().split(" ");
				for(int j = 0; j < n; j++){
					stores[i][j] = Integer.parseInt(st[j]);
				}
			}
			//int souvenir_sum[][] = new int[max][max];
			int souvenirs[][] = new int[n][n];
			
			int sum_row = 0;
			int sum_col = 0;
			for(int col = 0; col < n; col ++){
				sum_row += stores[0][col];
				souvenirs[0][col] = sum_row;
				
				sum_col += stores[col][0];
				souvenirs[col][0] = sum_col;
				
			}

			for(int i = 1; i < n; i++){
				for(int j = 1; j < n ; j++){
					souvenirs[i][j] = max(stores[i][j] + souvenirs[i-1][j], stores[i][j] + souvenirs[i][j-1]);
				}
			}
			System.out.println(souvenirs[n-1][n-1]);
			
		}
		}catch(Exception e){
			
		}
		
	}
	
	public static int max(int a, int b){
		return a>b?a:b;
	}

}







/*
 * 
 * for(int diag = 0; diag < n; diag ++){
				for(int i = 0; i < diag; i++){
					for(int j = 0; j < diag; j++){
						if( (i+j) == diag ){
							int max = 0;
							if( j-1 >= 0 && i-1 >= 0)
								max = stores[i][j-1] > stores[i-1][j] ? stores[i][j-1] : stores[i-1][j];
							souvenir_sum [i][j] += max;
						}
					}
				}
			}
			
			*/
