package Duke;

import Duke.Exception.NullCommandException;
import Duke.Task.Task;

public class Parser {
    private boolean isRunning = true;

    /**
     * Parse the input from user to determine what to do with the given input by appending
     * a characteristic letter in front.
     *
     * @param line input from user to be parsed.
     * @return a string of user's input with characteristic letter appended to the front.
     * @throws NullCommandException If input is in invalid format.
     */
    public String parseCommand(String line) {
        if (line.equals("bye")) {
            return "B";
        } else if (line.equals("list")) {
            return "L";
        } else if (line.startsWith("mark")) {
            return "M" + line;
        } else if (line.startsWith("unmark")) {
            return "U" + line;
        } else if (line.startsWith("delete")) {
            return "D" + line;
        } else if (line.startsWith("find")) {
            return "F" + line;
        } else {
            try {
                Task newTask = new Task(line);
                if (line.startsWith("todo")) {
                    return "T" + line;
                } else if (line.startsWith("deadline")) {
                    return "A" + line;
                } else if (line.startsWith("event")) {
                    return "E" + line;
                } else {
                    throw new NullCommandException();
                }
            } catch (NullCommandException e) {
                return "XNC";
            }
        }
    }

    /**
     * Gets the boolean value of parser object, for termination check of program
     *
     * @return boolean value of isRunning.
     */
    public boolean getIsRunning() {
        return this.isRunning;
    }

    /**
     * Sets the boolean value of parser object, to terminate the program
     *
     * @param bool boolean value of parser object to be set.
     */
    public void setIsRunning(boolean bool) {
        this.isRunning = bool;
    }
}
