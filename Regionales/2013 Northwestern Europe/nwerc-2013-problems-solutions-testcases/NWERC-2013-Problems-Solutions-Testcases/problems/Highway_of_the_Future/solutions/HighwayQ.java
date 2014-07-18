// @EXPECTED_RESULTS@: CORRECT
import java.util.*;
import java.io.*;
public class HighwayQ{
  public static void main (String [] args){
     runJ();
  }

  private static void runJ(){
      try {
         BufferedReader ir = new BufferedReader(
                                  new InputStreamReader(System.in));
         String s = ir.readLine();
         while (s != null) {
                int n = Integer.parseInt(s);
                Highway h = new Highway(n, ir);
                System.out.println(h.solution());
                s = ir.readLine();
         }
      }
      catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
   }

}

class Highway{
   //avoids non-existent collisions
   //keeps (per car) only times, not complete collisions
   ArrayList<Car> cars = new ArrayList<Car>();
   LinkedList<Car> byDepart = new LinkedList<Car>();

   int champ = 1;

   public Highway(int n, BufferedReader ir){
      for (int i = 0; i < n; i++) {
        try{
           String s = ir.readLine();
           String[] w = s.split(" ");
           int t = Integer.parseInt(w[0]);
           int v = Integer.parseInt(w[1]);
           cars.add(new Car(t, v));
        }
        catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }
      }
      Collections.sort(cars);
      compress();
   }

   int solution(){
      solve();
      return champ;
   }

   private void compress(){
        ArrayList<Car> newcars = new ArrayList<Car>();
        Car present = null;
        for (Car car : cars)
          if (car.equals(present))
             present.frequency++;
          else{
             present = car;
             newcars.add(present);
          }
        cars = newcars;
   }

   void solve(){
      ArrayList<Car> byDepartPre = new ArrayList<Car>();
      byDepartPre.addAll(cars);
      Collections.sort(byDepartPre, new ByDepart());
      byDepart.addAll(byDepartPre);

      for (Car car : cars)
         handle(car);
   }

   void handle(Car present){
      if (present.frequency > champ)
         champ = present.frequency;
      HashMap<Fraction, Integer> cols = new HashMap<Fraction, Integer>(500);
      ListIterator<Car> dCars = byDepart.listIterator();
      while (dCars.hasNext()){
        Car car = dCars.next();
        if (car == present){
           dCars.remove();
           break;
        }

        Fraction collt = present.collisionTime(car);
        if (!cols.containsKey(collt))
           cols.put(collt, present.frequency);
        int val = cols.get(collt) + car.frequency;
        cols.put(collt, val);
        if (val > champ)
           champ = val;
      }
   }
}

class Car implements Comparable<Car>{
   int time;
   int speed;
   Fraction leave;
   int frequency = 1;

   public Car(int t, int v){
       time = t;
       speed = v;
       leave = new Fraction(t * v + 100, v);
   }

   public int compareTo(Car other){
      if (this.time == other.time)
         return this.speed - other.speed;
      return this.time - other.time;
   }

   public boolean equals(Object other){
       if (other instanceof Car){
          Car that = (Car) other;
          return this.time  == that.time &&
                 this.speed == that.speed;
       }
       return false;
   }

   public String toString(){
      return "Car: time =  "  + time + "  speed =  " + speed + "  "  + frequency;
   }

   public Fraction collisionTime(Car other){
      return new Fraction(other.time * other.speed - this.time * this.speed, other.speed - this.speed);
   }
}

class ByDepart implements Comparator<Car>{

    public int compare (Car left, Car right){
       int dleave = left.leave.compareTo(right.leave);
       if (dleave == 0)
          return right.compareTo(left);
       else
          return dleave;
    }
}

class Fraction implements Comparable<Fraction>{

    private int numerator;
    private int denominator;  // 0  < denominator and gcd(numerator, denominator) = 1

    public Fraction(int t, int n){
    	if (n == 0)
    	  throw (new RuntimeException("denominator in construction of Fraction is zero"));
    	if (n < 0){
    	   n = -n;
         t = -t;
    	}

      int g = gcd(t%n,n);
      numerator = t/g;
      denominator = n/g;
    }

    public int compareTo(Fraction other){
       if (this.equals (other))
          return 0;
       if (this.numerator * other.denominator <  this.denominator * other.numerator)
          return -1;
       return 1;
    }

    public boolean equals(Object other){
       if (other instanceof Fraction){
          Fraction that = (Fraction) other;
          return this.numerator == that.numerator &&
                 this.denominator == that.denominator;
       }
       return false;
    }

    private static int gcd(int grootste, int kleinste){
      while (kleinste != 0){
	       int restant = grootste % kleinste;
         grootste    = kleinste;
	       kleinste    = restant;
      }
      return grootste;
    }

    public double doubleValue(){
        return numerator / (double) denominator;
    }

    public String toString(){
        return "(" + numerator + "/" + denominator + ") ";
    }

    public int hashCode(){
       return denominator +
              100 * numerator ;
    }
}

