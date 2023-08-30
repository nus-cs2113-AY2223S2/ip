/**
 * Represents a Parser to process user's input and execute appropriately. A <code>Parser</code> object extracts
 * the start of a given input into a <code>command</code> variable, to identify the action which the user intends to do.
 * The remainder of the input is stored into a <code>commandParameters</code> variable for usage in the action that follows.
 */
public class Parser {

    protected String command;
    protected String commandParameters;

    public Parser(String[] userInputSplit){
        this.command = userInputSplit[0].toLowerCase();
        if (userInputSplit.length > 1) {
            this.commandParameters = userInputSplit[1];
        }
    }

    /**
     * Processes the input from user and runs the appropriate command respectively.
     * If any modification to the TaskArray is expected, will also run the <code>updateFile</code> command
     * such that the text file is continually kept up to date.
     */
    public void processInput(){
        switch(this.command){
            case ("bye"):
                UI.bye();
                break;
            case ("todo"):
                    TaskArray.createToDo(commandParameters);
                    Storage.updateFile();
                    break;
            case ("deadline"):
                TaskArray.createDeadline(commandParameters);
                Storage.updateFile();
                break;
            case ("event"):
                TaskArray.createEvent(commandParameters);
                Storage.updateFile();
                break;
            case ("list"):
                TaskArray.print();
                break;
            case("mark"):
                TaskArray.mark(commandParameters);
                Storage.updateFile();
                break;
            case ("unmark"):
                TaskArray.unmark(commandParameters);
                Storage.updateFile();
                break;
            case ("delete"):
                TaskArray.delete(commandParameters);
                Storage.updateFile();
                break;
            case ("find"):
                TaskArray.find(commandParameters);
                break;
            default:
                System.out.println(UI.LINE + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" + UI.LINE);

        }
    }
}
