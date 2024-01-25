package mypack;

/**
 * A Class designed to read a and sort a file. This class was designed to make it easier to read
 * in multiple files for analysis
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class ReadFile {
	LinkedList list = new LinkedList();
	NaturalMerge nm = new NaturalMerge();
	static int input;
	int[] array1;
	int[] array2;
	int[] array3;
	int[] array4;
	QuickSort qs = new QuickSort();

	ReadFile(int input) {
		this.list = list;
		this.nm = nm;
		this.qs = qs;
		this.input = input;
		this.array1 = new int[input];
		this.array2 = new int[input];
		this.array3 = new int[input];
		this.array4 = new int[input];
	}
	
	public int getInput() {
		return input;
	}
	public void setInput(int input) {
		this.input = input;
	}

	public void readFile(String inputFile, String outputFolder, String natMergeFile, String quickSort, String quick50, String quick100, String medThree) {

		try {
					
			//Creates folder location
			String output1 = (outputFolder +  natMergeFile);
			String output2 = (outputFolder + quickSort);
			String output3 = (outputFolder + quick50);
			String output4 = (outputFolder + quick100);
			String output5 = (outputFolder + medThree);

			BufferedReader br = new BufferedReader(new FileReader(inputFile));
			BufferedWriter bwNat = new BufferedWriter(new FileWriter(output1));
			BufferedWriter bwQS = new BufferedWriter(new FileWriter(output2));
			BufferedWriter bwQSInsert50 = new BufferedWriter(new FileWriter(output3));
			BufferedWriter bwQSInsert100 = new BufferedWriter(new FileWriter(output4));
			BufferedWriter bwMedThree = new BufferedWriter(new FileWriter(output5));
			
			// Initialize scanner to read ints
			Scanner scan = new Scanner(br);

			// Now read from a file
			int i = 0;
			while (scan.hasNextInt()) {
				int a = scan.nextInt();
				list.add(a);
				array1[i] = a;
				array2[i] = a;
				array3[i] = a;
				array4[i] = a;
				i++;
			}

			long startTime = System.nanoTime(); // Used to track time for O() estimation
			// Now lets test the natural merge sort
			nm.natMergeSortRecur(list);
			long nmerge = System.nanoTime(); // Used to track time for O() estimation
			long nmergeTotal = nmerge - startTime;

			System.out.println("\nFile Size " + ReadFile.input + " Integers");
			System.out.println("-----------------------------------------------------------------");
			// Now lets display the number of swaps and comparisons
			System.out.println("Natural Merge Sort:");
			System.out.println("Comparisons: " + nm.getComparisons());
			System.out.println("Swaps: " + nm.getSwaps());
			System.out.println("Time to sort microseconds: " + nmergeTotal / 1000);
			System.out.println("---------------------------");

			// Now lets read our four arrays to test our four quicksorts
			qs.recursiveQuickSort(array1, 0, array1.length - 1);
			long quickSortTime = System.nanoTime();
			long qsTotal = quickSortTime - nmerge;

			System.out.println("Quicksort:");
			System.out.println("Comparisons: " + qs.getComparisons());
			System.out.println("Swaps: " + qs.getSwaps());
			System.out.println("Time to sort microseconds: " + qsTotal / 1000);
			System.out.println("---------------------------");

			qs.setComparisons(0);
			qs.setSwaps(0);
			qs.quickSortInsertionLast50(array2, 0, array2.length - 1);
			long quickSort2 = System.nanoTime();
			long qsTotal2 = quickSort2 - quickSortTime;

			System.out.println("Quicksort with last 50 insertion sort:");
			System.out.println("Comparisons: " + qs.getComparisons());
			System.out.println("Swaps: " + qs.getSwaps());
			System.out.println("Time to sort microseconds: " + qsTotal2 / 1000);
			System.out.println("---------------------------");

			qs.setComparisons(0);
			qs.setSwaps(0);
			qs.quickSortInsertionLast100(array3, 0, array3.length - 1);
			long quickSort3 = System.nanoTime();
			long qsTotal3 = quickSort3 - quickSort2;

			System.out.println("Quicksort with last 100 insertion sort:");
			System.out.println("Comparisons: " + qs.getComparisons());
			System.out.println("Swaps: " + qs.getSwaps());
			System.out.println("Time to sort microseconds: " + qsTotal3 / 1000);
			System.out.println("---------------------------");

			qs.setComparisons(0);
			qs.setSwaps(0);
			qs.medianThreeQuickSort(array4, 0, array4.length - 1);
			long quickSort4 = System.nanoTime();
			long qsTotal4 = quickSort4 - quickSort3;

			System.out.println("Quicksort with median of three pivot:");
			System.out.println("Comparisons: " + qs.getComparisons());
			System.out.println("Swaps: " + qs.getSwaps());
			System.out.println("Time to sort microseconds: " + qsTotal4 / 1000);

			//Write linked list to file
			LinkedList temp = nm.getFinalList();
			while (temp.head != null) {
				bwNat.write(temp.head.getValue() + "\n");
				temp.head = temp.head.next;
			}
			
			//Write array to file
			for (int j = 0; j < input; j++) {
				String a = array1[j] + "\n";
				String b = array2[j] + "\n";
				String c = array3[j] + "\n";
				String d = array4[j] + "\n";
				bwQS.write(a);
				bwQSInsert50.write(b);
				bwQSInsert100.write(c);
				bwMedThree.write(d);
			}

			bwNat.close();
			bwQS.close();
			bwQSInsert50.close();
			bwQSInsert100.close();
			bwMedThree.close();
			br.close();

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
