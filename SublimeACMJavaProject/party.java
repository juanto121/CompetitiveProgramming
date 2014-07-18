import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class party {
	
	public static void main (String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(in.readLine());
		int day = 1;
		while (n!= 0)
		{
			ArrayList<int[]> list = new ArrayList<int[]>();
			for (int i = 0; i < n; i ++)
			{
				String sp[] = in.readLine().split(" ");
				int s = Integer.parseInt(sp[0]);
				int f = Integer.parseInt(sp[1]);
				int[] k = {s,f};
				list.add(k);
			}
//			System.out.println(list.size());
			int cont = 0;
			
			for (int i = 0; i < 32; i ++)
			{
				int selected = -1;
				int start = 8+(i/2);
				int end = start +1;
				for (int k = 0; k < list.size(); k ++)
				{
					int[] p = list.get(k);
					if (p[0]<=start && p[1] >= end)
					{
						if (selected == -1)selected = k;
						else{
							if (p[1]<=list.get(selected)[1])selected = k;
						}
					}
				}
				if (selected != -1)
				{
//					System.out.println("De "+start+" a "+end+" "+list.get(selected)[0]+" "+list.get(selected)[1]);
					cont++;
					list.remove(selected);
				}
			}
			System.out.println("On day "+day+" Emma can attend as many as "+cont+" parties.");
			day++;
			n = Integer.parseInt(in.readLine());
		}
	}

}
