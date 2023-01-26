import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String line = "___________________________________________________";
        System.out.println(line);
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        System.out.println(line);
        Scanner in = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<Task>();

//        Task[] tasks = new Task[100];
        String input;
        while(in.hasNext()){
            input = in.nextLine();
            if(input.equals("bye")){
                break;
            }
            if(input.equals("list")){
                System.out.println(line);
                for(int i = 0;i<tasks.size();i++){
                    System.out.print(i+1+".");
                    System.out.println(tasks.get(i).toString());
                }
            }else if(input.contains("deadline")){
                String [] indexArr = input.split("/",2);
                String nameAndCommand = indexArr[0];
                String byDate = indexArr[1];
                String description = nameAndCommand.substring(9).trim();
                String by = byDate.substring(3).trim();
                tasks.add(new Deadline(description,by));
                //print out necessary statements
                System.out.println(line);
                int lastIndexOfTasks = tasks.size()-1;
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks.get(lastIndexOfTasks).toString());
                System.out.println("Now you have "+tasks.size()+" tasks in the list.");
            }else if(input.contains("event")){
                String [] indexArr = input.split("/",3);
                String nameAndCommand = indexArr[0];
                String fromDate = indexArr[1];
                String toDate = indexArr[2];
                String description = nameAndCommand.substring(6).trim();
                String from = fromDate.substring(5).trim();
                String to = toDate.substring(3).trim();
                tasks.add(new Event(description,from,to));
                //print out necessary statements
                System.out.println(line);
                int lastIndexOfTasks = tasks.size()-1;
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks.get(lastIndexOfTasks).toString());
                System.out.println("Now you have "+tasks.size()+" tasks in the list.");
            } else if(input.contains("unmark")){
                System.out.println(line);
                System.out.println("OK, I've marked this task as not done yet:");
                Integer index;
                //temp placeholder to organise inputs
                String [] IndexArr = input.split(" ",2);
                index = Integer.parseInt(IndexArr[1])-1;
                tasks.get(index).isDone=false;
                System.out.println(tasks.get(index).toString());
            }else if(input.contains("mark")){
                Integer index;
                String [] IndexArr = input.split(" ",2);
                System.out.println(line);
                System.out.println("Nice! I've marked this task as done:");
                index = Integer.parseInt(IndexArr[1])-1;
                tasks.get(index).isDone=true;
                System.out.println(tasks.get(index).toString());
            }else if(input.contains("todo")){
                String description = input.substring(5).trim();
                tasks.add(new Todo(description));
                System.out.println(line);
                int lastIndexOfTasks = tasks.size()-1;
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks.get(lastIndexOfTasks).toString());
                System.out.println("Now you have "+tasks.size()+" tasks in the list.");
            }else {
                tasks.add(new Task(input, false));
                System.out.println("Added: "+input);
            }
            System.out.println(line);
        }
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
