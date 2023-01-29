import java.util.Scanner;

public class Duke {

    static final int TASK_LIMIT = 100;

    static Task[] tasks = new Task[TASK_LIMIT];
    static int taskCount = 0;

    public static void addTask(String task){
        if(taskCount < TASK_LIMIT){
            tasks[taskCount] = new Task(task);
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
            Task currentTask = tasks[i-1];
            String taskString = String.format("[%s] %s", currentTask.getStatusIcon(), currentTask.getDescription());
            printf(" %d. %s\n", i, taskString);
        }
        println("____________________________________________________________");
    }

    public static void markTask(int index){
        println("____________________________________________________________");
        if(index >= taskCount || index < 0){
            println(" Invalid index!");
        } else {
            tasks[index].setDone(true);
            println(" Nice! I've marked this task as done:");
            printf("   [X] %s\n", tasks[index].getDescription());
        }
        println("____________________________________________________________");
    }

    public static void unmarkTask(int index){
        println("____________________________________________________________");
        if(index >= taskCount || index < 0){
            println(" Invalid index!");
        } else {
            tasks[index].setDone(false);
            println(" OK, I've marked this task as not done yet:");
            printf("   [ ] %s\n", tasks[index].getDescription());
        }
        println("____________________________________________________________");
    }

    public static void beginInputLoop(){
        Scanner in = new Scanner(System.in);
        while(true){
            try {
                String input = in.nextLine();
                String[] args = input.split(" ");
                int index;
                switch (args[0]) {
                case "bye":
                    return;
                case "list":
                    listTasks();
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
                    addTask(input);
                    break;
                }
            } catch(Exception e) {
                println("____________________________________________________________");
                System.out.println("Something went wrong: "+ e);
                println("____________________________________________________________");
            }

        }
    }

    // println adds indentation before each println output string
    public static void println(String x){
        System.out.print("\t");
        System.out.println(x);
    }

    // printf adds indentation before each printf output string
    public static void printf(String format, Object ...args){
        System.out.print("\t");
        System.out.printf(format, args);
    }
    public static void main(String[] args) {

        println("____________________________________________________________");
        println(" Hello! I'm Duke");
        println(" What can I do for you?");
        println("____________________________________________________________");

        beginInputLoop();

        println("____________________________________________________________");
        println(" Bye. Hope to see you again soon!");
        println("____________________________________________________________");
    }
}
