import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class allergies {
	
	static final int MAX_ALLERGEN = 26;
	static boolean existentAllergens[];

	public static void main(String[] args) throws IOException {
		
		/*
			If there is an allergen that describes all food I can't eat
			then that allergen must be in all foods I can't eat.

			If there is an allergen that describes all food I CAN eat,
			all foods I can eat must be FREE of such allergen.

			If I can eat food A that has allergen i, then I can not 
			say Im allergic to i.
		*/

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String testCases = bf.readLine();
		int t = Integer.parseInt(testCases);
		for(int test = 0; test < t; test++){
			String _allergens = bf.readLine();
			
			boolean solution [] = new boolean[MAX_ALLERGEN];
			Arrays.fill(solution, true);
			
			boolean solutionFree[] = new boolean[MAX_ALLERGEN];
			Arrays.fill(solutionFree, false);
			
			//No allergens exist until a food has it or Im allergic to it.
			existentAllergens = new boolean[MAX_ALLERGEN];
			
			//true if I'm allergic to allergens[i]
			boolean allergens[] = allergenBits(_allergens, false);
			
			int numFoods = Integer.parseInt(bf.readLine());
			for(int f = 0; f < numFoods; f++){
				String _food = bf.readLine();
				
				//food[i] true if contains allergen[i]
				boolean food[] = allergenBits(_food, true);
				if(false == edible(food, allergens)){
					updateSolution(solution, food);
				}else{
					updateSolutionFree(solutionFree, food);
				}
			}
			//Remove allergens that don't exist
			updateSolution(solution, existentAllergens);
			updateSolution(solutionFree, existentAllergens);
			
			//Remove inconsistencies, allergen is present in foods I can and can't eat.
			removeInconsitencies(solution, solutionFree);
			
			int count = 0;
			int allergen = 0;
			for(int i = 0; i < MAX_ALLERGEN; i++){
				if(solution[i]){
					count++;
					allergen = i;
				}
			}
			if(count == 0 || count >1){
				System.out.println("No Solution");
			}
			if(count == 1){
				System.out.println((char)(allergen + 65));
			}
		}
	}
	
	private static void removeInconsitencies(boolean[] solution, boolean[] solutionFree) {
		for(int i = 0; i < MAX_ALLERGEN; i++){
			if(solutionFree[i]){
				solution[i] = false;
			}
		}
	}

	private static void updateSolutionFree(boolean[] solution, boolean[] food) {
		for(int i = 0; i < MAX_ALLERGEN; i++){
			solution[i] = solution[i] || food[i];
		}
	}

	private static void updateSolution(boolean[] solution, boolean[] food) {
		for(int i = 0; i < MAX_ALLERGEN; i++){
			solution[i] = solution[i] && food[i];
		}
	}

	private static boolean edible(boolean[] food, boolean[] allergens) {
		for(int i = 0; i < MAX_ALLERGEN; i++){
			if(food[i] && allergens[i]){
				return false;
			}
		}
		return true;
	}

	public static boolean[] allergenBits(String allergen, boolean flip){
		int len = allergen.length();
		boolean a[] = new boolean[MAX_ALLERGEN];
		Arrays.fill(a, flip);
		for(int i = 0; i < len; i++){
			int index = allergen.charAt(i) - 'A';
			a[index] = !flip;
			existentAllergens[index] = true;
		}
		return a;
	}

}
