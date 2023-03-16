package validation;

import utility.Ui;

public class TaskValidation {
    public static boolean isNull(String description) {
        String removeWhitespace = description.trim();

        if (removeWhitespace.equals("")) {
            Ui.cannotBeNull();
            return false;
        }

        return true;
    }
}
