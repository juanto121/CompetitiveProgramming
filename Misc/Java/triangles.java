import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

class triangles {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		while(scan.hasNextLine()){
			int numPoints = Integer.parseInt(scan.nextLine());
			ArrayList<Point> G[] = new ArrayList[numPoints];

			for(int np = 0; np < numPoints; np++){
				StringTokenizer str = new StringTokenizer(scan.nextLine());
				Point p = new Point(np,Integer.parseInt(str.nextToken()), Integer.parseInt(str.nextToken()));
				for(int k = 0; k < numPoints; k++){
					if(k != np){
						if(G[k] == null)
						{	G[k] = new ArrayList<Point>(); }
						G[k].add(p);
						
					}
				}
			}

			for(int i = 0; i < numPoints; i++){
				System.out.println(G[i]);
			}

		}
		 
	}

}

class Point{
	int x;
	int y;
	int nodo;
	public Point(int nodo, int x, int y){
		this.nodo = nodo;
		this.x = x;
		this.y = y;
	}
	@Override
	public String toString(){
		return "("+x+","+y+") ";
	}
}


