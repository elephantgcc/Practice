import java.util.*;

class DirectedEdge {
	int start;
	int end;
	double weight;
	public DirectedEdge(int s, int e, double w) {
		start = s;
		end = e;
		weight = w;
	}
}

class EdgeWeightedDigraph {
	private int V;
	private int E;
	ArrayList<DirectedEdge>[] adj;

	public EdgeWeightedDigraph(int v) {
		V = v;
		E = 0;
		adj = (ArrayList<DirectedEdge>[]) new ArrayList [V];
		for (int i = 0; i < V; ++i) {
			adj[i] = new ArrayList<DirectedEdge>();
		}
	}

	public int getV() {
		return V;
	}

	public void addEdge(DirectedEdge e) {
		adj[e.start].add(e);
		++E;
	}

	public ArrayList<DirectedEdge> adj(int v) {
		return adj[v];
	}
}
