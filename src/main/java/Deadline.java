public class Deadline extends Todo {
    private static final String COMMAND = "deadline";
    private static final String DEADLINE_KEYWORD = "/by";
    private static final String TYPE = "D";
    protected final String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    private static String getDescriptionOf(String command) {
        String[] parameters = command.split(" ");
        StringBuilder descBuilder = new StringBuilder();
        for (int i = 1; i < parameters.length; i += 1) {
            if (parameters[i].equals(Deadline.DEADLINE_KEYWORD)) {
                break;
            }
            if (i != 1) {
                descBuilder.append(" ");
            }
            descBuilder.append(parameters[i]);
        }
        return descBuilder.toString();
    }

    private static String getDeadlineOf(String command) {
        int deadlineKeywordLastIndex = command.indexOf(Deadline.DEADLINE_KEYWORD)
                + Deadline.DEADLINE_KEYWORD.length();
        String deadline = command.substring(deadlineKeywordLastIndex).trim();
        return deadline;
    }

    public static Deadline create(String command) {
        String[] parameters = command.split(" ");
        // Wrong command for adding a deadline
        if (!parameters[0].equals(Deadline.COMMAND)) {
            return null;
        }
        String description = Deadline.getDescriptionOf(command);
        String deadline = Deadline.getDeadlineOf(command);
        return new Deadline(description, deadline);
    }

    public String getDeadline() {
        return this.deadline;
    }

    @Override
    protected String getType() {
        return Deadline.TYPE;
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), this.getDeadline());
    }

}
