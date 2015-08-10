import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class cyclicstring {

	public static void main(String[] args) {
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		
		try{
		int cases = Integer.parseInt(scan.readLine());
		Pattern p;
		Matcher m;
		for(int t = 0; t < cases; t++){
			StringBuilder symbol = new StringBuilder("");
			String line = scan.readLine();
			int l = line.length();
			char first = line.charAt(0);
			symbol.append(String.valueOf(first));
			int compPos = 0;
			boolean done = false;
			for(int c = 1; c < l; c++){
				int sl = symbol.length();
				char curChar = line.charAt(c);
				if(curChar == first && l-sl >= sl){
					compPos = 1;
					p = Pattern.compile("("+symbol.toString() + ")+");
					m = p.matcher(line);
					if(m.matches()){
						System.out.println(symbol.length());
						done = true;
						break;
					}
				}else{
					if(compPos < sl && symbol.charAt(compPos) == curChar && l-sl>=sl){
						compPos ++;
					}else{
						symbol.replace(0, sl, line.substring(0, c+1));
						
						compPos = 0;
					}
				}
			}
			
			if(!done) System.out.println(symbol.length());
			
		}
		}catch(IOException e){
			
		}
		
	}

}
