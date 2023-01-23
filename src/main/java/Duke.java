import java.util.Scanner;

public class Duke {

    static int taskLimit = 100;

    static String[] tasks = new String[taskLimit];
    static int taskCount = 0;



    public static void addTask(String task){
        if(taskCount < taskLimit){
            tasks[taskCount] = task;
            taskCount++;
            println("____________________________________________________________");
            printf(" added: %s\n", task);
            println("____________________________________________________________");
        } else {
            println("____________________________________________________________");
            println(" Too many tasks!");
            println("____________________________________________________________");
        }
    }

    public static void listTasks(){
        println("____________________________________________________________");
        for(int i=1;i<=taskCount;i++){
            printf(" %d. %s\n", i, tasks[i-1]);
        }
        println("____________________________________________________________");
    }

    public static void beginInput(){
        Scanner in = new Scanner(System.in);
        while(true){
            String input = in.nextLine();
            switch(input){
            case "bye":
                return;
            case "list":
                listTasks();
                break;
            default:
                addTask(input);
                break;
            }

        }
    }

    public static void println(String x){
        System.out.print("\t");
        System.out.println(x);
    }

    public static void printf(String format, Object ...args){
        System.out.print("\t");
        System.out.printf(format, args);
    }
    public static void main(String[] args) {

        println("____________________________________________________________");
        println(" Hello! I'm Duke");
        println(" What can I do for you?");
        println("____________________________________________________________");

        beginInput();

        println("____________________________________________________________");
        println(" Bye. Hope to see you again soon!");
        println("____________________________________________________________");
    }
}
