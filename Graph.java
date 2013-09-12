import java.util.*;

class Edge {
	int from;
	int to;
	double weight;
	public Edge(int from, int to, double weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}
}

public class Graph {
	int V;
	int E;
	ArrayList<Edge>[] adj;
	ArrayList<Edge> edges;

	public Graph(int V) {
		this.V = V;
		E = 0;
		adj = (ArrayList<Edge>[]) new ArrayList [V];
		for (int i = 0; i < V; ++i) {
			adj[i] = new ArrayList<Edge>();
		}
		edges = new ArrayList<Edge>();
	}

	public void addEdge(int i, int j, double weight) {
		Edge edge = new Edge(i, j, weight);
		adj[i].add(edge);
		edges.add(edge);

		edge = new Edge(j, i, weight);
		adj[j].add(edge);

		++E;
	}

	public ArrayList<Edge> adj(int v) {
		return adj[v];
	}

	public int getV() {
		return V;
	}

	public ArrayList<Edge> getEdges() {
		return edges;
	}
}
