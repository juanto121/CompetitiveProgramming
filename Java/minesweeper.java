import java.util.Scanner;
import java.util.StringTokenizer;
/**
 *
 * @author AndresCallejas
 */
public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Scanner scan = new Scanner(System.in);
        int n,m,i,j;
        String linea ;
        StringTokenizer str ;
        int cont =0;
        while(scan.hasNext()){
            cont ++;
            linea = scan.nextLine();
            str = new StringTokenizer(linea);
            n = Integer.parseInt(str.nextToken());
            m = Integer.parseInt(str.nextToken());
            if(n==0&&m==0){
                break;
            }
            int matrizMinas[][] = new int[n][m];
            int matrizResultado[][] = new int [n][m];
            for (i=0;i<n;i++){
                linea = scan.nextLine();
                str = new StringTokenizer(linea);
                for(j=0;j<m;j++)
                {
                    String dato = linea.charAt(j)+"";
                    if (dato.equals("*")){
                       matrizMinas[i][j] = 1;
                       matrizResultado[i][j] = -20;
                    }else
                       matrizMinas[i][j] = 0;
                }                
            }
            for(i=0;i<n;i++){
                for(j=0;j<m;j++){
                    if(matrizMinas[i][j]==1){
                        try{                            
                            matrizResultado[i][j+1] += 1;
                        }catch(Exception e){
                        }
                        try{                            
                            matrizResultado[i][j-1] += 1;
                        }catch(Exception e){
                        }
                        try{                            
                            matrizResultado[i+1][j] += 1;
                        }catch(Exception e){
                        }
                        try{                            
                            matrizResultado[i-1][j] += 1;
                        }catch(Exception e){
                        }
                        
                        try{                            
                           matrizResultado[i+1][j+1] += 1;
                        }catch(Exception e){
                        }
                        try{                            
                            matrizResultado[i+1][j-1] += 1;
                        }catch(Exception e){
                        }try{                            
                            matrizResultado[i-1][j+1] += 1;
                        }catch(Exception e){
                        }try{                            
                            matrizResultado[i-1][j-1] += 1;
                        }catch(Exception e){
                        }
                        
                     }
                }
            }
            
            System.out.println("Field #"+cont+":");
            for(i=0;i<n;i++){
                String x = "";
                for(j=0;j<m;j++){
                    if(matrizResultado[i][j]<0){
                        x+="*";
                    }else{
                        x+= matrizResultado[i][j] + "";
                    }
                }
                System.out.println(x);
            }
            System.out.println("");
            
            
        }
        
        
        
        
        
    }
}