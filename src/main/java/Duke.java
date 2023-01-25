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
        
        while(true){
            System.out.println();
            String cmd = in.nextLine();
        
            if (cmd.equals("bye")){
                System.out.println(line);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            }
            else{
                System.out.println(line);
                System.out.println(cmd);
                System.out.println(line);
            }
        }   
        
    }
}
