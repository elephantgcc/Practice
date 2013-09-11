// Thanks to Inspiration by:
// 1. 算法,第四版
// 2. 算法导论, 第二版
// 3. zh.wikipedia

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

class Node {
	int v;
	double dist;
	public Node(int v, double dist) {
		this.v = v;
		this.dist = dist;
	}
}

public class Dijkstra {
	private DirectedEdge[] edgeTo;
	private double[] distTo;
	private PriorityQueue<Node> pq;

	public Dijkstra(EdgeWeightedDigraph g, int s) {
	
		edgeTo = new DirectedEdge[g.getV()];
		distTo = new double[g.getV()];
		for (int i = 0; i < g.getV(); ++i) {
			distTo[i] = Double.POSITIVE_INFINITY;
		}
		distTo[s] = 0.0;
		pq = new PriorityQueue<Node>(1, new Comparator() {
				public int compare(Object o1, Object o2) {
					double result = ((Node)o1).dist - ((Node)o2).dist;
					if (result < 0) {
						return -1;
					} else if (result > 0) {
						return 1;
					} else {
						return 0;
					}
				}
		});
		pq.add(new Node(s, 0.0));

		while (pq.size() > 0) {
			int v = pq.poll().v;
			for (DirectedEdge e : g.adj(v)) {
				if (distTo[e.end] > distTo[v] + e.weight) {
					distTo[e.end] = distTo[v] + e.weight;
					edgeTo[e.end] = e;
					Iterator<Node> iter = pq.iterator();
					while (iter.hasNext()) {
						Node node = iter.next();
						if (node.v == e.end) {
							iter.remove();
							break;
						}
					}
					pq.add(new Node(e.end, distTo[e.end]));
				}
			}
		}
	}

	public double distTo(int v) {
		return distTo[v];
	}

	public ArrayList<Integer> pathTo(int v) {
		int iter = v;
		Stack<DirectedEdge> container = new Stack<DirectedEdge>();
		while (edgeTo[iter] != null) {
			container.add(edgeTo[iter]);
			iter = edgeTo[iter].start;
		}
		ArrayList<Integer> result = new ArrayList<Integer>();
		while (container.size() > 0) {
			result.add(container.pop().start);
		}
		result.add(v);
		return result;
	}
			

	public static void main(String args[]) {

		// Data from: 算法导论(第二版), p367
		EdgeWeightedDigraph g = new EdgeWeightedDigraph(5);
		g.addEdge(new DirectedEdge(0, 1, 10));
		g.addEdge(new DirectedEdge(0, 2, 5));
		g.addEdge(new DirectedEdge(1, 2, 2));
		g.addEdge(new DirectedEdge(2, 1, 3));
		g.addEdge(new DirectedEdge(1, 3, 1));
		g.addEdge(new DirectedEdge(2, 3, 9));
		g.addEdge(new DirectedEdge(2, 4, 2));
		g.addEdge(new DirectedEdge(3, 4, 4));
		g.addEdge(new DirectedEdge(4, 3, 6));
		g.addEdge(new DirectedEdge(4, 0, 7));

		Dijkstra dj = new Dijkstra(g, 0);

		System.out.println(dj.distTo(4));
		System.out.println(dj.pathTo(4));
		
		System.out.println(dj.distTo(3));
		System.out.println(dj.pathTo(3));
		
		System.out.println(dj.distTo(2));
		System.out.println(dj.pathTo(2));

		System.out.println(dj.distTo(1));
		System.out.println(dj.pathTo(1));
	}
}

	
