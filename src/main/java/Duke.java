
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println(
                "    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n"
        );

        Scanner in = new Scanner(System.in);
        String input_text;
        while(true){
            input_text = in.nextLine();
            if(input_text.length() == 0)
                break;
            System.out.println(
                    "    ____________________________________________________________\n\t " +
                    input_text +
                    "     \n\t____________________________________________________________\n"
            );
        }
        System.out.println(
                "     ____________________________________________________________\n" +
                "      Bye. Hope to see you again soon!\n" +
                "     ____________________________________________________________\n"
        );

        in.close();
    }
}
