package com.animationdemo;

/**
 * Created by NITISH on 1/14/2016.
 */

import android.text.InputFilter;
import android.text.Spanned;

import java.text.NumberFormat;

public class InputFilterMinMax implements InputFilter {

    private float min, max;
    private int maxDecimal;

    public InputFilterMinMax(float min, float max, int maxDecimal) {
        this.min = min;
        this.max = max;
        if (maxDecimal > 0) {
            this.maxDecimal = maxDecimal;
        } else {
            this.maxDecimal = 0;
        }
    }

    public InputFilterMinMax(String min, String max, int maxDecimal) {
        this.min = Float.parseFloat(min);
        this.max = Float.parseFloat(max);
        if (maxDecimal > 0) {
            this.maxDecimal = maxDecimal;
        } else {
            this.maxDecimal = 0;
        }

    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        try {
            String inputString = dest.toString() + source.toString();
            float input = Float.parseFloat(inputString);
            if (isInRange(min, max, input) && ((inputString.length() > 0 && inputString.contains(".")) ? ((maxDecimal > 0) ? ((inputString.length() - 1) <= (inputString.indexOf(".") + maxDecimal)) : true) : true))
                return null;
        } catch (NumberFormatException nfe) {
        }
        return "";
    }

    private boolean isInRange(float a, float b, float c) {
        return b > a ? c >= a && c <= b : c >= b && c <= a;
    }
}