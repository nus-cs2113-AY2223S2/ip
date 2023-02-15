package duke;

import duke.task.Task;

public class Pair {
    public Task[] tasks;
    public int tasksIndex;
    public Pair(Task[] tasks, int tasksIndex) {
        this.tasks = tasks;
        this.tasksIndex = tasksIndex;
    }
}
