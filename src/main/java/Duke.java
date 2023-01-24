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

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        ArrayList<Task> todo = new ArrayList<Task>();//Create an arraylist object to store commands

        while(true){
            String line = "";
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            if(line.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if(line.split(" ")[0].equals("mark")){
                int index = Integer.parseInt(line.split(" ")[1])-1;
                todo.get(index).setDone(true);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[X] "+todo.get(index).getDescription());
            }
            else if(line.split(" ")[0].equals("unmark")){
                int index = Integer.parseInt(line.split(" ")[1])-1;
                todo.get(index).setDone(false);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("[ ] "+todo.get(index).getDescription());
            }
            else if(line.equals("list")){
                System.out.println("Here are the tasks in your list: ");
                for(int i = 0; i<todo.size(); i++){
                    String status = todo.get(i).getStatusIcon();
                    System.out.println(String.valueOf(i+1) + ". [" + status+"] " + todo.get(i).getDescription());
                }
            }
            else{
                Task t = new Task(line);
                todo.add(t);
                System.out.println("added: "+line);
            }
        }
    }

}
