package duke.tools;

/**
 * Print an array of String on terminal.
 */
public class Tool {
    /**
     *
     * @param array
     */
    public void printStringArray(String[] array){
        for(int i=0; i<array.length; i+=1){
            System.out.print(array[i]+'\n');
        }
    }

}
