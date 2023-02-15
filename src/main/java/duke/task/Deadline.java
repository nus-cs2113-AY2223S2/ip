package duke.task;
import duke.Task;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String taskType, String by) {
        super(description, taskType);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String saveText() {
<<<<<<< HEAD
        return "D | " + (super.isDone ? 1 : 0) + " | " + super.description + " | " + by;
    }
}
=======
        return "D | " + (super.isDone ? 1 : 0) + " | " +
                super.description + " | " + by;
    }
}
>>>>>>> branch-Level-7
