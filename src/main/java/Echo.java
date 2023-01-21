import java.util.Scanner;

public class Echo {
    public static void Echo() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!(line.equals("Bye") || line.equals("bye"))) {
            System.out.println(line);
            line = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again!\n");
    }
}
