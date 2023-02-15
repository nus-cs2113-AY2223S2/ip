package duke.keycommand;

import duke.Task;

public class ToDo extends Task {
    public ToDo(String content) {
        super(content);
    }

    @Override
    public String getClassSymbol() {
        return "[T]";
    }

}
