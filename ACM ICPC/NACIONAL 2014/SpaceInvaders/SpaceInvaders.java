import java.util.Arrays;
import java.util.Scanner;

public class SpaceInvaders {
	static char shield[][];
	static int r;
	static int c;
	static int[] unionfind;
	static int[] size;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		//try{	
		StringBuilder sol = new StringBuilder();
		while(scan.hasNextLine()){
			
			String desc[] = scan.nextLine().split(" ");
			r = Integer.parseInt(desc[0]);
			c = Integer.parseInt(desc[1]);
			
			int alienVirtual = r*c;
			int nostalgiaVirtual = r*c+1;
			
			int s = Integer.parseInt(desc[2]);
			unionfind = new int[r*c+2];
			for(int i = 0; i < r*c+2; i++) {
				unionfind[i] = i;
			}
			
			for(int i = 0; i < c; i++){
				unionfind[i] = unionfind[alienVirtual];
				unionfind[r*c-1-i] = unionfind[nostalgiaVirtual];
			}
			
			shield = new char[r][c];
			
			int alien[] = new int[c]; Arrays.fill(alien, r-1);
			int nostalgia[] = new int[c]; ;
			size = new int [r*c+2]; Arrays.fill(size, 1);
			
			for(int i = 0; i < r; i++){
				shield[i] = scan.nextLine().toCharArray();
			}
			
			boolean breached = false;

			for(int i = 0; i < r; i++){
				for(int j = 0; j < c; j++){
					if(shield[i][j] == '#'){
						if(i < alien[j] ) alien[j] = i;
						if(i > nostalgia[j] ) nostalgia[j] = i;
					}

					traverse(i,j);
				}
			}
			int shot, sign;

			
			if(find(unionfind[alienVirtual], unionfind[nostalgiaVirtual]) || breached){
				breached = true;
				sol.append("0\n");
				for(int k = 0; k < s; k++)scan.nextLine();
			}else{
				for(int k = 0; k < s; k++){
					 shot = Integer.parseInt(scan.nextLine());
					 
					 if(shot > 0){
						 sign = 1;
						 shot -= 1;
						 shield[alien[shot]][shot] = '.';
						 traverse(alien[shot],shot);
						 for(int fila = alien[shot]; fila < r; fila++) if(shield[fila][shot] == '#'){alien[shot] = fila; break;}
					 }else{
						 sign = -1;
						 shot = -shot-1;;
						 
						 shield[nostalgia[shot]][shot] = '.';
						 traverse(nostalgia[shot],shot);
						 for(int fila = nostalgia[shot]; fila >= 0; fila--) if(shield[fila][shot] == '#'){ nostalgia[shot] = fila; break;}
					 }
					 
					 if(find(unionfind[alienVirtual], unionfind[nostalgiaVirtual])){
						 sol.append(sign*(k+1)+"\n");
						 breached = true;
						 for(int scanleft = k+1; scanleft < s; scanleft++)scan.nextLine();
						 break;
					 }
					 
				}
			}
			
			if(!breached){
				sol.append("X\n");
			}
			
		}
		System.out.print(sol.toString());
	
		scan.close();

	}
	
	
	private static boolean find(int i, int j) {
		return root(i) == root(j);
	}
	
	
	private static int root(int i){
		while(i!=unionfind[i]){
			i=unionfind[i];
		}
		return i;
	}


	private static void union(int i, int j, int ii, int jj) {
		int x = c*i + j;
		int xp = c*ii + jj;
		
		int rootx = root(x);
		int rootxp = root(xp);
		
		if(rootx != rootxp){
			if(size[rootx] < size[rootxp]){
				unionfind[rootx] = rootxp;
				size[rootxp] = size[rootxp] + size[rootx];
			}else{
				unionfind[rootxp] = rootx;
				size[rootx] = size[rootxp] + size[rootx];
			}
		}
	}
	
	private static void traverse(int i, int j){

		if(shield[i][j] == '.'){
			if(r==1){
				unionfind[unionfind.length-1] = unionfind[unionfind.length-2];
			}
			if(i-1>=0 && shield[i-1][j]=='.'){ //arriba
					union(i-1,j,i,j);
			}
			if(i+1<r && shield[i+1][j] == '.'){ //abajo
				union(i+1,j,i,j);
			}
			if(j-1>=0 && shield[i][j-1]=='.'){ //izq
				union(i,j-1,i,j);
			}
			if(j+1<c && shield[i][j+1] == '.'){ //der
				union(i,j+1,i,j);
			}
		}
	}

}