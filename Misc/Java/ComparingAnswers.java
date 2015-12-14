/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 *
 * @author USERE
 */
 class ComparingAnswers {
public static final BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
    /**
     * @param args the command line arguments
     */
  public static void main(String[] args) throws IOException {
        try {
           // Scanner in=new Scanner(System.in);
            
            StringTokenizer str;
            int len=Integer.parseInt(in.readLine());
             
            while(len!=0){
                
              
                
                double a[][]=new double[len][len];
                double c[][]=new double[len][len];
                double res[][]=new double [len][len];
                
                for(int i=0;i<len;i++){
                    str=new StringTokenizer(in.readLine());
                    for(int j=0;j<len;j++){
                        a[i][j]=Double.parseDouble(str.nextToken());
                    }
                }
                for(int i=0;i<len;i++){
                      str=new StringTokenizer(in.readLine());
                    for(int j=0;j<len;j++){
                        res[i][j]=Double.parseDouble(str.nextToken());
                    }
                }
               
                int determinante=det(a);
                System.out.println("+"+determinante);
                if(Math.abs(determinante*determinante)==Math.abs(det(res)))
                        
                    System.out.println("Yes");
                else
                    System.out.println("No");
            
             

                len=Integer.parseInt(in.readLine());

            }
          
            
            System.exit(0);
        } catch (IOException ex) {
            int len=Integer.parseInt(in.readLine());
        }
        
    }
  
  static int det(double b[][]){
      double a[][]=b;
      int len=a.length;
            for(int k=0;k<len-1;k++){
                for (int i=k+1;i<len;i++) {
                    for(int j=k+1;j<len;j++){
                        
                        a[i][j]-=a[i][k]*a[k][j]/a[k][k];
                    }
                }
            }
            
            int det=1;
            for(int i=0;i<len;i++){
                det*=a[i][i];
            }
      return det;
  }
}


//int l;
//                int det=a[1][1];
//                System.out.println("+"+det);
//                for(int k=1;k<len;k++){
//                    l=k+1;
//                    for(int i=l;i<=len;i++){
//                        for(int j=l;j<=len;j++){
//                            System.out.println(a[k][k]);
//                            a[i][j]=(a[k][k]*a[i][j]-a[k][j]*a[i][k])/a[k][k];
//                            System.out.println(a[i][j]);
//                        }
//                    }
//                    det = det*a[k+1][k+1];
//                }
//                System.out.println(det);
//
//      for(int i=0;i<len;i++){
//                for(int j=0;j<len;j++){
//                   
//                    System.out.println(a[i][j]);
//                    
//                }
//            }
//            for(int i=0;i<len;i++){
//                for(int j=0;j<len;j++){
//                   
//                    System.out.println(res[i][j]);
//                    
//                }
//            }
// 



//      for(int i=0;i<len;i++){
//                for(int j=0;j<len;j++){
//                   
//                    System.out.println(a[i][j]);
//                    
//                }
//            }
//            for(int i=0;i<len;i++){
//                for(int j=0;j<len;j++){
//                   
//                    System.out.println(res[i][j]);
//                    
//                }
//            }
            
          
                
//                boolean flag=true;
//                for(int i=0;i<len&&flag;i++){
//                    
//                    for(int j=0;j<len&&flag;j++){
//                        c[i][j]=0;
//                        for(int k=0;k<len&&flag;k++){
//                            c[i][j]+=a[i][k]*a[k][j];
//                            
//                        }
//                        if(c[i][j]!=res[i][j]){
//                               System.out.println("No");
//                               flag=false;
//                               break;
//                        }
//                    }
//                }
//                if(flag==true)
//                System.out.println("Yes");
//                