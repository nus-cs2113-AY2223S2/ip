public class Deadline extends Task {
    protected String by;

    public Deadline (String description, String by) {
        super(description);
        this.by = by;
        this.type = "D";
    }

//    public String getDescription() {
//        String event = description.substring(description.indexOf(" ")+1,description.indexOf("/")-1);
//        return event;
//    }
//
//    public String getDateTime() {
//        dateTime = description.substring(description.indexOf("/by "), description.length());
//        return dateTime;
//    }


    public String toString() {
        return super.toString() + "(by: " + by +")";
    }
}