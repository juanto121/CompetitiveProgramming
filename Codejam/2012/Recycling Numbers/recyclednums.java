package qualification;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class recyclednums {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		int cases = Integer.parseInt(scan.readLine());
		for(int t = 0; t < cases; t++){
			String limits[] = scan.readLine().split(" ");
			int start = Integer.parseInt(limits[0]);
			int end = Integer.parseInt(limits[1]);
			
			int total = end-start;
			
			String nums[] = new String[end-start+1];
			
			for(int index = start; index < end; index++){
				nums[index-start] = index+"";
			}
			
			HashSet<String> pairs = new HashSet<>();
			
			int recycled = 0;
			for(int i = 0; i< total; i++){
				int len = nums[i].length();
				String circular = nums[i]+""+nums[i];
				for(int j = 1; j < len; j++){
					String sw = circular.substring(j, j+len);
					int swapped = Integer.parseInt(sw);
					if(swapped >= start && swapped <= end && swapped > Integer.parseInt(nums[i])){
						String pair = sw+"-"+nums[i];
						
						if(!pairs.contains(pair)){
							recycled++;
							pairs.add(pair);
						}
					}
				}
			}
			
			System.out.println(String.format("Case #%d: %d",t+1, recycled));
			
		}
	}

}
