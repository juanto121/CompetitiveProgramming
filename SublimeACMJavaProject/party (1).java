/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acm;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author ESTUDIANTE
 */
public class party {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        while(true){
            int ndia=0; ndia++;
            int p=Integer.parseInt(scan.nextLine());
            if(p==0)break;

            for(int i=0;i<p;i++){
                int s;
                int e;
                StringTokenizer str=new StringTokenizer(scan.nextLine());
                s=Integer.parseInt(str.nextToken());
                e=Integer.parseInt(str.nextToken());
                int r=4;
                int c=p;
                Double matr[][]=new Double [r][p];
                if(i==0)Arrays.fill(matr, 0);
                for(int j=0;j<p;j++){
                    
                    if(matr[j][0]==p && matr[j][1]==e){    
                        //matr[j][0]=Double.parseDouble(s+"");
                        //matr[j][1]=Double.parseDouble(e+"");
                        matr[j][2]++;
                        matr[j][3]=matr[j][3]-0.5;
                    }else{
                        
                    }
                
                }
                
                
            }
        }
        
        
    }
}
