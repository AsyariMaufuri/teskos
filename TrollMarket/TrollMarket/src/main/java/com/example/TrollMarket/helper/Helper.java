package com.example.TrollMarket.helper;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class Helper {
    public static String FormatIDR(BigDecimal bigDecimal){
        Locale indo = new Locale("id","ID");
        String formatindo = NumberFormat.getCurrencyInstance(indo).format(bigDecimal);
        return formatindo;
    }
}
