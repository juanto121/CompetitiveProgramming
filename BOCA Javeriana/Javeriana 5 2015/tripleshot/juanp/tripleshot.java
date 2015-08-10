import java.util.Scanner;

//Wrong answer
//Errores de precision?
public class tripleshot {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		while(sc.hasNextDouble()){
			double x1=sc.nextDouble();
			double y1=sc.nextDouble();
			double x2=sc.nextDouble();
			double y2=sc.nextDouble();
			double x3=sc.nextDouble();
			double y3=sc.nextDouble();
			
			double determinante=x1*y2+y1*x3+x2*y3-y2*x3-y3*x1-y1*x2;
			
			if(determinante==0 || Math.abs(determinante)<0.000001){
				System.out.println("Impossible");
			}else{			
				//Regla de Cramer			
				double r1=-(x1*x1)-(y1*y1);
				double r2=-(x2*x2)-(y2*y2);
				double r3=-(x3*x3)-(y3*y3);
				
				double d1=r1*y2+y1*r3+r2*y3-y2*r3-y3*r1-y1*r2;
				double d2=x1*r2+r1*x3+x2*r3-r2*x3-r3*x1-r1*x2;
				//double d3=x1*y2*r3+y1*r2*x3+x2*y3*r1-r1*y2*x3-r2*y3*x1-y1*x2*r3;
				
				double A=d1/determinante;
				double B=d2/determinante;
				//double C=d3/determinante;
				
				double xc=-A/2;
				double yc=-B/2;
				
				System.out.println(xc+" "+yc);
			}		
		}
	}	
}
