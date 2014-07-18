/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acm;


import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author USERE
 */
public class DiccionarioPortuñol {

    /**
     * @param args the command line arguments
     */
      public static int gp[][]=new int [100005][26];
      public static int gs[][]=new int [100005][26];
      public static int g[][]=new int [100005][26];
      public static char car[]={'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
      public static int stateCountP;
      public static int stateCountS;
      public static int palabras;
    
    public static void main(String[] args) {
       
        Scanner in =new Scanner(System.in);
        
        
         int pEspa=in.nextInt();
           
         int pPort=in.nextInt();
       // while(in.hasNext()){
                

            
            String pEsp[]=new String[pEspa];
            String pPor[]=new String[pPort];
         
           
           
            
            for(int j=0;j<pPort;j++){
                pPor[j]=in.next();
            }
            
             for(int i=0;i<pEspa;i++){
                pEsp[i]=new StringBuffer(in.next()).reverse().toString();
            }
            
            clear();
            
            for(int k=0;k<pPort;k++){
                addgp(pPor[k]);
                addgp(pEsp[k]);
                palabras+=(stateCountP-1);
            }
            
           
       
           
            
            System.out.println(palabras);
       // }
        
            
        
    }
        
        
//        clear();
//        String s1="ab";
//       // String s3="grande";
//       // String s4="mundo";
//        
//        String s5="bc";
//       // String s6="grande";
//       // String s7="mundo";
//        
//        String rs1= new StringBuffer(s5).reverse().toString();
//       // String rs2= new StringBuffer(s6).reverse().toString();
//       // String rs3= new StringBuffer(s7).reverse().toString();
//        
//        addgp(s1);
//       // addgp(s3);
//       // addgp(s4);
//        
//        addgs(rs1);
//        //addgs(rs2);
//       // addgs(rs3);
//       
// 
//        palabras();
//        
    
    
    static void palabras(){
        palabras=(stateCountS-1)*(stateCountP-1);
        System.out.println("S: "+(stateCountS-1)+" P: "+(stateCountP-1)+"palabras: "+palabras);
        int coincidencias=0;
        for(int i=0;i<100005;i++){
            for(int j=0;j<26;j++){
                if(gs[i][j]!=0 && gs[i][j]!=-1 && gp[i][j]!=0 && gp[i][j]!=-1 && gs[i][j]==gp[i][j]){
                    ++coincidencias;
                }
            }
            
        }
        System.out.println(coincidencias);
        
        palabras=palabras-coincidencias;
        System.out.println(palabras);
    }
    
    
    static void addgp(String s){
                int state=0;
                for(int  i=0;i<s.length();i++){
                   int next=(s.charAt(i)-'a');
                    
                   if(gp[state][next] == -1){
                       gp[state][next] = stateCountP;
                       Arrays.fill(gp[stateCountP], -1);
                       stateCountP++;
                   }
                   state  = gp[state][next];
                    
                
               }
    }
    
    static void addgs(String s){
        
              int state=0;
                for(int  i=0;i<s.length();i++){
                   int next=(s.charAt(i)-'a');
                   
                   if(gs[state][next] == -1){
                       gs[state][next] = stateCountS;
                       Arrays.fill(gs[stateCountS], -1);
                       stateCountS++;
                   }
                   state  = gs[state][next];
                    
                
               }
    
        
    }
    
    static void clear(){
        Arrays.fill(gp[0], -1);
        
        stateCountP = 1;
        
        palabras=0;
    }
}
