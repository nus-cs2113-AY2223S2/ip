public class Task {

    public String name;
    public Boolean status;


    public Task(String name){
        this.name = name;
        this.status = false;
    }


    public void setStatus(String action){
        if (action.equals("mark")){
            this.status = true;
        } else if (action.equals("unmark")) {
            this.status = false;
        }
    }

//    public void printTask(){
//
//    }

    public String toString(){
        String checkbox = "[ ]";
        String typeIndicator = null;
        if(status){
            checkbox = "[X]";
        }

//        switch(type){
//            case "todo":
//                typeIndicator = "[T]";
//                break;
//
//            case "deadline":
//                typeIndicator = "[D]";
//                break;
//
//            case "event":
//                typeIndicator = "[E]";
//                break;
//        }
        return  checkbox + " " + name;
    }
}
