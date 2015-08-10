import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class building {

	public static void main(String[] args) {
		BufferedReader r=new BufferedReader(new InputStreamReader(System.in));		
		int t;
		try {
			t = Integer.parseInt(r.readLine());
			for(int i =0; i<t ; i++)		{
				int n= Integer.parseInt(r.readLine());
				String v[] = r.readLine().split(" ");
				int j;
				for(j=1; j< n; j++){
					int k=Integer.parseInt(v[j-1]);
					if(j!= k){
						System.out.println(j);		
						break;
					}
				}
				if(j==n){
					System.out.println(n);
				}
			}
		} catch (NumberFormatException e) {			
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}		
	}	
}
