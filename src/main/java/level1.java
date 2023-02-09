import java.util.Scanner;

public class level1 {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        String line;
        while (true) {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            if (line.equals("bye")){
                break;
            }
            System.out.println(line);
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
