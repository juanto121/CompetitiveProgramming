import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class a {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String l;
		int caso = 1;
		while((l = br.readLine()) != null){
			if(l.equals("0 0 0 0")) 
				break;			
			
			String s[] = l.split(" ");	
			
			long v1 = Long.parseLong(s[0]);
			long d1 = Long.parseLong(s[1]);
			long v2 = Long.parseLong(s[2]);
			long d2 = Long.parseLong(s[3]);
						
			System.out.print("Case #"+caso+": ");
			caso++;
			if(d1*v2 < d2*v1){
				System.out.println("You owe me a beer!");
			}else{
				System.out.println("No beer for the captain.");
			}
						
			long numerador, denominador;
			if(v1 == v2){
				denominador = v1;
				numerador = d1+d2;
			}else{
				denominador = v1*v2;
				numerador = d1*v2 + d2*v1;
			}
			
			denominador = denominador*2;			
			long comun = mcd(numerador, denominador);
			numerador = numerador/comun;
			denominador = denominador/comun;
			
			if(denominador == 1){
				System.out.println("Avg. arrival time: "+numerador);
			}else{
				System.out.println("Avg. arrival time: "+numerador+"/"+denominador);
			}
		}
	}
	
	public static long mcd(long a, long b){
		while(b != 0){
			long t = b;
			b = a%b;
			a = t;
		}
		return Math.abs(a);
	}
}
