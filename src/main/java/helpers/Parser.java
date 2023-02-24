package helpers;

import corefunctionalities.TaskList;

public class Parser {
    public Parser () {}
    public String[] getTodo(String userInput) {
        return userInput.split(" ");
    }

    public String[] getDeadline(String userInput) {
        String intermediateStage = userInput.replace("deadline ", "");
        String[] deadlineAndDescription = intermediateStage.split("/by ");
        return deadlineAndDescription;
    }

    public String[] getEvent(String userInput) {
        String intermediateStage = userInput.replace("event ", "");
        String[] eventDescription = intermediateStage.split("/from | /to ");
        return eventDescription;
    }

    public boolean isTheSame(String userInput, String toCompare) {
        return userInput.split(" ")[0].equals(toCompare);
    }

    public boolean isInRange(String userInput, TaskList taskList) throws NumberFormatException{
        boolean isReturn = false;
//        try {
        isReturn = Integer.parseInt(userInput.split(" ")[1])>0 && Integer.parseInt(userInput.split(" ")[1])<taskList.getSize()+1;
//        } catch (NumberFormatException e) {
//            System.out.println("\tWhoops, need to ensure that your inputs are numbers! BUT a");
//        }
        return (isReturn);
    }
}
