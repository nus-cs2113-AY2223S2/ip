import java.util.Scanner;
public class Duke {
    public static void printGreeting(){
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void printFarewell(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void addNewTask(Task[] tasks, String[] taskParameters, TaskType taskType) {
        if (Task.totalTasks >= tasks.length) {
            System.out.println("Storage is full, cannot store new task");
        }
        Task newTask = null;
        switch (taskType) {
        case TODO:
            newTask = new ToDo(taskParameters[0]);
            break;
        case DEADLINE:
            newTask = new Deadline(taskParameters[0], taskParameters[1]);
            break;
        case EVENT:
            newTask = new Event(taskParameters[0], taskParameters[1], taskParameters[2]);
        }
        tasks[Task.totalTasks - 1] = newTask;
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask.toString());
        System.out.println("Now you have " + Integer.toString(Task.totalTasks) + " tasks in the list.");
    }

    public static void printTasks(Task[] tasks){
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < Task.totalTasks; ++i) {
            System.out.print(i + 1 + ".");
            System.out.println(tasks[i].toString());
        }
    }
    public static void changeTaskStatus(Task[] tasks, String[] taskNumbers, boolean isDone){
        if(taskNumbers.length == 0){
            System.out.println("Please include the task number to be marked.");
            return;
        }
        if(taskNumbers.length > 1){
            // For now only allow marking of one task at a time
            System.out.println("Please only mark one task at a time. No tasks have been marked.");
            return;
        }
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(taskNumbers[0]);
        }
        catch (Exception e){
            System.out.println("Please input a valid task number exception.");
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
                System.out.println(tasks[taskIndex].toString());
            }
            else{
                tasks[taskIndex].setDone(true);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks[taskIndex].toString());
            }
        }else{
            if(!tasks[taskIndex].isDone()){
                System.out.println("This task is already marked as not done:");
                System.out.println(tasks[taskIndex].toString());
            }
            else{
                tasks[taskIndex].setDone(false);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(tasks[taskIndex].toString());
            }
        }
    }

    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        Scanner in = new Scanner(System.in);
        printGreeting();
        boolean isProgramRunning = true;
        while(isProgramRunning)
        {
            String userInput = in.nextLine();
            Command command = Parser.parseCommand(userInput);
            switch(command.getCommandType()){
            case ADD_TODO_COMMAND:
                addNewTask(tasks, command.getAdditionalParameters(), TaskType.TODO);
                break;
            case ADD_DEADLINE_COMMAND:
                addNewTask(tasks, command.getAdditionalParameters(), TaskType.DEADLINE);
                break;
            case ADD_EVENT_COMMAND:
                addNewTask(tasks, command.getAdditionalParameters(), TaskType.EVENT);
                break;
            case LIST_TASKS_COMMAND:
                printTasks(tasks);
                break;
            case MARK_TASK_COMMAND:
                changeTaskStatus(tasks, command.getAdditionalParameters(), true);
                break;
            case UNMARK_TASK_COMMAND:
                changeTaskStatus(tasks, command.getAdditionalParameters(), false);
                break;
            case END_PROGRAM_COMMAND:
                printFarewell();
                isProgramRunning = false;
                break;
            case UNKNOWN_COMMAND:
                System.out.println("Unknown task or task parameters received. Please try again.");
                break;
            }
        }
    }
}
