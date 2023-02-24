package duke.keycommands;

import duke.Common;

public class ChangeTaskStatusCommand {

    private static final String FINISH_UNMARKING_MESSAGE = "Ok! I've marked this task as not done yet:";
    private static final String FINISH_MARKING_MESSAGE = "Nice! I've marked this task as done:";
    String userInput;
    String[] separatedKeyWordAndContent;
    String keyword;

    public ChangeTaskStatusCommand(String userInput, String[] separatedKeyWordAndContent, String keyword) {
        this.userInput = userInput;
        this.separatedKeyWordAndContent = separatedKeyWordAndContent;
        this.keyword = keyword;
        doChangeTaskStatus();
    }

    public void doChangeTaskStatus() {
        if (userInput.split(" ").length != 2) {
            System.out.println(Common.INSTRUCTION + "\n"
                    + keyword + ": Number");
        } else {
            changeTaskStatus(separatedKeyWordAndContent);
        }
    }
    private static void changeTaskStatus(String[] seperatedWords) {
        try {
            int lastWordInInteger = Integer.parseInt(seperatedWords[1]);
            if (lastWordInInteger > Common.tasks.size()) {
                System.out.println(Common.BIG_NUMBER);
            } else {
                boolean doesCommandContainsUnmark = seperatedWords[0].equals("unmark");
                if (doesCommandContainsUnmark) {
                    Common.tasks.get(lastWordInInteger - 1).unMark();
                    System.out.println(FINISH_UNMARKING_MESSAGE);
                } else {
                    Common.tasks.get(lastWordInInteger - 1).mark();
                    System.out.println(FINISH_MARKING_MESSAGE);
                }
                System.out.println(lastWordInInteger + ". " + Common.tasks.get(lastWordInInteger - 1).getMarkingStatus()
                        + " " + Common.tasks.get(lastWordInInteger - 1).getContent());
            }
        } catch (Exception error) {
            System.out.println(Common.INSTRUCTION + "\n" + seperatedWords[0] + ": Number");
        }
    }

}
