import java.util.Arrays;
import java.util.Scanner;


class GoodTeacher {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int students = Integer.parseInt(scan.nextLine());
		int right[] = new int[students]; Arrays.fill(right, -1);
		int left[] = new int[students]; Arrays.fill(left, -1);
		String names[] = scan.nextLine().split(" ");
		
		int rInd = 0;
		boolean pastFirst = false;
		for(int i = 0;  i < students; i++){
			if(!names[i].equals("?")){
				pastFirst= true;
				right[i] = 0;
				rInd = 0;
			}else{
				if(!pastFirst)continue;
				rInd++;
				right[i] = rInd;			
			}
		}		
		pastFirst = false;
		int lInd = 0;
		for(int h = students-1; h >= 0; h--){
			if(!names[h].equals("?")){
				pastFirst= true;
				left[h] = 0;
				lInd = 0;
			}else{
				if(!pastFirst)continue;
				lInd++;
				left[h] = lInd;	
			}
		}
		
		int queries = Integer.parseInt(scan.nextLine());
		for(int i = 0; i < queries; i++){
			int qpos = Integer.parseInt(scan.nextLine());
			qpos--;
			if( (left[qpos]<right[qpos] && left[qpos]!= -1) || right[qpos] == -1){
				StringBuilder s = new StringBuilder();
				for(int k = 0; k < left[qpos]; k++){
					s.append("left of ");
				}
				s.append(names[left[qpos]+qpos]);
				System.out.println(s);
			}else{
				if( (left[qpos]>right[qpos] && right[qpos] != -1) || left[qpos] == -1){
					StringBuilder s = new StringBuilder();
					for(int k = 0; k < right[qpos]; k++){
						s.append("right of ");
					}
					s.append(names[qpos-right[qpos]]);
					System.out.println(s);
				}else{
					if(!names[qpos].equals("?")) 
						System.out.println(names[qpos]);
					else
						System.out.println(String.format("middle of %s and %s",names[qpos-right[qpos]],names[left[qpos]+qpos] ));
				}
			}
		
		}

	}
	
}
