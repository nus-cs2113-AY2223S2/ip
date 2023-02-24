import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

class Deadline extends Task {
    private final LocalDate date;
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

    public Task mark() {
        return new Deadline(true, super.taskDescription,
            this.date + " " + this.time);
    }

    public Task unmark() {
        return new Deadline(false, super.taskDescription,
            this.date + " " + this.time);
    }

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

    public String toStringForDatabase() {
        return super.CommonFieldsFor_toStringForDatabase() + "," +
             this.date + " " + this.time;
    }
}