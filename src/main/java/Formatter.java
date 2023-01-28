import java.util.Arrays;
public class Formatter {
    private int indentationSize;
    private int separationLineLength = 50;

    /**
     * Prints spaces for indentation purpose.
     * Length 4 for horizontal line, length 6 for words.
     *
     * @param size of indentation.
     */
    public void printIndentation(int size){
        this.indentationSize = size;
        for (int i = 0; i<this.indentationSize; i+=1){
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

    public void addStringIndentation(String[] inputArray){
        for(int i=0; i<inputArray.length; i+=1){
            inputArray[i] = "      "+ inputArray[i];
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
        String s = Character.toString(0x2500);
        for (int i=0; i<this.separationLineLength; i+=1){
            System.out.print(s);
        }
        System.out.print("\n");
    }

}
