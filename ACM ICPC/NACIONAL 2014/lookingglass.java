import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;


public class lookingglass {

	public static void main(String[] args) {
		try{
			BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
			String ns;
			int n = 0;
			//while((ns = bf.readLine()) != null){
			StringBuilder stb = new StringBuilder();
			stb.append("{");
			while(true){
				if(n==1000002)break;
				++n;
				
				stb.append(base(n));
				stb.append(", ");
			}
			stb.append("}");
			System.out.println(stb.toString());
		}catch(Exception io){
			
		}
		
	}
	
	public static int base(int n){
		
		
		
		int b = 2;
		for(;b<n-1;b++){
			ArrayList<String> baserep = new ArrayList<String>();
			int newN = n;
			while(newN/b != 0){
				
				baserep.add(newN%b+"");
				newN /= b;
			}
			baserep.add(newN%b+"");
			if(isPalindrome(baserep)){
				break;
			}
		}
		
		if(b==n-1)
			b = n-1;
		return b;
	}
	
	public static boolean isPalindrome(ArrayList<String> baserep){
		int i,j;
		int szbase = baserep.size();
		boolean res = true;
		for(i = 0, j = szbase-1;i<=j;i++, j-- ){
			if(! baserep.get(i) .equals( baserep.get(j) ) ){
				res = false;
				break;
			}
		}
		return res;
	}

}
