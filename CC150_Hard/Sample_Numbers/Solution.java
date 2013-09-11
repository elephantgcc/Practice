import java.util.*;

public class Solution {
	public ArrayList<Integer> sample(ArrayList<Integer> list, int n) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (list == null || list.size() == 0 || n <= 0) {
			return result;
		}
		Random rand = new Random();
		int targetIndex = list.size() - 1;
		while (n > 0) {
			int nextInt = rand.nextInt();
			while (nextInt == Integer.MIN_VALUE) {
				nextInt = rand.nextInt();
			}
			int luckyIndex = (int)Math.abs(nextInt) % (targetIndex + 1);
			result.add(list.get(luckyIndex));
			int temp = list.get(luckyIndex);
			list.set(luckyIndex, list.get(targetIndex));
			list.set(targetIndex, temp);
			--n;
			--targetIndex;
		}
		return result;
	}

	public static void main(String args[]) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i <= 9; ++i) {
			list.add(i);
		}
		System.out.println(new Solution().sample(list, 3));
	}
}
