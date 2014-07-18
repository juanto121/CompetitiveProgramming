// @EXPECTED_RESULTS@: TIMELIMIT

import java.util.*;
import java.io.*;

class BorisOptLL {

    public static Scanner scan = new Scanner(System.in);

    static class Car {
        int v, t;
        Car() {
            t = scan.nextInt();
            v = scan.nextInt();
        }
    }

    public static Comparator<Car> carComparator = new Comparator<Car>() {
        @Override
        public int compare(Car left, Car right) {
            return left.t - right.t;
        }
    };

    static class Frac {
        long p, q;
        Frac(long p, long q) {
            this.p = p;
            this.q = q;
        }
    }

    public static Comparator<Frac> fracComparator = new Comparator<Frac>() {
        @Override
        public int compare(Frac left, Frac right) {
            long x = left.p * right.q - right.p * left.q;
            if( x < 0l ) {
                return -1;
            } else if( x > 0l ) {
                return 1;
            }
            return 0;
        }
    };

    public static void solve() {
        int n = scan.nextInt();
        List<Car> cars = new ArrayList<Car>(n);
        for( int i = 0; i < n; ++i ) {
            cars.add(new Car());
        }
        Collections.sort(cars, carComparator);
        int jstart = 0;
        int ans = 1;
        for( int i = 0; i < n; ++i ) {
            Car car = cars.get(i);
            Map<Frac,Integer> counts = new TreeMap<Frac,Integer>(fracComparator);
            int nrsame = 1;
            for( int j = jstart; j < i; ++j ) {
                Car other = cars.get(j);
                if( other.t < car.t - 100 ) {
                    jstart = j+1;
                    continue;
                }
                if( other.t > car.t + 100 ) {
                    break;
                }
                if( car.v == other.v ) {
                    if( car.t == other.t ) {
                        nrsame += 1;
                    }
                    continue;
                }
                Frac t = new Frac(car.v * car.t - other.v * other.t, car.v - other.v);
                if( t.q < 0l ) {
                    t.p *= -1l;
                    t.q *= -1l;
                }
                Frac t1 = new Frac(car.t, 1);
                if( fracComparator.compare(t, t1) < 0 ) {
                    continue;
                }
                Frac t2 = new Frac(100 + car.v * car.t, car.v);
                if( fracComparator.compare(t2, t) < 0 ) {
                    continue;
                }
                if( counts.containsKey(t) ) {
                    counts.put(t, counts.get(t) + 1);
                } else {
                    counts.put(t, 1);
                }
            }
            if( nrsame > ans ) {
                ans = nrsame;
            }
            for( Map.Entry<Frac,Integer> entry : counts.entrySet() ) {
                if( entry.getValue() + nrsame > ans ) {
                    ans = entry.getValue() + nrsame;
                }
            }
        }
        System.out.printf("%d\n", ans);
    }

    public static void main(String[] args) {
        while( scan.hasNextInt() ) {
            solve();
        }
    }
}

