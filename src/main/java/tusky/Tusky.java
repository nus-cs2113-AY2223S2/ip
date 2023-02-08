package tusky;

import tusky.io.KeyNotFoundException;
import tusky.io.Message;
import tusky.io.Parser;

import tusky.tasks.ToDo;
import tusky.tasks.Task;
import tusky.tasks.TaskType;
import tusky.tasks.Event;
import tusky.tasks.Deadline;
import tusky.tasks.EmptyDescriptionException;

import java.util.Scanner;

public class Tusky {

    static final int MAX_TASKS = 100;

    static Task[] tasks = new Task[MAX_TASKS];
    static int taskCount = 0;

    public static void addTask (Task task) {
        if (taskCount < MAX_TASKS) {
            tasks[taskCount] = task;
            taskCount++;
            println(Message.LINE.toString());
            printf(Message.TASK_ADDED.toString());
            printf("   %s\n", task.getDetailedString());
            printf(Message.TASK_COUNT.toString(), taskCount);
            println(Message.LINE.toString());
        } else {
            println(Message.LINE.toString());
            println(Message.ERR_MAX_TASKS_EXCEEDED.toString());
            println(Message.LINE.toString());
        }
    }

    public static void listTasks () {
        println(Message.LINE.toString());
        println(Message.TASK_LIST.toString());
        for (int i = 1; i <= taskCount; i++) {
            printf(" %d.%s\n", i, tasks[i - 1].getDetailedString());
        }
        println(Message.LINE.toString());
    }

    public static void markTask (int index) {
        println(Message.LINE.toString());
        if (index >= taskCount || index < 0) {
            println(Message.ERR_INVALID_INDEX.toString());
        } else {
            tasks[index].setDone(true);
            println(Message.TASK_MARKED.toString());
            printf("   %s\n", tasks[index].getDetailedString());
        }
        println(Message.LINE.toString());
    }

    public static void unmarkTask (int index) {
        println(Message.LINE.toString());
        if (index >= taskCount || index < 0) {
            println(Message.ERR_INVALID_INDEX.toString());
        } else {
            tasks[index].setDone(false);
            println(Message.TASK_UNMARKED.toString());
            printf("   %s\n", tasks[index].getDetailedString());
        }
        println(Message.LINE.toString());
    }

    public static void beginInputLoop () {
        Scanner in = new Scanner(System.in);
        Parser parser = new Parser();
        while (true) {
            try {
                String input = in.nextLine();
                try{
                    parser.parse(input);
                } catch (ArrayIndexOutOfBoundsException e){
                    println(Message.LINE.toString());
                    printf(Message.ERR_INVALID_PARAMETERS.toString());
                    println(Message.LINE.toString());
                    continue;
                }

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
                    try{
                        addTask(new ToDo(parser.getBody()));
                    } catch (EmptyDescriptionException e){
                        println(Message.LINE.toString());
                        printf(Message.ERR_EMPTY_TASK_DESCRIPTION.toString(), TaskType.TODO);
                        println(Message.LINE.toString());
                    }
                    break;
                case "event":
                    try{
                        addTask(new Event(parser.getBody(), parser.get("from"), parser.get("to")));
                    } catch (EmptyDescriptionException e){
                        println(Message.LINE.toString());
                        printf(Message.ERR_EMPTY_TASK_DESCRIPTION.toString(), TaskType.EVENT);
                        println(Message.LINE.toString());
                    }
                    break;
                case "deadline":
                    try{
                        addTask(new Deadline(parser.getBody(), parser.get("by")));
                    } catch (EmptyDescriptionException e){
                        println(Message.LINE.toString());
                        printf(Message.ERR_EMPTY_TASK_DESCRIPTION.toString(), TaskType.DEADLINE);
                        println(Message.LINE.toString());
                    }
                    break;
                default:
                    println(Message.LINE.toString());
                    println(Message.ERR_UNKNOWN_COMMAND.toString());
                    println(Message.LINE.toString());
                    break;
                }
            } catch (KeyNotFoundException e){
                println(Message.LINE.toString());
                println(e.getMessage());
                println(Message.LINE.toString());
            } catch (Exception e) {
                println(Message.LINE.toString());
                println(Message.ERR_UNKNOWN_EXCEPTION.toString() + e);
                println(Message.LINE.toString());
                throw e;
            }

        }

    }

    // println adds indentation before each println output string
    public static void println (String x) {
        System.out.print("    ");
        System.out.println(x);
    }

    // printf adds indentation before each printf output string
    public static void printf (String format, Object... args) {
        System.out.print("    ");
        System.out.printf(format, args);
    }

    public static void main (String[] args) {

        println(Message.LINE.toString());
        println(Message.WELCOME.toString());
        println(Message.LINE.toString());

        beginInputLoop();

        println(Message.LINE.toString());
        println(Message.GOODBYE.toString());
        println(Message.LINE.toString());
    }
}
