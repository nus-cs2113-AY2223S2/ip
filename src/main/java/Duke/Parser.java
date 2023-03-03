package Duke;

import Duke.Exception.NullCommandException;
import Duke.Task.Task;

public class Parser {
    private boolean isRunning = true;

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
            } catch (StringIndexOutOfBoundsException e) {
                return "XIOB";
            } catch (NullCommandException e) {
                return "XNC";
            }
        }
    }

    public boolean getIsRunning() {
        return this.isRunning;
    }

    public void setIsRunning(boolean bool) {
        this.isRunning = bool;
    }
}
