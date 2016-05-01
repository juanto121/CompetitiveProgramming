import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A_r1a {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int cases = Integer.parseInt(bf.readLine());
		
		for(int t = 0; t < cases; t++){
			StringBuilder br = new StringBuilder();
			String word = bf.readLine();
			int prev = word.charAt(0);
			br.append((char)prev);
			for(int i = 1; i < word.length(); i++){
				int current = word.charAt(i);
				if(current < prev){
					br.append((char)current);
				}else{
					br.insert(0,(char)current);
					prev = current;
				}
			}
			System.out.println(String.format("Case #%d: %s", t + 1, br.toString()));
		}
	}

}
