
package acm;




import java.util.Scanner;
import java.util.Arrays;



class Main {

    /**
     * @param args the command line arguments
     */
   public static  int numHuecos=0; 
   public static int numRata=0;
   public static boolean ady[][];
   public static boolean[] seen;
   public static   int [] matchL;
   public static   int [] matchR;
   
   public static void main(String[] args) {
      
       Scanner in=new Scanner(System.in);
     
          System.out.println('b'-'a');
       
       while(in.hasNext()){
           
       numRata=Integer.parseInt(in.next());
       if(numRata==0)break;
       numHuecos=Integer.parseInt(in.next());
       double time=Double.parseDouble(in.next());
       double vel=Double.parseDouble(in.next());
           
       ady=new boolean[numRata][numHuecos]; 
       double ratas[][]=new double[numRata][2];
       double huecos[][]=new double[numHuecos][2];
         
          
         for(int i=0;i<numRata;i++)
             for(int j=0;j<2;j++){
                 ratas[i][j]=Double.parseDouble(in.next());
                 
             }
         
         for(int i=0;i<numHuecos;i++)
             for(int j=0;j<2;j++){
                 huecos[i][j]=Double.parseDouble(in.next());
                 
             }
        
          
        dist(huecos,ratas,time,vel);
        
           
        seen=new boolean[numHuecos];
        matchL=new int[numRata];
        matchR=new int[numHuecos];
       
       Arrays.fill(matchL, -1);
       Arrays.fill(matchR, -1);
   
       
       int cnt=0;
         
       for(int i=0;i<numRata;i++){
           
          Arrays.fill(seen, false);
           
           if(bpm( i ))
               cnt++;
      
           
       }
       
       
         
          System.out.println(numRata-cnt);
          
         
        
       }
       
      System.exit(0);
           
      
       
    }
    
    
   static boolean bpm(int u) {
       
       for(int v=0; v<numHuecos;v++)  if (ady[u][v]){
           
               if(seen[v]) continue;
               seen[v] = true;
           
           
           if( matchR[v]<0 || bpm( matchR[v] ) ){
               matchL[u] = v;
               matchR[v] = u;
               return true;
           }
       
       }
        return false;
    }

   static void dist(double huecos[][],double ratas[][],double time,double vel){
      double x1,x2,y1,y2;
            for(int i=0;i<ratas.length;i++){
                x1=ratas[i][0];
                y1=ratas[i][1];
                for(int j=0;j<huecos.length;j++){
                   x2=huecos[j][0];
                   y2=huecos[j][1];                   
                   if( (( Math.sqrt( Math.pow(x2-x1,2 ) + ( Math.pow(y2-y1, 2)  )    ) )/vel) <= time  ){
                     
                       ady[i][j]=true;
                   }else{
                       ady[i][j]=false;
                   }
                }
            }
      
    
  }

  
  
}
