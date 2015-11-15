package maraton_nacional_2015;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class farmer {

	public static void main(String[] args)throws Exception {
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		String line;
		while( (line = scan.readLine()) != null ){
			int ntrees = Integer.parseInt(line);
			tree bounds[] = new tree[ntrees];
			String aTree[];
			double resx = 0.0;
			double resy = 0.0;
			double w = 0.0;
			for(int t = 0; t < ntrees; t++){
				int x,y,c;
				aTree = scan.readLine().split(" ");
				x = Integer.parseInt(aTree[0]);
				y = Integer.parseInt(aTree[1]);
				c = Integer.parseInt(aTree[2]);
				bounds[t] = new tree(x,y,c);
				resx += x * c;
				resy += y * c;
				w += c;
			}		
			resx /= w;
			resy /= w;
			
			double total = cost(bounds,resx,resy);
			System.out.println(String.format("%.3f",total));
			
		}
		
	}
	
	private static double cost(tree[] bounds, double x, double y) {
		int len = bounds.length;
		double total = 0.0;
		for(int i  = 0; i < len; i++){
			total += distanceTo(bounds[i], x, y); 
		}
		return total;
	}

	public static double distanceTo(tree ti, double x, double y){
		double distance = ( (ti.x - x)*(ti.x - x) + (ti.y - y)*(ti.y - y) ); 
		return ti.cost * distance;
	}

}

class tree{
	int x;
	int y;
	int cost;
	public tree(int xi, int yi, int c){
		x = xi;
		y = yi;
		cost = c;
	}
}
