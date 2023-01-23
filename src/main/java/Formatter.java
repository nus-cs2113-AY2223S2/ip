import java.util.Arrays;
public class Formatter {
    private int indentationSize;
    private int separationLineLength = 50;

    /**
     * To print spaces for indentation purpose
     * length 4 for horizontal line, length 6 for words
     * @param size
     */
    public void printIndentation(int size){
        this.indentationSize = size;
        for (int i = 0; i<this.indentationSize; i+=1){
            System.out.print(' ');
        }
    }

    /**
     * Adding indentation for each element of the string array
     * Java array pass by reference
     * Copy of pointers still point to the original object
     * @param inputArray
     * @return String[]
     */

    public void addStringIndentation(String[] inputArray){
        for(int i=0; i<inputArray.length; i+=1){
            inputArray[i] = "      "+ inputArray[i];
        }
    }


    /**
     * To draw horizontal separation lines in between lines of text
     * Use unicode box drawings light horizontal(0x2500)
     * Important to note the code is in hex
     * Character.toString(char c) converts a character to string to use print output the character
     * Otherwise, use print(char) method of PrintStream class to output a single character
     * @author: wenxin
     * @param[in]: length/number of characters of the horizontal separation line
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
