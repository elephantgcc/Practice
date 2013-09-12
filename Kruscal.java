import java.util.*;

public class Kruscal {
	ArrayList<Edge> result;
	UnionFind uf;

	public Kruscal(Graph g) {
		uf = new UnionFind(g.getV());
		ArrayList<Edge> edges = g.getEdges();
		Collections.sort(edges, new Comparator() {
				public int compare(Object o1, Object o2) {
					double result = ((Edge)o1).weight - ((Edge)o2).weight;
					if (result < 0) {
						return -1;
					} else if (result > 0) {
						return 1;
					} else {
						return 0;
					}
				}
		});
		result = new ArrayList<Edge>();
		for (Edge edge : edges) {
			if (result.size() == g.getV() - 1) {
				break;
			}
			if (uf.reachable(edge.from, edge.to)) {
				continue;
			} else {
				result.add(edge);
				uf.union(edge.from, edge.to);
			}
		}
	}

	public ArrayList<Edge> getResult() {
		return result;
	}

/*
	public static void main(String args[]) {
		Graph g = new Graph(5);
		g.addEdge(0, 1, 0.9);
		g.addEdge(0, 3, 0.8);
		g.addEdge(0, 4, 2.5);
		g.addEdge(1, 4, 0.2);
		g.addEdge(1, 3, 0.5);
		g.addEdge(1, 2, 2.1);
		g.addEdge(2, 3, 4.3);
		g.addEdge(4, 3, 0.3);
		Kruscal krus = new Kruscal(g);
		ArrayList<Edge> result = krus.getResult();
		for(Edge e : result) {
			System.out.println(e.from + " " + e.to + " " + e.weight);
		}
*/

	public static void main(String args[]) {
		int N = 100;
		Graph g = new Graph(N);
		Random random = new Random(0);
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				if (j == i) {
					continue;
				}
				double weight = Math.abs(random.nextInt());
				g.addEdge(i, j, weight);
			}
		}
		long t1 = System.currentTimeMillis();
		Kruscal krus = new Kruscal(g);
		long t2 = System.currentTimeMillis();
		System.out.println(t2 - t1);
        ArrayList<Edge> result = krus.getResult();
/*        for(Edge e : result) {
            System.out.println(e.from + " " + e.to + " " + e.weight);
        }
*/
	}
}	
