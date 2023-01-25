import java.util.Scanner;
public class Duke {
    public static void printGreeting(){
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void printFarewell(){

        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void addNewTask(Task[] tasks, String newTaskName){
        if(Task.totalTasks >= tasks.length){
            System.out.println("Storage is full, cannot store new task");
        }
        Task newTask = new Task(newTaskName);
        tasks[Task.totalTasks - 1] = newTask;
        System.out.println("added: " + newTaskName);
    }

    public static void printTasks(Task[] tasks){
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < Task.totalTasks; ++i) {
            System.out.print(i + 1 + ".");
            if(tasks[i].isDone()){
                System.out.print("[X] ");
            }
            else{
                System.out.print("[ ] ");
            }
            System.out.println(tasks[i].getName());
        }
    }
    public static void markTask(Task[] tasks, String[] userInputs, boolean isDone){
        if(userInputs.length == 1){
            System.out.println("Please include the task number to be marked.");
            return;
        }
        if(userInputs.length > 2){
            System.out.println("Please only mark one task at a time. No tasks have been marked.");
            return;
        }
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(userInputs[1]);
        }
        catch (Exception e){
            System.out.println("Please input a valid task number.");
            return;
        }
        if(taskIndex <= 0 || taskIndex > Task.totalTasks){
            System.out.println("Please input a valid task number.");
            return;
        }
        taskIndex -= 1;
        if(isDone){
            if(tasks[taskIndex].isDone()){
                System.out.println("This task is already marked done:");
                System.out.println("[X] " + tasks[taskIndex].getName());
            }
            else{
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("[X] "+ tasks[taskIndex].getName());
                tasks[taskIndex].setDone(true);
            }
        }else{
            if(!tasks[taskIndex].isDone()){
                System.out.println("This task is already marked as not done:");
                System.out.println("[ ] " + tasks[taskIndex].getName());
            }
            else{
                System.out.println("OK, I've marked this task as not done yet: ");
                System.out.println("[ ] " + tasks[taskIndex].getName());
                tasks[taskIndex].setDone(false);
            }
        }

    }

    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        Scanner in = new Scanner(System.in);
        printGreeting();
        while(true)
        {
            String userInput = in.nextLine();
            if(userInput.equals("bye")){
                break;
            }
            else if(userInput.equals("list")) {
                printTasks(tasks);
            }
            else {
                String[] userInputs = userInput.split(" ");
                if(userInputs[0].equals("mark")){
                    markTask(tasks,userInputs,true);
                }
                else if(userInputs[0].equals("unmark")){
                    markTask(tasks,userInputs,false);
                }
                else {
                    addNewTask(tasks, userInput);
                }
            }
        }
        printFarewell();
    }
}
