/**
 * This class is designed to recursively sort an input linked list using
 * natural merge. This merge takes advantage of naturally occuring runs 
 * in the data and then merges these two runs until completion in the finalList
 */

package mypack;

public class NaturalMerge {
	
	int comparisons;
	private int swaps;
	LinkedList finalList = new LinkedList();

	// Constructor defines natural merge
	NaturalMerge() {
		int comparisons;
		int swaps;
		LinkedList finalList;
	}

	public LinkedList getFinalList() {
		return finalList;
	}

	public void setFinalList(LinkedList sortedList) {
		this.finalList = sortedList;
	}

	public int getComparisons() {
		return comparisons;
	}

	public void setComparisons(int comparisons) {
		this.comparisons = comparisons;
	}

	public int getSwaps() {
		return swaps;
	}

	public void setSwaps(int swaps) {
		this.swaps = swaps;
	}

	/**
	 * A Method to recursively natural merge sort a linked list
	 * @param input
	 * @return sortedList
	 */
	public LinkedList natMergeSortRecur(LinkedList input) {

		// Base case if list empty
		if (input.head == null) {
			return input;
		}
		//Now lets initialize the two lists we will be merging
		LinkedList list1 = new LinkedList();
		LinkedList list2 = new LinkedList();
		
		list1 = getRun(input, list1); // Gets first sequential run in list
		list2 = getRun(input, list2); // Gets second sequential run in list
		
		//Now lets initialize two merge lists to be used in assisting the merge method
		LinkedList temp = new LinkedList();
		
		//Now we merge the two natural runs, and then  merge these runs with our final list
		temp = merge(list1, list2);
		finalList = merge(finalList, temp); // Add merged runs to the merged final list
		return natMergeSortRecur(input); 
	}

	/**
	 * Method that merges two natural runs from a list
	 * @param inputList1
	 * @param inputList2
	 * @param sortedList
	 * @return merged list
	 */
	public LinkedList merge(LinkedList inputList1, LinkedList inputList2) {

		LinkedList sortedList = new LinkedList();
		LinkedList list1 = inputList1;
		LinkedList list2 = inputList2;


		//If both lists empty then compare the first elements in both lists
		while (list1.head != null && list2.head != null) {
			if (list1.head.getValue() < list2.head.getValue()) {
				sortedList.add(list1.head.getValue());
				list1.head = list1.head.next;
			} else {
				sortedList.add(list2.head.getValue());
				list2.head = list2.head.next;
			}
			comparisons++;
			swaps++;
		}
		
		//If one list null then the other will be added to the final list
		while(list1.head != null) {
			swaps++;
			sortedList.add(list1.head.getValue());
			list1.head = list1.head.next;
		}
		while(list2.head != null) {
			swaps++;
			sortedList.add(list2.head.getValue());
			list2.head = list2.head.next;
		}
		return sortedList;

	}
	
	/**
	 * Method that identifies a naturally sorted run in a list
	 * @param input
	 * @param run
	 * @return run
	 */
	public LinkedList getRun(LinkedList input, LinkedList run) {

		if (input.head == null) {
			return run;
		}
		if (input.head.next == null || input.head.getValue() < input.head.next.getValue()) {
			comparisons++;
			run.add(input.head.getValue());
			input.head = input.head.next;
		} else {
			comparisons++;
			run.add(input.head.getValue());
			input.head = input.head.next;
			return run;
		}
		return getRun(input, run);
	}
}

