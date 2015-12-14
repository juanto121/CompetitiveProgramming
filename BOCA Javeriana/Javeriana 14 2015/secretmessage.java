import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;


public class secretmessage {
	
	static int next[] = new int[10003];
	
	
	public static void main(String[] args) {
		
		int sq = 1;
		int index = 1;
		for(int i = 1; i < 10002; i++){
			
			if(i > sq){
				index++;
				sq = index*index;
			}
			next[i] = sq;
		}
		
		Scanner scan = new Scanner(System.in);
		int N = Integer.parseInt(scan.nextLine());
		for(int t = 0; t < N; t++){
			String text = scan.nextLine();
			int len = text.length();
			
			int nlen = nextSquare(len);
			StringBuilder asterisks = new StringBuilder();
			for(int i = 0; i < nlen - len; i++) asterisks.append("*");
			
			text += asterisks;
			StringBuilder stb = new StringBuilder();
			
			int offset = (int) Math.sqrt(nlen);
			int visited = 0;
			int row = 0;
			int k = nlen - offset;
			while(visited < len){
				if(k<0){row++; k = nlen - offset + row; continue;}
				char h = text.charAt(k);
				if(h!='*'){
					stb.append(h);
					visited++;
				}
				k -= offset;
			}
			
			System.out.println(stb);
			
		}
	}
	
	public static int nextSquare(int n){
		return next[n];
	}
	

	/*
	 * blegh:
	 * 
	while(!message.isEmpty()){
		if(k >= len){ k--; continue; }
		stb.append(message.get(k));
		message.remove(k);
		k*=2;
	}
	*/

}
