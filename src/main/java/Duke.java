
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
        String[] input_list = new String[100];
        int cnt = 0;

        while(true){
            input_text = in.nextLine();
            if(input_text.length() == 0)
                break;

            input_list[cnt++] = input_text;
            System.out.println(
                    "    ____________________________________________________________\n\t " +
                    " added: " + input_text +
                    "     \n\t____________________________________________________________"
            );
        }

        System.out.println(
                "list\n" +
                "    ____________________________________________________________"
        );
        for(int i=0; i<cnt; i++){
            System.out.println(
                    "\t " + Integer.toString(i+1) + ". " + input_list[i]
            );
        }
        System.out.println(
                "    ____________________________________________________________"
        );

        System.out.println(
                "\nbye\n" +
                "     ____________________________________________________________\n" +
                "      Bye. Hope to see you again soon!\n" +
                "     ____________________________________________________________\n"
        );
        in.close();
    }
}
