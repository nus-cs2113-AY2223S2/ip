package chronos.tasktype;

import chronos.savehandler.Save;
import chronos.savehandler.Storage;

import java.util.ArrayList;
import java.util.stream.Collectors;

//this class stores all the tasks
public class Stash {

    private ArrayList<Task> tasks;
    public Stash() {
        this.tasks = new ArrayList<Task>();;
    }

    public void addNewTask(Task task) {
        this.tasks.add(task);
    }

    public void generateTask(int count, Task task){
        this.tasks.add(task);
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }
    public void deleteTask(int index){
        this.tasks.remove(index);
    }

    public int ObtainTaskCount() {
        int curr = this.tasks.size();
        return curr;
    }

    public void printTasks() {
        for (Task task : tasks) {
            System.out.printf(" %s", task.toString());
            System.out.print("\n");
        }
        System.out.printf("Currently, you have %d tasks(s) in your ToDo list.", ObtainTaskCount());
        System.out.print("\n");
    }
    public void saveTasksToFile() {
        ArrayList<Save> saveTasks = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task instanceof Todo) {
                Todo todo = (Todo) task;
                saveTasks.add(new Save("[T]", todo.isDone(), todo.getDescription(), "", "", ""));
            } else if (task instanceof Event) {
                Event event = (Event) task;
                saveTasks.add(new Save("[E]", event.isDone(), event.getDescription(), "", event.getStart(), event.getEnd()));
            } else if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                saveTasks.add(new Save("[D]", deadline.isDone(), deadline.getDescription(), deadline.getDue(), "", ""));
            }
        }
        Storage.saveTasks(saveTasks.stream().map(Save::bucketConverter).collect(Collectors.toCollection(ArrayList::new)));
    }

    public void searchTask(String keyword){
        for (Task task: tasks){
            if (task.getDescription().contains(keyword)){
                System.out.printf(" %s", task.toString());
            }
        }
    }

}
