import java.util.HashMap;
import java.util.Scanner;


public class units {

	public static void main(String[] args) {
	
		Scanner scan = new Scanner(System.in);
		while(scan.hasNextLine()){
			int num_unit = Integer.parseInt(scan.nextLine());
			HashMap<String,String> nombres = new HashMap<String,String>();
			HashMap<String,Integer> valores = new HashMap<String,Integer>();
			String unidades[] = scan.nextLine().split(" ");
			for(int n = 0; n < num_unit; n++){
				nombres.put(unidades[n], null);
				valores.put(unidades[n], -1);
			}
			
			for(int n = 0; n < num_unit-1; n++){
				String equality[] = scan.nextLine().split(" ");
				if( nombres.get(equality[0])== null){
					//If left unit is empty
					nombres.put(equality[0], equality[1]);
					valores.put(equality[0], Integer.parseInt(equality[2]) );
				}else{
					// If left unit is already modified.
					
					String lastUnit = nombres.get(equality[0]);
					int lastValue = valores.get(equality[0]);
					String to_modify = lastUnit;
					
					while( lastValue < valores.get(to_modify) ){
						
						nombres.put(to_modify, equality[3]);
						valores.put(to_modify, Integer.parseInt(equality[2]));
						to_modify = lastUnit;
						
					}

					
				}
			}
			
		}

	}

}
