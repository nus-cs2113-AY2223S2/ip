package validation;

import utility.Ui;

public class TaskValidation {
    /**
     * Checks if a string is empty
     * @param description String to be checked
     * @return True or false
     */
    public static boolean isNull(String description) {
        String removeWhitespace = description.trim();

        if (removeWhitespace.equals("")) {
            Ui.cannotBeNull();
            return false;
        }

        return true;
    }
}
