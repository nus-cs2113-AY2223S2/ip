
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Task[] tasks = new Task[100];

        Scanner in = new Scanner(System.in);
        int cnt = 0;
        String input_text;

        while(true){
            input_text = in.nextLine();
            if(input_text.length() == 0)
                break;

            String[] tgt = input_text.split(",");

            switch(tgt[0]){
            case "todo":
                System.out.println("\t____________________________________________________________");
                System.out.println("\t Got it. I've added this task:");
                tasks[cnt] = new Todo(tgt[1]);
                System.out.println("\t\t" + tasks[cnt].toString());
                cnt += 1;
                System.out.println("\t Now you have " + cnt + " tasks in the list.");
                System.out.println("\t____________________________________________________________");
                break;
            case "deadline":
                System.out.println("\t____________________________________________________________");
                System.out.println("\t Got it. I've added this task:");
                tasks[cnt] = new Deadline(tgt[1], tgt[2]);
                System.out.println("\t\t" + tasks[cnt].toString());
                cnt += 1;
                System.out.println("\t Now you have " + cnt + " tasks in the list.");
                System.out.println("\t____________________________________________________________");
                break;
            case "event":
                System.out.println("\t____________________________________________________________");
                System.out.println("\t Got it. I've added this task:");
                tasks[cnt] = new Event(tgt[1], tgt[2], tgt[3]);
                System.out.println("\t\t" + tasks[cnt].toString());
                cnt += 1;
                System.out.println("\t Now you have " + cnt + " tasks in the list.");
                System.out.println("\t____________________________________________________________");
                break;
            case "list":
                System.out.println("\t____________________________________________________________");
                System.out.println("\t Here are the tasks in your list:");
                for(int i=0; i<cnt; i++)
                    System.out.println("\t " + (i+1) + ". " + tasks[i].toString());
                System.out.println("\t____________________________________________________________\n");
                break;
            }

            /*
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
            */
        }
        in.close();
    }
}
