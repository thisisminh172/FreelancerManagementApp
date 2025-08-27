/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JSpinner;

/**
 *
 * @author thisi
 */
public class Common {

    public static String convertDateSpinnerToString(Object oj) {
        java.util.Date date = (java.util.Date) oj;
        // Format to yyyy-MM-dd
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateOutput = sdf.format(date);
        return dateOutput;
    }

    public static LocalDate convertStringToLocalDate(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate customDate = LocalDate.parse(dateStr, formatter);
        return customDate;
    }
}
