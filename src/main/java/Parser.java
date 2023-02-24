public class Parser {
    Parser () {}
    public static String[] getTodo(String userInput) {
        return userInput.split(" ");
    }

    public static String[] getDeadline(String userInput) {
        String intermediateStage = userInput.replace("deadline ", "");
        String[] deadlineAndDescription = intermediateStage.split("/by ");
        return deadlineAndDescription;
    }

    public static String[] getEvent(String userInput) {
        String intermediateStage = userInput.replace("event ", "");
        String[] eventDescription = intermediateStage.split("/from | /to");
        return eventDescription;
    }

    public boolean isTheSame(String userInput, String toCompare) {
        return userInput.split(" ")[0].equals(toCompare);
    }

    public boolean isInRange(String userInput, TaskList taskList) {
        boolean isReturn = false;
        try {
            isReturn = Integer.parseInt(userInput.split(" ")[1])>0 && Integer.parseInt(userInput.split(" ")[1])<taskList.getSize()+1;
        } catch (NumberFormatException e) {
            System.out.println("\tWhoops, need to ensure that your inputs are numbers! BUT a");
        }
        return (isReturn);
    }
}
