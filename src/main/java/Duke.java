import java.util.Scanner;
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
        String input;
        while(in.hasNext()){
            input = in.nextLine();
            if(input.equals("bye")){
                break;
            }
            System.out.println(line);
            System.out.println(input);
            System.out.println(line);
        }
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
