import java.util.Arrays;
import java.util.Scanner;


public class WinStay {

	static final int R = 0, P = 1, S = 2;
	static final double maxi = 10010.0;
	static double probability[];
	static String opponent;
	
	static result dp[][] = new result[3][10001];
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int cases = Integer.parseInt(scan.nextLine());
		for(int t = 0; t < cases; t++){
			String mine = scan.nextLine();
			opponent = scan.nextLine();
			
			String probabilityString[] = scan.nextLine().split(" ");
			probability = new double[3];
			for(int i = 0; i < 3; i++) {
				probability[i] = Integer.parseInt(probabilityString[i])/100.0;
			}
			
			for(int k = 0; k < 3; k++) Arrays.fill(dp[k],null);
			
			int jugada = 0;
			int outcome = 0;
			//R = 0, P = 1, S = 2
			double r = probability[R]*play('R',jugada,outcome).outcome;		
			double s = probability[S]*play('S',jugada,outcome).outcome;		
			double p = probability[P]*play('P',jugada,outcome).outcome;
					
					
					
					
			double expected = r + p + s;
			
			int roundlen = mine.length();
			int score = 0;
			for(int j = 0; j < roundlen; j++){
				score += check(mine.charAt(j),opponent.charAt(j));
			}
			
			
			System.out.println(String.format("%d %.4f %s" ,score,expected,expected>score?"Y":"N") );
		}

	}
	
	private static result play(char current, int i, int outcome) {
		if(current=='R'&&dp[R][i]!=null)return new result(dp[R][i]);
		if(current=='S'&&dp[S][i]!=null)return new result(dp[S][i]);
		if(current=='P'&&dp[P][i]!=null)return new result(dp[P][i]);
		
		if(i == opponent.length()){
			return new result(0.0,1.0);
		}else{
			int matchResult = check(current, opponent.charAt(i)); 
								
			if(current != opponent.charAt(i)){
				char next = strategy(current,matchResult);
				
				result subtree = play(next, i+1, matchResult);
				subtree.probability *= 1;
				subtree.outcome += subtree.probability*matchResult;
				if(current=='R')dp[R][i]=subtree;
				if(current=='S')dp[S][i]=subtree;
				if(current=='P')dp[P][i]=subtree;
				return new result(subtree);
			}else{
				result r, p, s;
				
				r = play('R',i+1,matchResult);
				r.probability *= probability[R];
				r.outcome += matchResult;
				//dp[R][i+1] = r;
				
				p = play('P',i+1,matchResult);
				p.probability *= probability[P];
				p.outcome += matchResult;
				//dp[P][i+1] = p;
				
				s = play('S',i+1,matchResult);
				s.probability *= probability[S];
				s.outcome += matchResult;
				//dp[S][i+1] = s;
				
				result res = new result(r.probability*r.outcome+
										p.probability*p.outcome+
										s.probability*s.outcome,r.probability+p.probability+s.probability );
				
				if(current=='R')dp[R][i]=res;
				if(current=='S')dp[S][i]=res;
				if(current=='P')dp[P][i]=res;
				
				
				return new result(res);
			}
		}
		
	}
	
	private static char strategy(char current, int res) {
		if(res == 1){
			if(current == 'R') return 'P';
			if(current == 'P') return 'S';
			if(current == 'S') return 'R';
		}
		if(res == -1){
			if(current == 'R') return 'S';
			if(current == 'P') return 'R';
			if(current == 'S') return 'P';
		}
		return current;
	}

	private static int check(char a, char b) {
		if(a == 'R') if (b == 'S') return 1;
		if(a == 'P') if (b == 'R') return 1;
		if(a == 'S') if (b == 'P') return 1;
		
		if(a == b) return 0;
		return -1;
	}

}
class result{
	double outcome;
	double probability;
	
	public result(result copy){
		outcome = copy.outcome;
		probability = copy.probability;
	}
	
	public result(double outcome, double probability){
		this.outcome = outcome;
		this.probability = probability;
	}
	public String toString(){
		return outcome+" "+probability;
	}
}