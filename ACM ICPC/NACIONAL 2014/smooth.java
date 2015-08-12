import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class smooth {

	public static void main(String[] args) {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String line;
		StringBuilder stb = new StringBuilder();
		try {
			while( (line=reader.readLine()) != null ){
				int n = Integer.parseInt(line);
				line = reader.readLine();
				StringTokenizer str = new StringTokenizer(line);
				
				int partial_sum = 1;
				int last_sum = 0;
				int max = -1;
				int a,b;
				
				a = Integer.parseInt(str.nextToken());
				
				if(n == 1){stb.append("1\n");continue;}
				for(int i = 0; i < n; i++){
					if(i==n-1) b = a-1;
					else
						b = Integer.parseInt(str.nextToken());
					if(a<=b)
					{
						partial_sum ++;
					}else{
						int partial_max = last_sum + partial_sum;
						if( partial_max > max ){
							max = partial_max;
						}
						last_sum = partial_sum;
						partial_sum = 1;
					}
					a = b;
				}
				if(max!=-1)
					stb.append(max).append("\n");
				else{
					stb.append(partial_sum).append("\n");
				}
				
			}
			
			System.out.print(stb.toString());
		} catch (IOException e) {
		
		}
		
	}

}
