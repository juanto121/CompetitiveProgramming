using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace C06 {
	class C06 {
		//static StreamReader input = new StreamReader("C:/Users/b.pat-el/Documents/T.NET/CHipCIE/NWERC-2013/problems/C06_PietHein/testcases/cases.in");

		static void print(string toprint, bool newline = true) {
			if (newline)
				System.Diagnostics.Debug.WriteLine(toprint);
			else System.Diagnostics.Debug.Write(toprint);
		}

		static void output(string toprint) {
			Console.WriteLine(toprint);
		}

		static void Main(string[] args) {
			var v_e = "";
			while (true) {
				v_e = Console.ReadLine();
				if (v_e != null) {
					var _ve = v_e.Split(new [] { ' ' });
					solve(Int32.Parse(_ve[0]), Int32.Parse(_ve[1]));
				}
				else break;
			}

			print("Done!");
		}

		class edge {
			public int s, e;
		}

		class clique {
			public clique(params int[] vs) {
				vertices = vs;
			}

			public clique(int[] vs, vertex v) {
				vertices = new int[vs.Length + 1];
				for (int i = 0; i < vs.Length; i++)
					vertices[i] = vs[i];
				vertices[vs.Length] = v.id;
			}

			public int[] vertices;

			public bool contributes(vertex v) {
				if (vertices.FirstOrDefault(vertex => vertex == v.id) == default(int)) return false;
				return true;
			}

			public int loot {
				get {
					var result = 0;
					for (int i = 0; i < vertices.Length; i++)
						result += C06.vessels[vertices[i]];

					return result;
				}
			}

			public override string ToString() {
				string toprint = "";
				for (int i = 0; i < vertices.Length; i++)
					toprint += vertices[i] + ", ";

				return toprint;
			}
		}

		class vertex {
			public int id;

			public List<edge> edges = new List<edge>();
			public int[] _connected;

			public int[] connected { 
				get {
					if (_connected == null) {
						var result = new int[edges.Count];
						for (int i = 0; i < edges.Count; i++)
							result[i] = edges[i].s != id ? edges[i].s : edges[i].e;
						
						_connected = result;
					}
					return _connected;
				}
			}

			public int degree { get { return edges.Count; } }
		}

		static int[] vessels;
		static edge[] edges;
		static List<clique>[] cliques;
		static vertex[] vertices;

		static void solve(int v, int e) {
			vessels = new int[451];
			vertices = new vertex[451];
			edges = new edge[900];
			cliques = new List<clique>[451];

			for (int i = 0; i < v; i++) {
				vessels[i + 1] = Int32.Parse(Console.ReadLine());
				vertices[i + 1] = new vertex { id = i + 1 };
			}

			for (int i = 0; i < e; i++) {
				var s_e = Console.ReadLine().Split(' ');
				edges[i] = new edge { s = Int32.Parse(s_e[0]), e = Int32.Parse(s_e[1]) };

				vertices[edges[i].s].edges.Add(edges[i]);
				vertices[edges[i].e].edges.Add(edges[i]);
			}

			// Create all cliques of size 1
			cliques[1] = new List<clique>();
			for (int i = 1; i <= v; i++)
				cliques[1].Add(new clique(i));
			
			// Find all cliques in the graph
			for (int i = 1; i <= v; i++)
				if (cliques[i] != null && cliques[i].Count > 0)
					doslow(i);

			// Find max loot
			var maxloot = 0;
			for (int i = 1; i <= v; i++) {
				if (cliques[i] == null || cliques[i].Count == 0) continue;
				var max = cliques[i].Max(c => c.loot);
				if (maxloot < max) maxloot = max;
			}

			output("" + maxloot);
		}

		// Finds all cliques of size + 1
		static void doslow(int size) {
			var clist = cliques[size];
			cliques[size + 1] = new List<clique>();

			clist.ForEach(cliq => {
				// For every vertex that connects to each vertex in this clique, it contributes to a clique of size + 1
				foreach (var v_out in vertices) {
					if (v_out == null || v_out.degree < size || cliq.contributes(v_out)) continue;

					bool allConnected = cliq.vertices.All(v_in => v_out.connected.Contains(v_in));
					if (allConnected) cliques[size + 1].Add(new clique(cliq.vertices, v_out));
				}
			});
		}
	}
}
