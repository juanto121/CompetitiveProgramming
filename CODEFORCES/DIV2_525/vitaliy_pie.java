package DIV2_525;

import java.util.Scanner;

public class vitaliy_pie{

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = Integer.parseInt(scan.nextLine());
		int keys[] = new int[26+1];
		int rooms[] = new int[26+1];
		String house = scan.nextLine();
		int keys_to_buy = 0;
		for(int r = 0; r < 2*n-2; r+=2){
			int key_index = -1;
			int room_index = -1;
			if((r+1)%2!=0)
				key_index = house.charAt(r) - 'a';
				room_index = house.charAt(r+1) - 'A';
			keys[key_index]++;
			if(keys[room_index]==0) keys_to_buy++;
			else{
				keys[room_index]--;
			}
			
		}
		
		System.out.println(keys_to_buy);
		
	}

}
