// @EXPECTED_RESULTS@: TIMELIMIT

import java.util.*;
import java.io.*;

class BorisOpt {

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

    public static Comparator<Double> dblComparator = new Comparator<Double>() {
        @Override
        public int compare(Double left, Double right) {
            if( Math.abs(left - right) < 1e-6 ) {
                return 0;
            }
            if( left < right ) {
                return -1;
            } else if( left > right ) {
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
            Map<Double,Integer> counts = new TreeMap<Double,Integer>(dblComparator);
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
                double t = (double) (car.t*car.v - other.t*other.v) / (double) (car.v - other.v);
                if( t < car.t ) {
                    continue;
                }
                if( 100.0 / car.v + car.t < t ) {
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
            for( Map.Entry<Double,Integer> entry : counts.entrySet() ) {
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

