/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author Dell
 */
public class Launcher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         //new Authentified().setVisible(true);
         new SplashScreen(null, true).setVisible(true);
         new SplashScreen(null, true).setLocationRelativeTo(null);
    }
    
}
