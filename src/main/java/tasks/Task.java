package tasks;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Task {

    protected String taskSign;
    protected String taskName;
    protected boolean isDone;
    public static void printLines(){
        System.out.println("--------------------------------------------------");
    }

    public static void fileWrite(ArrayList<Task> task1) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("Rolex.txt"));
        for (Task x : task1) {
            writer.write(x.taskName + ";" + x.isDone + ";" + x.taskSign + "\n");
        }
        writer.close();
    }


    public static ArrayList<Task> fileAccess(File fileName) throws IOException {
        ArrayList<Task> taskList = new ArrayList<>();
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String input;
        while((input = bufferedReader.readLine())!=null){
            String[] line = input.split(";");
            Task r = new Task(line[0], Boolean.parseBoolean(line[1]), line[2]);
            taskList.add(r);
        }
        bufferedReader.close();
        return taskList;
    }

    public Task(String taskName, boolean test, String taskSign){
        this.taskName = taskName;
        this.taskSign = taskSign;
        this.isDone = test;
    }

    public void MarkTask(){
        printLines();
        if(this.isDone){
            System.out.println("Task is already marked.");
        } else{
            this.isDone = true;
            System.out.println("Well Done. This task is marked as done:");
            System.out.println(this.taskSign + "[" +this.taskStatus()+ "] " + this.taskName);
        }
        printLines();
    }

    public void unMarkTask(){
        printLines();
        if(this.isDone){
            this.isDone = false;
            System.out.println("Oh no, I've unmarked this task as it is not done:");
            System.out.println(this.taskSign + "[" +this.taskStatus()+ "] " + this.taskName);
        } else{
            System.out.println("Task is already unmarked.");
        }
        printLines();
    }

    public static void deleteTask(List<Task> task, int indexToDelete){
        printLines();
        if(indexToDelete >= 1 && indexToDelete <= task.size()){
            System.out.println("Noted. I've removed this task.");
            System.out.println(task.get(indexToDelete-1).taskSign + "["
                    + task.get(indexToDelete-1).taskStatus() + "] "
                    + task.get(indexToDelete-1).taskName);

            task.remove(indexToDelete-1);
            System.out.println("\nNow you have " + task.size() + " tasks in the list.");
        } else{
            System.out.println("Invalid index. Please enter valid index number!");
        }
        printLines();
    }

    public String taskStatus(){
        return (isDone ? "X" : " ");
    }

    public String toString(){
        return this.taskSign + "[" + this.taskStatus() + "] " + this.taskName;
    }

} // Task class ends here