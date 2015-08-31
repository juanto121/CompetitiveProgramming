import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class legacy {
    static ArrayList<Integer> g[];
    static ArrayList<String> mains = new ArrayList<String>();
    static HashMap<String,Integer> map = new HashMap<String,Integer>();
    static boolean seen[];
    static int count;
    
    public static void main(String[] args) {
           Scanner scan = new Scanner(System.in);
           int n = Integer.parseInt(scan.nextLine());
           
           g = new ArrayList[n];
           for(int i = 0; i < n; i++) g[i] = new ArrayList<Integer>();
           seen = new boolean[n];
           
           int gindex = 0;
            boolean used = false;
           for(int t = 0; t < n; t++){
              
               String desc[] = scan.nextLine().split(" ");
               String methodName = desc[0];
               if(methodName.split("::")[1].equals("PROGRAM")){
                   mains.add(methodName);
               }
               if(!map.containsKey(methodName)){
                    map.put(methodName, gindex++);
                    used = true;
               }
               int numCallers = Integer.parseInt(desc[1]);
               int callerIndex;
               for(int j = 0; j < numCallers; j++){
                   String caller = scan.next();
                   
                   if(map.containsKey(caller)){
                       callerIndex = map.get(caller);
                       used = false;
                   }else{
                       map.put(caller,gindex);
                       used = true;
                   }
                   callerIndex = map.get(caller);
                   g[callerIndex].add(map.get(methodName));
                   if(used){
                       gindex++;
                   }
               }
               
                   scan.nextLine();
               
           }
           int mainSize = mains.size();
           count = 0;
           for(int i = 0; i < mainSize; i++){
               int mainIndex = map.get(mains.get(i));
               if(!seen[mainIndex])
            	   dfs(mainIndex);
           }
           
           int remaining = n-count;
           System.out.println(remaining);
    }

    private static void dfs(int u) {
	    
        int adjSize = g[u].size();
        seen[u] = true;
        for(int v = 0; v < adjSize; v++){
            if(!seen[g[u].get(v)]){
                dfs(g[u].get(v));
            }
        }
        count++;
    }
 
}
