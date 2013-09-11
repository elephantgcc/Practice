public class Solution {
	public int count2s(int n) {
		if (n <= 1) {
			return 0;
		}
		int size = 1;
		int prefix = n / (size * 10);
		int suffix = n - prefix * (size * 10);
		int sum = 0;
		while (prefix > 0) {
			sum += prefix * size;
			if (suffix >= 2 * size && suffix < 3 * size) {
				sum += suffix - 2 * size + 1;
			} else if (suffix >= 3 * size) {
				sum += size;
			}
			size *= 10;
			prefix = n / (size * 10);
			suffix = n - prefix * (size * 10);
		}
		sum += prefix * size; // i.e. sum += 0
		if (suffix >= 2 * size && suffix < 3 * size) {
			sum += suffix - 2 * size + 1;
		} else if (suffix >= 3 * size) {
			sum += size;
		}
		return sum;
	}

	public static void main(String args[]) {

		System.out.println(new Solution().count2s(120));
	
	}
}
