public class Event extends Task{
    //protected String[] dateTime;
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        //String[] descriptionArray = description.split("/");
        //this.description = descriptionArray[0].substring(descriptionArray[0].indexOf(" "), descriptionArray[0].length());
//        this.dateTime = getDateTime();
//        this.description = getDescription();
        this.from = from;
        this.to = to;
        this.type = "E";
    }

//    public String getDescription() {
//        description = description.substring(description.indexOf(" ")+1,description.indexOf("/")-1);
//        return description;
//    }
//
//    public String[] getDateTime() {
//        String date = description.substring(description.indexOf("/")+1, description.length());
//        dateTime = date.split("/"); //split into 2 segments: from and to
//        return dateTime;
//    }

//    public String startDate() {
//        String dateTime = getDateTime();
//        String start = dateTime.substring(dateTime.indexOf("/from"), dateTime.indexOf(" /to"));
//        return start;
//    }
//
//    public String endDate() {
//        String dateTime = getDateTime();
//        String end = dateTime.substring(dateTime.indexOf("/to"), dateTime.length());
//        return end;
//    }

//    public void printTask() {
//        String status = getStatusIcon();
//        String type = getType();
//        System.out.print("[" + type + "][" + status + "]" + description.substring(description.indexOf(" "), description.indexOf(" /")));
//        System.out.println(" (" + startDate().replace("/from ", "from: ") + " " +endDate().replace("/to ", "to: ") + ")");
//    }
    public String toString() {
//        dateTime[0].replace("from", "");
//        dateTime[1].replace("to", "");
        return super.toString() + "(from: " + from.replace("from ", "") + " to: " + to + ")";
    }
}
