import java.util.Arrays;
import java.util.Scanner;

class theskyline{

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int start, end = 0,height, last = 0;
		int hbuild[] = new int[5003];
		Arrays.fill(hbuild, 0);
		String building [];
		while(scan.hasNextLine()){
			String line = "";
			building = scan.nextLine().split(" ");
			 start = Integer.parseInt(building[0]);
			 end = Integer.parseInt(building[2]);
			 height = Integer.parseInt(building[1]);
			 if(end > last) last = end;
			
			for(int i = start; (double)i < end -0.5  ; i++){
				if(i<0) i = 0;
				if(hbuild[i] < height){
					hbuild[i] = height;
				}
			}	
		}
		int lasth = -20000;
		boolean first = true;
		for(int b = 1; b <= last; b++){
			
			if( lasth != hbuild[b]){
				if(first){
					System.out.print(b+" "+hbuild[b]); first = false;}
				else
					System.out.print(" "+b+" "+hbuild[b]);
				
				}
				lasth = hbuild[b];
			}
			
		System.out.print("\n");
		}
}
