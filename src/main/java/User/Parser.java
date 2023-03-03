package User;

public class Parser {
    public static void parsing(String userInput){
        String[] inputParts;
        inputParts = userInput.split(" ",2);
        String command = inputParts[0];
        switch(command){
        case("list"):
            TaskList.printList();
            break;
        case("delete"):
            TaskList.deleteItem(userInput);
            break;
        case("mark"):
            TaskList.markItem(userInput);
            break;
        case("unmark"):
            TaskList.unMarkItem(userInput);
            break;
        case("event"):
            TaskList.createEvent(userInput);
            break;
        case("todo"):
            TaskList.createToDo(userInput);
            break;
        case("deadline"):
            TaskList.createDeadline(userInput);
            break;
        case("help"):
            UI.printHelp();
            break;
        case("find"):
            if(!TaskList.taskList.isEmpty()) {
                TaskList.find(userInput);
            } else {
                UI.listIsEmpty();
            }
            break;
        default:
            UI.invalidCommand();
            break;
        }
    }
}
