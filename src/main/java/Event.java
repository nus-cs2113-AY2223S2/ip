public class Event extends Task {

    protected String start;
    protected String end;
    public Event(String description) {
        super(description);
        String[] splitLine = description.split("/", 2);
        String[] getDescription = splitLine[0].split(" ",2);
        String[] splitPeriod = splitLine[1].split("/",2);
        String[] splitStartPreposition = splitPeriod[0].split(" ", 2);
        String[] splitEndPreposition = splitPeriod[1].split(" ", 2);

        this.description = getDescription[1];
        this.start = "(" + splitStartPreposition[0] + ": " + splitStartPreposition[1];
        this.end = splitEndPreposition[0] + ": " + splitEndPreposition[1] +")";
        this.isDone = false;
        this.label = "[E]";
    }
}
