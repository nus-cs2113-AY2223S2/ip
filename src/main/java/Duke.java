import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke\n");
        System.out.println("What can I do for you?\n");

        Print.PrintLine();

        Scanner in = new Scanner(System.in);
        while(true){
            String line = in.nextLine();
            line = line.trim();
            String[] command = line.split(" ");
            switch (command[0]){
            case "bye":
                Print.PrintString("Bye. Hope to see you again soon!");
                return;
            case "list":
                Print.PrintString("list");
                break;
            default:
                Print.PrintString(line);//simply echos commands entered by the user
            }
        }

    }
}
