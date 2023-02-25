import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
//import java.util.Arrays;
//import java.util.ArrayList;
public class Goot {
    public static void main(String[] args) {
    String DASH = "__________________________________";
    String greet = DASH + "\nI'm Goot :3\nWhat's up?\n" + DASH;
    System.out.println(greet);
    File f = new File("C:\\Users\\leong\\Desktop\\Comp_eng\\Y2S2\\CS2113\\Duke\\ip\\src\\data.txt");
    try{
        Task.loadFileContentsToTaskArray(f);
    }
    catch (IOException e){
        e.printStackTrace();
    }


    while (true) {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        String[] inputSplitBySpace = input.split(" ");

        if(inputSplitBySpace[0].equals("todo")){
            ToDo todo = new ToDo(input.substring(5), Task.lastIndex+1,"T");
            Task.addToTaskArrayList(todo);
            todo.acknowledgeTaskAdded();
            Task.save(f);
        }
        else if(inputSplitBySpace[0].equals("deadline")){
            try{
                GootExceptionHandler.validateDeadline(input.split("/"));
                Deadline deadline = new Deadline(Deadline.readName(input),Task.lastIndex+1,Deadline.readBy(input),"D");
                Task.addToTaskArrayList(deadline);
                deadline.acknowledgeTaskAdded();
                Task.save(f);
            }
            catch(GootExceptions e){
                GootExceptionHandler.wrongNumberOfSlashesDeadline();
            }

        }
        else if(inputSplitBySpace[0].equals("event")){
            try{
                GootExceptionHandler.validateEvent(input.split("/"));
                String fromString = Event.readFromTo(input)[0];
                String toString = Event.readFromTo(input)[1];
                Event event = new Event(Event.readName(input),Task.lastIndex+1,fromString,toString,"E");
                Task.addToTaskArrayList(event);
                event.acknowledgeTaskAdded();
                Task.save(f);
            }
            catch(GootExceptions e){
                GootExceptionHandler.wrongNumberOfSlashesEvent();
            }

        }
        else if(inputSplitBySpace[0].equals("mark")|inputSplitBySpace[0].equals("unmark")){
            int indexToMark = Integer.parseInt(inputSplitBySpace[1]);

            Task.markOrUnmark(indexToMark);
        }
        else if (input.equals("bye")) {
            System.out.println(DASH + "\nBye. Hope to see you again soon!\n" + DASH);
            break;
        }
        else if (input.equals("list")) {
            Task.printList();
        }
        else if (inputSplitBySpace[0].equals("delete")&&inputSplitBySpace.length==2){
            Task.deleteFromTaskArray(Integer.parseInt(inputSplitBySpace[1]));
            Task.save(f);
        }
        else{
            GootExceptionHandler.unidentifiedKeyword();
        }
    }
}


}
