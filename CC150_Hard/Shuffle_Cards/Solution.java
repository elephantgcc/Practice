import java.util.*;

public class Solution {
	public void shuffle(int[] array) {
		Random rand = new Random();
		int index = array.length - 1;
		while (index > 0) {
			int nextNum = rand.nextInt();
			while (nextNum == Integer.MIN_VALUE) {
				nextNum = rand.nextInt();
			}	
			int luckyIndex = (int)Math.abs(nextNum) % (index + 1);
			int temp = array[luckyIndex];
			array[luckyIndex] = array[index];
			array[index] = temp;
			--index;
		}
	}

	public static void main(String args[]) {
		int[] array = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
		new Solution().shuffle(array);
		for (int i = 0; i < array.length; ++i) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
}
