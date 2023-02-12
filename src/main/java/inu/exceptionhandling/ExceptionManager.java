package inu.exceptionhandling;

import inu.task.TaskList;

public class ExceptionManager {

    public static void checkEmptyUserInput(String... strings) throws EmptyUserInputException {

        for (String s : strings) {

            if (s.isEmpty()) {

                throw new EmptyUserInputException();

            }
        }

    }

    public static void checkEmptyTaskList(TaskList taskList) throws EmptyTaskListException {

        if (taskList.getTaskListSize() == 0) {

            throw new EmptyTaskListException();

        }

    }

}