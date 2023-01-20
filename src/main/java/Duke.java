import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = "";

        String lineBreak = "____________________________________________________________\n";
        System.out.println(lineBreak + "Hello I'm Duke!\nWhat can I do for you?\n" + lineBreak);

        while (!line.equals("bye")){
            line = in.nextLine();
            System.out.println(line + "\n" + lineBreak);
        }

        System.out.println("Bye. Hope to see you again soon!\n" + lineBreak);
    }
}
