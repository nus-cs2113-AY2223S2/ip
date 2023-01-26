import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String enter_greet = "    ____________________________________________________________\n"
                           + "     Hello! I'm Duke\n"
                           + "     What can I do for you?\n"
                           + "    ____________________________________________________________\n";
        String exit_prompt = "    ____________________________________________________________\n"
                           + "     Bye. Hope to see you again soon!\n"
                           + "    ____________________________________________________________\n";
        System.out.println(enter_greet);
        while(true){
            Scanner in = new Scanner(System.in);
            String line = in.next();            // get user input
            if(line.equals("bye")){             // quit
                System.out.println(exit_prompt);
                break;
            }else{                              // simply echo the input
                String repeatInput = "    ____________________________________________________________\n"
                                   + "    " + line + "\n"
                                   + "    ____________________________________________________________\n";
                System.out.println(repeatInput);
            }
        }
    }
}
