import java.util.Scanner;

public class Duke {
    static int limitTask = 100;
    static Task[] tasks = new Task[limitTask];
    static int taskCount = 0;



    public static void listTask(){
        int idxCount = 1;
        printHorizontalLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0 ; i < taskCount; i++){
            System.out.println(idxCount + "." + tasks[i].toString());
            idxCount++;
        }
        printHorizontalLine();
    }
    public static void addTask(String input, String taskType){
        if(taskCount == limitTask ) {
            printHorizontalLine();
            System.out.println("Too much tasks");
            printHorizontalLine();
            return;
        }

        String task;
        int firstSpaceAfterTaskType,firstSlashSeparator,secondSlashSeparator;

        switch (taskType){
            case "todo":
                //ex: todo read book
                firstSpaceAfterTaskType = input.indexOf(" ");
                task  = input.substring(firstSpaceAfterTaskType + 1);
                tasks[taskCount] = new Todo(task);
                break;

            case "deadline":
                //ex: deadline return book /by 2pm
                firstSpaceAfterTaskType = input.indexOf(" ");
                firstSlashSeparator = input.indexOf('/');

                task = input.substring(firstSpaceAfterTaskType + 1,firstSlashSeparator - 1);
                String by = input.substring(input.indexOf("/") + 4);
                tasks[taskCount] = new Deadline(task,by);
                break;

            case "event":
                //event meeting /from 2pm /to 4pm
                firstSpaceAfterTaskType = input.indexOf(" ");
                firstSlashSeparator = input.indexOf('/');
                secondSlashSeparator = input.indexOf('/',input.indexOf('/') + 1);

                task = input.substring(firstSpaceAfterTaskType + 1,firstSlashSeparator - 1);
                String from  = input.substring(firstSlashSeparator + 6, secondSlashSeparator - 1);
                String to = input.substring(secondSlashSeparator + 4);
                tasks[taskCount] = new Event(task, from, to);
                break;
        }

        printHorizontalLine();
        System.out.println("Got it. I've added this task:\n" + tasks[taskCount].toString() +
                "\nNow you have " + (taskCount + 1) + " in the list");
        printHorizontalLine();
        taskCount++;
    }


    public static void markTask(int index){
        if(index < taskCount && index >=0){
            tasks[index].markAsDone();
            printHorizontalLine();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks[index].toString());
            printHorizontalLine();
        }
    }

    public static void unmarkTask(int index){
        if(index < taskCount && index >=0){
            tasks[index].markAsNotDone();
            printHorizontalLine();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(tasks[index].toString());
            printHorizontalLine();
        }
    }
    public static void startBot(){
        Scanner in = new Scanner((System.in));
        while(true){
            String input  = in.nextLine();
            String[] args = input.split(" ");
            int index;
            switch(args[0]){
                case "bye":
                    return;
                case "list":
                    listTask();
                    break;
                case "mark":
                    index = Integer.parseInt(args[1]) - 1;
                    markTask(index);
                    break;
                case "unmark":
                    index = Integer.parseInt(args[1]) - 1;
                    unmarkTask(index);
                    break;
                default:
                    addTask(input,args[0]);

            }
        }
    }
    public static void main(String[] args) {
        printHorizontalLine();
        System.out.println( "Hello! I'm Duke\n" + " What can I do for you?\n");
        printHorizontalLine();

        startBot();

        printHorizontalLine();
        System.out.println(" Bye. Hope to see you again soon!\n");
        printHorizontalLine();
    }
    public static void printHorizontalLine(){
        System.out.println("____________________________________________________________");
    }
}
