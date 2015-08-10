
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class tripleshot {
	
	public static Double EPS = 1e-7;
	
	public static void main(String[] args) {
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		Point2D enemies[] = new Point2D[3];
		
		try{
			for(int e = 0; e < 3; e++){
				String point[] = scan.readLine().split(" ");
				Double x = Double.parseDouble(point[0]);
				Double y = Double.parseDouble(point[1]);
				enemies[e] = new Point2D.Double(x,y);
			}
				
				//AB
				//MIDPOINT AB
				Point2D midAB = new Point2D.Double( (enemies[0].getX() + enemies[1].getX())/2.0,
													(enemies[0].getY() + enemies[1].getY())/2.0 );
				//SLOPE AB
				Double m1 = ( enemies[1].getY() - enemies[0].getY() )/( enemies[1].getX() - enemies[0].getX());
				
				//BC
				//MIDPOINT BC
				Point2D midBC = new Point2D.Double( (enemies[1].getX() + enemies[2].getX())/2.0,
													(enemies[1].getY() + enemies[2].getY())/2.0 );
				//SLOPE BC
				Double m2 = ( enemies[2].getY() - enemies[1].getY() )/( enemies[2].getX() - enemies[1].getX());
				
				// Point perpendicular to AB through midAB point
				Double b1;
				Point2D perpendPointAB;
				if(Double.isInfinite(m1)){
					b1 = 0.0;
					perpendPointAB = new Point2D.Double(midAB.getX()+1,midAB.getY());
				}else{
					if( Double.isNaN(m1) || Math.abs(m1-0.0) <EPS){
						b1 = midAB.getY();
						perpendPointAB = new Point2D.Double( midAB.getX() , b1+1.0);
					}else{
						b1 = midAB.getY()-(-1.0/m1)*midAB.getX();
						perpendPointAB = new Point2D.Double( midAB.getX()+1.0 , (-1.0/m1)*(midAB.getX()+1.0) + b1 );
					}
				}
				
				
				
				// Point perpendicular to BC through midBC point
				Double b2;
				Point2D perpendPointBC;
				if(Double.isInfinite(m2)){
					b2 = 0.0;
					perpendPointBC = new Point2D.Double(midBC.getX()+1,midBC.getY());
				}else{
					if( Double.isNaN(m2) || Math.abs(m2-0.0) <EPS){
						b2 = midBC.getY();
						perpendPointBC = new Point2D.Double( midBC.getX() , b2+1.0);
					}else{
						b2 = midBC.getY()-(-1.0/m2)*midBC.getX();
						perpendPointBC = new Point2D.Double( midBC.getX()+1.0 , (-1/m2)*(midBC.getX()+1.0) + b2 );
					}
				}
					
				Double rectAB[] = getParamsLine(midAB, perpendPointAB);
				Double rectBC[] = getParamsLine(midBC, perpendPointBC);
				
				Double det = rectAB[0]*rectBC[1] - rectBC[0]*rectAB[1];
				if( Math.abs(0.0-det) < EPS ){
					System.out.println("Impossible");
				}else{
					Double x = (rectBC[1]*rectAB[2] - rectAB[1]*rectBC[2]) / det;
					Double y = (rectAB[0]*rectBC[2] - rectBC[0]*rectAB[2]) / det;
					System.out.print(String.format("%.10f %.10f%n",x,y));
				}
				
			
		}catch(IOException e){}
	}

	private static Double[] getParamsLine(Point2D midAB, Point2D perpendPointAB) {
		Double A1 = perpendPointAB.getY() - midAB.getY(); 
		Double B1 =  midAB.getX() - perpendPointAB.getX() ;
		Double C1 = A1*midAB.getX() + B1*midAB.getY();
		Double result[] = {A1, B1, C1};
		return result;
	}

	

}
