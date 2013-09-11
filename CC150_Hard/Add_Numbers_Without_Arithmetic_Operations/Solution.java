public class Solution {
	public int add(int a, int b) {
		if (a == 0) {
			return b;
		} else if (b == 0) {
			return a;
		}
		int base = a ^ b;
		int carry = (a & b) << 1;
		return add(base, carry);
	}

	public static void main(String args[]) {
		System.out.println(new Solution().add(3, 0));
		System.out.println(new Solution().add(-3, -5));
	}
}
