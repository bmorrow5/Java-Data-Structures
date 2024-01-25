DESIGN: 
This program is designed to be run from the command line to sort three input files 5 different ways

1. Reads three input files to be sorted
2. Conducts natural merge sort
3. Conducts quicksort with first element as pivot
4. Conducts quicksort where partitions of 50 are insertion sorted
5. Conducts quicksort where partitions of 100 are insertion sorted
6. Conducts quicksort with median of three as pivot


Please read the to run insturctions carefully. After inputting the first three input file locations put the output folder location.
After putting the output folder please put the names of the files only you want the output to be written to.

For the sake of my sanity and yours I decided to make it only 5 command line arguments. The output files will be automatically generated
for the outputFolder location. This was a decision to avoid 15 additional command line arguments

--------------------------------------------------------------------------------------------------
TO RUN:
1) To run the program enter the folder location: BMorrowLab3\Lab3 

2) After you input the folder location enter the following with input and output file locations:

java -jar Project04.jar [int fileSize] [inputFile1] [inputFile2] [inputFile3] [outputFolder]

EXAMPLE: 
java -jar Project04.jar 50 C:\Users\brand\Documents\BMorrowLab4\Input\ran50.dat C:\Users\brand\Documents\BMorrowLab4\Input\asc50.dat C:\Users\brand\Documents\BMorrowLab4\Input\rev50.dat C:\Users\brand\Documents\BMorrowLab4\Output
