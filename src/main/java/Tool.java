import java.util.Arrays;
public class Tool {
    public void printStringArray(String[] array){
        for(int i=0; i<array.length; i+=1){
            System.out.print(array[i]+'\n');
        }
    }

    public String[] addIndex(String[] inputArray, int size){
        for (int i=1; i<=size; i+=1){
            inputArray[i-1] = Integer.toString(i) + '.' + inputArray[i-1];
        }
        return Arrays.copyOf(inputArray, size);
    }
}
