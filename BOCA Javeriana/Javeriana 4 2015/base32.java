import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

class base32 {

	static HashMap<String,String> gatuno = new HashMap<String, String>();
	public static void main(String[] args) {
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		gatuno.put("00000","!");gatuno.put("00001","@");gatuno.put("00010","#");gatuno.put("00011","$");gatuno.put("00100","%");gatuno.put("00101","&");gatuno.put("00110","a");gatuno.put("00111","b");gatuno.put("01000","c");gatuno.put("01001","d");gatuno.put("01010","e");gatuno.put("01011","f");gatuno.put("01100","g");gatuno.put("01101","h");gatuno.put("01110","i");gatuno.put("01111","j");gatuno.put("10000","k");gatuno.put("10001","l");gatuno.put("10010","m");gatuno.put("10011","n");gatuno.put("10100","o");gatuno.put("10101","p");gatuno.put("10110","q");gatuno.put("10111","r");gatuno.put("11000","s");gatuno.put("11001","t");gatuno.put("11010","u");gatuno.put("11011","v");gatuno.put("11100","w");gatuno.put("11101","x");gatuno.put("11110","y");gatuno.put("11111","z");
		
		try {
			ArrayList<Integer>bytes = new ArrayList<Integer>();
			String byte_;
			while(true){
				 if ((byte_ = scan.readLine() ) == null || byte_.isEmpty()) {break;}
				 bytes.add(Integer.parseInt(byte_));
			}
					
			decode(bytes);
						
		} catch (IOException e) {}
	}

	public static void decode(ArrayList<Integer>bytes_array){
		
		int longi = bytes_array.size();
		
		Integer bytes[];
		bytes = bytes_array.toArray(new Integer[longi]);
		int num_words = 1;
		for(int w = 0; w < num_words; w++){
			StringBuilder stb = new StringBuilder();
			StringBuilder binary = new StringBuilder();
			
			for(int b = w; b < longi; b++){
				int in_byte = bytes[b];
				stb.append(String.format("%08d",Integer.parseInt(Integer.toBinaryString(in_byte))));				
			}
			
			int num_bits = stb.length();
			int zero_padding = 5 - num_bits%5 == 5 ? 0 : 5 - num_bits%5 ;
			num_bits+=zero_padding;
			for(int i = 0; i < zero_padding; i++) stb.append("0");
			int num_symbols = num_bits/5;
			for(int i = 0; i < num_symbols ; i++){
				if(i%80 == 0&&i!=0) binary.append(String.format("%n"));
				binary.append(gatuno.get(stb.substring(i*5,i*5+5)));
			}
		
			System.out.println(binary);
		}
	
	}
	
}

