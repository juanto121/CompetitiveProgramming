import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class RatAttack {
	
	static int MAXC = 1025;
	static int city[][] = new int[MAXC][MAXC];
	static int maxp = -1;
	static int maxpx = -1;
	static int maxpy = -1;
	
	public static void main(String[] args) {
		
		BufferedReader scan = new BufferedReader( new InputStreamReader(System.in));
		try{
			int cases = Integer.parseInt(scan.readLine());
			for(int t = 0; t < cases ; t++){
				for(int i = 0; i < MAXC; i++) Arrays.fill(city[i], 0);
				int d = Integer.parseInt(scan.readLine());
				int rats = Integer.parseInt(scan.readLine());
				int maxx = -1;
				int maxy = -1;
				
				maxp = -1;
				maxpx = -1;
				maxpy = -1;
				
				for(int r = 0; r < rats; r++){
					
					StringTokenizer str = new StringTokenizer(scan.readLine());
					int x = Integer.parseInt(str.nextToken());
					int y = Integer.parseInt(str.nextToken());
					maxx = x>maxx?x:maxx;
					maxy = y>maxy?y:maxy;
					
					int population = Integer.parseInt(str.nextToken());
					
					int xneg = x-d >= 0 ? x-d : 0;
					int yneg = y-d >= 0 ? y-d : 0;
					int xpos = x+d <MAXC?x+d:MAXC-1;
					int ypos = y+d <MAXC?y+d:MAXC-1;
					
					for(int i = xneg; i<=xpos; i++){
						for(int j = yneg; j <= ypos; j++){
							city[i][j] += population;
							max(i,j);
						}
					}
					
							
				}
				
				System.out.println(String.format("%d %d %d", maxpx, maxpy, maxp));
				
			}
		
		}catch(IOException e){}
	}

	private static boolean limites(int x, int y) {
		return x>=0 && y >= 0 && x < MAXC && y < MAXC;
	}

	private static void max(int x, int y) {
		if(city[x][y] > maxp){
			maxp = city[x][y];
			maxpx = x;
			maxpy = y;
		}
	}

}

/*
 * 	if(xneg>=0){ 
							city[xneg][y] += population;
							max(xneg,y);
						}
						if(yneg>=0){city[x][yneg] += population; max(x,yneg);}
						if(xpos<MAXC){city[xpos][y] += population; max(xpos,y);}
						if(ypos<MAXC){city[x][ypos] += population; max(x,ypos);}
						if(limites(xneg,yneg)){city[xneg][yneg] += population; max(xneg,yneg);}
						if(limites(xneg,ypos)){city[xneg][ypos] += population; max(xneg,ypos);}
						if(limites(xpos,yneg)){city[xpos][yneg] += population; max(xpos,yneg);}
						if(limites(xpos,ypos)){city[xpos][ypos] += population; max(xpos,ypos);}
						*/
