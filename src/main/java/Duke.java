import java.util.Scanner;
public class Duke {
    public static void echo(String command){
        System.out.println("    ____________________________________________________________\n");
        if(command.equals("bye")){
            System.out.println("    Bye. Hope to see you again soon!");
        }
        else{
            System.out.println("    " + command);
        }
        System.out.println("    ____________________________________________________________\n");
    }
    public static void main(String[] args) {
        String logo = "     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String greet = "    ____________________________________________________________\n"
                +"     Hello! I'm Duke \n"
                +"     What can I do for you? \n"
                +"    ____________________________________________________________\n";
        System.out.println(greet);

        String exit = "    ____________________________________________________________\n"
                + "     Bye. Hope to see you again soon!\n"
                + "    ____________________________________________________________\n";

        String line;
        boolean end = true;

        while(end){
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            echo(line);
            if(line.equals("bye")){
                end = false;
            }
        }
    }
}
