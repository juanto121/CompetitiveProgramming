/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//package acm;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author ESTUDIANTE
 */
 class susu {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        StringTokenizer str =new StringTokenizer(scan.nextLine());
      
        int casos=Integer.parseInt(str.nextToken());
        
        
        for(int i=0;i<casos;i++){
            int sud[][]=new int [10][10];
            
            String row;
            for(int j=0;j<9;j++){//lenando filas sumas
                int sum=0;
                row=scan.nextLine();
                for(int k=0;k<9;k++){
                    sud[j][k]=Integer.parseInt(row.charAt(k)+"");
                    sum+=sud[j][k];
                    //System.out.print(sud[j][k]);
                }
               // System.out.println("");
                sud[j][9]=sum;
            }
            
            for(int j=0;j<9;j++){//filas
                int ceros=0;
                if(sud[j][9]==45){
                    
                }else{
                    for(int k=0;k<9;k++){
                        if(sud[j][k]==0) ++ceros;
                        
                    }
                    if(ceros==1){
                       
                            for(int m=0;m<9;m++){
                                if( sud[j][m]==0 )
                                    sud[j][m]=45-sud[j][9];
                            }
                        }
                    }
                }
            
         //columnas sumas
           for(int g=0;g<9;g++){
               int sumc=0;
               for(int f=0;f<9;f++){
                   sumc+=sud[f][g];
               }
               sud[9][g]=sumc;
               
           }
           //columnas verify
           for(int g=0;g<9;g++){
               int cerosc=0;
               for(int f=0;f<9;f++){
                  if(sud[f][g]==0){
                      ++cerosc;
                  }  
               }
               if(cerosc==1){
                   for(int r=0;r<9;r++){
                       if(sud[r][g]==0){
                           sud[r][g]=45-sud[9][g];
                       }
                   }
               }
               
               
           }
           
           for(int j=0;j<9;j++){//filas
                int ceros=0;
                if(sud[j][9]==45){
                    
                }else{
                    for(int k=0;k<9;k++){
                        if(sud[j][k]==0) ++ceros;
                        
                    }
                    if(ceros==1){
                       
                            for(int m=0;m<9;m++){
                                if( sud[j][m]==0 )
                                    sud[j][m]=45-sud[j][9];
                            }
                        }
                    }
                }
            
         //columnas sumas
           for(int g=0;g<9;g++){
               int sumc=0;
               for(int f=0;f<9;f++){
                   sumc+=sud[f][g];
               }
               sud[9][g]=sumc;
               
           }
           //columnas verify
           for(int g=0;g<9;g++){
               int cerosc=0;
               for(int f=0;f<9;f++){
                  if(sud[f][g]==0){
                      ++cerosc;
                  }  
               }
               if(cerosc==1){
                   for(int r=0;r<9;r++){
                       if(sud[r][g]==0){
                           sud[r][g]=45-sud[9][g];
                       }
                   }
               }
               
               
           }
           
           
      boolean verdadero=verificar(sud);
      
      if(verdadero){
           
         for(int a=0;a<9;a++){
             for(int t=0;t<9;t++){
                 System.out.print(sud[a][t]);
             }
             System.out.println("");
         }
        
         
      }else{
          System.out.println("Could not complete this grid.");
          
      }
      
      System.out.print(""+"\n");
      

    }
}

    static boolean verificar(int[][] sud) {
        //suma filas
        for(int i=0;i<9;i++){
            int suma=0;
            for(int j=0;j<9;j++){
                suma+=sud[i][j];
            }
            if(suma!=45){
                return false;
            }
        }
        
        //suma columnas
        for(int i=0;i<9;i++){
            int sumac=0;
             for(int j=0;j<9;j++){
                sumac+=sud[j][i];
            }
             if(sumac!=45){
                 return false;
             }
        }
        
        //sumasubMatrices
//       int sumak=0;
//        for(int i=0;i<3;i++){
//           
//            for(int j=0;j<3;j++){
//                sumak=sud[i][j]+sumak;
//            }
//            
//        }
//        if(sumak!=45)return false;
//        
//        sumak=0;
//         for(int i=0;i<3;i++){
//             
//            for(int j=3;j<6;j++){
//                 sumak=sud[i][j]+sumak;
//            }
//            
//        }
//         if(sumak!=45)return false;
//         
//           sumak=0;
//          for(int i=0;i<3;i++){
//            
//            for(int j=6;j<9;j++){
//                sumak=sud[i][j]+sumak;
//
//            }
//            
//        }
//          
//           if(sumak!=45)return false;
//           sumak=0;
//           for(int i=3;i<6;i++){
//              
//            for(int j=0;j<3;j++){
//                sumak=sud[i][j]+sumak;
//
//            }
//        }
//            if(sumak!=45)return false;
//           sumak=0;
//            for(int i=3;i<6;i++){
//                
//            for(int j=3;j<6;j++){
//                sumak=sud[i][j]+sumak;
//
//            }
//        }
//             if(sumak!=45)return false;
//             sumak=0;
//            for(int i=3;i<6;i++){
//               
//            for(int j=6;j<9;j++){
//                sumak=sud[i][j]+sumak;
//
//            }
//        }
//             if(sumak!=45)return false;
//             sumak=0;
//            for(int i=6;i<9;i++){
//               
//            for(int j=0;j<3;j++){
//                sumak=sud[i][j]+sumak;
//
//            }
//        }
//             if(sumak!=45)return false;
//            sumak=0;
//            for(int i=6;i<9;i++){
//                
//            for(int j=3;j<6;j++){
//                sumak=sud[i][j]+sumak;
//
//            }
//        }
//             if(sumak!=45)return false;
//            sumak=0;
//            for(int i=6;i<9;i++){
//                
//            for(int j=6;j<9;j++){
//                sumak=sud[i][j]+sumak;
//
//            }
//        }
//            if(sumak!=45)return false;
//            
//       
        
        return true;
    }
}
