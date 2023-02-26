package tusky.commands;

public enum CommandType {
        BYE("bye"),
        LIST("list"),
        MARK("mark"),
        UNMARK("unmark"),
        TODO("todo"),
        EVENT("event"),

        DEADLINE("deadline"),
        DELETE("delete");
        private final String text;
        CommandType(String text){
            this.text = text;
        }
        public String getText(){
            return text;
        }
}
