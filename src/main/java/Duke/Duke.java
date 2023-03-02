package Duke;

import Duke.Exception.DukeException;
import Duke.Exception.MarkIndexException;
import Duke.Exception.NoTaskException;
import Duke.Exception.TaskInfoException;
import Duke.commands.Deadline;
import Duke.commands.Event;
import Duke.commands.Task;
import Duke.commands.Todo;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
//import java.io.FileInputStream;
//import java.io.ObjectInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * A project template for a greenfield Java project.
 *
 * @author Zeng Ziqiu
 * @version 2.0
 * @since 2023-01-27
 */
public class Duke {
    //    private static int taskCount = 0;
    private static ArrayList<Task> tasks = new ArrayList<>();

    private static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");

    private static final String dividingLine = "\n————————————————————————————————————————————————————————\n";

    /**
     * The method is to print the task list onto the screen,
     * to show the user what tasks are on the list.
     */
    public static void printList() {
        System.out.println(dividingLine + "Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + (tasks.get(i)).toString());
        }
        System.out.println("Now you have " + tasks.size() + " tasks in the list." + dividingLine);
    }

    public static void addTask(Task task) {
        tasks.add(task);
        System.out.println(dividingLine);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(dividingLine);
    }

    public static void processTask(String line) throws DukeException, TaskInfoException, MarkIndexException, NoTaskException {
        if (line.equals("list")) {
            printList();
        } else if (line.contains("mark") && !line.contains("unmark")) {
            if (tasks.size() == 0) {
                throw new NoTaskException();
            }
            if (line.length() == 4) {
                throw new MarkIndexException();
            }
            int taskNum = Integer.parseInt(line.substring(5)) - 1;
            tasks.get(taskNum).markAsDone();
            System.out.println(dividingLine + "Nice! I've marked this task as done:");
            System.out.println(tasks.get(taskNum).toString() + dividingLine);
        } else if (line.contains("delete")) {
            if (line.length() == 6) {
                throw new MarkIndexException();
            }
            int taskNum = Integer.parseInt(line.substring((7))) - 1;
            System.out.println(dividingLine + "Noted. I've removed this task:");
            System.out.println(tasks.get(taskNum));
            System.out.println("Now you have " + (tasks.size() - 1) + " tasks in the list.");
            System.out.println(dividingLine);
            tasks.remove(taskNum);
        } else if (line.contains("unmark")) {
            if (tasks.size() == 0) {
                throw new NoTaskException();
            }
            if (line.length() == 6) {
                throw new MarkIndexException();
            }
            int unmarkNum = Integer.parseInt(line.substring(7)) - 1;
            tasks.get(unmarkNum).unmarkAsDone();
            System.out.println(dividingLine + "OK, I've marked this task as not done yet:");
            System.out.println(tasks.get(unmarkNum).toString() + dividingLine);
        } else if (line.contains("todo")) {
            addTask(new Todo(line.substring(5)));
        } else if (line.contains("deadline")) {
            line = line.replace("deadline ", "");
            if (line.substring(line.indexOf("/by ") + 4).length() == 0) {
                throw new TaskInfoException();
            }
            String deadlineTimeStr = line.split("/by ")[1];
            LocalDateTime deadlineTime = LocalDateTime.parse(deadlineTimeStr, inputFormatter);
            String deadlineFormatted = deadlineTime.format(outputFormatter);
            addTask(new Deadline(line.substring(0, line.indexOf(" /")),
                    deadlineFormatted));
        } else if (line.contains("event")) {
            line = line.replace("event ", "");
            if (line.substring(line.indexOf("/to ") + 4).length() == 0) {
                throw new TaskInfoException();
            }
            String eventFromStr = line.split("/from ")[1].split(" /to ")[0];
            LocalDateTime eventFrom = LocalDateTime.parse(eventFromStr, inputFormatter);
            String eventFromFormatted = eventFrom.format(outputFormatter);
            String eventToStr = line.split("/from ")[1].split(" /to ")[1];
            LocalDateTime eventToTime = LocalDateTime.parse(eventToStr, inputFormatter);
            String eventToFormatted = eventToTime.format(outputFormatter);

            addTask(new Event(line.substring(0, line.indexOf(" /")), eventFromFormatted, eventToFormatted));
        } else if (line.contains("find")) {
            line = line.replace("find ", "");
            String keyword = line;
            keywordSearch(keyword);
        } else {
//            tasks[taskCount] = new Task(line);
            throw new DukeException();
        }
    }

    /**
     * The method is to find and print the tasks that contains the keyword that the user searches onto the screen.
     *
     * @param keyword The keyword the user intends to find in the task list
     */
    public static void keywordSearch(String keyword) {
        String path = "data/tasks.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            int count = 0;
            System.out.println(dividingLine);
            System.out.println("Here are the matching tasks in your list:");
            while ((line = br.readLine()) != null) {
                if (line.contains(keyword)) {
                    count++;
                    System.out.println(count + ". " + line);
                }
            }
            if (count == 0) {
                System.out.println("Sorry, there is no matching task in your list.");
            }
            System.out.println(dividingLine);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * The method is to process user's commands and write user's tasks to a txt file.
     * If certain exception is detected, the method print the corresponding message onto the screen,
     * to notify the user that there may be some issues with his input.
     *
     * @param line The command input by user
     * @param path The path of the text file that stores user's task list
     */
    public static void printTask(String line, String path) {
        try {
            processTask(line);
            writeToFile(tasks, path);
        } catch (IndexOutOfBoundsException e2) {
            System.out.println(dividingLine + "☹ OOPS!!! The description of a " + line + " cannot be empty."
                    + dividingLine);
        } catch (TaskInfoException e) {
            System.out.println(dividingLine + "☹ OOPS!!! Missing information." + dividingLine);
        } catch (DukeException e) {
            System.out.println(dividingLine + "☹ OOPS!!! I'm sorry, but I don't know what that means :-("
                    + dividingLine);
        } catch (MarkIndexException e4) {
            System.out.println(dividingLine + "☹ OOPS!!! Task index is unspecified." + dividingLine);
        } catch (NoTaskException e) {
            System.out.println(dividingLine + "☹ OOPS!!! No task in the list." + dividingLine);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private static void writeToFile(ArrayList<Task> tasks, String path) throws IOException {
        FileWriter fl = new FileWriter(path);
        for (Task task : tasks) {
            fl.write(task.toString());
            fl.write(System.lineSeparator());
        }
        fl.close();
    }

    /**
     * The method is to create a file to store user's tasks.
     * If the file does not exist, then it creates one at the "data/tasks.txt".
     *
     * @param file The file that stores user's tasks
     */

    private static void createFile(File file) {
        if (!file.exists()) {
            System.out.println("File not exists, create it ...");
            //getParentFile() 获取上级目录（包含文件名时无法直接创建目录的）
            if (!file.getParentFile().exists()) {
                System.out.println("Directory not exists, create it ...");
                //创建上级目录
                file.getParentFile().mkdirs();
            }
            try {
                //在上级目录里创建文件
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        String path = "data/tasks.txt";
        createFile(new File(path));
//        loadTasks();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke" + System.lineSeparator() + "What can I do for you?" + dividingLine);

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            printTask(line, path);
            line = in.nextLine();
        }
        System.out.println(dividingLine + "Bye. Hope to see you again soon!" + dividingLine);
    }
}

