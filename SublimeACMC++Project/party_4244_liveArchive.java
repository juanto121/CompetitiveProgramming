import util.*;

public class party{
	Scanner scan = new Scanner(System.in());
	int sch[][] = new Integer[25][2];
	Arrays.fill(sch,0);
	TreeMap map  = new TreeMap<Integer,String>();
	int npart = Integer.parseInt(scan.nextLine());
	while(true){
		if(npart==0)break;
		else{
			for(int i = 0;i<npart;i++){
				String hours = scan.nextLine();
				StringTokenizer str = new StringTokenizer(hours);
				int start = Integer.parseInt(str.nextToken());
				int end = Integer.parseInt(str.nextToken());
				int diff = end - start;
				
			}
		}
	}
}