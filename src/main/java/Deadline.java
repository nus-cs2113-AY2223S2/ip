//A different kind of Task

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Deadline extends Task {
    /** Date of the deadline stored as a LocalDate object */
    private final LocalDate date;
    /** Time of deadline stored as a String */
    private final String time;

    Deadline(boolean isDone, String taskDescription, String deadline) {
        super("D", isDone, taskDescription);
        String[] dateTimeArray = deadline.split(" ");
        int[] dateArray = this.splitData(dateTimeArray[0]);
        this.date = LocalDate.of(dateArray[2], dateArray[1], dateArray[0]);
        this.time = dateTimeArray.length == 2 ? dateTimeArray[1] : "";
    }

    Deadline(String[] parameters) {
        this(parameters[1].equals("true") ? true : false,
                parameters[2], parameters[3]);
    }

    /**
     * Returns a new Task that is marked
     */
    public Task mark() {
        return new Deadline(true, super.taskDescription,
            this.date + " " + this.time);
    }

    /**
     * Returns a new Task that is unmarked
     */
    public Task unmark() {
        return new Deadline(false, super.taskDescription,
            this.date + " " + this.time);
    }

    /**
     * Extracts date information from user input
     *
     * @param data String that represents a date
     * @return int[3] of {date, month, year}
     */
    public int[] splitData(String data) {
        String[] deadlineArray = new String[3];
        if (data.contains("/")) {
            deadlineArray = data.split("/");
        } else {
            deadlineArray = data.split("-");
        }
        int[] deadlineArguments = new int[3];
        deadlineArguments[0] = Integer.parseInt(deadlineArray[0]);
        deadlineArguments[1] = Integer.parseInt(deadlineArray[1]);
        deadlineArguments[2] = Integer.parseInt(deadlineArray[2]);
        if (deadlineArray[0].length() == 4) {
            deadlineArguments[0] = Integer.parseInt(deadlineArray[2]);
            deadlineArguments[2] = Integer.parseInt(deadlineArray[0]);
        }
        return deadlineArguments;
    }
    @Override
    public String toString() {
        String formattedDeadline = "(by: " + this.date.format(
            DateTimeFormatter.ofPattern("MMM d yyyy")) + " " +this.time + ")";
        return super.toString() + " " +formattedDeadline;
    }

    /**
     * Formats details of the Deadline Task to be stored in the database
     *
     * @return Formatted string that represents the Deadline
     */
    public String toStringForDatabase() {
        return super.CommonFieldsFor_toStringForDatabase() + "," +
             this.date + " " + this.time;
    }
}