import java.util.*;

public class RadixSorter {

	public void LSD(String[] a, int W) {
		if (a.length <= 1) {
			return;
		}
		int R = 10;
		String [] aux = new String[a.length];
		for (int j = W - 1; j >= 0; --j) {
			int [] count = new int[R + 1];
			for (int i = 0; i < a.length; ++i) {
				++count[a[i].charAt(j) - '0' + 1];
			}
			for (int k = 1; k <= R; ++k) {
				count[k] += count[k - 1];
			}
			for (int i = 0; i < a.length; ++i) {
				int radix = a[i].charAt(j) - '0';
				aux[count[radix]] = a[i];
				++count[radix];
			}
			for (int i = 0; i < aux.length; ++i) {
				a[i] = aux[i];
			}
		}
	}

	public void MSD(String[] a, int begin, int end, int d) {
		if (begin >= end) {
			return;
		}
		int R = 10;
		String [] aux = new String[a.length];

		int[] count = new int[R + 1];
		for (int i = begin; i <= end; ++i) {
			++count[a[i].charAt(d) - '0' + 1];
		}
		for (int k = 1; k <= R; ++k) {
			count[k] += count[k - 1];
		}
		for (int i = begin; i <= end; ++i) {
			int radix = a[i].charAt(d) - '0';
			aux[count[radix]] = a[i];
			++count[radix];
		}
		for (int i = begin; i <=end; ++i) {
			a[i] = aux[i];
		}

		for (int r = 0; r < R; ++r) {
			MSD(a, begin + count[r], begin + count[r + 1] - 1, d + 1);
		}
	}	

	public static void main(String args[]) {
		int N = 5;
		int W = 10;
		int R = 10;
		Random rand = new Random(0);
		String[] array = new String[N];
		String[] array2 = new String[N];
		String[] array3 = new String[N];
		for (int i = 0; i < array.length; ++i) {
			array[i] = "";
			for (int x = 0; x < W; ++x) {
				array[i] += (int)Math.abs(rand.nextInt()) % R;
			}
			array2[i] = array[i];
			array3[i] = array[i];
		}
		long t1 = System.currentTimeMillis();
		new RadixSorter().LSD(array, W);
		long t2 = System.currentTimeMillis();
		for (String s : array) {
			System.out.println(s);
		}
		System.out.println(t2 - t1);

		t1 = System.currentTimeMillis();
		new RadixSorter().MSD(array2, 0, array2.length - 1, 0);
		t2 = System.currentTimeMillis();
		for (String s : array) {
			System.out.println(s);
		}
		System.out.println(t2 - t1);

		t1 = System.currentTimeMillis();
		Arrays.sort(array3);
		t2 = System.currentTimeMillis();
		System.out.println(t2 - t1);
	}
}
