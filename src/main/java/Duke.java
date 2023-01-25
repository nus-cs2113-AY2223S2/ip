import java.util.Scanner;

public class Duke {
    static int limitTask = 100;
    static Task[] task = new Task[limitTask];
    static int taskCount = 0;



    public static void listInput(){
        int idxCount = 1;
        printHorizontalLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0 ; i < taskCount; i++){
            System.out.println(idxCount + ".[" + task[i].getStatusIcon() + "] " + task[i].getDescription());
            idxCount++;
        }
        printHorizontalLine();
    }
    public static void addList(String input){
        if(taskCount == limitTask ){
            printHorizontalLine();
            System.out.println("Too much tasks");
            printHorizontalLine();
        }else{
            Task t = new Task(input);
            task[taskCount] = t;
            taskCount++;
            printHorizontalLine();
            System.out.println("added: " + input);
            printHorizontalLine();
        }

    }

    public static void markTask(int index){
        if(index < taskCount && index >=0){
            task[index].markAsDone();
            printHorizontalLine();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("[X] " + task[index].getDescription());
            printHorizontalLine();
        }
    }

    public static void unmarkTask(int index){
        if(index < taskCount && index >=0){
            task[index].markAsNotDone();
            printHorizontalLine();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("[ ] " + task[index].getDescription());
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
                    listInput();
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
                    addList(input);

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
