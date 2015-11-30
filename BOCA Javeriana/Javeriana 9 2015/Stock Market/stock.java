

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class stock {

	public static void main(String[] args) {
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		String line;
		try{
			while((line = scan.readLine()) != null){
				String desc[] = line.split(" ");
				int n = Integer.parseInt(desc[0]);
				int c = Integer.parseInt(desc[1]);
				String p[] = scan.readLine().split(" ");
				int len = p.length;
				int total = 0;
				int minCompra = Integer.MAX_VALUE;
				int maxVenta = Integer.MIN_VALUE;
				
				int maxSubInversion = 0;
				for(int i = len-1; i >= 0; i--){
					int pi = Integer.parseInt(p[i]);
					
					if(pi > maxVenta){
						maxVenta = pi;
						minCompra = pi;

						total += maxSubInversion;
						maxSubInversion = 0;
						continue;
					}
					
					if(pi < minCompra){
						minCompra = pi;
						int inversion = maxVenta - (minCompra +c); 
						if( inversion > maxSubInversion){
							maxSubInversion = inversion;
						}
					}
					
					if(pi > minCompra){
						if(pi >= minCompra + c){
							maxVenta = pi;
							minCompra = pi;
							total += maxSubInversion;
							maxSubInversion = 0;
						}
					}
					
				}
 				System.out.println(total+maxSubInversion);
			}
			
		}catch(Exception e){}
	}

}
