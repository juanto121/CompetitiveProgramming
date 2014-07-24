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
public class PermutationRecovery {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scan=new Scanner (System.in);
        int permutation[]=new int[510];          
        while(true){
        Arrays.fill(permutation, -1);
        int n=scan.nextInt();
        if(n==0)break;
        int posicion;
            for(int i=1;i<n+1;i++){
                int ai=scan.nextInt();
                posicion=-1;
                while(ai!=-1){
                    posicion++;
                    if(permutation[posicion]>i || permutation[posicion]==-1){
                        --ai;
                    }
                   
                }
                permutation[posicion]=i;
                               
            }
            
            
            for(int j=0;j<n-1;j++){
                if(permutation[j]==-1)break;
                System.out.print(permutation[j]+",");
                
            }
            System.out.print(permutation[n-1]);
            System.out.println("");
        }
        
    }
}
