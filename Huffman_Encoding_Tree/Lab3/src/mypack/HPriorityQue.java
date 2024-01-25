/**
 * This class is a custom made priority queue which uses an array. It uses an insert
 * sort to ensure that the queue remains sorted by frequency
 * @author brand
 */
package mypack;

public class HPriorityQue<T> {

	private int size; 
	private HuffNode[] que;

	//Constructor consists of array with space for all alphabetical letters
	public HPriorityQue() {
		que = new HuffNode[26];
		size = 0;
	}

	/**
	 * A Method that adds a Huffman tree node and adds it to the priority queue according
	 * to frequency. This method utilizes an insertion sort.
	 * @param value
	 */
	public void add(HuffNode value) {

		//If array is empty set head to value
		if (size == 0) {
			que[0] = value;
			size++;
			return;
		}
		//If the array is not empty find the insertion location and insertion sort the array
		int i = 0;
		for (i = (size - 1); i >= 0; i--) {//Insertion sort
			if (value.getFreq() >= que[i].getFreq()) {
				que[i + 1] = que[i];
			} else {
				break;
			}
		}
		que[i + 1] = value; // Add value to array
		size++;//Increase queue size
	}

	/**
	 * A method that removes and returns element form array by reducing array index/size
	 * @return removed HuffNode
	 */
	public HuffNode remove() {
		return que[--size];
	}
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}