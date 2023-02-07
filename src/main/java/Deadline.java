public class Deadline extends Todo {
    private static final String COMMAND = "deadline";
    private static final String TYPE = "D";
    protected final String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public static Deadline create(String command) {
        final String DEADLINE_KEYWORD = "/by";
        String[] parameters = command.split(" ");
        // Wrong command for adding a deadline
        if (!parameters[0].equals(Deadline.COMMAND)) {
            return null;
        }
        StringBuilder descBuilder = new StringBuilder();
        int keyword_index = parameters.length;
        for (int i = 1; i < parameters.length; i += 1) {
            if (parameters[i].equals(DEADLINE_KEYWORD)) {
                keyword_index = i;
                break;
            }
            if (i != 1) {
                descBuilder.append(" ");
            }
            descBuilder.append(parameters[i]);
        }
        String description = descBuilder.toString();
        // No deadline given, cannot create a valid deadline object
        if (keyword_index >= (parameters.length + 1)) {
            return null;
        }
        StringBuilder deadlineBuilder = new StringBuilder();
        for (int i = keyword_index + 1; i < parameters.length; i += 1) {
            if (i != (keyword_index + 1)) {
                deadlineBuilder.append(" ");
            }
            deadlineBuilder.append(parameters[i]);
        }
        String deadline = deadlineBuilder.toString();
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
