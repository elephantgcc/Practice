
/*
 * Thanks to 
 * http://jakeboxer.com/blog/2009/12/13/the-knuth-morris-pratt-algorithm-in-my-own-words/ for KMP explanation, and
 * http://blog.csdn.net/yearn520/article/details/6729426 for O(m) next construction
*/

public class KMP {
	public int kmpFind(String source, String pattern) {
		if (pattern.length() == 0) {
			return 0;
		} else if (pattern.length() > source.length()) {
			return -1;
		}

		//int [] next = makeNext(pattern);
		int [] next = makeNextEfficient(pattern);

		int i = 0;
		while (i <= source.length() - pattern.length()) {
			if (source.charAt(i) != pattern.charAt(0)) {
				++i;
				continue;
			}
			int j = 1;
			for (; j < pattern.length(); ++j) {
				if (pattern.charAt(j) != source.charAt(i + j)) {
					break;
				}
			}
			if (j == pattern.length()) {
				return i;
			}
			System.out.println(j);
			i += j - next[j - 1];
		}
		return -1;
	}

	public int [] makeNextEfficient(String pattern) {
		int [] next = new int [pattern.length()];
		next[0] = 0;
		for (int i = 1; i < pattern.length(); ++i) {
			int k = next[i - 1];
			while (pattern.charAt(k) != pattern.charAt(i) && k > 0) {
				k = next[k - 1];
			}
			if (pattern.charAt(k) == pattern.charAt(i)) {
				next[i] = k + 1;
			} else {
				next[i] = 0;
			}
		}
		for (int i : next) {
			System.out.print(i + " ");
		}
		System.out.println();
		return next;
	}

	public int [] makeNext(String pattern) {
		int [] next = new int [pattern.length()];
		next[0] = 0;
		for (int i = 1; i <= pattern.length() - 1; ++i) {
			int max = 0;
			for (int j = 0; j <= i - 1; ++j) {
				String prefix = pattern.substring(0, j + 1);
				String suffix = pattern.substring(i - j, i + 1);
				if (prefix.equals(suffix)) {
					max = Math.max(max, j + 1);
				}
			}
			next[i] = max;
		}
		for (int i : next) {
			System.out.print(i + " ");
		}
		System.out.println();
		return next;
	}

	public static void main(String args[]) {
		String source  = "ababbaababcaabababcabcc"; // ans is 12
		String pattern =             "abababcab";
		System.out.println(new KMP().kmpFind(source, pattern));
	}
}
