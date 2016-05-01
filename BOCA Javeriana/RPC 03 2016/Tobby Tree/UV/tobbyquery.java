/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author estudiantes
 */
class tobbyquery {
    public static void main(String args[]) throws IOException{
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        String cases = null;
        while((cases = sc.readLine()) != null){
            String[] array=sc.readLine().split(" ");
            int logs=Integer.parseInt(sc.readLine());
            for (int i = 0; i < logs; i++) {
                String[] log=sc.readLine().split(" ");
                Set<Integer> set = new HashSet<Integer>();
                int pos0=Integer.parseInt(log[0]);
                int pos1=Integer.parseInt(log[1]);
                int cont=0;
                for(int j=pos0-1;j<=pos1-1;j++){
                    int x=Integer.parseInt(array[j]);
                    if(!set.contains(x)){
                        set.add(x);
                        cont++;
                    }
                }
                System.out.println(cont);
            }
        }
    }
}