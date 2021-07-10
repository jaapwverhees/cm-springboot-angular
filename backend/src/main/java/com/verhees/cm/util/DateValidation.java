package com.verhees.cm.util;

import com.verhees.cm.model.exceptions.IllegalDateException;

import java.util.Date;

public class DateValidation {

    public static void checkDateAfterCurrent(Date date) {
        if (date.after(new Date())) {
            throw new IllegalDateException();
        }
    }

    public static void checkDateBeforeCurrent(Date date) {
        if (date.before(new Date())) {
            throw new IllegalDateException();
        }
    }
}
