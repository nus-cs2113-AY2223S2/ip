import java.util.Scanner;

public class level3 {
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
                    System.out.println("Here are the tasks in your list: ");
                    System.out.print(j+1);
                    if (done[j]){
                        System.out.print(".[X] ");
                    }
                    if (!done[j]){
                        System.out.print(".[ ] ");
                    }
                    System.out.println(lines[j]);
                }
            }
            else if (line.startsWith("mark")){
                char numbchar = line.charAt(line.length() - 1);
                int number = Character.getNumericValue(numbchar) - 1;
                done[number] = true;
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  [X] " + lines[number]);
            }
            else if (line.startsWith("unmark")){
                char numbchar = line.charAt(line.length() - 1);
                int number = Character.getNumericValue(numbchar) - 1;
                done[number] = false;
                System.out.println("Ok, I've marked this task as not done yet:");
                System.out.println("  [ ] " + lines[number]);
            }
            else {
                lines[i] = line;
                done[i] = false;
                i++;
                System.out.println("added: " + line);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
