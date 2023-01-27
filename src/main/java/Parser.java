import java.util.Arrays;
public class Parser {

    public String[] splitCommand(String inputCommand){
        String inputWords[] = inputCommand.split(" ");
        return inputWords;
    }
    public String getCommandType(String inputWords){
        String[] commandWords = splitCommand(inputWords);
        String command = changeCommandLowerCase(commandWords[0]);
//        if(!(command.equals("list")||command.equals("bye")||command.equals("mark")||command.equals("unmark"))){
//            command = "Add";
//        }
        return command;
    }

    public String changeCommandLowerCase(String command){
        return command.toLowerCase();
    }

    public String getCommandDescription(String inputWords){
        //String[] commandWords = splitCommand(inputWords);
        String command = getCommandType(inputWords);
        if(command.equals("bye")||command.equals("list")){
            return inputWords;
        }else{
            //String[] commandDescriptionArray = Arrays.copyOfRange(commandWords, 1, commandWords.length);
            String commandDescriptionString = inputWords.substring((command.length())+1);
            return commandDescriptionString;
        }
    }

    public String[] parseStringToArrayOneElement(String input){
        String[] output = {input};
        return output;
    }
}
