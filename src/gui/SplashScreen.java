package gui;

import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import org.jdesktop.swingx.painter.ImagePainter;

/**
 *
 * @author RDN
 */
public class SplashScreen extends javax.swing.JDialog {

    /**
     * Creates new form Splash
     *
     * @param parent
     * @param modal
     */
    public SplashScreen(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        try {
            paneau.setBackgroundPainter(new ImagePainter(getClass().getResource("/res/img/ima.png")));
        } catch (Exception e) {
        }
        this.setIconImage(new ImageIcon(this.getClass().getResource("/res/img/java.png")).getImage());
        this.setResizable(false);
        this.setLocationRelativeTo(this);

        t = new Timer(50, (ActionEvent e) -> {
            comp++;
            if (comp == 100) {
                end();
            }
        });
        t.start();
    }

    public void end() {
        t.stop();
        this.dispose();
//        Authentified authentified = new Authentified();
//        authentified.setVisible(true);
        ESDeskView.getInstance("admin").setVisible(true); 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        paneau = new org.jdesktop.swingx.JXPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        paneau.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout paneauLayout = new javax.swing.GroupLayout(paneau);
        paneau.setLayout(paneauLayout);
        paneauLayout.setHorizontalGroup(
            paneauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        paneauLayout.setVerticalGroup(
            paneauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 222, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(paneau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(paneau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXPanel paneau;
    // End of variables declaration//GEN-END:variables
    Timer t;
    int comp = 0;
}
