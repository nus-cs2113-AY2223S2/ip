public class Event extends Task{
    public String description;
    public Event(String input) {
        String[] strArray = input.split("/");
        this.description = strArray[0].replace("deadline ", "") + ( "(" + strArray[1].replace("from", "from:")) + (strArray[2].replace("to", "to:") + ")");
        items.add(description);
        marked.add(false);
        tasks.add(TaskType.EVENT);
        print();
    }
}
