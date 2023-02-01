public class Deadline extends Task {
    protected String dateTime;

    public Deadline (String description) {
        super(description);
        this.type = "D";
    }

    public String getDateTime(String description) {
        dateTime = description.substring(description.indexOf("/by "), description.length());
        return dateTime;
    }


    public void printTask() {
        String status = getStatusIcon();
        String type = getType();
        dateTime = getDateTime(description);
        System.out.print("[" + type + "][" + status + "]" + description.substring(description.indexOf(" "), description.indexOf(" /")));
        System.out.println(" (" + dateTime.replace("/by ", "by: ") + ")");
    }
}