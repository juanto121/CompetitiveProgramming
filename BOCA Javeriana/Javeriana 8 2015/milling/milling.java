import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class milling {
	
	public static void main(String[] args) {
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		try{
			String v [] = scan.readLine().split(" ");
			int w = Integer.parseInt(v[0]);
			int s = Integer.parseInt(v[1]);
			v = scan.readLine().split(" ");
			int xmax = Integer.parseInt(v[0]);
			int ymax = Integer.parseInt(v[1]);
			
			StringTokenizer str;
			
			int wpieces[][] = new int[w][xmax];
			int cuts[][] = new int[1][xmax];
			
			String line[];
			for(int i = 0; i < w; i++){
				line = scan.readLine().split(" "); 
				for(int j = 0; j < xmax; j++) wpieces[i][j] = Integer.parseInt(line[j]);
			}
			
			for(int i = 0; i < s; i++){
				line = scan.readLine().split(" "); 
				for(int j = 0; j < xmax; j++){
					int cut = Integer.parseInt(line[j]);
					cuts[0][j] = cut > cuts[0][j] ? cut : cuts[0][j];
				}
			}
			
			StringBuilder stb = new StringBuilder(w*xmax);
			for(int i = 0; i < w; i++){
				for(int j = 0; j < xmax; j++){
					if(j!=0)stb.append(" ");
					stb.append( Math.min((ymax - cuts[0][j] ), wpieces[i][j] ));
				}
				stb.append("\n");				
			}
			System.out.print(stb.toString());
			
		}catch(Exception e){}
	}

}
