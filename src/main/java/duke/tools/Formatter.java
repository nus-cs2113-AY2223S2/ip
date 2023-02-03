package duke.tools;

import java.util.Arrays;
public class Formatter {
    private final static int LINE_LENGTH = 60;
    private final static String STRING_INDENTATION = "      ";

    /**
     * Prints spaces for indentation purpose.
     * Length 4 for horizontal line, length 6 for words.
     *
     * @param size of indentation.
     */
    public void printIndentation(int size) {
        for (int i = 0; i<size; i+=1){
            System.out.print(' ');
        }
    }

    /**
     * Adds indentation for each element of the string array.
     * Java array pass by reference.
     * Copy of pointers still point to the original object.
     *
     * @param inputArray array.
     */

    public void addStringIndentation(String[] inputArray) {
        for(int i=0; i<inputArray.length; i+=1){
            inputArray[i] = STRING_INDENTATION + inputArray[i];
        }
    }


    /**
     * Draws horizontal separation lines in between lines of text.
     * Use unicode box drawings light horizontal(0x2500).
     * Important to note the code is in hex.
     * Character.toString(char c) converts character to string to print.
     * Otherwise, use print(char) method of PrintStream class to output a single character.
     *
     * @param[in]: length/number of characters of the horizontal separation line.
     */
    public void drawSeparationLine(){
        printIndentation(4);
        String s = "_";
        for (int i=0; i<LINE_LENGTH; i+=1){
            System.out.print(s);
        }
        System.out.print("\n");
    }

}
