package duke.tools;

/**
 * Format terminal output with separation lines and indentation.
 */
public class Formatter {
    private final static int LINE_LENGTH = 60;
    private final static String STRING_INDENTATION = "      ";

    /**
     * Prints spaces for indentation purpose.
     * Length 4 for horizontal line, length 6 for words.
     *
     * @param size of indentation.
     */
    public static void printIndentation(int size) {
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

    public static void addStringIndentation(String[] inputArray) {
        for(int i=0; i<inputArray.length; i+=1){
            inputArray[i] = STRING_INDENTATION + inputArray[i];
        }
    }


    /**
     * Draws horizontal separation lines in between lines of text.
     *
     * @param[in]: length/number of characters of the horizontal separation line.
     */
    public static void drawSeparationLine(){
        printIndentation(4);
        String s = "_";
        for (int i=0; i<LINE_LENGTH; i+=1){
            System.out.print(s);
        }
        System.out.print("\n");
    }

}
