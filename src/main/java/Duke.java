
// import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    public static void listArray(String[] list) {
        int count = 1;
        for (String item : list) {
            if (item != null) {
                System.out.println(count + ". " + item);
                count++;
            } else {
                System.out.println("\n");
                break;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner in = new Scanner(System.in);
        String line;
        int count = 0;
        String[] list = new String[100];
        line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                listArray(list);
            } else {
                list[count] = line;
                count++;
                System.out.println("added: " + line + "\n");
            }
            line = in.nextLine();
        }

        in.close();
        System.out.println("Bye. Hope to see you again soon!");
    }
}
