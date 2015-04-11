import java.util.Scanner;
import java.util.ArrayList;

class Main
{
	//public static add_remove()
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int m,n, fdisk;
		while(true){

			ArrayList<Integer> A = new ArrayList<Integer>();
			ArrayList<Integer> B = new ArrayList<Integer>();
			ArrayList<Integer> C = new ArrayList<Integer>();

			ArrayList peg[] = {A, B, C};

			m = scan.nextInt();
			n = scan.nextInt();
			int pegs[] = new int[3];
			fdisk = 0;

			for(int i = 0; i < m ; i++)	A.add(i);

			if(m == 0 && n == 0)break;
			int move = 0;	
			int min = -1;
			int min_ind = 0;
				
			for(int i = 0; i < m; i++){

				if(i % 2 == 0){
					pegs[fdisk]--;
					fdisk = (fdisk + 1)%3;
					pegs[fdisk]++;
				}
				else{
					int prox = (fdisk + 1) % 3;
					int prev = (fdisk + 1) % 3;
					int prox_size = peg[prox].size();
					int prev_size = peg[prev].size();
					if(peg[prox].isEmpty() && !peg[prev].isEmpty()){
						peg[prox].add(peg[prev].get(prev_size-1));
						peg[prev].remove(peg[prox].get(prox_size-1));
						pegs[prox]++;
						pegs[prev]--;
						continue;
					}
					if(!peg[prox].isEmpty() && peg[prev].isEmpty()){
						peg[prox].remove(peg[prev].get(prev_size-1));
						peg[prev].add(peg[prox].get(prox_size-1));
						pegs[prox]--;
						pegs[prev]++;
						continue;
					}
					int prev_item = (int)peg[prox].get(prox_size-1);
					int prox_item = (int)peg[prox].get(prox_size-1);
					if( prox_item < prev_item ){
						peg[prev].add(prox_item);
						peg[prox].remove(prox_size-1);
						pegs[prev] ++;
						pegs[prox] --;
					}else{
						peg[prox].add(prev_item);
						peg[prev].remove(prev_size-1);
						pegs[prev]--;
						pegs[prox]++;
					}

				}

			}

			System.out.print(pegs[0]+" "+pegs[1]+" "+pegs[2]);
		}
	}
}