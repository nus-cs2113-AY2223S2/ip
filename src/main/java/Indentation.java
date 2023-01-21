public class Indentation {
    private int size;

    public void printIndentation(int size){
        this.size = size;
        for (int i = 0; i<this.size; i+=1){
            System.out.print(' ');
        }
    }
}
