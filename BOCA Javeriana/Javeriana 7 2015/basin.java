import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;


public class basin {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int cases = Integer.parseInt(scan.nextLine());
		for(int t = 0; t < cases; t++){
			String sounds[] = scan.nextLine().split(" ");

			LinkedHashMap<String, Integer> soundMap = new LinkedHashMap<String,Integer>();
			
			for(String r : sounds){
				Integer val;
				if( (val = soundMap.get(r))==null){
					soundMap.put(r, 1);
				}else{
					soundMap.put(r, val+1);
				}
			}
			
			String known;
			while(true){
				known = scan.nextLine();
				if(known.equals("what does the fox say?")) break;
				soundMap.put(known.split(" ")[2], -1);
			}
			
			Iterator it = soundMap.entrySet().iterator();
			ArrayList<String>res = new ArrayList<String>();
			for(String s : sounds){
				if(soundMap.containsKey(s) && soundMap.get(s)!=-1){
					res.add(s);
				}
			}
			
			int len = res.size();

			if(t!=0)
				System.out.println("");
			for(int i = 0; i < len; i++){
				if(i == 0) System.out.print(res.get(i));
				else System.out.print(" "+res.get(i));
			}
			
		}

	}

}


/*
String recording[] = scan.nextLine().split(" ");

*/
