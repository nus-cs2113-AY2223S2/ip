package tusky;

import tusky.io.KeyNotFoundException;
import tusky.io.Messages;
import tusky.io.Parser;
import tusky.repository.FileManager;

import tusky.tasks.ToDo;
import tusky.tasks.Task;
import tusky.tasks.TaskType;
import tusky.tasks.Event;
import tusky.tasks.Deadline;
import tusky.tasks.EmptyDescriptionException;

import java.nio.file.NoSuchFileException;
import java.util.Scanner;
import java.util.ArrayList;

import java.io.FileNotFoundException;

public class Tusky {
    static ArrayList<Task> tasks = new ArrayList<>();

    public static void addTask (Task task) {
        tasks.add(task);
        println(Messages.LINE.toString());
        printf(Messages.TASK_ADDED.toString());
        printf("   %s\n", task.getDetailedString());
        printf(Messages.TASK_COUNT.toString(), tasks.size());
        println(Messages.LINE.toString());
        FileManager.writeFile(tasks);
    }

    public static void listTasks () {
        println(Messages.LINE.toString());
        println(Messages.TASK_LIST.toString());
        for (int i = 1; i <= tasks.size(); i++) {
            printf(" %d.%s\n", i, tasks.get(i-1).getDetailedString());
        }
        println(Messages.LINE.toString());
    }

    public static void markTask (int index) {
        println(Messages.LINE.toString());
        if (index >= tasks.size() || index < 0) {
            println(Messages.ERR_INVALID_INDEX.toString());
        } else {
            tasks.get(index).setDone(true);
            println(Messages.TASK_MARKED.toString());
            printf("   %s\n", tasks.get(index).getDetailedString());
            FileManager.writeFile(tasks);
        }
        println(Messages.LINE.toString());
    }

    public static void unmarkTask (int index) {
        println(Messages.LINE.toString());
        if (index >= tasks.size() || index < 0) {
            println(Messages.ERR_INVALID_INDEX.toString());
        } else {
            tasks.get(index).setDone(false);
            println(Messages.TASK_UNMARKED.toString());
            printf("   %s\n", tasks.get(index).getDetailedString());
            FileManager.writeFile(tasks);
        }
        println(Messages.LINE.toString());
    }

    public static void deleteTask (int index) {
        println(Messages.LINE.toString());
        if (index >= tasks.size() || index < 0) {
            println(Messages.ERR_INVALID_INDEX.toString());
        } else {
            Task t = tasks.get(index);
            tasks.remove(index);
            println(Messages.TASK_DELETED.toString());
            printf("   %s\n", t.getDetailedString());
            printf(Messages.TASK_COUNT.toString(), tasks.size());
            FileManager.writeFile(tasks);
        }
        println(Messages.LINE.toString());
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
                    println(Messages.LINE.toString());
                    printf(Messages.ERR_INVALID_PARAMETERS.toString());
                    println(Messages.LINE.toString());
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
                        addTask(new ToDo("false",parser.getBody()));
                    } catch (EmptyDescriptionException e){
                        println(Messages.LINE.toString());
                        printf(Messages.ERR_EMPTY_TASK_DESCRIPTION.toString(), TaskType.TODO);
                        println(Messages.LINE.toString());
                    }
                    break;
                case "event":
                    try{
                        addTask(new Event("false",parser.getBody(), parser.get("from"), parser.get("to")));
                    } catch (EmptyDescriptionException e){
                        println(Messages.LINE.toString());
                        printf(Messages.ERR_EMPTY_TASK_DESCRIPTION.toString(), TaskType.EVENT);
                        println(Messages.LINE.toString());
                    }
                    break;
                case "deadline":
                    try{
                        addTask(new Deadline("false",parser.getBody(), parser.get("by")));
                    } catch (EmptyDescriptionException e){
                        println(Messages.LINE.toString());
                        printf(Messages.ERR_EMPTY_TASK_DESCRIPTION.toString(), TaskType.DEADLINE);
                        println(Messages.LINE.toString());
                    }
                    break;
                case "delete":
                    index = Integer.parseInt(parser.getBody()) - 1;
                    deleteTask(index);
                    break;
                default:
                    println(Messages.LINE.toString());
                    println(Messages.ERR_UNKNOWN_COMMAND.toString());
                    println(Messages.LINE.toString());
                    break;
                }
            } catch (KeyNotFoundException e){
                println(Messages.LINE.toString());
                println(e.getMessage());
                println(Messages.LINE.toString());
            } catch (Exception e) {
                println(Messages.LINE.toString());
                println(Messages.ERR_UNKNOWN_EXCEPTION.toString() + e);
                println(Messages.LINE.toString());
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

        println(Messages.LINE.toString());
        println(Messages.WELCOME.toString());

        try{
            tasks = FileManager.readFile();
            if(tasks != null){
                println(Messages.FILE_LOADED.toString());
            } else {
                println(Messages.FILE_CREATED.toString());
                tasks = new ArrayList<>();
                FileManager.writeFile(tasks);
            }
        } catch (FileNotFoundException | NoSuchFileException e){
            println(Messages.FILE_CREATED.toString());
            tasks = new ArrayList<>();
            FileManager.writeFile(tasks);

        }
        println(Messages.LINE.toString());

        beginInputLoop();

        println(Messages.LINE.toString());
        println(Messages.GOODBYE.toString());
        println(Messages.LINE.toString());
    }
}
