/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphsacm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author USERE
 */
 class bic{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        StringTokenizer str;
       
       while(scan.hasNextLine()){
        int vert = Integer.parseInt(scan.nextLine());
      
        if(vert==0)break;
  int edges = Integer.parseInt(scan.nextLine());
        ArrayList<Integer> graph[] = new ArrayList[vert] ;
        int color[] = new int[vert];
        Arrays.fill(color, 0);
        for(int i = 0;i<edges;i++){
            str = new StringTokenizer(scan.nextLine());
            int source = Integer.parseInt(str.nextToken());
            int dest = Integer.parseInt(str.nextToken());
            if( graph[source] == null ) 
                graph[source] = new ArrayList();
            if(graph[dest] == null)
                graph[dest] = new ArrayList();
           
            graph[source].add(dest);
            graph[dest].add(source);
        }
        
        if(DFS(color,graph,0,1)){
            System.out.println("BICOLORABLE.");
        }else{
            System.out.println("NOT BICOLORABLE.");
        }
        
    }
       // System.out.println(graph[0].toString());
    }
    //Colores de los Nodos
    //Grafo
    //Nodo Actual
    //Color esperado para el nodo actual.
    static boolean DFS(int[]colores,ArrayList<Integer>[]graph,int CuNode,int color){
       if(colores[CuNode] != 0){
           return (colores[CuNode]==color);
       }else{
            colores[CuNode] = color;
            for(int i = 0;i<graph[CuNode].size();i++){
                int nwC = (color == 1) ? 2:1;
                if(DFS(colores,graph,graph[CuNode].get(i),nwC) == false ) return false;
            }
       }
        return true;
       
    }
}