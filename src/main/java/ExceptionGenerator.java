import exceptions.*;

public class ExceptionGenerator {
    protected Parser parser = new Parser();
    ExceptionGenerator () {}
    public void todoExceptionGenerator(String [] holder) throws EmptyTodo {
        if(holder.length<2)
        {
            throw new EmptyTodo();
        }
    }

    public void deadlineExceptionGenerator(String[]  deadlineAndDescription, String userInput) throws EmptyDeadline, DeadlineMissingPhrase, DeadlineIsBlank{
        if(!userInput.contains("/by ") && userInput.split(" ").length>1) {
            throw new DeadlineMissingPhrase();
        } else if(deadlineAndDescription.length==1) {
            throw new EmptyDeadline();
        } else if(deadlineAndDescription[1].isBlank()) {
            throw new DeadlineIsBlank();
        }
    }

    public void eventExceptionGenerator(String [] eventDescription, String userInput) throws EmptyEvent, EventMissingBothPhrases, EventMissingToPhrase, EventMissingFromPhrase, EventFromIsBlank, EventToIsBlank {
        if(!userInput.contains("/from") && userInput.split(" ").length>1) {
            throw new EventMissingFromPhrase();
        } else if(!userInput.contains("/to") && userInput.split(" ").length>1) {
            throw new EventMissingToPhrase();
        } else if(!(userInput.contains("/from") || userInput.contains("/to")) && userInput.split(" ").length>1) {
            throw new EventMissingFromPhrase();
        } else if (eventDescription.length==1) {
            throw new EmptyEvent();
        }else if(eventDescription[1].isBlank()) {
            throw new EventFromIsBlank();
        } else if(eventDescription[2].isBlank()) {
            throw new EventToIsBlank();
        }
    }

    public void markExceptionGenerator(String userInput, TaskList taskList) throws MarkQualityException, NumberFormatException {
        if(parser.isInRange(userInput, taskList)==false) {
            throw new MarkQualityException();
        }
    }

    public void unMarkExceptionGenerator(String userInput, TaskList taskList) throws UnmarkQualityException, NumberFormatException {
        if(parser.isInRange(userInput, taskList)==false) {
            throw new UnmarkQualityException();
        }
    }
}
