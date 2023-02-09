
import Onandon.function.*;
import Onandon.print.Print;
import Onandon.exception.OnandonEmptyException;
import Onandon.exception.OnandonUnknownException;

import java.util.Scanner;

public class Onandon {
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

    public static void checkException(String inputText) throws OnandonEmptyException, OnandonUnknownException {
        String tgt = inputText.split(" ")[0];
        Boolean isNotTodo = !tgt.equals("todo");
        Boolean isNotDeadline = !tgt.equals("deadline");
        Boolean isNotEvent = !tgt.equals("event");
        Boolean isNotMark = !tgt.equals("mark");
        Boolean isNotUnmark = !tgt.equals("unmark");

        if(inputText.length() == 0){
            throw new OnandonEmptyException();
        } else if(isNotTodo && isNotDeadline && isNotEvent && isNotMark && isNotUnmark){
            throw new OnandonUnknownException();
        }
    }

    public static void printException(String inputText){
        try{
           checkException(inputText);
        } catch (OnandonEmptyException e){
            Print.printUnderline();
            System.out.println("\t ☹ OOPS!!! The description of a todo cannot be empty.");
            Print.printUnderline();
        } catch (OnandonUnknownException e){
            Print.printUnderline();
            System.out.println("\t ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            Print.printUnderline();
        }
    }

    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        Print.printGreet();

        Scanner in = new Scanner(System.in);
        int cnt = 0;
        String inputText;
        String tgt;

        while(true){
            inputText = in.nextLine();
            if(inputText.equals("exit"))
                break;

            printException(inputText);
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
        Print.printBye();
    }
}
