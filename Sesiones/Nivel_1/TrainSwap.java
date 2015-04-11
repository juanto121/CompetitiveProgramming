import java.util.Arrays;
import java.util.Scanner;



    /**
     * Order The wagons and register # of swaps
     *	In:
     *	3 <- Test Cases
     *  3 <- Wagons
     *  1 3 2 <- Carriage current order
     *  4
     *  4 3 2 1
     *  Out:
     *	Optimal train swapping takes 6 swaps.
	 * 	Optimal train swapping takes 1 swaps.
     *
     */


public class TrainSwap {

    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        
        int cases=scan.nextInt();
        for(int i=0;i<cases;i++){
            int length=scan.nextInt();
             
            int carriage[]=new int[length];
            
            for(int j=0;j<length;j++){
                carriage[j]=scan.nextInt();
                
            }
            
           
            int swaps=0;
            for(int k=0;k<length;k++){
                for(int l=k+1;l<length;l++){
                    if(carriage[k]>carriage[l]){
                        swaps++;
                    }
                }
            }
            
            System.out.println("Optimal train swapping takes "+swaps+" swaps." );
        }
     
        
    }
}