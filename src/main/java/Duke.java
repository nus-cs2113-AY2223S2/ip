import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = "    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n";

        System.out.println(logo);

        String line = "    ____________________________________________________________\n";

        String[] tasks = new String[100];
        Scanner in = new Scanner(System.in);
        String action = in.nextLine();
        int index = 0;
        String bye = "bye";
        while(!action.equals(bye)){
            System.out.println(line);

            if(action.equals("list")) {
                for(int i = 0; i<index; i = i + 1) {
                    int num = i+1;
                    System.out.println("     " + num + ": " + tasks[i]);
                }
            } else {
                tasks[index] = action;
                index = index + 1;
                System.out.println("     added: " + action);
            }

            System.out.println(line);
            action = in.nextLine();
        }
        System.out.println(line);
        System.out.println("     " + "Bye. Hope to see you again soon!");
        System.out.println(line);


    }
}
