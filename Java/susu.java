import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class susu {

	public static void main(String[] args)throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		final int N=Integer.parseInt(br.readLine());
		int[][] sudoku=new int[9][9];
		List<int[]> post=new ArrayList<int[]>();
		for(int n=0;n<N;n++){
			post.clear();
			for(int e=0;e<sudoku.length;e++){
				char[]h=br.readLine().toCharArray();
				for(int i=0;i<9;i++){
					sudoku[e][i]=h[i]-'0';
					if(sudoku[e][i]==0)post.add(new int[]{e,i});
				}
			}
			int e=0;
			for(;e>=0&&e<post.size();){
				int[] pos=post.get(e);
				sudoku[pos[0]][pos[1]]=(sudoku[pos[0]][pos[1]]+1)%10;
				if(sudoku[pos[0]][pos[1]]==0){
					e--;
				}
				else if(check(sudoku,pos[0],pos[1])){
					e++;
				} 
			}
			c:for(int k=0;k<9;k++)for(int i=0;i<9;i++){
				if(!check(sudoku,k,i)){
					e=-1;
					break c;
				}
			}
			if(e==-1){
				System.out.println("Could not complete this grid.");
			}else{
				imp(sudoku);
			}
			if(n!=N-1)System.out.println();
		}
	}
	static boolean[] checker=new boolean[10]; 
	public static boolean check(int[][] sud,int e,int i){
		Arrays.fill(checker,false);
		for(int j=0;j<9;j++)if(sud[e][j]!=0){
			if(checker[sud[e][j]])return false;
			checker[sud[e][j]]=true;
		}
		Arrays.fill(checker,false);
		for(int j=0;j<9;j++)if(sud[j][i]!=0){
			if(checker[sud[j][i]])return false;
			checker[sud[j][i]]=true;
		}
		Arrays.fill(checker,false);
		e/=3;i/=3;
		i*=3;e*=3;
		for(int j=0;j<3;j++)for(int k=0;k<3;k++)if(sud[e+j][i+k]!=0){
			if(checker[sud[e+j][i+k]])return false;
			checker[sud[e+j][i+k]]=true;
		}
		return true;
	}
	public static void imp(int[][] s){
		for(int e=0;e<9;e++){
			for(int i=0;i<9;i++)System.out.print(s[e][i]);
			System.out.println();
		}
	}
}
