import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int cases = Integer.parseInt(bf.readLine());
		for(int t = 0; t < cases; t++){
			String des[] = bf.readLine().split(" ");
			int b = Integer.parseInt(des[0]);
			int m = Integer.parseInt(des[1]);
			if(m > Math.pow(2,b-2))
				System.out.println(String.format("Case #%d: %s", t + 1, "IMPOSSIBLE"));
			else{
				System.out.println(String.format("Case #%d: %s%n%s", t + 1, "POSSIBLE",solve(b,m)));
			}
			
			
		}
	
	}

	private static String solve(int b, int m) {
		StringBuilder stb = new StringBuilder();
		int count = 0;
		for(int i = 0; i < b; i++){
			if(i>0)stb.append("\n");
			for(int j = 0; j < b; j++){
				
				if( count < m && (i != j && i < j) || (j == b-1) && (i != b-1) ){
					stb.append("1");
					count ++;
				}else{
					stb.append("0");
				}
			}
		}
		return stb.toString();
	}

}
