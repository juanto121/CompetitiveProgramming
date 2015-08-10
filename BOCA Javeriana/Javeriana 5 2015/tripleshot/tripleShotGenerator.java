
public class tripleShotGenerator {

	public static void main(String[] args) {
		for(int i = 0; i < 100; i++){
			for (int j = 0; j < 3; j++) {
				System.out.println( (int)(Math.random()*1001)+" "+ (int)(Math.random()*1001));
			}
			System.out.println("");
		}
	}

}
