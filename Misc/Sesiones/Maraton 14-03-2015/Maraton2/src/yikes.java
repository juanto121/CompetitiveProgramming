import java.util.Scanner;


public class yikes {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
		int n=Integer.parseInt(sc.nextLine());
		
		for(int i=0;i<n;i++){
			int velocidad_m=Integer.parseInt(sc.nextLine());
			int velocidad_b=Integer.parseInt(sc.nextLine());
			int d=Integer.parseInt(sc.nextLine());
			int t=Integer.parseInt(sc.nextLine());
			
			int cont_bicicletas=0;
			
			//Distancia recorrida por bicicletas antes de t
			int dist=velocidad_b*t;
			
			if(dist<d){
				d=d-dist;
				double tb=d/velocidad_b;
				double rm=-5.5+tb*velocidad_m;
				if(rm>0.5){
					
				}
				
				
				
			}else{
				int delante=dist-d;
				int j=delante;
				int cont=1;
				while(j>0){
					j=j-2;
					cont++;
					if(j>=2 && cont%2!=0){
						cont_bicicletas=cont_bicicletas+cont;
					}
				}
				
				if(cont_bicicletas==0){
					//Primera atras o atravesada
					
				}else{
					//Primera paso
					
				}
			}
			
			
			
			
			
		}
		
		
		
		

	}

}
