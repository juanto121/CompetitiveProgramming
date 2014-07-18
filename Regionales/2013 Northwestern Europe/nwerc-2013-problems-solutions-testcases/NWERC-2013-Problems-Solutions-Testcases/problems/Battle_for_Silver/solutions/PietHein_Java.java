// @EXPECTED_RESULTS@: CORRECT
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PietHein_Java {
    public static Scanner scanner;
    public static void main(String[] args) {
        try {
            scanner = new Scanner(System.in);//new File("C:/Users/b.pat-el/Documents/T.NET/CHipCIE/NWERC-2013/problems/C06_PietHein/testcases/generated_30000.in"));

            while (scanner.hasNext()) new PietHein_Java().solve();

        } catch(Exception ex) {
			ex.printStackTrace();
		}	

        finally {
            scanner.close();
        }
    }

    private void solve() {
        int vertexCount = scanner.nextInt();
        int edgeCount = scanner.nextInt();

        Map<Integer, Vessel> vesselDict = new HashMap<>();
        ArrayList<Vessel> vessels = new ArrayList<>();
        for (int i = 0; i < vertexCount; i++) {
            int id = i + 1;
            int loot = scanner.nextInt();
            Vessel v = new Vessel(id, loot);
            vessels.add(v);
            vesselDict.put(id, v);
        }
        ArrayList<Chain> chains = new ArrayList<>();
        for (int i = 0; i < edgeCount; i++) {
            int startId  = scanner.nextInt();
            int endId = scanner.nextInt();
            Vessel vstart = vesselDict.get(startId);
            Vessel vend = vesselDict.get(endId);
            Chain newChain = new Chain(vstart, vend);
            chains.add(newChain);
            vstart.chains.add(newChain);
            vend.chains.add(newChain);
        }

        Squadron graph = new Squadron();
        graph.chains = chains;
        graph.vessels = vessels;

        Collection<Chaingroup> cliques = findCliques(graph, false);

        int largest = 0;
        for (Chaingroup cg : cliques) {
            if (cg.getLoot() > largest)
                largest = cg.getLoot();
        }

        System.out.println(largest);
    }

    private Collection<Chaingroup>findCliques(Squadron graph, boolean verbose) {
        Collection<Chaingroup> cliquesSize2 = findCliquesOfSize2(graph, verbose);
        Collection<Chaingroup> cliquesSize3 = findCliquesOfSize3(graph, verbose);
        Collection<Chaingroup> cliquesSize4 = findCliquesOfSize4(graph, verbose);

        Collection<Chaingroup> result = new ArrayList<>();
        result.addAll(cliquesSize2);
        result.addAll(cliquesSize3);
        result.addAll(cliquesSize4);

        return result;
    }

    private ArrayList<Chaingroup> findCliquesOfSize2(Squadron graph, boolean verbose) {
        ArrayList<Chaingroup> result = new ArrayList<>();

        for (Chain c : graph.chains) {
            ArrayList<Vessel> clique = new ArrayList<>();
            clique.add(c.start);
            clique.add(c.end);
            result.add(new Chaingroup(clique));
        }

        return result;
    }

    private Collection<Chaingroup> findCliquesOfSize3(Squadron graph, boolean verbose) {
        graph.removeVertexWithDegreeLowerThan(2);

        Map<Integer, Chaingroup> result = new HashMap<>();
        Map<Integer, Boolean> alreadyHandled = new HashMap<>();

        for (Vessel v : graph.vessels)
            alreadyHandled.put(v.id, false);

        ArrayList<Vessel> vesselCopy = (ArrayList<Vessel>) graph.vessels.clone();
        for (Vessel vertex : graph.vessels) {
            if (verbose) System.out.println("" + vertex.id);
            for (Vessel buur : vertex.getNeighbours()) {
                if (alreadyHandled.get(buur.id) || buur == vertex) continue;
                if (verbose) System.out.println("\t" + buur.id);
                for (Vessel buur2 : buur.getNeighbours()) {
                    if (alreadyHandled.get(buur2.id) || buur2 == vertex) continue;
                    if (verbose) System.out.println("\t\t" + buur2.id);
                    for (Vessel buur3 : buur2.getNeighbours()) {
                        if (buur3 == vertex) {
                            ArrayList<Vessel> vessels = new ArrayList<>();
                            vessels.add(vertex);
                            vessels.add(buur);
                            vessels.add(buur2);
                            Chaingroup foundGroup = new Chaingroup(vessels);
                            result.put(foundGroup.getLoot(), foundGroup);
                            if (verbose) System.out.println("\t\t\t" + buur3.id + " !");
                        }
                        else if (verbose)  System.out.println("\t\t\t" + buur3.id + " :/");
                    }
                }
            }
            alreadyHandled.put(vertex.id, true);
        }

        return result.values();
    }

    private Collection<Chaingroup> findCliquesOfSize4(Squadron graph, boolean verbose) {
        graph.removeVertexWithDegreeLowerThan(3);

        Map<Integer, Chaingroup> result = new HashMap<>();
        Map<Integer, Boolean> alreadyHandled = new HashMap<>();

        for (Vessel v : graph.vessels)
            alreadyHandled.put(v.id, false);

        ArrayList<Vessel> vesselCopy = (ArrayList<Vessel>) graph.vessels.clone();
        for (Vessel vertex : graph.vessels) {
            if (verbose) System.out.println("" + vertex.id);
            for (Vessel buur : vertex.getNeighbours()) {
                if (alreadyHandled.get(buur.id) || buur == vertex) continue;
                if (verbose) System.out.println("\t" + buur.id);
                for (Vessel buur2 : buur.getNeighbours()) {
                    if (alreadyHandled.get(buur2.id) || buur2 == vertex) continue;
                    if (!buur2.getNeighbours().contains(vertex)) continue;

                    if (verbose) System.out.println("\t\t" + buur2.id);
                    for (Vessel buur3 : buur2.getNeighbours()) {
                        if (alreadyHandled.get(buur3.id) || buur3 == buur || buur3 == vertex) continue;
                        ArrayList<Vessel> buren = buur3.getNeighbours();

                        if (buren.contains(vertex) && buren.contains(buur)) {
                            ArrayList<Vessel> vessels = new ArrayList<>();
                            vessels.add(vertex);
                            vessels.add(buur);
                            vessels.add(buur2);
                            vessels.add(buur3);
                            Chaingroup foundGroup = new Chaingroup(vessels);
                            result.put(foundGroup.getLoot(), foundGroup);
                            if (verbose) System.out.println("\t\t\t" + buur3.id + " !");
                        }
                        else if (verbose)  System.out.println("\t\t\t" + buur3.id + " :/");
                    }
                }
            }
            alreadyHandled.put(vertex.id, true);
        }

        return result.values();
    }

    public class Vessel {
        public int id;
        public int loot;
        public ArrayList<Chain> chains;

        public Vessel(int id, int loot) {
            this.id = id;
            this.loot = loot;
            this.chains = new ArrayList<>();
        }

        public int getDegree() {
            return this.chains.size();
        }

        public ArrayList<Vessel> getNeighbours() {
            ArrayList<Vessel> result = new ArrayList<>();

            for (Chain chain : this.chains) {
                if (chain.start != this)
                    result.add(chain.start);
                else result.add(chain.end);
            }

            return result;
        }

    }

    public class Chain {
        public Vessel start;
        public Vessel end;

        public Chain(Vessel start, Vessel end) {
            this.start = start;
            this.end = end;
        }
    }

    public class Squadron {
        public ArrayList<Vessel> vessels;
        public ArrayList<Chain> chains;

        public Squadron() {
            this.vessels = new ArrayList<>();
            this.chains = new ArrayList<>();
        }

        public Vessel getVesselWithId(int id) {
            for (Vessel v : this.vessels) {
                if (v.id == id) return v;
            }

            return null;
        }

        public void removeVessel(int id) {
            Vessel v = this.getVesselWithId(id);
            this.removeVessel(v);
        }

        public void removeVessel(Vessel v) {
            this.vessels.remove(v);

            // Remove all chains bound to this vessel
            for (Chain c : v.chains) {
                this.chains.remove(c);
                for (Vessel buur : v.getNeighbours()) {
                    if (buur.chains.contains(c))
                        buur.chains.remove(c);
                }
            }
        }

        public void removeVertexWithDegreeLowerThan(int degree) {
            ArrayList<Vessel> vesselCopy = (ArrayList<Vessel>) this.vessels.clone();
            for (Vessel v : vesselCopy)
                if (v.getDegree() < degree)
                    this.removeVessel(v);
        }
    }

    public class Chaingroup {
        public ArrayList<Vessel> vessels;

        public Chaingroup(ArrayList<Vessel> vessels) {
            this.vessels = vessels;
        }

        public int getSize() {
            return this.vessels.size();
        }

        public int getLoot() {
            int result = 0;
            for (Vessel v : this.vessels) {
                result += v.loot;
            }

            return result;
        }
    }

}

