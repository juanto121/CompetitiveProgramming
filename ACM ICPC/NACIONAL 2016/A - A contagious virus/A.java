import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

public class A {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while(true){
			String _n = scan.nextLine();
			if(_n.equals("*")) break;
			
			int n = Integer.parseInt(_n);
			Point2D center = new Point2D.Double();
			Vertex vertices[] = new Vertex[n];
			
			String _center[] = scan.nextLine().split(" ");
			center.setLocation(Double.parseDouble(_center[0]), Double.parseDouble(_center[1]));
			
			for(int v = 0; v < n; v++){
				String _vert[] = scan.nextLine().split(" ");
				Point2D point = new Point2D.Double();
				double x = Double.parseDouble(_vert[0]);
				double y = Double.parseDouble(_vert[1]);
				point.setLocation(x, y);
				Vertex vertex = new Vertex();
				vertex.point = point;
				vertices[v] = vertex;
			}
			
			//sortVertices(vertices,center);
			
			Line2D segments[] = new Line2D.Double[n];
			Point2D first = vertices[0].point;
			Point2D last = vertices[n-1].point;
			Line2D lastSegment = new Line2D.Double(first, last);
			segments[n-1] = lastSegment; 
			Double shortest = lastSegment.ptSegDist(center);
			for(int i = 1; i < n; i++){
				Line2D segment = new Line2D.Double();
				Point2D second = vertices[i].point; 
				segment.setLine(first, second);
				Double dist = segment.ptSegDist(center);
				if(dist < shortest){
					shortest = dist;
				}
				first = second;
			}
			BigDecimal bd = new BigDecimal(""+shortest);
			bd = bd.setScale(3, RoundingMode.HALF_UP);
			System.out.println(new DecimalFormat("0.000").format(bd.doubleValue()));
		}
		scan.close();
	}

	private static void sortVertices(Vertex[] vertices, Point2D center) {
		int n = vertices.length;
		for(int i = 0; i < n; i++){
			Vertex v = vertices[i];
			v.angle = Math.atan2(v.point.getY()-center.getY(), v.point.getX()-center.getX());
		}
		Arrays.sort(vertices);
	}
	
}

class Vertex implements Comparable{
	Point2D point;
	Double angle;
	
	@Override
	public int compareTo(Object other) {
		if(this.angle < ((Vertex)other).angle) return -1;
		if(this.angle > ((Vertex)other).angle) return +1;
		return 0;
	}

	@Override
	public String toString() {
		return "("+point.getX()+","+point.getY()+")" + ", angle=" + String.format("%.3f",angle);
	}
	
}
