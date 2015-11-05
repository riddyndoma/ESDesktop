/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import beans.JUtils;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import model.Bailleurs;
import model.Entree;
import org.jdesktop.swingx.JXTable;

/**
 *
 * @author Dell
 */
public final class EntreeView extends javax.swing.JPanel {
    
    static JXTable myTable;
    private static final String PERSISTENCE_UNIT_NAME = "ESDeskAppPU";
    private static EntityManagerFactory factory;
    boolean testUpdate = false;// State of edit button
    List<Bailleurs> bailleurList;

    /**
     * Creates new form BailleursView
     */
    public EntreeView() {
        createModel();
        
        EntreeView.fillDataValuesInTable();
        initComponents();
        this.getAllItemsType();
        onButtonBehavior();
        scrll.getViewport().setBackground(Color.WHITE);
        scrll.setViewportView(myTable);
    }
    
    private void createModel() {
        myTable = new JXTable(new MyModelTable(1));
        myTable.getTableHeader().setReorderingAllowed(false);
        myTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        myTable.setShowGrid(false);
        myTable.getColumnModel().getColumn(0).setMinWidth(100);
//        myTable.getColumnModel().getColumn(0).setMaxWidth(100);
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
                    if (!myTable.getValueAt(myTable.getSelectedRow(), 0).toString().equals("")) {
                        bEdit.setEnabled(true);
                        bDelete.setEnabled(true);
                        txtLibelle.setText(myTable.getValueAt(myTable.getSelectedRow(), 0).toString());
                        txtMontant.setText(myTable.getValueAt(myTable.getSelectedRow(), 1).toString());
                        cbBailleurs.setSelectedItem(myTable.getValueAt(myTable.getSelectedRow(), 3).toString());
                        dcDateEntree.setDate(new Date(myTable.getValueAt(myTable.getSelectedRow(), 2).toString())); 
                        testUpdate = true;
                    } else {
                        bEdit.setEnabled(false);
                        bDelete.setEnabled(false);
                        testUpdate = false;
                    }
                } catch (Exception ex) {
                }
            }
        });
        TableCellRenderer headerRenderer = myTable.getTableHeader().getDefaultRenderer();
        ((DefaultTableCellRenderer) headerRenderer).setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        
    }
    
    public class MyModelTable extends AbstractTableModel {
        
        private final String[] columnNames = {JUtils.setBlackColor("LIBELLE"), JUtils.setBlackColor("MONTANT"), JUtils.setBlackColor("DATE"), JUtils.setBlackColor("BAILLEURS")};
        private final ArrayList[] Data;
        
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
    
    public static void clearTable() {
        MyModelTable model = (MyModelTable) myTable.getModel();
        int row = myTable.getRowCount();
        while (row > 0) {
            model.removeNewRow();
            row--;
        }
    }
    
    public void refresh() {
        txtLibelle.setText("");
        txtMontant.setText("");
        cbBailleurs.setSelectedIndex(-1); 
        testUpdate = false;
        EntreeView.clearTable();
        EntreeView.fillDataValuesInTable();
        stateEditButtonOnTable();
    }
    
    private void stateEditButtonOnTable() {
        int comp = 0;
        for (int i = 0; i < myTable.getRowCount(); i++) {
            if (myTable.isRowSelected(i) == false) {
                comp++;
            }
        }
        if (comp == myTable.getRowCount()) {
            bEdit.setEnabled(false);
            bDelete.setEnabled(false);
        }
    }
    
    public void getAllItemsType() {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        
        Query q = em.createNamedQuery("Bailleurs.findAll");
        bailleurList = q.getResultList();
        bailleurList.stream().forEach((bailleurList1) -> {
            cbBailleurs.addItem(bailleurList1.getNom());
        });
        cbBailleurs.setSelectedIndex(-1);
    }
    
    public static void fillDataValuesInTable() {
        myTable.setEditable(true);
        
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        
        Query q = em.createNamedQuery("Entree.findAll");
        List<Entree> entreeList = q.getResultList();
        int j = 0;
        for (Entree entree : entreeList) {
            if (j >= myTable.getModel().getRowCount()) {
                MyModelTable model = (MyModelTable) myTable.getModel();
                model.addNewRow();
            }
            myTable.setValueAt(entree.getLibelle(), j, 0);
            myTable.setValueAt(entree.getMontant(), j, 1);
            myTable.setValueAt(entree.getDateentree(), j, 2);
            myTable.setValueAt(entree.getBailleurid().getNom(), j, 3);
            
            j++;
        }
        
        myTable.setEditable(false);
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
        txtLibelle = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cbBailleurs = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        txtMontant = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        dcDateEntree = new com.toedter.calendar.JDateChooser();
        scrll = new javax.swing.JScrollPane();
        jToolBar1 = new javax.swing.JToolBar();
        bSave = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        bActualize = new javax.swing.JButton();
        bDelete = new javax.swing.JButton();
        bEdit = new javax.swing.JButton();

        setBackground(java.awt.Color.white);

        jPanel1.setBackground(java.awt.Color.white);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setText("Libelle");

        jLabel2.setText("Bailleur");

        jLabel3.setText("Montant");

        jLabel4.setText("Date");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtLibelle, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                    .addComponent(cbBailleurs, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMontant, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                    .addComponent(dcDateEntree, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtLibelle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtMontant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(dcDateEntree, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbBailleurs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jToolBar1.setBackground(java.awt.Color.white);
        jToolBar1.setRollover(true);

        bSave.setBackground(java.awt.Color.white);
        bSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/img/saveb.png"))); // NOI18N
        bSave.setEnabled(false);
        bSave.setFocusable(false);
        bSave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bSave.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSaveActionPerformed(evt);
            }
        });
        jToolBar1.add(bSave);
        jToolBar1.add(jSeparator1);

        bActualize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/img/refresh.png"))); // NOI18N
        bActualize.setFocusable(false);
        bActualize.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bActualize.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bActualize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bActualizeActionPerformed(evt);
            }
        });
        jToolBar1.add(bActualize);

        bDelete.setBackground(java.awt.Color.white);
        bDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/img/delete.png"))); // NOI18N
        bDelete.setEnabled(false);
        bDelete.setFocusable(false);
        bDelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bDelete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDeleteActionPerformed(evt);
            }
        });
        jToolBar1.add(bDelete);

        bEdit.setBackground(java.awt.Color.white);
        bEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/img/edit_g.png"))); // NOI18N
        bEdit.setFocusable(false);
        bEdit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bEdit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEditActionPerformed(evt);
            }
        });
        jToolBar1.add(bEdit);

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
                .addComponent(scrll, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
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
                .addComponent(scrll, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void bSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSaveActionPerformed
        Entree entree = new Entree();
        entree.setLibelle(txtLibelle.getText());
        entree.setMontant(Double.valueOf(txtMontant.getText()));
        entree.setDateentree(dcDateEntree.getDate());        
        
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        
        Query q = em.createNamedQuery("Bailleurs.findByNom");
        q.setParameter("nom", cbBailleurs.getSelectedItem().toString());
        Bailleurs bl = (Bailleurs) q.getSingleResult();
        entree.setBailleurid(bl);
        
        em.persist(entree);
        em.getTransaction().commit();
        em.close();
        clearTable();
        fillDataValuesInTable();
        JOptionPane.showMessageDialog(this, "Enregistrement effectué !");
        refresh();
    }//GEN-LAST:event_bSaveActionPerformed

    private void bDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDeleteActionPerformed
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Query q = em.createNamedQuery("Entree.deleteByLibelleAndMontant");
        q.setParameter("libelle", (myTable.getValueAt(myTable.getSelectedRow(), 0).toString()));
        q.setParameter("montant", Double.parseDouble((myTable.getValueAt(myTable.getSelectedRow(), 1).toString())));
        q.executeUpdate();
        em.getTransaction().commit();
        em.close();
        clearTable();
        fillDataValuesInTable();
        JOptionPane.showMessageDialog(this, "Suppression effectuée !");
        refresh();
    }//GEN-LAST:event_bDeleteActionPerformed

    private void bEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEditActionPerformed
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Query q = em.createNamedQuery("entreefd.update");
        q.setParameter("libelle", txtLibelle.getText());
        q.setParameter("libelleKey", (myTable.getValueAt(myTable.getSelectedRow(), 0).toString()));
        q.executeUpdate();
        em.getTransaction().commit();
        em.close();
        clearTable();
        fillDataValuesInTable();
        JOptionPane.showMessageDialog(this, "Mise à jour effectuée !");
        refresh();
    }//GEN-LAST:event_bEditActionPerformed

    private void bActualizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bActualizeActionPerformed
        refresh();
    }//GEN-LAST:event_bActualizeActionPerformed
    
    private void onButtonBehavior() {
        Timer temps = new Timer(20, (ActionEvent e) -> {
            if (txtLibelle.getText().equals("")
                    || testUpdate == true) {
                bSave.setEnabled(false);
            } else {
                bSave.setEnabled(true);
            }
        });
        temps.start();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bActualize;
    private javax.swing.JButton bDelete;
    private javax.swing.JButton bEdit;
    private javax.swing.JButton bSave;
    private javax.swing.JComboBox cbBailleurs;
    private com.toedter.calendar.JDateChooser dcDateEntree;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JScrollPane scrll;
    private javax.swing.JTextField txtLibelle;
    private javax.swing.JTextField txtMontant;
    // End of variables declaration//GEN-END:variables
}
