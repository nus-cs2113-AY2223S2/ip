package sage.utility;


/**
 * A class containing methods for parsing of raw user input for easier reading
 */
public class Parser {
    private String[] rawOutput;
    private String taskType;
    private String taskDescription;
    private String by = null;
    private String from = null;
    private String to = null;

    private static final Integer MAXSPLIT = 2;
    private static final Integer TASKTYPEINDEX = 0;
    private static final Integer TASKDESCRIPTIONINDEX = 1;

    /**
     * Parses user input into its respective task type, description and other attributes
     *
     * @param input raw user input to be parsed
     */
    public Parser(String input) {
        this.rawOutput = input.split("/");
        String[] body = rawOutput[TASKTYPEINDEX].split(" ", MAXSPLIT);
        this.taskType = body[TASKTYPEINDEX].toLowerCase();
        if (body.length > 1) {
            this.taskDescription = body[TASKDESCRIPTIONINDEX];
        }

        for (String i : rawOutput) {
            String[] line = i.split(" ", MAXSPLIT);
            String field = line.length > 1 ? line[TASKDESCRIPTIONINDEX].trim() : null;
            switch (line[TASKTYPEINDEX]) {
            case "by":
                this.by = field;
                break;
            case "from":
                this.from = field;
                break;
            case "to":
                this.to = field;
                break;
            }
        }

    }

    public String getTaskType() {
        return taskType;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public String getBy() {
        return by;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}
