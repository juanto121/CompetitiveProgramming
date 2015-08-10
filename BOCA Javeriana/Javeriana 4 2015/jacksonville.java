import java.util.Scanner;


public class jacksonville {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String l = s.nextLine();
		
		while(!l.equals("0 0")){
			String z[] = l.split(" ");
			int k=Integer.parseInt(z[0]);
			int n= Integer.parseInt(z[1]);	
			boolean visitados []= new boolean[k+1];
			int cont = 0;
			for(int j=0; j< n; j++){
				int f = Integer.parseInt(s.nextLine());				
				for(int i=f; i<=k; i++){
					if(i%f==0 && !visitados[i]){
						cont++;
						visitados[i]=true;
					}					
				}				
			}
			System.out.println(cont);
			l=s.nextLine();
		}		
	}
}
