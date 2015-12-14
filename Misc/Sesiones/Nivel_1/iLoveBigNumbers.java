
import java.math.BigInteger;
import java.util.Scanner;

/**
 *
 * @author USERE
 */
public class iLoveBigNumbers {

    /**
     * @param args the command line arguments
     */
    
    static BigInteger factor[] = new BigInteger[1000+2];
    
    public static void main(String[] args) {
        factor[0] = new BigInteger (""+1);
        factor[1] = new BigInteger (""+1);
        factor[2] = new BigInteger (""+2);
        init();
        Scanner scan = new Scanner (System.in);
        while(scan.hasNextLine()){
            int num = Integer.parseInt(scan.nextLine());
            String number = factor[num].toString();
            int sum =0;
            for(int k = 0 ; k < number.length() ; k++ ){
                sum += Integer.parseInt(number.charAt(k)+"");
            }
            System.out.println(sum);
            
        }
        
        
    }
    
    public static void init(){//Precalculation O(n)
        for(int i  = 3 ; i< 1000+1;i++){
            factor [i] = factor[i-1].multiply(new BigInteger(i+""));
        }
    }
}
