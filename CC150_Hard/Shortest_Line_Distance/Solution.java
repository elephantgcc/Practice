public class Solution {
	public int getDistance(String[] words, String w1, String w2) {
		if (words.length == 0) {
			return -1;
		} else if (w1.equals(w2)) {
			return 0;
		}
		int i = -1;
		int j = -1;
		int min = Integer.MAX_VALUE;
		for (int k = 0; k < words.length; ++k) {
			if (words[k].equals(w1)) {
				i = k;
			} else if (words[k].equals(w2)) {
				j = k;
			}
			if (i != -1 && j != -1) {
				min = Math.min(min, Math.abs(i - j));
			}
		}
		if (i != -1 && j != -1) {
			return min;
		} else {
			return -1;
		}
	}

	public static void main(String args[]) {

		String[] words = new String[] {"a", "a", "b", "c", "c", "d", "f", "x"};
		System.out.println(new Solution().getDistance(words, "a", "d"));

	}
}
