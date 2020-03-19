package models.validators;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import models.Record;

public class RecordValidator {
    public static List<String> validate(Record r) {
        List<String> errors = new ArrayList<String>();

        String overview_error = _validateOverview(r.getOverview());
        if(!overview_error.equals("")) {
            errors.add(overview_error);
        }

        String tranzaction_error = _validateTranzaction(r.getTranzaction());
        if(!tranzaction_error.equals("")) {
            errors.add(tranzaction_error);
        }

        String use_date_error = _validateUseDate(r.getUse_date());
        if(!use_date_error.equals("")) {
            errors.add(use_date_error);
        }

        return errors;
    }

    private static String _validateOverview(String overview) {
        if(overview == null || overview.equals("")) {
            return "概要を入力して下さい。";
        }

        return "";
    }

    private static String _validateTranzaction(String tranzaction) {
        if(tranzaction == null || tranzaction.equals("")) {
            return "取引額を入力して下さい。";
        }

        return "";
    }

    private static String _validateUseDate(Date use_date) {
        if(use_date == null || use_date.equals("")) {
            return "取引日を入力して下さい。";
        }

        return "";
    }
}
