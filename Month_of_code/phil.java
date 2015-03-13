import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class phil {

	static int mod = 1000000007;
	
	public static void main(String[] args) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			int cases = Integer.parseInt(reader.readLine());
			StringBuilder stb = new StringBuilder();
			boolean first_out = true;
			for(int t = 0;t < cases ; t++){
				long decimal = 0l;
				String bits = reader.readLine();
				int size = bits.length();
				int half_size = size /2;
				int pleft = 0, pright = 0;
				
				if(size%2==0){
					pleft = half_size - 1;
					pright = half_size;
				}else{
					pright = half_size;
					pleft = pright;
				}
				
				int middle = 0, skip = 0;
				boolean lskip = true;

				for(int b = 0;b<size;b++){
					skip++;
					if(pleft == pright){
						//Current size odd
						middle = pleft;
						if(lskip){
							pleft -= skip;
							pright++;
						}
						else{
							pleft--;
							pright += skip;
						}
					}else{
						//Current size even
						middle = bits.charAt(pleft)>bits.charAt(pright)?pleft:pright;
						if(middle == pleft){
							pleft += skip;
							lskip = true;
						}else{
							pright -= skip;
							lskip = false;
						}
					}
					
					
					
					long binary_value = (bits.charAt(middle)-'0');
					
					if(decimal == 0 && binary_value == 0){	
					}else{
						if(binary_value == 0){
							decimal =  (decimal % mod * 2 ) % mod; 
						}else{
							if(decimal == 0){
								decimal = 1; continue;
							}
							decimal = ((((decimal%mod) * 2 ) %mod ) + 1) % mod;
						}
					}
									
					
				}
				decimal %= mod;
				if(first_out){
					stb.append(String.format("Case #%d: %d",t+1,decimal));
					first_out = false;
				}else{
					stb.append(String.format("%nCase #%d: %d",t+1,decimal));
				}
				
			}
			System.out.println(stb.toString());
					
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
