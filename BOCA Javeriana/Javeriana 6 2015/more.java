import java.util.Scanner;


public class more {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while(scan.hasNextLine()){
			String num = scan.nextLine();
			StringBuilder str = new StringBuilder();
			int len = num.length();
			int carry = 1;
			int sign = 1;
			for(int i = len-1; i >= 0; i--){		
				if(num.charAt(i) == '0'){
					if(sign > 0){
						if(carry == 1){
							str.append("1");
							carry = 0;
						}else{
							str.append("0");
							carry = 0;
						}
					}else{ //negative for 0
						if(carry == 1){
							str.append("1");
							carry = 1;
						}else{
							str.append("0");
							carry = 0;
						}
					}
				}else{
					// 1's
					if(sign > 0){
						if(carry == 1){
							str.append("0");
							carry = 1;
						}else{
							str.append("1");
							carry = 0;
						}
					}else{//negative for 1
						if(carry == 1){
							str.append("0");
							carry = 0;
						}else{
							str.append("1");
							carry = 0;
						}
					}
				}
				
			 sign*=-1;
			}
			if(carry == 1){
				if(sign >0){
					str.append("1");
				}else{
					str.append("11");
				}
			}
		
			String res = ( str.reverse().toString().replaceFirst("^0+", ""));
			if(res.equals(""))res = "0";
			System.out.println(res);
		}

	}
	

}
