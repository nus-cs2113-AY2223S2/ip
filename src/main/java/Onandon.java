
import Onandon.module.*;
import Onandon.print.Print;
import Onandon.exception.OnandonEmptyException;
import Onandon.exception.OnandonUnknownException;
import Onandon.exception.OnandonNotaskException;
import Onandon.checkpoint.Checkpoint;

import java.util.ArrayList;
import java.util.Arrays;
<<<<<<< HEAD
=======
import java.util.List;
>>>>>>> branch-Level-7
import java.util.Scanner;

public class Onandon {
    static Task[] tasks = new Task[100];

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

    public static int printDeleteOfTasks(String inputText, Task[] tasks, int cnt){
        int index;

        Print.printDelete();
        index = Integer.parseInt(inputText.split(" ")[1])-1;
        System.out.println("\t\t" + tasks[index].toString());
        ArrayList<Task> result = new ArrayList<Task>(Arrays.asList(tasks));
        result.remove(index);
        tasks = result.toArray(new Task[0]);
        cnt -= 1;
        System.out.println("\t Now you have " + cnt + " tasks in the list.");
        Print.printUnderline();
        return cnt;
    }

    public static void checkException(String inputText) throws OnandonEmptyException, OnandonUnknownException, OnandonNotaskException {
        String[] split = inputText.split(" ");
        String tgt = inputText.split(" ")[0];
        Boolean isNotTodo = !tgt.equals("todo");
        Boolean isNotDeadline = !tgt.equals("deadline");
        Boolean isNotEvent = !tgt.equals("event");
        Boolean isNotMark = !tgt.equals("mark");
        Boolean isNotUnmark = !tgt.equals("unmark");
        Boolean isNotList = !tgt.equals("list");
        Boolean isNotDelete = !tgt.equals("delete");

        if(inputText.length() == 0){
            throw new OnandonEmptyException();
        } else if(isNotTodo && isNotDeadline && isNotEvent && isNotMark && isNotUnmark && isNotList && isNotDelete){
            throw new OnandonUnknownException();
        } else if(split.length <= 1 && isNotList){
            throw new OnandonNotaskException();
        }
    }

    public static Boolean printException(String inputText){
        try{
           checkException(inputText);
        } catch (OnandonEmptyException e){
            Print.printUnderline();
            System.out.println("\t ☹ OOPS!!! The description of a todo cannot be empty.");
            Print.printUnderline();
            return true;
        } catch (OnandonUnknownException e){
            Print.printUnderline();
            System.out.println("\t ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            Print.printUnderline();
            return true;
        } catch (OnandonNotaskException e){
            Print.printUnderline();
            System.out.println("\t ☹ OOPS!!! There is no task on input!");
            Print.printUnderline();
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        Print.printGreet();
        List<Object> checkpointOutput = Checkpoint.recallCheckpoint();
        int cnt = (int) checkpointOutput.get(1);
        tasks = (Task[]) checkpointOutput.get(0);

        Scanner in = new Scanner(System.in);
        String inputText;
        String tgt;

        while(true){
            inputText = in.nextLine();
            if(inputText.equals("exit"))
                break;

            if(printException(inputText)){
                continue;
            }

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
            case "delete":
                cnt = printDeleteOfTasks(inputText, tasks, cnt);
                break;
            }
        }

        Checkpoint.storeCheckpoint(tasks, cnt);
        in.close();
        Print.printBye();
    }
}
