import java.util.Scanner;

public class Echo {
    public static void Echo() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        String[] tasks = new String[100];
        int count = 0;
        while (!(line.equals("Bye") || line.equals("bye"))) {
            if (!line.equals("list")) {
                System.out.println("added: " + line);
                tasks[count] = line;
                ++count;
                line = in.nextLine();
            } else {
                int index = 1;
                for (int i = 0; i < count; ++i) {
                    System.out.println(index + ": " + tasks[i]);
                    ++index;
                }
                line = in.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again!\n");
    }
}
