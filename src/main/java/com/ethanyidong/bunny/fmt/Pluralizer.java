package com.ethanyidong.bunny.fmt;

public class Pluralizer {
    private String singularForm;
    private String pluralForm;
    private int count;

    public Pluralizer(String singularForm, String pluralForm, int count) {
        this.singularForm = singularForm;
        this.pluralForm = pluralForm;
        this.count = count;
    }

    @Override
    public String toString() {
        if(this.count == 1) {
            return this.singularForm;
        }
        return this.pluralForm;
    }
}
