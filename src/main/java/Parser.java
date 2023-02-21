/**
 * This class represents the parser that parses the input command and returns a command.
 * @param parse method to parse the input command and return a command
 * @param input the input command
 * @param command the command to be returned
 * @throws DukeException if the input command is not in the correct format
 */

public class Parser {
    //parse the input string and return a command
    public static Command parse(String input) throws DukeException{
        //split the input string
        String[] inputSplit = input.split(" ");
        //if the input is bye
        if(input.equals("bye")){
            //return a bye command
            return new ByeCommand();
        }
        //if the input is list
        else if(input.equals("list")){
            //return a list command
            return new ListCommand();
        }
        //if the input is done
        else if(inputSplit[0].equals("mark")){
            //if the input is not done followed by a number
            if(inputSplit.length == 1){
                //throw an exception
                throw new DukeException("OOPS!!! The description of a done cannot be empty.");
            }
            //if the input is done followed by a number
            else{
                //try to parse the number
                try{
                    //parse the number
                    int taskNumber = Integer.parseInt(inputSplit[1]);
                    //return a done command
                    return new MarkCommand(taskNumber);
                } catch(NumberFormatException e){
                    //throw an exception
                    throw new DukeException("OOPS!!! The description of a done must be a number.");
                }
            }
        }
        //if the input is unmark
        else if(inputSplit[0].equals("unmark")){
            //if the input is not unmark followed by a number
            if(inputSplit.length == 1){
                //throw an exception
                throw new DukeException("OOPS!!! The description of a unmark cannot be empty.");
            }
            //if the input is unmark followed by a number
            else{
                //try to parse the number
                try{
                    //parse the number
                    int taskNumber = Integer.parseInt(inputSplit[1]);
                    //return a unmark command
                    return new UnmarkCommand(taskNumber);
                } catch(NumberFormatException e){
                    //throw an exception
                    throw new DukeException("OOPS!!! The description of a unmark must be a number.");
                }
            }
        }
        //if the input is delete
        else if(inputSplit[0].equals("delete")){
            //if the input is not delete followed by a number
            if(inputSplit.length == 1){
                //throw an exception
                throw new DukeException("OOPS!!! The description of a delete cannot be empty.");
            }
            //if the input is delete followed by a number
            else{
                //try to parse the number
                try{
                    //parse the number
                    int taskNumber = Integer.parseInt(inputSplit[1]);
                    //return a delete command
                    return new DeleteCommand(taskNumber);
                } catch(NumberFormatException e){
                    //throw an exception
                    throw new DukeException("OOPS!!! The description of a delete must be a number.");
                }
            }
        }
        //if the input is todo
        else if(inputSplit[0].equals("todo")){
            //if the input is not todo followed by a description
            if(inputSplit.length == 1){
                //throw an exception
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }
            //if the input is todo followed by a description
            else{
                //get the description
                String description = input.substring(5);
                //return a todo command
                return new TodoCommand(description);
            }
        }
        //if the input is deadline
        else if(inputSplit[0].equals("deadline")){
            //if the input is not deadline followed by a description
            if(inputSplit.length == 1){
                //throw an exception
                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
            }
            //if the input is deadline followed by a description
            else{
                //get the description
                String description = input.substring(9);
                //if the description does not contain a /by
                if(!description.contains("/by")){
                    //throw an exception
                    throw new DukeException("OOPS!!! The description of a deadline must contain a /by.");
                }
                //if the description contains a /by
                else{
                    //get the description and the date
                    String[] descriptionSplit = description.split("/by");
                    //return a deadline command
                    return new DeadlineCommand(descriptionSplit[0], descriptionSplit[1]);
                }
            }
        }
        //if the input is event
        else if(inputSplit[0].equals("event")){
            //if the input is not event followed by a description
            if(inputSplit.length == 1){
                //throw an exception
                throw new DukeException("OOPS!!! The description of a event cannot be empty.");
            }
            //if the input is event followed by a description
            else{
                //get the description
                String description = input.substring(6);
                //if the description does not contain a /at
                if(!description.contains("/at")){
                    //throw an exception
                    throw new DukeException("OOPS!!! The description of a event must contain a /at.");
                }
                //if the description contains a /at
                else{
                    //get the description and the date
                    String[] descriptionSplit = description.split("/at");
                    //return a event command
                    return new EventCommand(descriptionSplit[0], descriptionSplit[1]);
                }
            }
        }

        //return a invalid command of the input is not any of the above
        else{
            return new Command();
        }

            

    }
    
}
