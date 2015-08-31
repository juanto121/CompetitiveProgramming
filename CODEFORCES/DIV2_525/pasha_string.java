package DIV2_525;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class pasha_string {

	public static void main(String[] args) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			
			String string = reader.readLine();
			int length = string.length();
			StringBuilder stb = new StringBuilder(string);
			int MAX = 2*100000 + 2;
			String mem[] = new String[MAX];
			int num_days = Integer.parseInt(reader.readLine());
			String ai_s[] = reader.readLine().split(" ");
			for(int d = 0; d < num_days; d++){
				int ai = Integer.parseInt(ai_s[d]);
				int low = ai-1;
				int up = length - low -1;
				
				for(int l = low, u = up; l < u; l++, u--){

					char temp = stb.charAt(l);
					stb.setCharAt(l, stb.charAt(u));
					stb.setCharAt(u, temp);
				}
			}
			
			System.out.println(stb.toString());
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
