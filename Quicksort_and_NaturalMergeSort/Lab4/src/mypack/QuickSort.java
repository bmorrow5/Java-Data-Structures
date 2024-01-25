package mypack;

/**
 * This quicksort algorithm is designed for use four different ways, and
 * measures the number of comparisons and swaps that occur during execution.
 * 
 * @author brand
 */

//
//Needs to have 4 versions of quicksort
//Select first item of partition as pivot. Treat partition of size 1 and 2 as stopping cases
//Same pivot. For partition size of 100 or less us an insertion sort to finish
//Same pivot. For partition of size 50 or less use an insertion sort to finish
//Median of three pivot. Treat partitions of size 1 and 2 as stopping cases
//
class QuickSort {

	private static int comparisons;
	private static int swaps;

	QuickSort() {
		comparisons = 0;
		swaps = 0;
	}

	public int getComparisons() {
		return comparisons;
	}

	public void setComparisons(int comparisons) {
		QuickSort.comparisons = comparisons;
	}

	public int getSwaps() {
		return swaps;
	}

	public void setSwaps(int swaps) {
		QuickSort.swaps = swaps;
	}

	// Version 1. Selects first item as pivot and treats partitions of size 1 and 2
	// as stopping cases
	public void recursiveQuickSort(int[] array, int startIndex, int stopIndex) {

		// StopIndex - startIndex is out partition size
		if (startIndex < stopIndex) {
			int pivot = partition(array, startIndex, stopIndex);

			recursiveQuickSort(array, startIndex, pivot - 1); // sort left of pivot
			recursiveQuickSort(array, pivot, stopIndex); // sort right of pivot
		}

	}

	/**
	 * Method used to set partitions to the left and right of the pivot position
	 * 
	 * @param array
	 * @param left
	 * @param right
	 * @return pivot location
	 * @author https://www.java67.com/2014/07/quicksort-algorithm-in-java-in-place-example.html
	 */
	public int partition(int[] array, int left, int right) {

		int pivot = array[left]; // We will use first element as pivot

		while (left <= right) {

			while (array[left] < pivot) {
				comparisons++;
				left++;
			}

			while (array[right] > pivot) {
				comparisons++;
				right--;
			}

			if (left <= right) {
				comparisons++;
				swaps++;
				int temp = array[left];
				array[left] = array[right];
				array[right] = temp;
				left++;
				right--;
			}
		}
		return left;
	}

	/**
	 * Version 2. Last 50 elements are sorted using insertion sort Assistance from
	 * code by @naina024 at geeks for geeks
	 * https://www.geeksforgeeks.org/advanced-quick-sort-hybrid-algorithm/
	 * 
	 * @param array
	 * @param startIndex
	 * @param stopIndex
	 */
	public void quickSortInsertionLast50(int[] array, int startIndex, int stopIndex) {

		while (startIndex < stopIndex) {

			if (stopIndex - startIndex <= 50) {
				insertionSort(array, startIndex, stopIndex);
				break;
			} else {
				int pivot = partition(array, startIndex, stopIndex);
				if (pivot - startIndex < pivot - stopIndex) {
					quickSortInsertionLast50(array, startIndex, pivot - 1);
					startIndex = pivot + 1;
				} else {
					quickSortInsertionLast50(array, pivot + 1, stopIndex);
					stopIndex = pivot - 1;
				}

			}
		}
	}

	/**
	 * Version 3. Last 100 elements are sorted using insertion sort Asssistance from
	 * code by @naina024 at geeks for geeks
	 * https://www.geeksforgeeks.org/advanced-quick-sort-hybrid-algorithm/
	 * 
	 * @param array
	 * @param startIndex
	 * @param stopIndex
	 */
	public void quickSortInsertionLast100(int[] array, int startIndex, int stopIndex) {

		while (startIndex < stopIndex) {

			if (stopIndex - startIndex <= 100) {
				insertionSort(array, startIndex, stopIndex);
				break;
			} else {
				int pivot = partition(array, startIndex, stopIndex);
				if (pivot - startIndex < pivot - stopIndex) {
					quickSortInsertionLast100(array, startIndex, pivot - 1);
					startIndex = pivot + 1;
				} else {
					quickSortInsertionLast100(array, pivot + 1, stopIndex);
					stopIndex = pivot - 1;
				}

			}
		}
	}

	/**
	 * /** Method used to set partitions to the left and right of the pivot position
	 * 
	 * @param arr
	 * @param n
	 * @author Assisted by https://www.sortvisualizer.com/insertionsort/
	 */
	public void insertionSort(int[] array, int startIndex, int stopIndex) {

		// If the entire segment has been sorted
		if (stopIndex - startIndex < 1)
			return;

		// Recursive call that will continually move through every element
		insertionSort(array, startIndex, stopIndex - 1);

		int key = array[stopIndex];
		int j = stopIndex - 1;

		// While array[j] > array[stopIndex] swap elements
		while (j >= 0 && array[j] > key) {
			swaps++;
			comparisons++;
			array[j + 1] = array[j];
			j = j - 1;
		}

		comparisons++;
		array[j + 1] = key;
	}

	/**
	 * Version 4 uses the median of three technique to find best pivot
	 * 
	 * @param array
	 * @param startIndex
	 * @param stopIndex
	 */
	public void medianThreeQuickSort(int[] array, int startIndex, int stopIndex) {

		// StopIndex - startIndex is out partition size
		if (startIndex < stopIndex) {
			int index = partition(array, startIndex, stopIndex);

			recursiveQuickSort(array, startIndex, index - 1);
			recursiveQuickSort(array, index, stopIndex);
		}

	}

	/**
	 * Method used to set partitions to the left and right of the pivot position
	 * 
	 * @param array
	 * @param left
	 * @param right
	 * @return pivot location
	 * @author myself for median code and the partition code from:
	 *         https://www.java67.com/2014/07/quicksort-algorithm-in-java-in-place-example.html
	 */
	public int medOfThreePartition(int[] array, int left, int right) {

		// Get first middle and last elements
		int first = array[0];
		int middle = array[(array.length / 2)];
		int last = array[(array.length - 1)];
		int pivot;

		// Now find the median value and set equal to the pivot
		if ((first > last && first < middle) || (first < last && first > middle)) {
			pivot = first;
		} else if ((last > first && last < middle) || (last < first && last > middle)) {
			pivot = last;
		} else {
			pivot = middle;
		}

		// Now execute partition algorithm as normal
		while (left <= right) {

			while (array[left] < pivot) {
				comparisons++;
				left++;
			}

			while (array[right] > pivot) {
				comparisons++;
				right--;
			}

			if (left <= right) {
				comparisons++;
				swaps++;
				int temp = array[left];
				array[left] = array[right];
				array[right] = temp;
				left++;
				right--;
			}
		}
		return left;
	}

	// Displays the array contents to assist in debugging
	public void printArray(int[] array) {

		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}

}
