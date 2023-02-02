public class Deadline extends Task {

    protected String by;

    public static Deadline toDeadline(String instruction){
        int contentIdx = instruction.indexOf("/by");

        if (contentIdx == -1) return null;
        String deadlineContent = instruction.substring(0, contentIdx);
        String deadlineBy = instruction.substring(contentIdx + "/by ".length());
        return new Deadline(deadlineContent, deadlineBy);
    }

    public Deadline(String description, String by) {
        super(description, 'D');
        this.by = by;
    }

    @Override
    public String toString() {
        return super.toString() + "(by: " + by + ")";
    }
}
