import java.util.ArrayList;

public class ToDoList {
    private ArrayList<Task> TaskArrayList = new ArrayList<Task>();
    private Task[] TaskArray = new Task[100];
    private String dash = "__________________________________";

    private void syncArrayList_Array(){
        TaskArray = TaskArrayList.toArray(TaskArray);
    }

    public void addToList(Task taskToBeAdded){
        TaskArrayList.add(taskToBeAdded);
        System.out.println(dash+"\nadded: "+taskToBeAdded.name+"\n"+dash);
        syncArrayList_Array();
    }

    public void printList(){
        System.out.println(dash+"\nHere are the tasks in your list:\n");
        for(int index = 0;index<TaskArrayList.size();index++){
            if(TaskArray[index].isDone){
                System.out.println(Integer.toString(index+1)+". [X]"+TaskArray[index].name+"\n");
            }
            else{
                System.out.println(Integer.toString(index+1)+". [ ]"+TaskArray[index].name+"\n");
            }
        }
        System.out.println(dash + "\n");
    }

    public void mark(Task item){
        if(item.isDone){
            item.isDone = false;       //unmark
            System.out.println(dash+"\nOK, I've marked this task as not done yet:\n  [ ] "+item.name);

        }
        else{
            item.isDone = true;       //mark
            System.out.println(dash+"\nNice! I've marked this task as done:\n  [X] "+item.name);
        }
    }

    public int size(){
        return TaskArrayList.size();
    }

    public Task at(int index){
        syncArrayList_Array();
        return TaskArray[index-1];
    }
}
