package duke.command;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static ArrayList<Task> tasks = new ArrayList<Task>();//Create an arraylist object to store commands
    static int size = tasks.size();

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
        } catch (ArrayIndexOutOfBoundsException e) {
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
        } catch (ArrayIndexOutOfBoundsException e) {
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

    public static void list_tasks(String line){
        System.out.println("Here are the tasks in your list: ");
        for(int i = 0; i<size; i++){
            System.out.println(String.valueOf(i+1) + ". " + tasks.get(i).toString());
        }
    }

    public static void print_list_count() {
        if(size == 1) {
            System.out.println("Now you have 1 task in the list.");
        }
        else{
            System.out.println("Now you have "+ String.valueOf(size)+" tasks in the list.");
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

    public static void main(String[] args) {

        greeting();

        while(true){
            Scanner in = new Scanner(System.in);
            String line = in.nextLine();
            if(line.equals("bye")){
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
                list_tasks(line);
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
            else {
                System.out.println(":( OOPS!!! Please choose from the command: todo, deadline, event, list, mark, unmark, bye.");
            }
        }
    }

}
