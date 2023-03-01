package io.github.haoyangw.rica.task;

import io.github.haoyangw.rica.exception.RicaSerializationException;
import io.github.haoyangw.rica.exception.RicaTaskException;

/**
 * Represents a deadline kind of Task that consists of a due date
 */
public class Deadline extends Todo {
    private static final String COMMAND = "deadline";
    private static final String DEADLINE_KEYWORD = "/by";
    private static final String MISSING_DEADLINE_ERROR = " Forget something? (Hint: Where's the deadline for this deadline task?)";
    private static final String MISSING_DESCRIPTION_ERROR = " Did you give a description for this deadline task xD";
    private static final int NUM_OF_SERIALIZED_DATA = 4;
    private static final String WRONG_CREATE_CMD_ERROR = " Hmm I'm being told to set a deadline with the wrong command. Help xP";
    protected static final String TYPE = "D";
    protected final String deadline;

    public Deadline(String description, String deadline) {
        this(description, false, deadline);
    }

    private Deadline(String description, boolean isDone, String deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    /**
     * Parses a command issued by the user and extracts the deadline description
     *   portion, which is returned to the caller
     *
     * @param command Full command issued by the user
     * @return String Object containing the description of the deadline to be created
     * @throws RicaTaskException If command doesn't contain a description of the
     *   deadline to be created
     */
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

    /**
     * Parses a command issued by the user and extracts the date of the deadline,
     *   which is returned to the caller.
     *
     * @param command Full command issued by the user
     * @return String Object containing the date of the deadline to be created
     * @throws RicaTaskException If command doesn't contain a date to set as the
     *   deadline of the new Task
     */
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

    /**
     * Parses a full command issued by the user and creates a new deadline Task
     *   with the given parameters
     *
     * @param command Full command issued by the user
     * @return Instance of Deadline containing the designated due date and Task
     *   description
     * @throws RicaTaskException If the wrong command was issued(not 'deadline')
     */
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

    /**
     * Takes a String representation of a Deadline(probably saved in the data file)
     *   and re-creates an instance of Deadline with the corresponding state variables.
     *
     * @param objectData String representation of all of Deadline's state variables
     * @return Instance of Deadline with the previously saved state
     */
    public static Deadline deserializeObject(String objectData) {
        String[] variables = objectData.split(Task.DATA_STRING_SEPARATOR_REGEX);
        if (variables.length < Deadline.NUM_OF_SERIALIZED_DATA) {
            throw new RicaSerializationException(Task.INCOMPLETE_SERIALIZED_OBJECT_STRING);
        }
        if (!variables[0].equals(Deadline.TYPE)) {
            throw new RicaSerializationException(Task.WRONG_SERIALIZED_OBJECT_TYPE);
        }
        String description = variables[1];
        boolean isDone = Boolean.parseBoolean(variables[2]);
        String deadline = variables[3];
        return new Deadline(description, isDone, deadline);
    }

    @Override
    protected String getType() {
        return Deadline.TYPE;
    }

    /**
     * Saves all of this Deadline instance's state into a String Object that can
     *   then be written to persistent storage.
     *
     * @return String representation of Deadline instance's state
     * @see io.github.haoyangw.rica.storage.Serializable
     */
    @Override
    public String serializeObject() {
        String todoData = super.serializeObject();
        return todoData + Task.DATA_STRING_SEPARATOR + this.getDeadline();
    }

    /**
     * Sets this Deadline Task as done/not done by the user
     *
     * @param isDone Whether this Deadline is now done
     * @return Instance of Deadline with isDone state variable set accordingly
     */
    @Override
    public Deadline setDone(boolean isDone) {
        return new Deadline(super.getDescription(), isDone, this.getDeadline());
    }

    /**
     * Generates a user-friendly String representation of this Deadline instance
     *   for the user to understand this Deadline's current state
     *
     * @return String representation of this Deadline instance's state
     */
    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), this.getDeadline());
    }

}
