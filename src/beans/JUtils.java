/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import com.toedter.calendar.JDateChooser;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Dell
 */
public class JUtils {

    public static void getLAF() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
        }
    }

    public static String setBlueColor(String text) {
        return "<html><font color=" + "blue" + "><b>" + text + "</b></font></html>";
    }

    public static String setBlackColor(String text) {
        return "<html><font color=" + "black" + "><b>" + text + "</b></font></html>";
    }

    public static String setRedColor(String text) {
        return "<html><font color=" + "red" + "><b>" + text + "</b></font></html>";
    }

    static DateFormatSymbols mySimpleDateFormatSymb = new DateFormatSymbols();
    public static SimpleDateFormat mySimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", mySimpleDateFormatSymb);
    public static SimpleDateFormat mySimpleHeure = new SimpleDateFormat("H:s", mySimpleDateFormatSymb);
    public static SimpleDateFormat myHeureDateFormat = new SimpleDateFormat("dd-MM-yyyy H:s", mySimpleDateFormatSymb);

    public static String getDateInString(JDateChooser dt) {
        return new SimpleDateFormat("yyyy-MM-dd", new DateFormatSymbols()).format(dt.getDate());
    }
}
