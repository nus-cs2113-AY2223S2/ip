
import java.util.Scanner;

public class Duke {
    public static void printFormAbove(){
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Got it. I've added this task:");
    }

    public static void printCnt(int cnt){
        System.out.println("\t Now you have " + cnt + " tasks in the list.");
        System.out.println("\t____________________________________________________________");
    }

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
                printFormAbove();
                tasks[cnt] = new Todo(tgt[1]);
                System.out.println("\t\t" + tasks[cnt].toString());
                cnt += 1;
                printCnt(cnt);
                break;
            case "deadline":
                printFormAbove();
                tasks[cnt] = new Deadline(tgt[1], tgt[2]);
                System.out.println("\t\t" + tasks[cnt].toString());
                cnt += 1;
                printCnt(cnt);
                break;
            case "event":
                printFormAbove();
                tasks[cnt] = new Event(tgt[1], tgt[2], tgt[3]);
                System.out.println("\t\t" + tasks[cnt].toString());
                cnt += 1;
                printCnt(cnt);
                break;
            case "list":
                System.out.println("\t____________________________________________________________");
                System.out.println("\t Here are the tasks in your list:");
                for(int i=0; i<cnt; i++)
                    System.out.println("\t " + (i+1) + ". " + tasks[i].toString());
                System.out.println("\t____________________________________________________________\n");
                break;
            }
        }
        in.close();
    }
}
