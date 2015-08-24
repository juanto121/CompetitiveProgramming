
public class SpaceInvadersGenerator {

	public static void main(String[] args) {
		for(int i = 0; i < 100;i++){
		 int r =(int)(Math.random()*1000.0 + 1);
		 int c =(int)(Math.random()*1000.0 + 1);
		 int s =(int)(Math.random()*1000.0 + 1);
		 System.out.println(String.format("%d %d %d",r,c,s));
		 
		 StringBuilder stb = new StringBuilder();
		 
		 for(int rows = 0; rows<r; rows++){
			 for(int cols = 0; cols < c; cols++){
				 stb.append(Math.random()>0.5?"#":".");
			 }
			 stb.append("\n");
		 }
		 
		 for(int shot = 0; shot<s; shot++){
			 stb.append(Math.random()>0.5?"-":"");
			 if(shot==s-1)
				 stb.append( String.format("%d",(int)(Math.random()*c + 1.0)));
			 else
				 stb.append( String.format("%d%n",(int)(Math.random()*c + 1.0)));
		 }
		 
		 System.out.println(stb.toString());
		
		}

	}

}
