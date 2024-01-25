/**
 * A program that is designed to build a huffman tree to encode
 * and decode messages. This program builds a huffman tree based on a 
 * given frequency table, and then can encode and decode messages from files.
 * It utilized custom data structures priority queue and huffman tree
 * 
 * @author Brandon L Morrow
 * @date 11/02/2022
 */
package mypack;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

public class Main {

	public static void main(String[] args) {
		try {
			
			if (args.length == 3)//Checks that user input correct number of args
			{
			long startTime = System.nanoTime(); // Used to track time for O() estimation

			//Args read file
			BufferedReader br = new BufferedReader(
					new FileReader(args[0]));
			BufferedReader br2 = new BufferedReader(
					new FileReader(args[1]));
			BufferedReader br3 = new BufferedReader(
					new FileReader(args[2]));

			HPriorityQue<HuffNode> cpq = new HPriorityQue<HuffNode>();

			// Reads the frequency table, stores the values in huffman nodes, and then
			// places them in a priority queue
			String inputString;
			while ((inputString = br.readLine()) != null) {
				String[] parts = inputString.split("-");
				String value = (parts[0].charAt(0) + "");
				Integer freq = Integer.parseInt(parts[1].trim());
				HuffNode huffnode = new HuffNode(value, freq, null, null);
				cpq.add(huffnode);
			}

			// New instance of huffman tree and builds the tree based on priority queue
			HuffNode huffTree = HuffTree.buildTree(cpq); // Build the tree and condense to root node huffTree
			HuffTree ht = new HuffTree(); // Instance of tree class

			long treeTime = System.nanoTime();//Reads the time it took to build Huffman Tree
			long treeBuildTime = treeTime - startTime;
			
			// Now display Huffman Tree
			System.out.println("\nNodes Preorder Traversal:"); // Displays
			ht.printTreePreOrder(huffTree); // Print the tree contained in root huffTree
			System.out.println("\n\nHuff Key:");

			long preTime = System.nanoTime();//Reads the time it took to read tree prefix
			long traversalTime = preTime - startTime;
			
			// Now lets identify and display the Key for each letter in the tree
			String[] huffKey = new String[26]; // Creates a new string array to store each letters encoded values
			huffKey = ht.displayHuffKey(huffTree, "", huffKey); // finds letters in the tree and displays encoded value
			for (int a = 0; a < huffKey.length; a++) // Displays key
				System.out.print(huffKey[a] + " ");

			// Now lets see the decoded strings
			System.out.println("\n\nDecoded messages:");
			String inputString2;
			while ((inputString2 = br2.readLine()) != null) {
				System.out.print(inputString2 + " = ");
				System.out.println(ht.decode(huffTree, inputString2));//Decodes statement using hufftree
			}
			
			long decode = System.nanoTime();//Reads the time it took to decode input
			long decodeTime = decode - preTime;

			System.out.println("\nEncoded messages:");
			String inputString3;
			while ((inputString3 = br3.readLine()) != null) {
				inputString3 = inputString3.replaceAll("\\p{Punct}", "");//Removes all punctuation
				inputString3 = inputString3.replaceAll(" ", "").toUpperCase(); //Removes all spaces
				System.out.print(inputString3 + " = ");
				System.out.println(ht.encodeString(huffKey, inputString3));//Encodes strings according to huffkey
			}
			
			long encode = System.nanoTime();//Reads the time it took to decode input
			long encodeTime = encode - decode;
			
			// Displays the time the program took to run
			long endTime = System.nanoTime();
			long totalTime = endTime - startTime;
			System.out.println("\nBuild Huffman tree time in ms: " + (treeBuildTime / 1000000));
			System.out.println("Preorder Traversal time in ms: " + (traversalTime / 1000000));
			System.out.println("Decode time in ms: " + (decodeTime / 1000000));
			System.out.println("Encode time in ms: " + (encodeTime / 1000000));
			System.out.println("Total program time in ms: " + (totalTime / 1000000));

			br.close();
			br2.close();
			br3.close();
			}else {
				System.out.println("Invalid number of arguments try inputting again!");
			}

		} catch (FileNotFoundException fnfe) {
			System.err.println("File not found try again!");
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			System.err.println("IO Error other than file not found!");
			ioe.printStackTrace();
		} catch (Exception e) {
			System.err.print("Error see stack trace!");
			e.printStackTrace();
		}
	}
}
