
import java.util.Scanner;

public class Duke {
    public static void printUnderBar(){
        System.out.println("\t____________________________________________________________");
    }
    public static void printFormAbove(){
        printUnderBar();
        System.out.println("\t Got it. I've added this task:");
    }
    public static void printCnt(int cnt){
        System.out.println("\t Now you have " + cnt + " tasks in the list.");
        printUnderBar();
    }
    public static void printMark(){
        printUnderBar();
        System.out.println("\t Nice! I've marked this task as done:");
    }
    public static void printUnMark(){
        printUnderBar();
        System.out.println("\t OK, I've marked this task as not done yet:");
    }
    public static void printList(){
        printUnderBar();
        System.out.println("\t Here are the tasks in your list:");
    }

    public static void main(String[] args) {
        Task[] tasks = new Task[100];

        Scanner in = new Scanner(System.in);
        int cnt = 0;
        int index = 0;
        String input_text;
        String[] tgt;

        while(true){
            input_text = in.nextLine();
            if(input_text.length() == 0)
                break;

            tgt = input_text.split(", ");

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
            case "mark":
                printMark();
                index = Integer.parseInt(tgt[1])-1;
                tasks[index].markAsDone();
                System.out.println("\t\t[" + tasks[index].getStatusIcon() + "] " +
                        tasks[index].getDescription());
                printUnderBar();
                break;
            case "unmark":
                printUnMark();
                index = Integer.parseInt(tgt[1])-1;
                tasks[index].markAsUnDone();
                System.out.println("\t\t[" + tasks[index].getStatusIcon() + "] " +
                        tasks[index].getDescription());
                printUnderBar();
                break;
            case "list":
                printList();
                for(int i=0; i<cnt; i++){
                    System.out.println("\t " + (i+1) + ". " + tasks[i].toString());
                }
                printUnderBar();
                break;
            }
        }
        in.close();
    }
}
