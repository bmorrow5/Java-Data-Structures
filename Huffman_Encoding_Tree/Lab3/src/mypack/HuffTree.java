/**
 * A class that can create, encode strings, and decode a Huffman binary tree
 * This class also prints the tree in preorder format and has a method that
 * displays the key. It utilitzes the Hprioirityqueue data structure to 
 * help build the tree
 * @author brand
 */

package mypack;

public class HuffTree {

	// Index for recursive calls
	private int index = 0;

	/**
	 * Builds the huffman tree based on the frequency table utilizing out custom
	 * priority queue *
	 * 
	 * @param hpq
	 * @return HuffNode huffman tree stored in root node
	 */
	public static HuffNode buildTree(HPriorityQue<HuffNode> hpq) {

		while (hpq.getSize() > 1) {
			HuffNode left = hpq.remove();
			HuffNode right = hpq.remove();

			String parentValue = left.getValue() + right.getValue() + "";
			Integer parentFreq = (left.getFreq() + right.getFreq());

			HuffNode parent = new HuffNode(parentValue, parentFreq, left, right);
			hpq.add(parent);
		}
		return hpq.remove();
	}

	/**
	 * This method creates a huffman key that shows the encoded version of each
	 * character based on the huffman tree. This key will then be used to encode
	 * messages
	 * 
	 * @param root
	 * @param output
	 * @param outputArray
	 * @return String[] displayKey contains characters and their encoded version
	 */
	public String[] displayHuffKey(HuffNode root, String output, String outputArray[]) {

		if (root == null) {// Base case if node is null
			return outputArray;
		}
		if (root.left == null && root.right == null) {// If the node is a leaf
			outputArray[index] = (root.getValue() + ":" + output);
			index++;
		}
		displayHuffKey(root.left, output + '0', outputArray);
		return displayHuffKey(root.right, output + '1', outputArray);
	}

	/**
	 * This method is designed to use the huffman key we built to encode an input
	 * string
	 * 
	 * @param huffKey (key that shows code per character)
	 * @param convert (string to be converted)
	 * @return encoded string
	 */
	public String encodeString(String[] huffKey, String input) {

		String output = "";
		String[] split = new String[2];

		// Searches the key for the correct character
		for (int j = 0; j < input.length(); j++) {
			for (int i = 0; i < huffKey.length; i++) {
				if (huffKey[i].charAt(0) == input.charAt(j)) {
					split = huffKey[i].split(":");
					output += split[1].trim();
				}
			}
		}
		return output;
	}

	/**
	 * This method takes the huffman tree itself and searches the tree to decode an
	 * encoded string to a clear text string
	 * 
	 * @param root
	 * @param input
	 * @return decoded clear text string
	 */
	public String decode(HuffNode root, String input) {

		String output = "";
		HuffNode temp = root;
		for (int i = 0; i < (input.length()); i++) {

			if (input.charAt(i) == '0') { // If zero move left if not move right
				root = root.left;
			} else {
				root = root.right;
			}
			if (root.left == null && root.right == null) {
				output += root.getValue();// Add leaf character to output string
				root = temp; // If leaf resets node back to root node
			}
		}
		return output;
	}

	/**
	 * Recursive preorder traversal to print the tree
	 * 
	 * @param root
	 */
	public void printTreePreOrder(HuffNode root) {

		if (root == null) {
			return;
		}
		System.out.print(root.toString() + " ");
		printTreePreOrder(root.left);
		printTreePreOrder(root.right);
	}
}
