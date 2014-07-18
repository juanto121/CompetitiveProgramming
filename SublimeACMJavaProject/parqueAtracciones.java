/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acm;

import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @author USERE
 */
public class parqueAtracciones {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int DineroAmigos[]=new int[5];
        recolectarDinero(DineroAmigos);
        int parqueA=irParque(DineroAmigos,5000,2000);
        int parqueB=irParque(DineroAmigos,0,3000);
        
        
        if(parqueA>parqueB){
            System.out.println("EL PARQUE A CONVIENE MAS");
        }else{
            System.out.println("EL PARQUE B CONVIENE MAS");
        }
    }

    public static void recolectarDinero(int[]DineroAmigos){
        for(int i=0;i<DineroAmigos.length;i++){       
           int dinero=0;
           while(dinero<2000){
               dinero=Integer.parseInt(JOptionPane.showInputDialog("Dinero amigo "+i+1));
           }
        }
    }

    private static int irParque(int[] DineroAmigos,int entrada,int boleta) {
      int []DineroAmigos1=new int[5];
      int[]numeroAtracciones=new int[5];
      DineroAmigos1=Arrays.copyOf(DineroAmigos, 5);
     
      
      for(int i=0;i<DineroAmigos1.length ;i++){
         
          DineroAmigos1[i]=DineroAmigos1[1]-(entrada);
          numeroAtracciones[i]=0;
          while(DineroAmigos1[i]>0){
              DineroAmigos1[i]=DineroAmigos1[i]-boleta;
              numeroAtracciones[i]++;
          }         
          
      }
      
      Arrays.sort(numeroAtracciones);
      
      return numeroAtracciones[0];
    }



 
 
}
