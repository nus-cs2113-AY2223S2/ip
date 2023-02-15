package duke.tasks;

public class Task {

    public String name;
    public Boolean status;


    public Task(String name, Boolean status){
        this.name = name;
        this.status = status;
    }


    public void setStatus(String action){
        if (action.equals("mark")){
            this.status = true;
        } else if (action.equals("unmark")) {
            this.status = false;
        }
    }


    public String toString(){
        String checkbox = "[ ]";
        String typeIndicator = null;
        if(status){
            checkbox = "[X]";
        }

        return  checkbox + " " + name;
    }

    public String toTextFileFormat(){
        return name + status;
    }
}
