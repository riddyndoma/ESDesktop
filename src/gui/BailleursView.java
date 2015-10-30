/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import beans.JUtils;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import model.Bailleurs;
import org.jdesktop.swingx.JXTable;

/**
 *
 * @author Dell
 */
public class BailleursView extends javax.swing.JPanel {

    static JXTable myTable;
    private static final String PERSISTENCE_UNIT_NAME = "ESDeskAppPU";
    private static EntityManagerFactory factory;

    /**
     * Creates new form BailleursView
     */
    public BailleursView() {
        createModel();
        initComponents();
        scrll.getViewport().setBackground(Color.WHITE);
        scrll.setViewportView(myTable);
    }

    private void createModel() {
        myTable = new JXTable(new MyModelTable(1));
        myTable.getTableHeader().setReorderingAllowed(false);
        myTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        myTable.setShowGrid(false);
        myTable.getColumnModel().getColumn(0).setMinWidth(100);
        myTable.getColumnModel().getColumn(0).setMaxWidth(100);
        myTable.getColumnModel().getColumn(1).setMinWidth(100);
        myTable.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent evt) {
                if (evt.isPopupTrigger()) {
//                    pop.show(table, evt.getX(), evt.getY());
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                try {
//                    if (!myTable.getValueAt(myTable.getSelectedRow(), 1).toString().equals("")) {
//                        bEdit.setEnabled(true);
//                        bDelete.setEnabled(true);
//                        txtID.setText(myTable.getValueAt(myTable.getSelectedRow(), 0).toString());
//                        txtDistrict.setText(myTable.getValueAt(myTable.getSelectedRow(), 1).toString());
//                        cbProvince.setSelectedItem(myTable.getValueAt(myTable.getSelectedRow(), 2).toString());
//                        testUpdate = true;
//                        ID_UPDATE = myTable.getValueAt(myTable.getSelectedRow(), 0).toString();
//                    } else {
//                        bEdit.setEnabled(false);
//                        bDelete.setEnabled(false);
//                        testUpdate = false;
//                    }
                } catch (Exception ex) {
                }
            }
        });
        TableCellRenderer headerRenderer = myTable.getTableHeader().getDefaultRenderer();
        ((DefaultTableCellRenderer) headerRenderer).setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

    }

    public class MyModelTable extends AbstractTableModel {

        private String[] columnNames = {
            JUtils.setBlackColor("CODE"), JUtils.setBlackColor("NOM")};
        private ArrayList[] Data;

        public MyModelTable(int taille) {

            Data = new ArrayList[columnNames.length];
            for (int i = 0; i < columnNames.length; i++) {
                Data[i] = new ArrayList();

            }
            for (int i = 0; i < columnNames.length; i++) {
                for (int j = 0; j < taille; j++) {
                    Data[i].add(j, "");

                }
            }
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public int getRowCount() {
            return Data[0].size();
        }

        @Override
        public String getColumnName(int col) {
            return columnNames[col];
        }

        @Override
        public Object getValueAt(int row, int col) {
            return Data[col].get(row);
        }

        @Override
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        @Override
        public boolean isCellEditable(int row, int col) {
            return (true);
        }

        @Override
        public void setValueAt(Object value, int row, int col) {
            Data[col].set(row, value);
            fireTableCellUpdated(row, col);
        }

        public void addNewRow() {
            for (int i = 0; i < columnNames.length; i++) {
                Data[i].add(Data[i].size(), "");
            }
            this.fireTableRowsInserted(0, Data[0].size() - 1);
        }

        public void removeNewRow(int index) {
            if (getRowCount() == 0 || index < 0) {
                return;
            }
            for (int i = 0; i < 1; i++) {
                Data[i].remove(index);
            }
            for (int i = 1; i < columnNames.length; i++) {
                Data[i].remove(index);
            }
            this.fireTableRowsDeleted(0, Data[0].size() - 1);
        }

        public void removeNewRow() {
            for (int i = 0; i < columnNames.length; i++) {
                Data[i].remove(Data[i].size() - 1);
            }
            this.fireTableRowsDeleted(0, Data[0].size() - 1);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSpinner1 = new javax.swing.JSpinner();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCode = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNom = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtAdresse = new javax.swing.JTextField();
        scrll = new javax.swing.JScrollPane();
        jToolBar1 = new javax.swing.JToolBar();
        bSave = new javax.swing.JButton();

        setBackground(java.awt.Color.white);

        jPanel1.setBackground(java.awt.Color.white);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setText("Code");

        jLabel2.setText("Nom");

        jLabel3.setText("Adresse");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNom, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
                    .addComponent(txtCode)
                    .addComponent(txtAdresse, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtAdresse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jToolBar1.setBackground(java.awt.Color.white);
        jToolBar1.setRollover(true);

        bSave.setBackground(java.awt.Color.white);
        bSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/img/saveb.png"))); // NOI18N
        bSave.setFocusable(false);
        bSave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bSave.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSaveActionPerformed(evt);
            }
        });
        jToolBar1.add(bSave);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(73, 73, 73))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrll)
                .addContainerGap())
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(scrll, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void bSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSaveActionPerformed
        Bailleurs bls = new Bailleurs();
        bls.setCode(txtCode.getText());
        bls.setAdresse(txtAdresse.getText());
        bls.setNom(txtNom.getText());
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(bls);
        em.getTransaction().commit();
        em.close();


    }//GEN-LAST:event_bSaveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JScrollPane scrll;
    private javax.swing.JTextField txtAdresse;
    private javax.swing.JTextField txtCode;
    private javax.swing.JTextField txtNom;
    // End of variables declaration//GEN-END:variables
}
