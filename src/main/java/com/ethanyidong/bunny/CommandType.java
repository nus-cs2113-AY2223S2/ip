package com.ethanyidong.bunny;

import com.ethanyidong.bunny.arg.*;

public enum CommandType {
    BYE,
    LIST,
    MARK {
        @Override
        public CommandValidator[] validators() {
            CommandValidator markIndexValidator =
                    new PositionalArgumentCommandValidator(new TaskIndexArgumentValidator());
            return new CommandValidator[]{markIndexValidator};
        }
    },
    UNMARK {
        @Override
        public CommandValidator[] validators() {
            CommandValidator markIndexValidator =
                    new PositionalArgumentCommandValidator(new TaskIndexArgumentValidator());
            return new CommandValidator[]{markIndexValidator};
        }
    },
    ADD_TODO {
        @Override
        public CommandValidator[] validators() {
            CommandValidator todoNameValidator =
                    new PositionalArgumentCommandValidator(new NotEmptyArgumentValidator());
            return new CommandValidator[]{todoNameValidator};
        }
    },
    ADD_DEADLINE {
        @Override
        public CommandValidator[] validators() {
            CommandValidator deadlineNameValidator =
                    new PositionalArgumentCommandValidator(new NotEmptyArgumentValidator());
            CommandValidator byValidator =
                    new FlagArgumentCommandValidator("by", new NotEmptyArgumentValidator());
            return new CommandValidator[]{deadlineNameValidator, byValidator};
        }
    },
    ADD_EVENT {
        @Override
        public CommandValidator[] validators() {
            CommandValidator eventNameValidator =
                    new PositionalArgumentCommandValidator(new NotEmptyArgumentValidator());
            CommandValidator fromValidator =
                    new FlagArgumentCommandValidator("from", new NotEmptyArgumentValidator());
            CommandValidator toValidator =
                    new FlagArgumentCommandValidator("to", new NotEmptyArgumentValidator());
            return new CommandValidator[]{eventNameValidator, fromValidator, toValidator};
        }
    };
    public CommandValidator[] validators() {
        return new CommandValidator[]{};
    }

    public static CommandType fromString(String command) {
        switch(command) {
        case "bye":
            return CommandType.BYE;
        case "list":
            return CommandType.LIST;
        case "mark":
            return CommandType.MARK;
        case "unmark":
            return CommandType.UNMARK;
        case "todo":
            return CommandType.ADD_TODO;
        case "deadline":
            return CommandType.ADD_DEADLINE;
        case "event":
            return CommandType.ADD_EVENT;
        default:
            // TODO: create error type and throw instead
            return null;
        }
    }
}
