package codejam_2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StandingOvation {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String line;
		int t = Integer.parseInt(bf.readLine());
		for(int c = 0; c < t; c++){
			String desc[] = bf.readLine().split(" ");
			int maxs = Integer.parseInt(desc[0]);
			String audience[] = desc[1].split("");
			int currentStanding = 0;
			int friends = 0;
			for(int s = 0; s <= maxs; s++){
				int shy = Integer.parseInt(audience[s]);
				currentStanding += currentStanding>=s?shy:0;
				int nfriends = 0;
				if(shy > 0 && currentStanding < s){
					nfriends = s - currentStanding;
					currentStanding += shy + nfriends;
				}
				friends += nfriends;
			}
			System.out.println(String.format("Case #%d: %d", c+1, friends));
		}
	}

}
