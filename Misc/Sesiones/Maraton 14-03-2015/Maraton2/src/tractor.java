import java.util.Scanner;


public class tractor {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=Integer.parseInt(sc.nextLine());
		for(int i=0;i<n;i++){
			String punto[]=sc.nextLine().split(" ");
			int x=Integer.parseInt(punto[0]);
			int y=Integer.parseInt(punto[1]);
			
			boolean[][] matriz=new boolean[x][y];		
			
			int k=0;
			int z=1;
			
			while(k<x){
				matriz[0][k]=true;
				int paso=(int)Math.pow(2, z-1);
				k=k+paso;
				z++;
			}
			
			z=1;
						
			int puntos=0;
			
			for(int j=0;j<x;j++){
				if(matriz[0][j]){
					int p=0;
					while(p<y){
						matriz[j][p]=true;
						int paso=(int)Math.pow(2,z-1);
						p=p+paso;
						z++;	
						puntos++;
					}
				}
			}	
			
			System.out.println(puntos);
		}
	}
}
