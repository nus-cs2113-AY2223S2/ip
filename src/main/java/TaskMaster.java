import java.util.Arrays;

public class TaskMaster {
    //initialise an array to store the tasks
    private TaskManager[] toDo;

    public TaskMaster() {
        this.toDo = new TaskManager[0];
    }

    public void addNewItem(String toAdd) {
        //instead of a fixed sized array, use an array that 'dynamically' increases in size for every task added
        int listLength = toDo.length;
        this.toDo = Arrays.copyOf(this.toDo, listLength + 1);
        this.toDo[listLength] = new TaskManager(toAdd);
    }
    public TaskManager getTask(int index){
        return this.toDo[index];
    }

    public void printList() {
        //this is only for UX purposes
        if (this.toDo.length == 0) {
            System.out.println("Hm... It looks like you have not added any tasks.");
        }
        for (int i = 0; i < this.toDo.length; i++) {
            this.toDo[i].printTaskAndStatus(i);
        }
    }
}
