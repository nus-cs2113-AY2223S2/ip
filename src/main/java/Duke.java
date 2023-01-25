import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Print.PrintEditor();
        System.out.println("Hello! I'm Duke, edited by liuziyang\n");
        System.out.println("What can I do for you?\n");

        Print.PrintLine();

        Scanner in = new Scanner(System.in);
        while(true){
            String line = in.nextLine();
            line = line.trim();
            String[] command = line.split(" ");
            String secondword = line.substring(line.indexOf(" ")+1);
            switch (command[0]){
            case "bye":
                Print.PrintString("Bye. Hope to see you again soon!");
                Print.PrintGoodbye();
                return;
            case "list":
                DukeList.list();
                break;
            default:
                DukeList.added(line);
            }
        }

    }
}
