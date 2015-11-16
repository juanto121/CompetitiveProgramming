package maraton_latam_2015;

/*
 * 
 * The Mighty Non Polynomial Squids
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class I {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String ts;
		while( (ts = br.readLine()) != null ){
			String p[] = br.readLine().split(" ");
			int count = 0;
			for(int i = 0; i < 5; i++){
				if(p[i].equals(ts))count++;
			}
			System.out.println(count);
		}
	}

}
