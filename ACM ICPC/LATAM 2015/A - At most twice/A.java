package maraton_latam_2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A {

	public static void main(String[] args) throws IOException {
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		String u;
		while((u = scan.readLine()) != null){
			int len = u.length();
			long max = -1;
			if(isSorted(u)){
				System.out.println(u);
			}else{
				for(int i = 2; i <= len; i++){
					String l = u.substring(0, i);
					long u_ = Long.parseLong(l) - 1;
					while(!isSorted(u_+"")){
						u_--;
						if((u_+"").charAt((u_+"").length() - 1) == '0') 
							break;
					}
					String formed = buildapp(u_ + "", len - i);
					long f = Long.parseLong(formed);
					max = f > max && less(u, formed) && isSorted(formed) ? f:max;
				}
				System.out.println(max);
			}
		}
	}
	
	static String buildapp(String u, int len){
		StringBuilder str = new StringBuilder();
		int count[] = freq(u);
		str.append(u);
		int leng = 0;
		for(int k = 0; k < len && leng < len; k++)
			for(int i = 9; i >= 0 && leng < len; i--)
				for(int j = 0; j < 2-count[i] && leng < len; j++){
					str.append(i+"");
					leng++;
				}
		return str.toString();
	}
	
	static boolean less(String u, String l){
		long n = Long.parseLong(l);
		long orig = Long.parseLong(u);
		return n < orig;
	}
	
	static boolean isSorted(String u){
		int c[] = freq(u);
		for(int k = 0; k < 10; k++){
			if(c[k] > 2) return false;
		}
		return true;
	}
	
	static int[] freq(String u){
		int count[] = new int[10];
		for(int i = 0; i < u.length(); i++){
			count[Integer.parseInt(u.charAt(i)+"")]++;
		}
		return count;
	}

}
