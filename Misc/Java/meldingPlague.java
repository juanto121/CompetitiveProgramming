import java.util.Scanner;
import java.util.Hashtable;
import java.util.StringTokenizer;

public  class plague {
 
  public static void main(String[] args) {
 
        Scanner scan = new Scanner(System.in);
       
        while(true){
  
        Hashtable mut = new Hashtable<String,String>();
        Hashtable cm = new Hashtable<String,Integer>();
        Hashtable ini = new Hashtable<String,Integer>();
        
        int nm,ni,nc,n;
        StringTokenizer str = new StringTokenizer(scan.nextLine());
        
        nm = Integer.parseInt(str.nextToken());
        ni = Integer.parseInt(str.nextToken());
        nc = Integer.parseInt(str.nextToken());
        n = Integer.parseInt(str.nextToken());
        System.out.println(n);
        
        if(nm == 0 && ni == 0 && nc == 0 && n == 0) break;
        
        str = new StringTokenizer(scan.nextLine());
        String m1,m2;
        for(int i=0;i<nm;i++){
        	m1 = str.nextToken();
        	m2 = str.nextToken();
			if(mut.contains(m1)){
				if(!mut.get(m1).equals(m2))
					System.out.println("");//Not deterministic
        		else{
            	    
        		}
			}
			else
				mut.put(m1,m2);        	
        }
        
        for(int or = 0 ; or < ni;or++){
        	 str = new StringTokenizer(scan.nextLine());
        	 ini.put(str.nextToken(),Integer.parseInt(str.nextToken()));
        }
        
        for(int c = 0;c<nc;c++){
        	 str = new StringTokenizer(scan.nextLine());
        	 cm.put(str.nextToken(),Integer.parseInt(str.nextToken()));
        }
        
        for(int u = 0;u<nc;u++){
            mutate(mut,nm);
            compare();
        }
    
        }//while
        
    }//main
    
    public static void mutate(Hashtable mut,Hashtable ini ,Hashtable cm,int nm){
        for(int i = 0;i<nm;i++)
            {
                //recorrer keys de mutacion -> si se encuentra en key de ini -> delete key de ini & 
                //añadir al HT o sumar al value.
            }
    }
    
    public static void compare(){
        
    }
}