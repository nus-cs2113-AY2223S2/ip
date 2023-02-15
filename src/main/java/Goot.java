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
    File f = new File("data.txt");
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
        }
        else if(inputSplitBySpace[0].equals("deadline")){
            try{
                GootExceptionHandler.validateDeadline(input.split("/"));
                Deadline deadline = new Deadline(Deadline.readName(input),Task.lastIndex+1,Deadline.readBy(input),"D");
                Task.addToTaskArrayList(deadline);
                deadline.acknowledgeTaskAdded();
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
        else if (input.equals("save")){
            try{
                if(!f.exists()){
                    f.createNewFile();
                }
                if(Task.lastIndex>0){     //possible to use continue here instead?
                    FileWriter fw = new FileWriter(f);
                    for(int index=0;index<Task.lastIndex;index++){
                        fw.append((Task.get(index)).createEntry());
                    }
                    fw.close();
                }
                System.out.println("Saved!");

            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        else{
            try{
                GootExceptionHandler.validateInput(inputSplitBySpace);
            }
            catch (GootExceptions e){
                GootExceptionHandler.unidentifiedKeyword();
            }
        }
    }
}


}
