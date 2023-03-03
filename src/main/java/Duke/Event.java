package Duke;
/**
 * Represents events, one subtype of task.
 * It contains event information, as well as the start date and end date.
 */
public class Event extends Task {
    private String startDate;
    private String endDate;
    public static String TYPE = "event";

    private Event(String content, String startDate, String endDate) {
        super(content);
        this.startDate = startDate;
        this.endDate = endDate;
    }
/**
 * Creates new event.
 *
 * @param commandByWord String array the contains deadline data.
 * @return New event made according to informations provided.
 * @throws IllegalArgumentException When information given in insufficient.
 * @throws ArrayIndexOutOfBoundsException When information given in insufficient.
 */
    public static Event createEvent(String[] commandByWord)
            throws IllegalArgumentException, ArrayIndexOutOfBoundsException {

        if (commandByWord.length < 2) {
            throw new IllegalArgumentException();
        }

        int index = 1;
        String taskToDo = "";
        String startDate = "";
        String endDate = "";

        while (commandByWord[index].charAt(0) != '/') {
            if (index != 1) {
                taskToDo += " ";
            }

            taskToDo += commandByWord[index];
            ++index;
        }
        ++index;
        int indexOfFrom = index;

        while (commandByWord[index].charAt(0) != '/') {
            if (index != indexOfFrom) {
                startDate += " ";
            }

            startDate += commandByWord[index];
            ++index;
        }

        for (int i = index + 1; i < commandByWord.length; ++i) {
            if (i != index + 1) {
                endDate += " ";
            }
            endDate += commandByWord[i];
        }

        return new Event(taskToDo, startDate, endDate);
    }

    public String getType() {
        return Event.TYPE;
    }

    /**
     * Returns boolean on whether the keyword in contained in the task's information.
     * It looks through the event's information and start/end dates to determine keyword's relevance
     *
     * @param keyword Word that is looked for in the task.
     * @return Boolean on whether the task contains the keyword.
     */
    public boolean contains(String keyword) {
        return (this.content.contains(keyword)
                | this.startDate.contains(keyword)
                | this.endDate.contains(keyword));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + this.startDate
                + " to: " + this.endDate + ")";
    }
}
