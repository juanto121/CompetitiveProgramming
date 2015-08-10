import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;


public class opening {

	public static void main(String[] args) {
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		try{
			int blocks = Integer.parseInt(scan.readLine());
			TreeMap<Integer,Integer> alturas = new TreeMap<Integer,Integer>();
			String edificios[] = new String[blocks];
			StringTokenizer str = new StringTokenizer(scan.readLine());
			int tipingh = 0;
			Integer token;
			Integer h;
			while(str.hasMoreTokens()){
				token = Integer.parseInt(str.nextToken());
				if( (h=alturas.get(token))==null){
					alturas.put(token, 0);
					h=0;
				}
				alturas.put(token,++h);
			}
			
			int hcuts = 0;
			int totalcuts = 0;
			int edpisos;
			int edcount;
			Iterator it = alturas.entrySet().iterator();
			//System.out.println(alturas.toString());
			while(it.hasNext()){
				Map.Entry<Integer, Integer> pair = (Map.Entry)it.next();
				edpisos = pair.getKey();
				edcount = pair.getValue();
				if( edpisos-hcuts < blocks){
					hcuts = edpisos - hcuts;
					totalcuts += hcuts;
				}else{
					totalcuts += edcount;
				}
				blocks -= edcount;
			}
			System.out.println(totalcuts);
		}catch(IOException io){}
	}

}
