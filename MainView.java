/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.List;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Stefan, Tobias
 */
public class MainView extends javax.swing.JFrame {
    private MainControl control;
    private List<User> showing;
    boolean defaulttext = true;
    private final static Logger LOG = Logger.getLogger("*");
            
    /**
     * Creates new form MainView
     */
    public MainView(MainControl control) {
        this.control = control;
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        initComponents();
        //TODO: ActionListener button?
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblUserList = new javax.swing.JTable();
        lblStatusBar = new javax.swing.JLabel();
        btnCreate = new javax.swing.JButton();
        txtSearchText = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnExtend = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        lblLogo = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();
        btnShowTransactions = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("HenkeNet account manager");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        tblUserList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Username", "Room", "Surname", "Givenname", "Expiration"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblUserList.getTableHeader().setReorderingAllowed(false);
        tblUserList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUserListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblUserList);
        tblUserList.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        lblStatusBar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblStatusBar.setName("lblStatusBar"); // NOI18N

        btnCreate.setText("Create account");
        btnCreate.setToolTipText("");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        txtSearchText.setText("Search by username, room or real name...");
        txtSearchText.setNextFocusableComponent(btnSearch);
        txtSearchText.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSearchTextMouseClicked(evt);
            }
        });
        txtSearchText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchTextKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchTextKeyReleased(evt);
            }
        });

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnExtend.setText("Extend validity");
        btnExtend.setToolTipText("");
        btnExtend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExtendActionPerformed(evt);
            }
        });

        btnEdit.setText(" Edit account");
        btnEdit.setToolTipText("");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HenkeNet-128x128.png"))); // NOI18N
        lblLogo.setName("LogoLabel"); // NOI18N

        btnDelete.setText("Delete");
        btnDelete.setToolTipText("");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnShowTransactions.setText("Show history");
        btnShowTransactions.setToolTipText("");
        btnShowTransactions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowTransactionsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblStatusBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtSearchText)
                                .addGap(18, 18, 18)
                                .addComponent(btnSearch))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnExtend, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnShowTransactions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSearchText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSearch))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblLogo)
                        .addGap(22, 22, 22)
                        .addComponent(btnEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnExtend)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnShowTransactions)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCreate)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblStatusBar, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        lblStatusBar.getAccessibleContext().setAccessibleName("txtStatusBar");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        control.closeConn();
    }//GEN-LAST:event_formWindowClosing

    private void tblUserListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUserListMouseClicked
        if (evt.getClickCount() == 2) {
            // do what a double click does
            // -> open the edit window                                     
            User currusr = getSelectedUser();
            if (currusr != null) {
                control.showECForm(currusr);
            }
        }
    }//GEN-LAST:event_tblUserListMouseClicked

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        control.showECForm(null);
    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        updateBrowserView();
    }
    
    public void updateBrowserView() {
        showing = control.doSearch(txtSearchText.getText());
        //delete from tbl
        DefaultTableModel model = (DefaultTableModel)tblUserList.getModel(); 
        int rows = model.getRowCount(); 
        for(int i = rows - 1; i >=0; i--)
        {
           model.removeRow(i); 
        }
        for(User u : showing){
            addRow(u, model);
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        User currusr = getSelectedUser();
        if(currusr!=null){
            control.showECForm(currusr);
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void txtSearchTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchTextKeyPressed
        //Remove default text
        if(defaulttext){
            txtSearchText.setText("");
            defaulttext= false;
        }
            
    }//GEN-LAST:event_txtSearchTextKeyPressed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        User currusr = getSelectedUser();
        if(currusr!=null){
            control.initDelete(currusr);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnExtendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExtendActionPerformed
       User currusr = getSelectedUser();
        if(currusr!=null){
            control.initExtend(currusr);
        }
    }//GEN-LAST:event_btnExtendActionPerformed

    private void txtSearchTextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSearchTextMouseClicked
        //Remove default text
        if(defaulttext){
            txtSearchText.setText("");
            defaulttext= false;
        }
    }//GEN-LAST:event_txtSearchTextMouseClicked

    private void txtSearchTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchTextKeyReleased
        updateBrowserView();
    }//GEN-LAST:event_txtSearchTextKeyReleased

    private void btnShowTransactionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowTransactionsActionPerformed
        User currentUser = getSelectedUser();
        if (currentUser != null) 
            control.showTransactionHistory(currentUser);
    }//GEN-LAST:event_btnShowTransactionsActionPerformed

    private void addRow(User u, DefaultTableModel model){
        model.addRow(new Object[]{u.getUsername(), u.getRoom(), u.getSurname(),
            u.getGivenname(), u.getExpirationDate()});
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnExtend;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnShowTransactions;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblStatusBar;
    private javax.swing.JTable tblUserList;
    private javax.swing.JTextField txtSearchText;
    // End of variables declaration//GEN-END:variables
    
    public User getSelectedUser(){
        DefaultTableModel model = (DefaultTableModel)tblUserList.getModel(); 
        if(model.getRowCount()!=0){
            int userrow = tblUserList.getSelectedRow();
            if(userrow==-1){
               LOG.warning("No user selected.");
                return null; 
            }
            return showing.get(userrow);
        } else {
            LOG.warning("No user selected.");
            return null;
        }
    }
    
    public JLabel getStatusBar() {
        return lblStatusBar;
    }

    public JTable getTblUserList() {
        return tblUserList;
    }

    public JTextField getTxtSearchText() {
        return txtSearchText;
    }
}
