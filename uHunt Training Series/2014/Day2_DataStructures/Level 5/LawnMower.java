import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class LawnMower {

	public static void main(String[] args) {
		StringTokenizer str ;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try{
			while(true){
				String desc = reader.readLine();
				str = new StringTokenizer(desc);
				
				int nx = Integer.parseInt(str.nextToken());
				int ny = Integer.parseInt(str.nextToken());
				double w = Double.parseDouble(str.nextToken());
				
				if(nx == 0 && ny == 0 && w == 0.0){
					break;
				}
				
				if( false )
					System.out.println("NO");
				else{
					double mowed_regions_x [] = new double[nx];
					double mowed_regions_y [] = new double[ny];
					
					String x_regions = reader.readLine();
					str = new StringTokenizer(x_regions);
					
					for(int i = 0; i < nx ; i++){
						mowed_regions_x [i] = Double.parseDouble(str.nextToken())-w/2;
					}
					
					String y_regions = reader.readLine();
					str = new StringTokenizer(y_regions);
					
					for(int i = 0; i < ny ; i++){
						mowed_regions_y [i] = Double.parseDouble(str.nextToken())-w/2;
					}
					
					Arrays.sort(mowed_regions_x);
					Arrays.sort(mowed_regions_y);
					
					double lastMowed = 0.0;
					boolean solved = true;
					for(int r = 0; r < nx; r++){
						if( !( mowed_regions_x[r] <= lastMowed && mowed_regions_x[r]+w >= lastMowed  )){
							solved = false;
							break;
						}else{
							lastMowed = mowed_regions_x[r]+w;
						}
					}
					
					if(lastMowed < 75.0 ){
						solved = false;
					}else{
												
						lastMowed = 0.0;
						
						for(int r = 0; r < ny; r++){
							if( !( mowed_regions_y[r] <= lastMowed && lastMowed <= mowed_regions_y[r]+w   )){
								solved = false;
								break;
							}else{
								lastMowed = mowed_regions_y[r]+w;
							}
						}
						if(lastMowed < 100){solved = false;}
						
					}
					
					if(solved) System.out.println("YES");
					else System.out.println("NO");
				}
			}
			
		}catch(IOException io){
			
		}
		
	}

}
