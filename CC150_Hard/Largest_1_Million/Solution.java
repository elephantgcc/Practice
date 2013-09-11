import java.util.*;

public class Solution {
	public ArrayList<Integer> largestK(int[] array, int k) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (k >= array.length ) {
			for (int i = 0; i < array.length; ++i) {
				result.add(array[i]);
			}
			return result;
		} else if (k <= 0) {
			return result;
		}
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(k, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Integer)o2).compareTo((Integer)o1);
			}
		});
		for (int num : array) {
			if (pq.size() < k) {
				pq.add(num);
			} else if (num < pq.peek()) {
				pq.poll();
				pq.add(num);
			}
		}
		result.addAll(pq);
		return result;
	}

	public ArrayList<Integer> largestK_2(int[] array, int begin, int end, int k) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (k >= (end - begin + 1)) {
			for (int i = begin; i <= end; ++i) {
				result.add(array[i]);
			}
			return result;
		} else if (k <= 0) {
			return result;
		}
		int pivot = array[begin];
		int i = begin;
		int j = end;
		while (i < j) {
			if (array[j] >= pivot) {
				--j;
				continue;
			}
			if (array[i] < pivot) {
				++i;
				continue;
			}
			int temp = array[i];
			array[i] = array[j];
			array[j] = temp;
		}
		if (j - begin + 1 == k) {
			for (int x = begin; x <= j; ++x) {
				result.add(array[x]);
			}
		} else if (j - begin + 1 < k) {
			for (int x = begin; x <= j; ++x) {
				result.add(array[x]);
			}
			result.addAll(largestK_2(array, j + 1, end,  k - (j - begin + 1)));
		} else {
			result = largestK_2(array, begin, j, k);
		}
		return result;
	}

	public static void main(String args[]) {
		Random rand = new Random();
		int N = 100000;
		int[] array = new int[N];
		for (int i = 0; i < N; ++i) {
			array[i] = Math.abs(rand.nextInt()) % N;
		}

		long t1 = System.currentTimeMillis();
		new Solution().largestK(array, 10000);
		long t2 = System.currentTimeMillis();
		System.out.println(t2 - t1);

		t1 = System.currentTimeMillis();
		new Solution().largestK_2(array, 0, N - 1, 10000);
		t2 = System.currentTimeMillis();
		System.out.println(t2 - t1);
	}
}
