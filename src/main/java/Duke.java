
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Task[] t = new Task[3];
        t[0] = new Task("read book");
        t[1] = new Task("return book");
        t[2] = new Task("buy bread");
        t[0].markAsDone();

        System.out.println(
                "list\n" +
                "    ____________________________________________________________"
        );
        for(int i=0; i<3; i++)
            System.out.println("\t" + i + ".[" + t[i].getStatusIcon() + "] " + t[i].getDescription());
        System.out.println("    ____________________________________________________________\n");

        Scanner in = new Scanner(System.in);
        int tgt;
        boolean mark = true;
        String input_text;

        while(true){
            input_text = in.nextLine();
            if(input_text.length() == 0)
                break;

            tgt = Integer.parseInt(input_text.replaceAll("[^0-9]",""));
            if(input_text.contains("un")) {
                t[tgt].markAsUnDone();
                mark = false;
            }else if(input_text.contains("mark")) {
                t[tgt].markAsDone();
                mark = true;
            }else {
                System.out.println("unsupported command!");
                continue;
            }

            System.out.println("\t____________________________________________________________");
            if(mark)
                System.out.println("\tNice! I've marked this task as done:");
            else
                System.out.println("\tOK, I've marked this task as not done yet:");
            System.out.println("\t\t[" + t[tgt].getStatusIcon() + "] " + t[tgt].getDescription());
            System.out.println("\t____________________________________________________________\n");
        }
        in.close();
    }
}
