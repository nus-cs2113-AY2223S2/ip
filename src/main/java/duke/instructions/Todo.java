package duke;

public class Todo extends Task{
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getState(){
        String arrayOfTodo = this.description.substring(description.indexOf(" ") + 1);
        return "      [T]" + "[" + getStatusIcon() + "]" +  " "
                + arrayOfTodo + System.lineSeparator();
    }

    @Override
    public String guideline(){
        return "     Got it. I've added this task: "
                + System.lineSeparator();
    }


}


