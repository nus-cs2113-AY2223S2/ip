package errors;

public class ErrorMessages {

    //The following are meant for Tasks
    public String provideTaskEmptyDescriptionText(){
        return "Description cannot be empty :<, please write a description after the task keyword (i.e. todo/deadline/event)";
    }
    public String provideTaskNoDeadlineParamsText(){
        return "You did not include the /by parameter, please split include it for Pikapi!";
    }
    public String provideTaskEmptyDescriptionForDeadlineText(){
        return "Description cannot be empty :<, please write a description after the word deadline";
    }
    public String provideTaskEmptyDueDateDeadlineText(){
        return "DueDate cannot be empty :<, please write a the description of a deadline";
    }
    public String provideTaskEmptyStartDateEventText(){
        return "Start Date cannot be empty :<";
    }
    public String provideTaskEmptyEndDateEventText(){
        return "End Date cannot be empty :<";
    }
    public String provideTaskWrongEventFormatText(){
        return "You did not key in a correct format for events :< ";
    }
    public String provideTaskWrongEventParamsText(){
        return "Pikapi's events should include /to and /from fields!";
    }
    public String provideWrongTaskNameText(){
        return "Pikapi does not understand this task, please give Pikapi a todo, deadline or event task";
    }

    //The following are meant for Marking tasks
    public String provideEmptyListText(){
        return "PIKAPII the list is currently empty! You can't mark/unmark an empty list! Please input some tasks for Pikapi to add :3";
    }

    public String provideExceedListLengthText(int listLength){
        if (listLength == 1){
            return "PIKAPII you dont have that many tasks! You only have 1 task hehe :3";
        }
        return "PIKAPII you dont have that many tasks! Please write a number between 1 and " + listLength;
    }

    public String provideInvalidNumberText(){
        return "PIKAPII we dont have negative or tasks indexed as 0 >:<";
    }

    public String provideNoNumberText(){
        return "PIKAPII you did not input a number beside your marking action!";
    }

    public String provideStringAsNumber(){
        return "Pikapiii you did not give me an index(number)! Please give me a number after the mark/unmark/delete keyword please :3 UwU";
    }
    public String provideWrongCommandText(){
        return "Pikapi is unable to find that command, please type in a correct command";
    }

}
