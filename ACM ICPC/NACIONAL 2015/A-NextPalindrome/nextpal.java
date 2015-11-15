import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

class nextpal{
	public static void main(String[] args) throws Exception{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		String line;
		StringTokenizer str;
		
		while((line = scan.readLine()) != null){
			str = new StringTokenizer(line);
			int n = Integer.parseInt(str.nextToken());
			String d = str.nextToken();
			StringBuilder stb = new StringBuilder();
			for(int i = 0; i < n; i++){
				if(d.length()>1){
					String next = nextPal(d,d);
					stb.append( next );
					d = next;					
				}else{
					if(i!=0)
						stb.append(d);
					d = Integer.parseInt(d)+1+"";
				}
				if(i!=n-1) stb.append("\n");
			}
			System.out.print(stb.toString());
		}
		System.exit(0);		
	}

	private static String nextPal(String d, String original) {
		BigInteger bi = new BigInteger(original);
		int len = d.length();
		StringBuilder stb = new StringBuilder(len*4);
		StringBuilder rev = new StringBuilder(len*4);
		String left, mid;
		boolean even = len%2==0;
		if(even){
			left = d.substring(0,len/2);
			mid = "";
		}else{
			left = d.substring(0,len/2);
			mid = d.charAt(len/2)+"";
		}
		String res = makePalindrome(left,mid);
		BigInteger temp = new BigInteger(res);
		if(bi.compareTo(temp) >= 0 ){
			String newD = left+mid;
			BigInteger plusOne = new BigInteger(newD).add(new BigInteger("1"));
			
			int nlen = plusOne.toString().length();
			String nLeft = plusOne.toString().substring(0,nlen-1);
			String nMid = plusOne.toString().substring(nlen-1,nlen);
			if(original.length()%2==0) 
				res = new StringBuilder(plusOne.toString()).append(new StringBuilder(plusOne.toString()).reverse()).toString();
			else
				res = nextPal(makePalindrome(nLeft,nMid),original);
		}
		return res;
	}
	public static String getLeftHalf(String d){
		int len = d.length();
		if(len==1)return d;
		return d.substring(0,len/2);
	}
	public static String getMid(String d){
		int len = d.length();
		if(len==1)return "";
		return d.charAt(len/2)+"";
		
	}
	
	public static String makePalindrome(String left, String mid){
		StringBuilder stb = new StringBuilder(left);
		stb.append(mid);
		StringBuilder rev = new StringBuilder(left);
		return stb.append(rev.reverse()).toString();
	}
}