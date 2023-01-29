import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static ArrayList<Task> tasks = new ArrayList<Task>();//Create an arraylist object to store commands
    static int size = tasks.size();

    public static void add_todo(String line){
        String command = line.substring(5);
        Todo t = new Todo(command);
        tasks.add(t);
        System.out.println("Got it. I've added this task: ");
        System.out.println(t);
        size = tasks.size();
        print_list_count();
    }

    public static void add_deadline(String line){
        String command = line.substring(9);
        String task_content = command.split("/")[0];
        String by_time = command.split("/")[1].substring(3);
        Deadline d = new Deadline(task_content, by_time);
        tasks.add(d);
        System.out.println("Got it. I've added this task: ");
        System.out.println(d);
        size = tasks.size();
        print_list_count();
    }

    public static void add_event(String line){
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
    }

    public static void mark_done(String line){
        int index = Integer.parseInt(line.split(" ")[1])-1;
        tasks.get(index).setDone(true);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(index).toString());
    }

    public static void unmark(String line){
        int index = Integer.parseInt(line.split(" ")[1])-1;
        tasks.get(index).setDone(false);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(index).toString());
    }

    public static void list_tasks(String line){
        System.out.println("Here are the tasks in your list: ");
        for(int i = 0; i<size; i++){
            System.out.println(String.valueOf(i+1) + ". " + tasks.get(i).toString());
        }
    }

    public static void print_list_count(){
        if(size == 1) {
            System.out.println("Now you have 1 task in the list.");
        }
        else{
            System.out.println("Now you have "+ String.valueOf(size)+" tasks in the list.");
        }
    }

    public static void main(String[] args) {
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



        while(true){
            String line = "";
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
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
        }
    }

}
