package duke.commands;

import duke.exceptions.LackOfTaskDetail;

public class Deadline extends Task {
    String due;

    private static boolean containTime(String s) {
        boolean haveContent = false;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                haveContent = true;
                break;
            }
        }

        return haveContent;
    }

    public Deadline(String discription, String due) throws LackOfTaskDetail {
        super(discription);
        if (due.equals("") || !containTime(due)) {
            throw new LackOfTaskDetail("No time information!");
        }
        this.due = due;
    }

    public String getDue() {
        return this.due;
    }

    public String toString() {
        return "[D]" + super.toString() + " (" + due + ")";
    }
}
