/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

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
}
