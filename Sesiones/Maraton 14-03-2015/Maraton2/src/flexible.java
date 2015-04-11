import java.util.Scanner;


public class flexible {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while(scan.hasNextLine()){
			String line[] = scan.nextLine().split(" ");
			
			int total =Integer.parseInt(line[0]);
			int part = Integer.parseInt(line[1]);
			
			StringBuilder stb = new StringBuilder();
			
			int partitions[] = new int[part+2];
			line = scan.nextLine().split(" ");
			
			boolean esta[] = new boolean[total+1];
			
			for(int i = 1; i < part+1; i++){
				partitions[i] = Integer.parseInt(line[i-1]);
			}
			partitions[part+2-1] = total;
			
			for(int p = part+2 -1; p >= 0; p--){
				for(int k = p-1; k >=0 ; k--){
					int len = partitions[p]-partitions[k]; 
					if(!esta[len]){
						esta[len] = true;
					}
				}
			}
			
			for(int l = 1; l < total+1; l++){
				if(esta[l]){
					System.out.print(l + " ");
				}
			}
			
			System.out.println("");
		}

	}

}
