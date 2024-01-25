/**
 * A class that creates nodes used to store a huffman binary tree
 * These nodes are utilized by the HuffTree class and are sorted in
 * the Hpriority queue class
 * @author brand
 */

package mypack;

public class HuffNode {

	//Initialize pointers for child nodes
	HuffNode left;
	HuffNode right;
	private Integer freq;
	private String value;


	/**
	 * Constructor creates binary tree node object
	 * @param value
	 * @param freq
	 * @param left
	 * @param right
	 */
	HuffNode(String value, Integer freq, HuffNode left, HuffNode right) {
		this.value = value;
		this.freq = freq;
		this.left = left;
		this.right = right;
	}

	//Overrides tostring method
	public String toString() {
		return ("[" + value + ":" + freq + "]");
	}

	//Generic getters and setters below to get children and stored values
	
	public HuffNode getLeft() {
		return left;
	}

	public void setLeft(HuffNode left) {
		this.left = left;
	}

	public HuffNode getRight() {
		return right;
	}

	public void setRight(HuffNode right) {
		this.right = right;
	}

	public Integer getFreq() {
		return freq;
	}

	public void setFreq(Integer freq) {
		this.freq = freq;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
