import java.util.Scanner;

public class B_tidy_numbers {
	/// The greed is strong with this one!
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int cases = Integer.parseInt(scan.nextLine());
		for(int t = 1; t <= cases; t++){
			String num = scan.nextLine();
			int tidyNumIndex = -1;
			while((tidyNumIndex=tidy(num))!=-1){
				int len = num.length();
				long n = Long.parseLong(num);
				int digit = num.charAt(tidyNumIndex-1)-'0';
				String pre = num.substring(0, tidyNumIndex-1);
				String mid = (digit-1)+"";
				String post = new String(new char[len-tidyNumIndex]).replace("\0", "9");
				String lastTidy = pre + mid + post;
				num = lastTidy;
			}
			System.out.println(String.format("Case #%d: %d", t, Long.parseLong(num)));
		}
	}

	private static int tidy(String num) {
		int tidy = -1;
		int last = -1;
		for(int i= 0 ; i<num.length(); i++){
			int digit = num.charAt(i)-'0';
			if(i==0){
				last = digit;
			}else{
				if(digit<last){
					tidy = i;
					break;
				}
				last = digit;
			}
		}
		return tidy;
	}

}
