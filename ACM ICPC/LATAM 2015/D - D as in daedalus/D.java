package maraton_latam_2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class D {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String in;
		
		while((in = br.readLine()) != null){
		
		String s[] = in.split(" ");
		
		int n = Integer.parseInt(s[0]);
		int m = Integer.parseInt(s[1]);
		
		int cartas[] = {1, 10, 100, 1000, 10000};
		
		int pg = 0;
		int pp = 0;
		
		for(int i = 0; i < m; i++){
			s = br.readLine().split(" ");
			int b = Integer.parseInt(s[0]);
			int dedalus = Integer.parseInt(s[1]);
			int pr = 0;
			
			for(int j = 2; j < s.length; j ++){
				pr += Integer.parseInt(s[j]);
			}
			
			if(pr + dedalus <= b){
				pg += dedalus;
				int best = -1;
				for(int k = 0; k < 5; k++){
					if(pr + cartas[k] >= pr + dedalus && pr + cartas[k] <= b)
						best = cartas[k];
				}
				if(best != -1){
					pp += best;
				}
			}else{
				int best = -1;
				for(int k = 0; k < 5; k++){
					if(pr + cartas[k] <= b){
						best = cartas[k];
					}
				}
				if(best != -1){
					pp += best;
				}
			}
		}
		if(pp > pg){
			System.out.println(pp - pg);
		}else{
			System.out.println(0);
		}
		
	}
	}

}
