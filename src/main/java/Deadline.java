public class Deadline extends Task {
    public String description;


    public Deadline(String input) {
        String[] strArray = input.split("/");
        this.description = strArray[0].replace("deadline ", "") + ( "(" + strArray[1].replace("by", "by:") + ")");
        items.add(description);
        marked.add(false);
        tasks.add(TaskType.DEADLINE);
        print();
    }

}
