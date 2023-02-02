package com.ethanyidong.bunny.fmt;

public class Formatter {
    public static String pluralize(String singularForm, String pluralForm, int count) {
        if(count == 1) {
            return singularForm;
        }
        return pluralForm;
    }
}
