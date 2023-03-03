package duke;

/**
 * DukeException is for command word not recognised by Duke
 * TaskEmpty is when the content of the command word recognised is empty
 */
public class DukeException extends Exception{
    public static class TaskEmpty extends Exception{}
}
