import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Ar1b {
	
	static int nums[][] = 
			{
				{0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,1},
				{0,0,0,0,1,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,1,0,0,0},
				{0,0,0,0,2,0,0,1,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0},
				{0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,1,0,0,1,0,0,0,0,0},
				{0,0,0,0,1,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0},
				{0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0},
				{0,0,0,0,2,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,1,0,0,0,0},
				{0,0,0,0,1,0,1,1,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0},
				{0,0,0,0,1,0,0,0,1,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0}
			};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int cases = Integer.parseInt(bf.readLine());
		for(int t = 0; t < cases; t++){
			String _phone = bf.readLine();
			System.out.println(String.format("Case #%d: %s", t+1, solve(_phone)));
		}
	}

	private static String solve(String _phone) {
		int map[] = new int[26+8];
		int len = _phone.length();
		for(int i = 0; i < len; i++){
			char c = _phone.charAt(i);
			map[c-'A'] ++;
		}
		
		ArrayList<Integer> pss = new ArrayList<Integer>();
		for(int num = 0; num <= 9; num++){
			boolean possible = true;	
			for(int k = 0; k < nums[num].length; k++){
				if(nums[num][k] != 0 &&  map[k]<nums[num][k]){
					possible = false;
					break;
				}
					
			}
			
			if(possible){
				pss.add(num);
			}
			
		}
		
		ArrayList<Integer> stb = new ArrayList<Integer>();
		
		for(int u = 0; u < pss.size(); u++){
			if(isUnique(pss.get(u))){
				int times = removeUnique(pss.get(u), map);
				for(int r = 0; r < times; r++){
					stb.add(pss.get(u));
				}
			}
		}
		
		for(int num = 0; num <= 9; num++){
			boolean zeros = true;
			while(zeros){
				for(int k = 0; k < nums[num].length; k++){
					if(nums[num][k] != 0 &&  map[k]<nums[num][k]) 
						zeros = false;
				}
				if(zeros){
					stb.add(num);
					for(int k = 0; k < nums[num].length; k++){
						map[k]-=nums[num][k];
					}
				}
			}
		}
		
		Collections.sort(stb);
		
		StringBuilder st = new StringBuilder();
		
		for(Integer r  = 0; r < stb.size(); r++){
			st.append(stb.get(r));
		}
		
		return st.toString();
	}
	
	private static int removeUnique(int num, int[] map) {
		boolean still = true;
		int times = 0;
		while(still){
			for(int k = 0; k < nums[num].length; k++){
				if(nums[num][k] != 0 &&  map[k]<nums[num][k]) 
					still = false;
			}
			if(still){
				for(int k = 0; k < nums[num].length; k++){
					map[k]-=nums[num][k];
				}
				times++;
			}
		}
		return times;
	}

	private static boolean isUnique(int num){
		switch(num){
		case 0: 
		case 2:
		case 4:
		case 6:
		case 8:
			return true;
		}
		return false;
	}

}
