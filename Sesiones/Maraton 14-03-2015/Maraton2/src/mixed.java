import java.util.Scanner;


public class mixed {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String l=sc.nextLine();
		while(!l.equals("0 0")){
			String fraccion[]=l.split(" ");
			int numerador=Integer.parseInt(fraccion[0]);
			int denominador=Integer.parseInt(fraccion[1]);
			
			if(numerador<denominador){
				System.out.println("0 "+numerador+" / "+denominador);
			}else if(numerador%denominador==0){
					int r=numerador/denominador;
					System.out.println(r+" 0 / "+denominador);
			}else{
				int r=numerador/denominador;
				int d=numerador-(r*denominador);
				System.out.println(r+" "+d+" / "+denominador);
			}	
			l=sc.nextLine();
		}
	}
}
