import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class susu {
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine());
		uno: for (int cas = 0; cas < n; cas ++)
		{
			int[][] sudoku = new int[9][9];

			int[][] pos = new int[5][2];
			int cont = 0;
			for (int i = 0; i < 9; i ++)
			{
				char[] ar = in.readLine().toCharArray();
				for (int j = 0; j < 9; j ++)
				{
					sudoku[i][j] = Integer.parseInt(""+ar[j]);
					if (sudoku[i][j] == 0)
					{
						pos[cont][0] = i;
						pos[cont][1] = j;
						cont++;
					}
				}
			}
//			int[][] copia = new int[9][9];
//			copia = sudoku.clone();
			for (int i = 1; i < 10; i++) {
				for (int j = 1; j < 10; j++) {
					for (int j2 = 1; j2 < 10; j2++) {
						for (int k = 1; k < 10; k++) {
							for (int k2 = 1; k2 < 10; k2++) {
								sudoku[pos[0][0]][pos[0][1]]= i;
								sudoku[pos[1][0]][pos[1][1]]= j;
								sudoku[pos[2][0]][pos[2][1]]= j2;
								sudoku[pos[3][0]][pos[3][1]]= k;
								sudoku[pos[4][0]][pos[4][1]]= k2;
								if (revisar(sudoku))
								{
									for (int a = 0; a < 9; a ++)
									{
										for (int b = 0; b < 9; b++)
										{
											System.out.print(sudoku[a][b]);
										}
										System.out.println();
									}
									if(cas<n-1)
										System.out.println();
									continue uno;
								}
							}
						}
					}
				}
			}
			
			System.out.println("Could not complete this grid.");
			if(cas<n-1)
				System.out.println();
		}
		
	}
	
	
	public static boolean revisar(int[][] sud)
	{
		for (int i = 0; i < 9; i++)
		{
			int[] ar = new int[10];
			for (int j = 0; j<9; j ++)
			{
				ar[sud[i][j]]++;
			}
			for (int j = 1; j <=9; j ++)
			{
				if(ar[j] != 1)return false;
			}
		}
		for (int i = 0; i < 9; i++)
		{
			int[] ar = new int[10];
			for (int j = 0; j<9; j ++)
			{
				ar[sud[j][i]]++;
			}
			for (int j = 1; j <=9; j ++)
			{
				if(ar[j] != 1)return false;
			}
		}
		
		for (int j = 0; j < sud.length; j++) {
			int marked [] = new int [10] ; 
			
			int I = (j/3)*3 ; 
			int J  = (j%3)*3 ; 
				
			for (int k = I; k < I+3 ; k++) {
				for (int k2 = J; k2 < J+3 ; k2++) {
					if ( sud [ k ][k2] == 0 )
						continue ; 
					marked [sud [k][k2]] ++ ;
				}
			}
			for (int i = 1; i <=9; i ++)
			{
				if(marked[i] != 1)return false;
			}
			
		}
		return true;
	}
	
	

}
