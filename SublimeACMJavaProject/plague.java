import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map.Entry;


public class plague {
	static class N{
		int n;
		public N(int v) {
			n=v;
		}
	}
	static HashMap<String,String> mutaciones=new HashMap<String, String>();
	static HashMap<String,Integer> proteina=new HashMap<String,Integer>(),cura=new HashMap<String,Integer>();
	public static void main(String[] args)throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		ciclo:for(String h;!(h=br.readLine()).equals("0 0 0 0");mutaciones.clear(),proteina.clear(),cura.clear()){
			String[]j=h.split(" +");
			int Nm=Integer.parseInt(j[0]),Ni=Integer.parseInt(j[1]),Nc=Integer.parseInt(j[2]),n=Integer.parseInt(j[3]);
			boolean deterministic=true;
			for(int e=0;e<Nm;e++){
				j=br.readLine().split(" +");
				if(mutaciones.get(j[0])!=null&&!mutaciones.get(j[0]).equals(j[1])){
					deterministic=false;
				}
				mutaciones.put(j[0],j[1]);
			}
			for(int e=0;e<Ni;e++){
				j=br.readLine().split(" +");
				proteina.put(j[0],Integer.parseInt(j[1]));
			}
			for(int e=0;e<Nc;e++){
				j=br.readLine().split(" +");
				cura.put(j[0],Integer.parseInt(j[1]));
			}
			if(!deterministic){
				System.out.println("Protein mutations are not deterministic");
				continue ciclo;
			}
			boolean iguales=true;
			for(int k=0;k<n+1;k++){
				iguales=true;
				for(Entry<String,Integer> ent:cura.entrySet()){
					Integer v=proteina.get(ent.getKey());
					if(v==null||v.intValue()!=ent.getValue().intValue()){
						iguales=false;
						break;
					}
				}
				for(Entry<String,Integer> ent:proteina.entrySet()){
					Integer v=cura.get(ent.getKey());
					if(v==null||v.intValue()!=ent.getValue().intValue()){
						iguales=false;
						break;
					}
				}
				if(iguales){
					System.out.println("Cure found in "+(k)+" mutation(s)");
					break;
				}
				if(k==n)break;
				HashMap<String,Integer> siguienteProteina=new HashMap<String, Integer>();
				for(Entry<String,Integer> ent:proteina.entrySet()){
					String next=mutaciones.get(ent.getKey());
					if(next==null){
						next=ent.getKey();
					}
					Integer cant=siguienteProteina.get(next);
					if(cant==null)cant=0;
					siguienteProteina.put(next,cant+ent.getValue());
				}
				//Igualdad
				proteina=siguienteProteina;
			}
			if(!iguales){
				System.out.println("Nostalgia for Infinity is doomed");
			}
		}
	}
}
