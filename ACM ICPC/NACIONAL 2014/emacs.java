import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class emacs {
	
	static String text;
	static int textlen;
	static ArrayList<Integer> letterIndex[];
	public static void main(String[] args) {
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		letterIndex = new ArrayList[26];
		try{
			String np;
		while( ( np = scan.readLine()) != null ){
			
			int numPatterns = Integer.parseInt(np);
			text = scan.readLine();
			
			textlen = text.length();
			//for(int l = 0; l < 26; l++)letterIndex[l]=null;
			for(int k = 0; k < textlen; k++){
				if( letterIndex[text.charAt(k)-'a'] == null ){
					letterIndex[text.charAt(k)-'a'] = new ArrayList<Integer>();
					letterIndex[text.charAt(k)-'a'].add(k);
				}else{
					letterIndex[text.charAt(k)-'a'].add(k);
				}
			}
			
			for(int i= 0; i < numPatterns; i++){
				
				String p = scan.readLine();
				
				boolean match = true;
				String words[] = p.split("\\*");
				
				int index = -1;
				
				wordCycle:for(String w : words){
					
					if(!w.isEmpty()){
						int wlen = w.length();
						index = findIndexOf(w.charAt(0), index);
						if(index!=-1){
							while(!checkWord(w,index)){
								if(index + wlen > textlen){
									match=false;
									break wordCycle;
								}
								index = findIndexOf(w.charAt(0), index);
								if(index == -1){match = false; break wordCycle;}
							}
						}else{
							match = false;
							break;
						}
					}
				}
				
				System.out.println(match?"yes":"no");		
			}
		}
		}catch(IOException e){}
	}
	private static int findIndexOf(char first, int index) {
		int pos = first-'a';
		int res = -1;
		
		if(letterIndex[pos]!=null){
			int letterlen = letterIndex[pos].size();
			
			for(int i = 0; i < letterlen; i++){
				int tempindex = letterIndex[pos].get(i);
				if(tempindex > index){
					res = tempindex;
					break;
				}
			}
		}
		return res;
	}
	private static boolean checkWord(String w, int index) {
		int wlen = w.length();
		boolean matches = true;
		if(index+wlen < textlen){
			for(int i = index; i < index+wlen; i++){
				if(w.charAt(i-index) != text.charAt(i)){
					matches = false;
					break;
				}
			}
		}else{
			matches = false;
		}
		return matches;
	}
	
	
	
}
