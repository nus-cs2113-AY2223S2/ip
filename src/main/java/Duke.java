import java.util.Scanner;

public class Duke {
    public static String ask() {
        String line;
        Scanner in = new Scanner(System.in);

        
        line = in.nextLine();
        return line;
        }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(" Hello! I'm Duke ");
        System.out.println(" What can I do for you? \n");
        System.out.println("____________________________________________________________\n");
        boolean not_finished= true;
        while (not_finished==true) {
            String line=ask();
            if (line.equals("bye")){
                System.out.println("____________________________________________________________\n");
                System.out.println("\t Bye. Hope to see you again soon!\n");
                System.out.println("____________________________________________________________\n");
                not_finished=false;
            }
            else{
                System.out.println("____________________________________________________________");
                System.out.println("\t "+line);
                System.out.println("____________________________________________________________");
            }
        }


    }
}
