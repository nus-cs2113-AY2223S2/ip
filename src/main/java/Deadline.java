public class Deadline extends Task {
    String by;

    Deadline(String content, String end) {
        super(content);
        this.by = end;
    }

    @Override
    public String toString() {
        String returnStr = "[D]";
        if (getIsDone()) {
            returnStr = returnStr.concat("[O] ");
        } else {
            returnStr = returnStr.concat("[ ] ");
        }

        return returnStr + getContents() + "| by " + by;
    }
}
