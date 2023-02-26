public class GootExceptionHandler {
    public static void unidentifiedKeyword() {
        System.out.println("I only understand stuff like \'todo have fun\' where todo is the keyword and have fun is " +
                "the additional information");
    }
    public static void wrongNumberOfSlashesDeadline(){
        System.out.println("Deadlines can only contain ONE \'/\' in the form of \'/by\'");
    }
    public static void wrongNumberOfSlashesEvent(){
        System.out.println("Events can only contain TWO \'/\' in the form of \'/from\' and  \'/to\'");
    }

    public static void fileNotFound(){
        System.out.println("I can't find the file :((");
    }


    public static void validateDeadline (String[] inputArray)throws GootExceptions{
        if (inputArray.length!=2 || !(inputArray[1].contains("by"))){
            throw new GootExceptions();
        }
    }
    public static void validateEvent (String[] inputArray)throws GootExceptions{
        if (inputArray.length!=3 || !(inputArray[1].contains("from")) || !(inputArray[2].contains("to"))){
            throw new GootExceptions();
        }
    }


}
