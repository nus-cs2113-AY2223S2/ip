package duke.command;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;

public class Duke {
    static ArrayList<Task> tasks = new ArrayList<>();//Create an arraylist object to store commands
    static int size;

    public static void add_todo(String line){
        try {
            String command = line.substring(5);
            Todo t = new Todo(command);
            tasks.add(t);
            System.out.println("Got it. I've added this task: ");
            System.out.println(t);
            size = tasks.size();
            print_list_count();
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(":(OOPS! Please pay attention to the input format.");
        }
    }

    public static void add_deadline(String line){
        String command = line.substring(9);
        try {
            String task_content = command.split("/")[0];
            String by_time = command.split("/")[1].substring(3);
            Deadline d = new Deadline(task_content, by_time);
            tasks.add(d);
            System.out.println("Got it. I've added this task: ");
            System.out.println(d);
            size = tasks.size();
            print_list_count();
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(":(OOPS! Please pay attention to the input format.");
        }
    }

    public static void add_event(String line){
        try {
            String command = line.substring(6);
            String task_content = command.split("/")[0];
            String from_time = command.split("/")[1].substring(5);
            String to_time = command.split("/")[2].substring(3);
            Event e = new Event(task_content, from_time, to_time);
            tasks.add(e);
            System.out.println("Got it. I've added this task: ");
            System.out.println(e);
            size = tasks.size();
            print_list_count();
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(":(OOPS! Please pay attention to the input format.");
        }
    }

    public static void mark_done(String line){
        try {
            int index = Integer.parseInt(line.split(" ")[1]) - 1;
            tasks.get(index).setDone(true);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks.get(index).toString());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("OOPS! The maximum number of task index is " + tasks.size());
        }

    }

    public static void unmark(String line){
        try {
            int index = Integer.parseInt(line.split(" ")[1]) - 1;
            tasks.get(index).setDone(false);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(tasks.get(index).toString());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("OOPS! The maximum number of task index is " + tasks.size());
        }
    }

    public static void delete(String line) {
        int index = Integer.parseInt(line.split(" ")[1])-1;
        System.out.println("Noted. I've removed this task: ");
        System.out.println(tasks.get(index));
        tasks.remove(index);
        print_list_count();
    }

    public static void list_tasks(){
        size = tasks.size();
        System.out.println("Here are the tasks in your list: ");
        if(size > 0) {
            for (int i = 0; i < size; i++) {
                System.out.println(i + 1 + ". " + tasks.get(i).toString());
            }
        } else {
            System.out.println("Hooray! Your task list is empty.");
        }
    }

    public static void print_list_count() {
        size = tasks.size();
        if (size == 1) {
            System.out.println("Now you have 1 task in the list.");
        }
        else if (size == 0) {
            System.out.println("Now you have 0 task in the list.");
        }
        else {
            System.out.println("Now you have "+ size+" tasks in the list.");
        }
    }

    public static void greeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public static void FileReading(String filePath) {
        File f = new File(filePath);
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] text = line.split("\\|");
                if (line.startsWith("T")) {
                    Todo todo = new Todo(text[2]);
                    todo.setDone(!text[1].equals(" "));
                    tasks.add(todo);
                } else if (line.startsWith("D")) {
                    Deadline deadline = new Deadline(text[2], text[3]);
                    deadline.setDone(!text[1].equals(" "));
                    tasks.add(deadline);
                } else if (line.startsWith("E")) {
                    Event event = new Event(text[2], text[3], text[4]);
                    event.setDone(!text[1].equals(" "));
                    tasks.add(event);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            throw new RuntimeException(e);
        } catch (NoSuchElementException e) {
            System.out.println("Sorry no line found. ");
        }
    }

    public static void FileWriting(String filePath) {
        //File f = new File(filePath);
        try {
            FileWriter fw1 = new FileWriter(filePath);
            fw1.write("");
            FileWriter fw2 = new FileWriter(filePath, true);
            for (Task t: tasks) {
                if (t instanceof duke.task.Todo) {
                    Todo todo = (Todo) t;
                    String text = "T|" + todo.getStatusIcon() + "|" + todo.getDescription() + "\n";
                    fw2.write(text);
                } else if (t instanceof duke.task.Deadline) {
                    Deadline deadline = (Deadline) t;
                    String text = "D|" + deadline.getStatusIcon() + "|" + deadline.getDescription() + "|" + deadline.getBy() + "\n";
                    fw2.write(text);
                } else if (t instanceof duke.task.Event) {
                    Event event = (Event) t;
                    String text = "E|" + event.getStatusIcon() + "|" + event.getDescription() + "|" + event.getFrom() + "|" + event.getTo() + "\n";
                    fw2.write(text);
                }
            }
            fw1.close();
            fw2.close();
            System.out.println("Your task list is saved!");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) throws IOException {

        greeting();

        File duke = new File("./duke.txt");
        if (duke.exists()) {
            System.out.println("Task list already exists.");
            FileReading("./duke.txt");
            list_tasks();
        } else {
            System.out.println("Your new task list is created!");
        }
        System.out.println("Please input your command: ");


        while(true){
            Scanner in = new Scanner(System.in);
            String line = in.nextLine();
            if(line.equals("bye")){
                FileWriting("./duke.txt");
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if(line.startsWith("mark")){
                mark_done(line);
            }
            else if(line.startsWith("unmark")){
                unmark(line);
            }
            else if(line.equals("list")){
                list_tasks();
            }
            else if(line.startsWith("deadline")){
                add_deadline(line);
            }
            else if(line.startsWith("todo")){
                add_todo(line);
            }
            else if(line.startsWith("event")){
                add_event(line);
            }
            else if(line.startsWith("delete")) {
                delete(line);
            }
            else {
                System.out.println(":( OOPS!!! Please choose from the command: todo, deadline, event, list, mark, unmark, bye.");
            }
        }
    }

}
