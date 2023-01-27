import java.util.Arrays;

public class TaskMaster {
    //initialise an array to store the tasks
    String[] toDo;

    TaskMaster(){
        this.toDo = new String[0];
    }

    public void addNewItem(String toAdd){
        //instead of a fixed sized array, use an array that 'dynamically' increases in size for every task added
        int listLength = toDo.length;
        this.toDo = Arrays.copyOf(this.toDo, listLength + 1);
        this.toDo[listLength] = toAdd;
    }

    public void printList(){
        //this is only for UX purposes
        if (this.toDo.length == 0){
            System.out.println("Hm... It looks like you have not added any tasks.");
        }
        for (int i = 0; i< this.toDo.length; i++){
            System.out.println(this.toDo[i]);
        }
    }
}
