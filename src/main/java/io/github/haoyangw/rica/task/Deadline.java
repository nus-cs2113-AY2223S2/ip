package io.github.haoyangw.rica.task;

import io.github.haoyangw.rica.exception.RicaTaskException;

public class Deadline extends Todo {
    private static final String COMMAND = "deadline";
    private static final String DEADLINE_KEYWORD = "/by";
    private static final String MISSING_DEADLINE_ERROR = " Forget something? (Hint: Where's the deadline for this deadline task?)";
    private static final String MISSING_DESCRIPTION_ERROR = " Did you give a description for this deadline task xD";
    private static final String TYPE = "D";
    private static final String WRONG_CREATE_CMD_ERROR = " Hmm I'm being told to set a deadline with the wrong command. Help xP";
    protected final String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    private static String getDescriptionOf(String command) throws RicaTaskException {
        String[] parameters = command.split(" ");
        int MISSING_PARAMS = 1;
        if (parameters.length == MISSING_PARAMS) {
            throw new RicaTaskException(Deadline.MISSING_DESCRIPTION_ERROR);
        }
        StringBuilder descBuilder = new StringBuilder();
        for (int i = 1; i < parameters.length; i += 1) {
            if (parameters[i].equals(Deadline.DEADLINE_KEYWORD)) {
                if (i == 1) {
                    throw new RicaTaskException(Deadline.MISSING_DESCRIPTION_ERROR);
                }
                break;
            }
            if (i != 1) {
                descBuilder.append(" ");
            }
            descBuilder.append(parameters[i]);
        }
        return descBuilder.toString();
    }

    private static String getDeadlineOf(String command) throws RicaTaskException {
        int deadlineKeywordFirstIndex = command.indexOf(Deadline.DEADLINE_KEYWORD);
        final int NOT_FOUND = -1;
        if (deadlineKeywordFirstIndex == NOT_FOUND) {
            throw new RicaTaskException(Deadline.MISSING_DEADLINE_ERROR);
        }
        int deadlineKeywordLastIndex = deadlineKeywordFirstIndex
                + Deadline.DEADLINE_KEYWORD.length();
        String deadline = command.substring(deadlineKeywordLastIndex).trim();
        if (deadline.isBlank()) {
            throw new RicaTaskException(Deadline.MISSING_DEADLINE_ERROR);
        }
        return deadline;
    }

    public static Deadline create(String command) throws RicaTaskException {
        String[] parameters = command.split(" ");
        // Wrong command for adding a deadline
        if (!parameters[0].equals(Deadline.COMMAND)) {
            throw new RicaTaskException(Deadline.WRONG_CREATE_CMD_ERROR);
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
