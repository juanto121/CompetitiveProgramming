import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class flip {
	
	static int solutions[][] = new int[513][9];
	static int lights[];
	static int result[];
	
	public static int checkSolution(int k){
		int moves = 0;
		result = new int[9];
		for(int i = 0; i < 9; i++){
			if(solutions[k][i]==1){
				switchLights(i);	
			}
		}
		boolean equals = true;
		for(int h = 0; h < 9; h++){
			if(result[h]!=lights[h]){equals=false;break;}
				if(solutions[k][h]==1)moves++;
		}
		if(equals)
			return moves;
		else{
			return -1;
		}
	}
	
	public static void switchLights(int i){
		if(i%3-1>=0) result[i-1] = 1-result[i-1];
		if(i%3+1<3) result[i+1] = 1-result[i+1];
		if(i-3>=0) result[i-3] = 1-result[i-3];
		if(i+3<=8) result[i+3] = 1-result[i+3];  
		result[i] = 1-result[i];
	}
	
	public static void generateSolutions(){
		for(int i = 0; i < 513; i++){
			int tempi = i;
			int index = 0;
			while(index<9){
				solutions[i][index] = tempi%2;
				tempi/=2;
				index++;
			}
		}
	}
	
	
	public static void main(String[] args) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		generateSolutions();
		try {
			
			int cases = Integer.parseInt(reader.readLine());
			for( int P = 0; P < cases ; P ++){
				
			lights = new int[9];
			int index = 0;
			for(int row = 0; row < 3; row++){
				String read_row = reader.readLine();
				for(int col = 0; col < 3; col++){
					lights[index] = String.valueOf(read_row.charAt(col)).equals("*")?1:0;
					index++;
				}
			}
			int clics = -1;
			for(int i = 0; i < 512; i++){
				clics = checkSolution(i);
				if(clics!=-1)break;
			}
			
			System.out.println(clics);
			}	
				
		
		}catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
	
	
	
	
	
	
	
	
	
	
	}

}
