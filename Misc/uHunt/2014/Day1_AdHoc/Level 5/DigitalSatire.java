import java.util.Scanner;
class DigitalSatire{
	public static String conf[];
	public static int w[];
	public static void main(String[] args) {
		conf = configurations();
		w = calcValues();
		int zVar = 'Z'-0;
		int aVar = 'A'-0;
		//for(int p:w) System.out.println(p);
		//for(String s:conf) System.out.println(s);

		Scanner scan = new Scanner(System.in);
		int cases = Integer.parseInt(scan.nextLine());
		
		for(int i = 0; i < cases; i++){
			StringBuilder stb = new StringBuilder();
			int configuration = 0;
			for(int j = 0; j < 7; j++){
				stb.append(scan.nextLine());
			}
			scan.nextLine();// -------- 
			String desc = stb.toString();
			//System.out.println(desc.substring(91,97)+" "+desc.substring(65,71));
			//System.out.println(desc.substring(73,79)+" "+desc.substring(83,89));
			//System.out.println(desc.substring(55,61)+" "+desc.substring(101,107));

			System.out.println(String.format("Case %d:",i+1));	
			boolean correct = true;
     		if( desc.charAt(91)-0 >= aVar && desc.charAt(91)-0 <= zVar && desc.charAt(65)-0 >= aVar && desc.charAt(65)-0 <= zVar )
			{
				//configuration = 0;
				StrW left = makeStrW(91,97,desc);
				StrW rigth = makeStrW(65,71,desc);
				if( !(left.weight>rigth.weight) ){
					correctConfig(left,rigth);
					correct = false;
				}	
			}

			if( desc.charAt(73)-0 >= aVar && desc.charAt(73)-0 <= zVar && desc.charAt(83)-0 >= aVar && desc.charAt(83)-0 <= zVar )
			{	
				configuration = 1;
				StrW left = makeStrW(73,79,desc);
				StrW rigth = makeStrW(83,89,desc);
				if(left.weight!=rigth.weight){
					correctConfig(left,rigth);
					correct = false;
				}

			}

			if( desc.charAt(55)-0 >= aVar && desc.charAt(55)-0 <= zVar && desc.charAt(101)-0 >= aVar && desc.charAt(101)-0 <= zVar )
			{	
				configuration = 2;
				StrW left = makeStrW(55,61,desc);
				StrW rigth = makeStrW(101,107,desc);
				if(!(left.weight<rigth.weight)){
					 correctConfig(left,rigth);
					 correct=false;
				}
				
			}
				
			if(correct){
				System.out.println("The Figure is correct.");
			}


		
		}

	}

	public static void correctConfig(StrW l, StrW r){
		StringBuilder con;
		int initL, initR;
		int finL, finR;
		int crl ,crr;
		
		if(l.weight==r.weight){
			con = new StringBuilder(conf[1]);
			initL = 73; initR = 83;
			finL = 79;  finR= 89;
			crl = 4; crr=4;
		}else{
			if(l.weight<r.weight){
				con = new StringBuilder(conf[2]);
				initL = 55; initR = 101;
				finL = 61;  finR= 107;
				crl = 3; crr=5;
			}else{
				con = new StringBuilder(conf[0]);
				initL = 91; initR = 65;
				finL = 97;  finR= 71;
				crl=5; crr = 3;
			}
		}
		
		for(int i = initL; i < initL+l.str.length(); i++){
			con.setCharAt(i+crl,l.str.charAt(i-initL));
		}
		for(int j = initR; j < initR+r.str.length(); j++){
			con.setCharAt(j+crr,r.str.charAt(j-initR));
		}
		
		System.out.print(con.toString());
	}

		/*Conf i)  first:[92,98) Second: [66,72)
		  Conf ii) first:[73,79) Second: [83,89)
		  conf iii)first:[55,61) Second: [101,107]
		*/

	public static StrW makeStrW(int init,int fin,String desc){
		int sum = 0;
		int i = init;
		for(i = init; i < fin && desc.charAt(i) != '.' ; i++){
			sum += w[desc.charAt(i)-'A'];
		}
		StrW strw = new StrW(sum,desc.substring(init,i));
		return strw;
	}
	

	public static int[] calcValues(){
		int zVar = 'Z'-0;
		int aVar = 'A'-0;
		int weights[] = new int[zVar-aVar];
		for(int i = aVar; i < zVar; i++ ){
			String binLt = Integer.toBinaryString(i);
			int szLt = binLt.length();
			int sum = 0;
			for(int j=0; j < szLt; j++){
				sum += binLt.charAt(j)=='1'?500:250;
			}
			weights[i-aVar] = sum;
		}
		return weights;
	}

	public static String[] configurations(){

		String confi = "........||.../\\...\n"+
					   "........||../..\\..\n"+
					   ".../\\...||./....\\.\n"+
					   "../..\\..||/......\\\n"+
					   "./....\\.||\\______/\n"+
					   "/......\\||........\n"+
					   "\\______/||........\n";
		
		String confii = "........||........\n"+
						".../\\...||.../\\...\n"+
						"../..\\..||../..\\..\n"+
						"./....\\.||./....\\.\n"+
						"/......\\||/......\\\n"+
						"\\______/||\\______/\n"+
			  			"........||........\n";


		String confiii = 	".../\\...||........\n"+
							"../..\\..||........\n"+
							"./....\\.||.../\\...\n"+
							"/......\\||../..\\..\n"+
							"\\______/||./....\\.\n"+
							"........||/......\\\n"+
							"........||\\______/\n";

	
		String configs[] = {confi,confii,confiii};
		return configs;
	}
}

class StrW{
	public int weight;
	public String str;
	public StrW(int w, String s){
		weight = w;
		str = s;
	}
}

