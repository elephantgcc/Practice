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

	public int charAt(String s, int d) {
		if (d < s.length()) {
			return s.charAt(d) - '0';
		} else {
			return -1;
		}
	}

	public void MSD(String[] a, int begin, int end, int d, String[] aux) {
		if (begin >= end) {
			return;
		}
		int R = 10;

		int[] count = new int[R + 2];
		for (int i = begin; i <= end; ++i) {
			++count[charAt(a[i], d) + 2];
		}
		for (int k = 1; k <= R + 1; ++k) {
			count[k] += count[k - 1];
		}
		for (int i = begin; i <= end; ++i) {
			int radix = charAt(a[i], d) + 1;
			aux[count[radix]] = a[i];
			++count[radix];
		}
		for (int i = begin; i <=end; ++i) {
			a[i] = aux[i - begin];
		}
		for (int r = 0; r <= R; ++r) {
			MSD(a, begin + count[r], begin + count[r + 1] - 1, d + 1, aux);
		}
	}

	public void MSD_opt(String[] a, int begin, int end, int d, String[] aux) {
		if (end <= begin + 10) {
			insertionSort(a, begin, end);
			return;
		}
		int R = 10;

		int[] count = new int[R + 2];
		for (int i = begin; i <= end; ++i) {
			++count[charAt(a[i], d) + 2];
		}
		for (int k = 1; k <= R + 1; ++k) {
			count[k] += count[k - 1];
		}
		for (int i = begin; i <= end; ++i) {
			int radix = charAt(a[i], d) + 1;
			aux[count[radix]] = a[i];
			++count[radix];
		}
		for (int i = begin; i <=end; ++i) {
			a[i] = aux[i - begin];
		}
		for (int r = 0; r <= R; ++r) {
			MSD(a, begin + count[r], begin + count[r + 1] - 1, d + 1, aux);
		}
	}
	
	public void insertionSort(String[] A, int start, int end) {
		if (start >= end) {
			return;
		}
		for (int i = start + 1; i <= end; ++i) {
			String target = A[i];
			int j = i;
			for (; j >= start + 1; --j) {
				if (A[j - 1].compareTo(target) > 0) {
					A[j] = A[j - 1];
				} else {
					break;
				}
			}
			A[j] = target;
		}
	}

	public static void main(String args[]) {
		int N = 100000;
		int W = 10;
		int R = 10;
		Random rand = new Random(0);
		String[] array1 = new String[N];
		String[] array2 = new String[N];
		String[] array3 = new String[N];
		String[] array4 = new String[N];
		for (int i = 0; i < array1.length; ++i) {
			array1[i] = "";
			for (int x = 0; x < W; ++x) {
				array1[i] += (int)Math.abs(rand.nextInt()) % R;
			}
			array2[i] = array1[i];
			array3[i] = array1[i];
			array4[i] = array1[i];
		}

		long t1 = 0, t2 = 0;
		String [] aux = new String[N];

		// Arrays.sort(), mergeSort
		t1 = System.currentTimeMillis();
		Arrays.sort(array1);
		t2 = System.currentTimeMillis();
		System.out.println("Arrays.sort = " + (t2 - t1));

		// LSD
		t1 = System.currentTimeMillis();
		new RadixSorter().LSD(array2, W);
		t2 = System.currentTimeMillis();
//		for (string s : array) {
//			System.out.println(s);
//		}
		System.out.println("LSD         = " + (t2 - t1));

		// MSD
		t1 = System.currentTimeMillis();
		new RadixSorter().MSD(array3, 0, array3.length - 1, 0, aux);
		t2 = System.currentTimeMillis();
//		for (String s : array) {
//			System.out.println(s);
//		}
		System.out.println("MSD         = " + (t2 - t1));

		// MSD + insertionSort
		t1 = System.currentTimeMillis();
		new RadixSorter().MSD_opt(array4, 0, array4.length - 1, 0, aux);
		t2 = System.currentTimeMillis();
//		for (String s : array) {
//			System.out.println(s);
//		}
		System.out.println("MSD_opt     = " + (t2 - t1));
	}
}
