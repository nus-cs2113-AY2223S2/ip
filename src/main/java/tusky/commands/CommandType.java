package tusky.commands;

/**
 * Enum for the different types of commands.
 */
public enum CommandType {
        BYE("bye"),
        LIST("list"),
        MARK("mark"),
        UNMARK("unmark"),
        TODO("todo"),
        EVENT("event"),

        DEADLINE("deadline"),
        DELETE("delete"),
        FIND("find");
        private final String text;
        CommandType(String text){
            this.text = text;
        }
        public String getText(){
            return text;
        }
}
