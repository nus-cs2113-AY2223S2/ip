import tasks.*;
import io.Message;
import io.Parser;

import java.util.Scanner;

public class Duke {

    static final int MAX_TASKS = 100;

    static Task[] tasks = new Task[MAX_TASKS];
    static int taskCount = 0;

    public static void addTask(Task task){
        if(taskCount < MAX_TASKS){
            tasks[taskCount]= task;
            taskCount++;
            println(Message.LINE.toString());
            printf(" Got it. I've added this task:\n");
            printf("   [%s][%s] %s\n", task.getTaskSymbol(), task.getStatusIcon(), task);
            printf(" Now you have %d tasks in the list.\n", taskCount);
            println(Message.LINE.toString());
        } else {
            println(Message.LINE.toString());
            println(" Too many tasks!");
            println(Message.LINE.toString());
        }
    }

    public static void listTasks(){
        println(Message.LINE.toString());
        println(" Here are the tasks in your list:");
        for(int i=1;i<=taskCount;i++){
            Task currentTask = tasks[i-1];
            String taskString = String.format("[%s][%s] %s",
                    currentTask.getTaskSymbol(),
                    currentTask.getStatusIcon(),
                    currentTask);
            printf(" %d.%s\n", i, taskString);
        }
        println(Message.LINE.toString());
    }

    public static void markTask(int index){
        println(Message.LINE.toString());
        if(index >= taskCount || index < 0){
            println(" Invalid index!");
        } else {
            tasks[index].setDone(true);
            println(" Nice! I've marked this task as done:");
            printf("   [%s][%s] %s\n", tasks[index].getTaskSymbol(), tasks[index].getStatusIcon(), tasks[index]);
        }
        println(Message.LINE.toString());
    }

    public static void unmarkTask(int index){
        println(Message.LINE.toString());
        if(index >= taskCount || index < 0){
            println(" Invalid index!");
        } else {
            tasks[index].setDone(false);
            println(" OK, I've marked this task as not done yet:");
            printf("   [%s][%s] %s\n", tasks[index].getTaskSymbol(), tasks[index].getStatusIcon(), tasks[index]);
        }
        println(Message.LINE.toString());
    }

    public static void beginInputLoop(){
        Scanner in = new Scanner(System.in);
        Parser parser = new Parser();
        while(true){
            try {
                String input = in.nextLine();
                parser.parse(input);
                int index;
                switch (parser.getCommand()) {
                case "bye":
                    in.close();
                    return;
                case "list":
                    listTasks();
                    break;
                case "mark":
                    index = Integer.parseInt(parser.getBody()) - 1;
                    markTask(index);
                    break;
                case "unmark":
                    index = Integer.parseInt(parser.getBody()) - 1;
                    unmarkTask(index);
                    break;
                case "todo":
                    addTask(new ToDo(parser.getBody()));
                    break;
                case "event":
                    addTask(new Event(parser.getBody(), parser.get("from"), parser.get("to")));
                    break;
                case "deadline":
                    addTask(new Deadline(parser.getBody(), parser.get("by")));
                    break;
                default:
                    println(" Unknown command!");
                    break;
                }
            } catch(Exception e) {
                println(Message.LINE.toString());
                println(" Something went wrong: "+ e);
                println(Message.LINE.toString());
            }

        }

    }

    // println adds indentation before each println output string
    public static void println(String x){
        System.out.print("    ");
        System.out.println(x);
    }

    // printf adds indentation before each printf output string
    public static void printf(String format, Object ...args){
        System.out.print("    ");
        System.out.printf(format, args);
    }
    public static void main(String[] args) {

        println(Message.LINE.toString());
        println(" Hello! I'm Duke");
        println(" What can I do for you?");
        println(Message.LINE.toString());

        beginInputLoop();

        println(Message.LINE.toString());
        println(" Bye. Hope to see you again soon!");
        println(Message.LINE.toString());
    }
}
