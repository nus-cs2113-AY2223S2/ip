package com.ethanyidong.bunny.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

public class FlexibleDate {
    private String dateString;
    private LocalDate concreteDate;

    public FlexibleDate(String dateString) {
        this.dateString = dateString;
        try {
            this.concreteDate = LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            this.concreteDate = null;
        }
    }

    public boolean isConcrete() {
        return this.concreteDate != null;
    }

    @Override
    public String toString() {
        if (this.isConcrete()) {
            return this.concreteDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
        } else {
            return this.dateString;
        }
    }
}
