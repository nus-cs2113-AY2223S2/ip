public class Event extends Task{
    protected String dateTime;

    public Event(String description) {
        super(description);
        this.type = "E";
    }

    public String getDateTime() {
        dateTime = description.substring(description.indexOf("/"), description.length());
        return dateTime;
    }

    public String startDate() {
        String dateTime = getDateTime();
        String start = dateTime.substring(dateTime.indexOf("/from"), dateTime.indexOf(" /to"));
        return start;
    }
    
    public String endDate() {
        String dateTime = getDateTime();
        String end = dateTime.substring(dateTime.indexOf("/to"), dateTime.length());
        return end;
    }

    public void printTask() {
        String status = getStatusIcon();
        String type = getType();
        System.out.print("[" + type + "][" + status + "]" + description.substring(description.indexOf(" "), description.indexOf(" /")));
        System.out.println(" (" + startDate().replace("/from ", "from: ") + " " +endDate().replace("/to ", "to: ") + ")");
    }
}
