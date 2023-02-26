import java.util.ArrayList;
public class Parser {
    /**
     *this function is in charge of reading user inputs, storing and reading from a text file and generating responses
     * @param input is a string containing a keyword,taskName and task details.Eg: event orientation /from now /to later
     */
    public static void interpretInput(String input){
        String[] inputSplitBySpace = input.split(" ");

        if(inputSplitBySpace[0].equals("todo")){
            ToDo todo = new ToDo(input.substring(5), Tasklist.lastIndex+1,"T");
            Tasklist.addToTaskArrayList(todo);
            UserInterface.acknowledgeTaskAdded();
            Storage.save();
            UserInterface.savedMessage();
        }
        else if(inputSplitBySpace[0].equals("deadline")){
            try{
                GootExceptionHandler.validateDeadline(input.split("/"));
                int taskNumber = Tasklist.lastIndex+1;
                Deadline deadline = new Deadline(Deadline.readName(input),taskNumber,Deadline.readBy(input),"D");
                Tasklist.addToTaskArrayList(deadline);
                UserInterface.acknowledgeTaskAdded();
                Storage.save();
                UserInterface.savedMessage();
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
                int taskNumber = Tasklist.lastIndex+1;
                Event event = new Event(Event.readName(input),taskNumber,fromString,toString,"E");
                Tasklist.addToTaskArrayList(event);
                UserInterface.acknowledgeTaskAdded();
                Storage.save();
                UserInterface.savedMessage();
            }
            catch(GootExceptions e){
                GootExceptionHandler.wrongNumberOfSlashesEvent();
            }

        }
        else if(inputSplitBySpace[0].equals("mark")|inputSplitBySpace[0].equals("unmark")){
            int indexToMark = Integer.parseInt(inputSplitBySpace[1]);
            Tasklist.markOrUnmark(indexToMark);
            Storage.save();
            UserInterface.savedMessage();
        }
        else if (input.equals("list")) {
            UserInterface.printList();
        }
        else if (inputSplitBySpace[0].equals("delete")&&inputSplitBySpace.length==2){
            Tasklist.deleteFromTaskArray(Integer.parseInt(inputSplitBySpace[1]));
            Storage.save();
            UserInterface.savedMessage();
        }
        else if(inputSplitBySpace[0].equals("find")){
            ArrayList<String> listOfTasksFound = Tasklist.find(input.substring(5));
            UserInterface.printFoundList(listOfTasksFound);
        }
        else{
            GootExceptionHandler.unidentifiedKeyword();
        }
    }
}
