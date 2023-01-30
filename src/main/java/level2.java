import java.util.Scanner;

public class level2 {
    public static void main(String[] args){
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        String line;
        String[] lines = new String[100];
        boolean[] done = new boolean[100];
        int i = 0;
        while (true) {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            if (line.equals("bye")) {
                break;
            }
            if (line.equals("list")) {
                for (int j = 0; j < i; j++) {
                    System.out.print(j+1);
                    System.out.println(". " + lines[j]);
                }
            }
            else {
                lines[i] = line;
                i++;
                System.out.println("added: " + line);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
