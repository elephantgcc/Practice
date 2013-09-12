public class UnionFind {
	int [] array;
	int [] count;

	public UnionFind(int N) {
		array = new int[N];
		count = new int[N];
		for (int i = 0; i < N; ++i) {
			array[i] = i;
			count[i] = 1;
		}
	}

	public void union(int i, int j) {
		int topi = find(i);
		int topj = find(j);
		if (topi == topj) {
			return;
		}
		if (count[topi] > count[topj]) {
			array[topj] = topi;
			count[topi] += count[topj];
		} else {
			array[topi] = topj;
			count[topj] += count[topi];
		}	
	}

	public int find(int i) {
		while (i != array[i]) {
			i = array[i];
		}
		return i;
	}

	public boolean reachable(int i, int j) {
		return find(i) == find(j);
	}

	public static void main(String args[]) {
		UnionFind uf = new UnionFind(10);
		uf.union(1, 2);
		uf.union(2, 4);
		uf.union(4, 7);
		uf.union(3, 5);
		uf.union(5, 9);
		System.out.println(uf.reachable(2, 7));
		System.out.println(uf.reachable(3, 9));
		System.out.println(uf.reachable(2, 9));
	}
}
