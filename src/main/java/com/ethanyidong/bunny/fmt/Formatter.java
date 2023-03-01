package com.ethanyidong.bunny.fmt;

/**
 * Helper class to perform various String formatting functions for output
 */
public class Formatter {
    /**
     * @param singularForm the singular form of the word
     * @param pluralForm the plural form of the word
     * @param count the count of the object in question
     * @return <code>singularForm</code> if the count is equal to 1, or <code>pluralForm</code> otherwise
     */
    public static String pluralize(String singularForm, String pluralForm, int count) {
        if (count == 1) {
            return singularForm;
        }
        return pluralForm;
    }
}
