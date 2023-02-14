import java.util.Locale;

public class Parser {

    protected String command;
    protected String commandParameters;

    public Parser(String[] userInputSplit){
        this.command = userInputSplit[0].toLowerCase();
        if (userInputSplit.length > 1) {
            this.commandParameters = userInputSplit[1];
        }
    }

    public void processInput(){
        switch(this.command){
            case ("bye"):
                UI.bye();
                break;
            case ("todo"):
                    TaskArray.createToDo(commandParameters);
                    break;
            case ("deadline"):
                TaskArray.createDeadline(commandParameters);
                break;
            case ("event"):
                TaskArray.createEvent(commandParameters);
                break;
            case ("list"):
                TaskArray.print();
                break;
            case("mark"):
                TaskArray.mark(commandParameters);
                break;
            case ("unmark"):
                TaskArray.unmark(commandParameters);
                break;
            case ("delete"):
                TaskArray.delete(commandParameters);
                break;
            default:
                System.out.println(UI.LINE + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" + UI.LINE);

        }
    }
}
