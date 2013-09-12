import java.util.*;

public class Prim {
	ArrayList<Edge> result;
	PriorityQueue<Edge> pq;
	HashSet<Integer> marked;

	public Prim(Graph g) {
		result = new ArrayList<Edge>();
		pq = new PriorityQueue<Edge>(1, new Comparator() {
				public int compare(Object o1, Object o2) {
					double cmp = ((Edge)o1).weight - ((Edge)o2).weight;
					if (cmp < 0) {
						return -1;	
					} else if (cmp > 0) {
						return 1;
					} else {
						return 0;
					}
				}
		});
		marked = new HashSet<Integer>();
		marked.add(0);
		for (Edge edge : g.adj(0)) {
			pq.add(edge);
		}
		while (pq.size() > 0) {
			Edge edge = pq.poll();
			result.add(edge);
			marked.add(edge.to);
			if (result.size() == g.getV() + 1) {
				break;
			}
			Iterator<Edge> iter = pq.iterator();
			while (iter.hasNext()) {
				Edge insideEdge = iter.next();
				if (insideEdge.from == edge.to || insideEdge.to == edge.to) {
					iter.remove();
				}
			}
			for (Edge newEdge : g.adj(edge.to)) {
				if (!marked.contains(newEdge.to)) {
					pq.add(newEdge);
				}
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
		Prim prim = new Prim(g);
		ArrayList<Edge> result = prim.getResult();
		for(Edge e : result) {
			System.out.println(e.from + " " + e.to + " " + e.weight);
		}
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
