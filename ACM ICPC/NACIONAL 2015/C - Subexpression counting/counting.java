package maraton_nacional_2015;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class counting {

	public static void main(String[] args) {
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		String line;
		try{
			while((line = scan.readLine()) != null){
				String line2 = scan.readLine();
				String pattern = stringMap(line);
				String text = stringMap(line2);
				int start = 0;
				int patternlen = 0;
				int len = text.length();
				int count = 0;
				int loquelleva = 0;
				while(start < len){
					start = match(text.substring(loquelleva, len), pattern);
					patternlen = pattern.length();
					if(start == -1) break;
					loquelleva += start + patternlen;
					count++;
				}
				System.out.println(count);
			}
		}catch(Exception e){}
	}
	
	public static String stringMap(String expression){
		StringBuilder stb = new StringBuilder();
		String line = expression;
		int len = expression.length();
		for(int i=0; i<len; i++){
			char c = line.charAt(i);
			if(c == '('){
				stb.append("c");
			}else if(c == ')'){
				stb.append("d");
			}else if(c == '*' || c == '+' || c == '-' || c == '/'){
				stb.append("b");
			}else{
				stb.append("a");
				while(i+1 < len && Character.isDigit(line.charAt(i+1))){
					++i;
				}
			}
		}
		return stb.toString();
	}
	
	public static int[] buildPrefix(String pattern){
		int prefix[] = new int[pattern.length()];
		int k = 0;
		int plen = pattern.length();
		for(int i=1; i<plen; i++){
			while(k>0 && pattern.charAt(k) != pattern.charAt(i)){
				k = prefix[k-1];
			}
			if(pattern.charAt(k) == pattern.charAt(i)){
				++k;
			}
			prefix[i] = k;
		}
		return prefix;
	}
	
	public static int match(String text, String pattern){
		int plen = pattern.length();
		int textlen = text.length();
		if(plen == 0){
			return 0;
		}
		int[] prefix = buildPrefix(pattern);
		for(int i=0, k=0; i<textlen; i++){
			while(k>0 && pattern.charAt(k) != text.charAt(i))
				k = prefix[k-1];
			if(pattern.charAt(k) == text.charAt(i))
				++k;
			if(k==plen)
				return i+1-plen;			
		}
		return -1;
	}

}