DESIGN: 
This program is designed to be run from the command line to encode and decode encoded messages using a huffman tree.
This program:

1. Builds a huffman tree by reading frequencies from the frequency file input
2. Displays the tree using preorder traversal
3. Displays a key that shows each characters encoded value
4. Decodes the encrypted file and shows their decoded form in clear text
5. Encodes the clear text sentences


This program does not write to an output file, as it is designed to simply display the decoded messages in the command prompt.
This feature can be added by quickly addded if desired by placing an outputwriter where the system.out.println
statements are in the main..

--------------------------------------------------------------------------------------------------
TO RUN:
1) To run the program enter the folder location: BMorrowLab3\Lab3 

2) After you input the folder location enter the following with input and output file locations:

java -jar Project03.jar [frequencytableFile] [decodeFile] [encodeFile]

EXAMPLE: 
java -jar Project03.jar C:\Users\brand\Documents\BMorrowLab3\Input\FreqTable.txt C:\Users\brand\Documents\BMorrowLab3\Input\Encoded.txt C:\Users\brand\Documents\BMorrowLab3\Input\ClearText.txt
