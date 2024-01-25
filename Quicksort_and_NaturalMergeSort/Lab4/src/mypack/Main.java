/**
 * This program is designe to read three input files and sort them four different
 * ways each
 * @author Brandon L Morrow
 * 
 */

package mypack;

public class Main {

	public static void main(String[] args) {

		if (args.length < 5) {
			System.out.println(
					"Invalid argument length try again!! Remember the arguments are [#integers] [inputFile1] [inputFile2] [inputFile3] [OutputFolder]");
		} else {

			int numberIntegers = Integer.parseInt(args[0]);
			String readFile1 = args[1];
			String readFile2 = args[2];
			String readFile3 = args[3];

			String outputFolder = args[4];

			String natMergeFile = "NatMerge.txt";
			String quickSort = "QuickSort.txt";
			String quick50 = "QuickSortInsert50.txt";
			String quick100 = "QuickSortInsert100.txt";
			String medThree = "QuickSortMedThree.txt";

			System.out.println("\n\nInput File 1 Sorting Data:");
			ReadFile rf1 = new ReadFile(numberIntegers);
			rf1.readFile(readFile1, outputFolder, "/inputFile1_" + natMergeFile, "/inputFile1_" + quickSort,
					"/inputFile1_" + quick50, "/inputFile1_" + quick100, "/inputFile1_" + medThree);

			System.out.println("\n\nInput File 2 Sorting Data:");
			ReadFile rf2 = new ReadFile(numberIntegers);
			rf2.readFile(readFile2, outputFolder, "/inputFile2_" + natMergeFile, "/inputFile2_" + quickSort,
					"/inputFile2_" + quick50, "/inputFile2_" + quick100, "/inputFile2_" + medThree);

			System.out.println("\n\nInput File 3 Sorting Data:");
			ReadFile rf3 = new ReadFile(numberIntegers);
			rf3.readFile(readFile3, outputFolder, "/inputFile3_" + natMergeFile, "/inputFile3_" + quickSort,
					"inputFile3_" + quick50, "/inputFile3_" + quick100, "/inputFile3_" + medThree);

		}

	}
}