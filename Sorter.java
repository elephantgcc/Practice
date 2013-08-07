import java.util.*;

public class Sorter {

	public void quickSort(int [] A, int start, int end) {
		if (start >= end) {
			return;
		}

		int i = start;
		int j = end;
		int cutter = A[i];
		while (i < j) {
			if (A[j] >= cutter) {
				--j;
				continue;
			} else if (A[i] < cutter) {
				++i;
				continue;
			}
			int temp = A[i];
			A[i] = A[j];
			A[j] = temp;
		}
		quickSort(A, start, j);
		quickSort(A, j + 1, end);
	}

	public void insertionSort(int [] A, int start, int end) {
		if (start >= end) {
			return;
		}
		for (int i = start + 1; i <= end; ++i) {
			int target = A[i];
			int j = i;
			for (; j >= start + 1; --j) {
				if (A[j - 1] > target) {
					A[j] = A[j - 1];
				} else {
					break;
				}
			}
			A[j] = target;
		}
	}

	public void mergeSort(int [] A, int start, int end) {
		int [] temp = new int[A.length];
		mergeSortCore(A, start, end, temp);
	}

	public void mergeSortCore(int [] A, int start, int end, int [] temp) {
		if (start >= end) {
			return;
		}
		int mid = (start + end) / 2;
		mergeSortCore(A, start, mid, temp);
		mergeSortCore(A, mid + 1, end, temp);
		mergeArray(A, start, mid, end, temp);
		return;
	}

	public void mergeArray(int [] A, int start, int mid, int end, int[] temp) {
		if (start >= end) {
			return;
		}
		int i = start;
		int j = mid + 1;
		int k = 0;
		while (i <= mid && j <= end) {
			if (A[i] < A[j]) {
				temp[k] = A[i];
				++k;
				++i;
			} else {
				temp[k] = A[j];
				++k;
				++j;
			}
		}
		while (i <= mid) {
			temp[k] = A[i];
			++i;
			++k;
		}
		while (j <= end) {
			temp[k] = A[j];
			++k;
			++j;
		}
		
		for (int x = 0; x < k; ++x) {
			A[start + x] = temp[x];
		}
	}

	public void heapSort(int[] A) {
		if (A.length == 0) {
			return;
		}

		// build max heap
		int startIndex = ((A.length - 1) - 1) / 2;
		for (int i = startIndex; i >= 0; --i) {
			heapifyTop(A, i, A.length - 1);
		}

		// swap and heapify
		for (int i = A.length - 1; i >= 1; --i) {
			int temp = A[0];
			A[0] = A[i];
			A[i] = temp;
			heapifyTop(A, 0, i - 1);
		}

	}

	public void heapifyTop(int[] A, int index, int endIndex) {

		int largestIndex = index;
		int leftChildIndex = 2 * index + 1;
		int rightChildIndex = 2 * index + 2;

		if (leftChildIndex <= endIndex && A[largestIndex] < A[leftChildIndex]) {
			largestIndex = leftChildIndex;
		}
		if (rightChildIndex <= endIndex && A[largestIndex] < A[rightChildIndex]) {
			largestIndex = rightChildIndex;
		}

		if (largestIndex != index) {
			int temp = A[index];
			A[index] = A[largestIndex];
			A[largestIndex] = temp;
			heapifyTop(A, largestIndex, endIndex);
		}
	}

	public void radixSort(int[] A) {




	}

	public static void main(String args[]) {

		int N = 100000;
		boolean debug = false;
		long t1 = 0, t2 = 0;

		Random rand = new Random(System.currentTimeMillis());
		int[] array1 = new int[N];
		int[] array2 = new int[N];
		int[] array3 = new int[N];
		int[] array4 = new int[N];
		int[] array5 = new int[N];
		for (int i = 0; i < N; ++i) {
			array1[i] = Math.abs(rand.nextInt());
			array2[i] = array1[i];
			array3[i] = array1[i];
			array4[i] = array1[i];
			array5[i] = array1[i];
		}

		if (debug) {
			for (int i = 0; i < N; ++i) {
				System.out.print(array5[i] + " ");
			}
			System.out.println();
		}

		// Arrays.sort
		t1 = System.currentTimeMillis();
		Arrays.sort(array1);
		t2 = System.currentTimeMillis();
		System.out.println("Arrays.sort   = " + (t2 - t1));

		// insertionSort
		t1 = System.currentTimeMillis();
		new Sorter().insertionSort(array2, 0, array2.length - 1);
		t2 = System.currentTimeMillis();
		System.out.println("insertionSort = " + (t2 - t1));

		// quickSort
		t1 = System.currentTimeMillis();
		new Sorter().quickSort(array3, 0, array3.length - 1);
		t2 = System.currentTimeMillis();
		System.out.println("quickSort     = " + (t2 - t1));

		// mergeSort
		t1 = System.currentTimeMillis();
		new Sorter().mergeSort(array4, 0, array4.length - 1);
		t2 = System.currentTimeMillis();
		System.out.println("mergeSort     = " + (t2 - t1));

		// heapSort
		t1 = System.currentTimeMillis();
		new Sorter().heapSort(array5);
		t2 = System.currentTimeMillis();
		System.out.println("heapSort      = " + (t2 - t1));


		if (debug) {
			for (int i = 0; i < N; ++i) {
				System.out.print(array5[i] + " ");
			}
			System.out.println();
		}
	}
}
