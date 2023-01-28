import java.util.Arrays;
public class Tool {
    public void printStringArray(String[] array){
        for(int i=0; i<array.length; i+=1){
            System.out.print(array[i]+'\n');
        }
    }

    /**
     * Adds index and task status in front of the task description.
     * Returns a string array with indexed taskDescription.
     *
     * @param inputArray array of task objects.
     * @param size of array.
     * @return a string array with index added for each element.
     */
//    public String[] addIndex(Task[] inputArray, int size){
//        String[] indexedArray = new String[size];
//        for (int i=1; i<=size; i+=1){
//
//            indexedArray[i-1] = Integer.toString(i) + '.'
//                    + '[' + inputArray[i-1].getTaskStatus() + "] "
//                    + inputArray[i-1].taskDescription;
//        }
//        return indexedArray;
//    }
}
