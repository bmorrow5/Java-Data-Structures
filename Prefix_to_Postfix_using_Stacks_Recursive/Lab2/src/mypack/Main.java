package mypack;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * A program that is designed to convert prefix expressions to post-fix using
 * recursion. Built in functions like ArrayList are not used. The file is read
 * using the readLines method, which reads the file one line at a time while
 * converting prefix to postfix expressions. A more detailed description of this
 * program is in the readme and the analysis document.
 * 
 * @author Brandon L Morrow
 * @date 10/23/2022
 */
public class Main {

	public static void main(String[] args) {

		try {
			long startTime = System.nanoTime(); // Used to track time for O() estimation

			// Check command args length
			if (args.length > 0) {

				FileReader inputStream = null;
				FileWriter outputStream = null;
				try {
					inputStream = new FileReader(args[0]); // input file command line input
					outputStream = new FileWriter(args[1]); // output file command line input
					Main main = new Main();

					//System.out.print(main.readLine(inputStream, "", ""));
					outputStream.write(main.readLine(inputStream, "", "")); //Writes conversion to
					
					System.out.println("Success! See output file to view the output.");

					// Calculates endtime of program
					long endTime = System.nanoTime();
					long totalTime = endTime - startTime;
					System.out.println("Total program time in ms: " + (totalTime / 1000000));

				} finally {
					if (inputStream != null)
						inputStream.close();
					if (outputStream != null)
						outputStream.close();
				}
			} // Below is error checking
			if (args.length < 1) {
				System.err.print("Check your args length for command line arguments!!");
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
	}// End main()

	/**
	 * This method is designed to handle the reading of each line of input
	 * characters. It reads until the end of the line where there is \r and \n. It
	 * is compatible reading files on windows and linux devices. It error checks the
	 * postfix expression after it reads it.
	 * 
	 * @param inputStream
	 * @param acumulator
	 * @param currentLine
	 * @return accumulated string
	 * @throws IOException
	 */
	private String readLine(FileReader inputStream, String acumulator, String currentLine) throws IOException {

		int c = inputStream.read();
		if (c == -1) {
			return acumulator;
		}
		if ((char) c == '\r') {// Handles if being read on windows OS
			int a = inputStream.read();
			String temp = preToPost(inputStream) + (char) c + (char) a; // Converts line and adds \r and \n
			if (lastIsOperator(temp) == false) {
				temp = "Invalid prefix! Check input! Last element in postfix is not an operator!" + (char) c + (char) a;
			}
			acumulator += temp; // Adds converted string to accumulator
			return readLine(inputStream, acumulator, "");// Clears currentline and recursion call
		}
		if ((char) c == '\n') { // Handles if file being read on linux OS
			String temp = preToPost(inputStream) + (char) c;// Converts line and adds \r and \n
			if (lastIsOperator(temp) == false) {
				temp = "Invalid prefix! Check input! Last element in postfix is not an operator!" + (char) c;
			}
			acumulator += temp; // Adds converted line to accumulator
			return readLine(inputStream, acumulator, "");// Clears currentline and recursion call
		}
		return readLine(inputStream, acumulator, currentLine + (char) c);
	}

	/**
	 * A method that reads through the line from the inputstream, and converts the
	 * prefix expression to postfix using recursion.
	 * 
	 * @param inputStream
	 * @return postfix string
	 * @throws IOException
	 */
	private String preToPost(FileReader inputStream) throws IOException {

		int c = inputStream.read();
		char ch = (char) c;

		// Base case and handles spaces
		if ((c == -1) || (ch == ' '))
			return "";
		else if (isOperator(ch))
			return preToPost(inputStream) + preToPost(inputStream) + ch;
		else
			return ch + "";
	}

	/**
	 * Checks postfix to ensure it is in the proper form
	 * 
	 * @param currentLine
	 * @return true/false
	 */
	private boolean lastIsOperator(String currentLine) {
		if (currentLine.length() >= 3) {
			int last = currentLine.length() - 3; // reads through the \r and \n to get to the last char
			char ch = currentLine.charAt(last);
			if (isOperator(ch)) { // If last element is operator it can be a valid postfix
				return true;
			} else if (!isOperator(ch)) {// If element not an operator invalid postfix
				return false;
			}
		}
		return true;
	}

	/**
	 * Method that determines if a character is an operator
	 * @param ch
	 * @return true/false
	 */
	private boolean isOperator(char ch) {
		switch (ch) {
		case ('/'):
		case ('*'):
		case ('+'):
		case ('-'):
		case ('$'):
		case ('^'):
			return true;
		}
		return false;
	}
}
