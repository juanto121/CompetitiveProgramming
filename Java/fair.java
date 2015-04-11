import java.io.BufferedReader;
import java.io.InputStreamReader;


public class fair {
	static long[] masks=new long[62];
	public static void main(String[] args)throws Exception{
		masks[1]=1;
		for(int e=2;e<masks.length;e++)masks[e]=masks[e-1]*2;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		final int casos=Integer.parseInt(br.readLine());
		for(int caso=0;caso<casos;caso++){
			br.readLine();
			int n=Integer.parseInt(br.readLine());
			String[]j=br.readLine().split(" +");
			int m=0;
			for(int e=0;e<n;e++)if(j[e].equals("0.500000"))m++;
			System.out.println(masks[n+1]-masks[n-m+1]);
		}
	}
	
    
//	public static void main(String...args)throws Exception{
//		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
//		String[]j=br.readLine().split(" +");
//		double[] ps=new double[j.length];
//		for(int e=0;e<ps.length;e++)ps[e]=Double.parseDouble(j[e]);
//		int max=(int)Math.pow(2,j.length);
//		for(int e=0;e<max;e++){
//			int paridad=Integer.bitCount(e);
//			for(int i=0;)
//		}
//	}

	
}
