public class Parser {
    public static void interpretInput(String input){
        String[] inputSplitBySpace = input.split(" ");

        if(inputSplitBySpace[0].equals("todo")){
            ToDo todo = new ToDo(input.substring(5), Tasklist.lastIndex+1,"T");
            Tasklist.addToTaskArrayList(todo);
            UserInterface.acknowledgeTaskAdded();
            Storage.save();
        }
        else if(inputSplitBySpace[0].equals("deadline")){
            try{
                GootExceptionHandler.validateDeadline(input.split("/"));
                Deadline deadline = new Deadline(Deadline.readName(input),Tasklist.lastIndex+1,Deadline.readBy(input),"D");
                Tasklist.addToTaskArrayList(deadline);
                UserInterface.acknowledgeTaskAdded();
                Storage.save();
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
                Event event = new Event(Event.readName(input),Tasklist.lastIndex+1,fromString,toString,"E");
                Tasklist.addToTaskArrayList(event);
                UserInterface.acknowledgeTaskAdded();
                Storage.save();
            }
            catch(GootExceptions e){
                GootExceptionHandler.wrongNumberOfSlashesEvent();
            }

        }
        else if(inputSplitBySpace[0].equals("mark")|inputSplitBySpace[0].equals("unmark")){
            int indexToMark = Integer.parseInt(inputSplitBySpace[1]);
            Tasklist.markOrUnmark(indexToMark);
        }
        else if (input.equals("list")) {
            UserInterface.printList();
        }
        else if (inputSplitBySpace[0].equals("delete")&&inputSplitBySpace.length==2){
            Tasklist.deleteFromTaskArray(Integer.parseInt(inputSplitBySpace[1]));
            Storage.save();
        }
        else{
            GootExceptionHandler.unidentifiedKeyword();
        }
    }
}
