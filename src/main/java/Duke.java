import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);
        Scanner in = new Scanner(System.in);
        
        String line = "____________________________________________________________";

        System.out.println(line);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println(line);
        
        Task[] tasks = new Task[100];
        int numTasks = 0;

        while(true){
            System.out.println();
            String cmd = in.nextLine();
        
            if (cmd.equals("bye")){
                System.out.println(line);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            }
            if (cmd.equals("list")){
                System.out.println(line);
                for(int i = 0; i < numTasks; i++){
                    System.out.println(Integer.toString(i+1) + ". " + tasks[i].getDescription());
                }
                System.out.println(line);
            }
            else{
                System.out.println(line);
                System.out.println("added: " + cmd);
                System.out.println(line);
                tasks[numTasks] = new Task(cmd);
                numTasks++;
            }
        }   
        
    }
}
