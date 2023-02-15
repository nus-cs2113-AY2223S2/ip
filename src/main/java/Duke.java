import duke.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static duke.FileOperations.*;

public class Duke {
    public static void greetUser() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        printLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLine();
    }

    public static void sayBye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon! :)");
        printLine();
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    //load the data from the hard disk when Duke starts up
    //write the existing data into the current list of tasks
    public static void loadData() throws FileNotFoundException {
        String filePath = "data/duke.txt";
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String str = s.nextLine();
            convertFromData(str);
        }
    }

    //save the tasks in the hard disk automatically whenever the task list changes
    public static void saveData() {
        String filePath = "data/duke.txt";
        try {
            writeToFile(filePath, convertToData(Task.tasks.get(0)) + System.lineSeparator());
            for (int i = 1; i < Task.tasks.size(); i++) {
                appendToFile(filePath, convertToData(Task.tasks.get(i)) + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    //convert data from text file into Task list format to Load Data
    public static void convertFromData(String str) {
        String[] task = str.split("\\|");
        //for (String s : task) { System.out.println(s); }
        Character c = task[0].charAt(0); //check if task is a To-do, Deadline or Event
        Character marked = task[1].charAt(1); //check if task is marked done or not
        if (c.equals('T')) {
            String description = task[2].substring(1);
            //System.out.println(description);
            Task t = new Todo(description);
            Task.tasks.add(Task.taskCount, t);
            Task.taskCount++;
            if (marked.equals('1')) {
                t.markAsDone();
            }
        } else if (c.equals('D')) {
            String description = task[2].substring(1,(task[2].length()-1));
            //System.out.println(description);
            String deadline = task[3].substring(1);
            //System.out.println(deadline);
            Task t = new Deadline(description, deadline);
            Task.tasks.add(Task.taskCount, t);
            Task.taskCount++;
            if (marked.equals('1')) {
                t.markAsDone();
            }
        } else {
            String description = task[2].substring(1,(task[2].length()-1));
            //System.out.println(description);
            String event = task[3].substring(1);
            String[] duration = event.split("-");
            //System.out.println(event);
            Task t = new Event(description, duration[0], duration[1]);
            Task.tasks.add(Task.taskCount, t);
            Task.taskCount++;
            if (marked.equals('1')) {
                t.markAsDone();
            }
        }
    }

    //convert tasks in arraylist to save in text file for Save Data
    public static String convertToData(Task t) {
        //String str = String.valueOf(t);
        String str = ""+t;
        //System.out.println(str);
        String data;
        Character c = str.charAt(1); //check if task is a To-do, Deadline or Event
        Character marked = str.charAt(4); //check if task is marked done or not
        String task =  str.substring(6);
        if (c.equals('T')) {
            if (marked.equals('X')) {
                data = ("T | 1 |" + task);
            } else {
                data = ("T | 0 |" + task);
            }
        } else if (c.equals('D')) {
            int openBracket = task.indexOf("(");
            int closeBracket = task.indexOf(")");
            String description = task.substring(0,openBracket);
            String by = task.substring(openBracket+1, closeBracket);
            String[] deadline = by.split("by: ");
            if (marked.equals('X')) {
                data = ("D | 1 |" + description + " | " + deadline[1]);
            } else {
                data = ("D | 0 |" + description + " | " + deadline[1]);
            }
        } else {
            int openBracket = task.indexOf("(");
            int closeBracket = task.indexOf(")");
            String description = task.substring(0,openBracket);
            String duration = task.substring(openBracket+6, closeBracket);
            String[] eventTime = duration.split("to: ");
            if (marked.equals('X')) {
                data = ("E | 1 |" + description + " | " + eventTime[0].substring(1) + "-" + eventTime[1]);
            } else {
                data = ("E | 0 |" + description + " | " + eventTime[0].substring(1) + "-" + eventTime[1]);
            }
        }
        return data;
    }

    //check if task description is blank (for to-do)
    public static boolean isDescriptionBlank(String description) {
        boolean isBlank = true;
        for (int i = 0; i < description.length(); i++) {
            char c = description.charAt(i);
            isBlank = Character.isWhitespace(c); //true if character is whitespace
        }
        return isBlank;
    }

    //list tasks
    public static void listTasks(){
        printLine();
        System.out.println("Here are the tasks in your list:");
        int i = 1;
        for (Task t : Task.tasks) {
            System.out.println(i + "." + t);
            i++;
        }
        printLine();
    }

    //mark task as done
    public static void markTask(String[] input){
        try {
            int index = Integer.parseInt(input[1]);
            if (index < 1 || index > Task.taskCount) {
                throw new DukeException();
            }
            Task t = duke.Task.tasks.get(index - 1);
            t.markAsDone();
            saveData();
            printLine();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(t);
            printLine();
        } catch (NumberFormatException e) {
            System.out.println("☹ OOPS!!! Task number is blank. Please try again.");
            printLine();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! Task number is missing. Please try again. ");
            printLine();
        } catch (DukeException e) {
            System.out.println("☹ OOPS!!! You entered an invalid task number. Please try again.");
            printLine();
        }
    }

    //mark task as not done
    public static void unmarkTask(String[] input){
        try {
            int index = Integer.parseInt(input[1]);
            if (index < 1 || index > Task.taskCount) {
                throw new DukeException();
            }
            Task t = duke.Task.tasks.get(index - 1);
            t.markNotDone();
            saveData();
            printLine();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(t);
            printLine();
        } catch (NumberFormatException e) {
            System.out.println("☹ OOPS!!! Task number is blank. Please try again.");
            printLine();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! Task number is missing. Please try again. ");
            printLine();
        } catch (DukeException e) {
            System.out.println("☹ OOPS!!! You entered an invalid task number. Please try again.");
            printLine();
        }
    }

    //delete task
    public static void deleteTask(String[] input){
        try {
            int index = Integer.parseInt(input[1]);
            if (index < 1 || index > Task.taskCount) {
                throw new DukeException();
            }
            Task t = duke.Task.tasks.get(index - 1);
            Task.tasks.remove(index - 1);
            Task.taskCount--;
            //saveData();
            printLine();
            System.out.println("Noted. I've removed this task:");
            System.out.println(t);
            System.out.println("Now you have " + Task.taskCount + " tasks in the list.");
            printLine();
        } catch (NumberFormatException e) {
            System.out.println("☹ OOPS!!! Task number is blank. Please try again.");
            printLine();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! Task number is missing. Please try again. ");
            printLine();
        } catch (DukeException e) {
            System.out.println("☹ OOPS!!! You entered an invalid task number. Please try again.");
            printLine();
        }
    }

    //to-do
    public static void addTodo(String[] input){
        try {
            if (isDescriptionBlank(input[1])) {
                throw new DukeException();
            }
            Task t = new Todo(input[1]);
            Task.tasks.add(Task.taskCount, t);
            Task.taskCount++;
            saveData();
            printLine();
            System.out.println("Got it. I've added this task:");
            System.out.println(t);
            System.out.println("Now you have " + Task.taskCount + " tasks in the list.");
            printLine();
        } catch (IndexOutOfBoundsException e) {
            printLine();
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
            System.out.println("Please follow this format: todo [description].");
            printLine();
        } catch (DukeException e) {
            printLine();
            System.out.println("☹ OOPS!!! The description of a todo cannot be blank.");
            System.out.println("Please follow this format: todo [description].");
            printLine();
        }
    }

    //deadline
    public static void addDeadline(String[] input){
        try {
            if (isDescriptionBlank(input[1])) {
                throw new DukeException();
            }
            String[] doBy = input[1].split("/by ", 2);
            Task t = new Deadline(doBy[0], doBy[1]);
            Task.tasks.add(Task.taskCount, t);
            Task.taskCount++;
            saveData();
            printLine();
            System.out.println("Got it. I've added this task:");
            System.out.println(t);
            System.out.println("Now you have " + Task.taskCount + " tasks in the list.");
            printLine();
        } catch (IndexOutOfBoundsException e) {
            printLine();
            System.out.println("☹ OOPS!!! The description of a deadline cannot be empty or incomplete.");
            System.out.println("Please follow this format: deadline [description] /by [due date/time]");
            printLine();
        } catch (DukeException e) {
            printLine();
            System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
            System.out.println("Please follow this format: deadline [description] /by [due date/time]");
            printLine();
        }
    }

    //event
    public static void addEvent(String[] input){
        try {
            if (isDescriptionBlank(input[1])) {
                throw new DukeException();
            }
            String[] start = input[1].split("/from ", 2);
            String[] end = start[1].split("/to ", 2);
            Task t = new Event(start[0], end[0], end[1] );
            Task.tasks.add(Task.taskCount, t);
            Task.taskCount++;
            saveData();
            printLine();
            System.out.println("Got it. I've added this task:");
            System.out.println(t);
            System.out.println("Now you have " + Task.taskCount + " tasks in the list.");
            printLine();
        } catch (IndexOutOfBoundsException e) {
            printLine();
            System.out.println("☹ OOPS!!! The description of an event cannot be empty or incomplete.");
            System.out.println("Please follow this format: event [description] /from [start] /to [end]");
            printLine();
        } catch (DukeException e) {
            printLine();
            System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
            System.out.println("Please follow this format: event [description] /from [start] /to [end]");
            printLine();
        }
    }

    public static void handleCommands() {
        Scanner in = new Scanner(System.in);
        String command = in.nextLine();

        while (!(command.equals("bye"))) {
            try {
                String[] input = command.split(" ", 2); //only check the first word
                String firstWord = input[0];
                switch (firstWord) {
                    //list tasks
                    case "list" :
                        listTasks();
                        break;
                    //mark task
                    case "mark":
                        markTask(input);
                        break;
                    //un-mark task
                    case "unmark":
                        unmarkTask(input);
                        break;
                    //delete task
                    case "delete":
                        deleteTask(input);
                        break;
                    //to-do
                    case "todo":
                        addTodo(input);
                        break;
                    //deadline
                    case "deadline":
                        addDeadline(input);
                        break;
                    //event
                    case "event":
                        addEvent(input);
                        break;
                    //unidentified command
                    default:
                        throw new DukeException();
                }
            } catch (DukeException e) {
                printLine();
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means.");
                printLine();
            }
            command = in.nextLine(); //read next command
        }
    }

    public static void main(String[] args) throws IOException {
        greetUser();
        try {
            loadData();
        } catch (IOException e) {
            System.out.println("☹ OOPS!!! The file does not exist. Creating a new file.");
            String pathName = "data";
            String filePath = "data/duke.txt";
            createFile(filePath, pathName);
        }
        handleCommands();
        saveData();
        sayBye();
    }
}

