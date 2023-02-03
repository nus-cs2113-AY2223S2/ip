
import java.util.Scanner;

public class Duke {

    public static int printTodoAction(String inputText, Task[] tasks, int cnt){
        String description;
        description = inputText.replaceAll("todo ", "");

        Print.printFormAbove();
        tasks[cnt] = new Todo(description);
        System.out.println("\t\t" + tasks[cnt].toString());
        cnt++;
        Print.printCnt(cnt);
        return cnt;
    }

    public static int printDeadlineAction(String inputText, Task[] tasks, int cnt){
        String description;
        String by;
        int indexBy;

        indexBy = inputText.indexOf("/by");
        description = inputText.substring(9, indexBy-1);
        by = inputText.substring(indexBy + 4);

        Print.printFormAbove();
        tasks[cnt] = new Deadline(description, by);
        System.out.println("\t\t" + tasks[cnt].toString());
        cnt += 1;
        Print.printCnt(cnt);

        return cnt;
    }

    public static int printEventAction(String inputText, Task[] tasks, int cnt){
        String description;
        String from;
        String to;
        int indexFrom;
        int indexTo;

        indexFrom = inputText.indexOf("/from");
        indexTo = inputText.indexOf("/to");
        description = inputText.substring(6, indexFrom-1);
        from = inputText.substring(indexFrom+6, indexTo-1);
        to = inputText.substring(indexTo+4);

        Print.printFormAbove();
        tasks[cnt] = new Event(description, from, to);
        System.out.println("\t\t" + tasks[cnt].toString());
        cnt += 1;
        Print.printCnt(cnt);

        return cnt;
    }

    public static void printMarkAction(String inputText, Task[] tasks){
        int index;

        Print.printMark();
        index = Integer.parseInt(inputText.split(" ")[1])-1;
        tasks[index].markAsDone();
        System.out.println("\t\t[" + tasks[index].getStatusIcon() + "] " +
                tasks[index].getDescription());
        Print.printUnderline();
    }

    public static void printUnmarkAction(String inputText, Task[] tasks){
        int index;

        Print.printUnmark();
        index = Integer.parseInt(inputText.split(" ")[1])-1;
        tasks[index].markAsUnDone();
        System.out.println("\t\t[" + tasks[index].getStatusIcon() + "] " +
                tasks[index].getDescription());
        Print.printUnderline();
    }

    public static void printListOfTasks(Task[] tasks, int cnt){
        Print.printList();
        for(int i=0; i<cnt; i++){
            System.out.println("\t " + (i+1) + ". " + tasks[i].toString());
        }
        Print.printUnderline();
    }

    public static void main(String[] args) {
        Task[] tasks = new Task[100];

        Scanner in = new Scanner(System.in);
        int cnt = 0;
        String inputText;
        String tgt;

        while(true){
            inputText = in.nextLine();
            if(inputText.length() == 0)
                break;

            tgt = inputText.split(" ")[0];

            switch(tgt){
            case "todo":
                cnt = printTodoAction(inputText, tasks, cnt);
                break;
            case "deadline":
                cnt = printDeadlineAction(inputText, tasks, cnt);
                break;
            case "event":
                cnt = printEventAction(inputText, tasks, cnt);
                break;
            case "mark":
                printMarkAction(inputText, tasks);
                break;
            case "unmark":
                printUnmarkAction(inputText, tasks);
                break;
            case "list":
                printListOfTasks(tasks, cnt);
                break;
            }
        }
        in.close();
    }
}
