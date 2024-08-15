package login;

import admin.Admin_Dashboard;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Admin_Login extends javax.swing.JFrame {

    /**
     * Creates new form Admin_Login
     */
    public Admin_Login() {
        initComponents();
        
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Bus-Favicon.png")));
        setTitle("CITY TRAVELS");
        setResizable(false);
        setLocationRelativeTo(null);
        
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_h_adminlogin = new javax.swing.JLabel();
        lbl_1 = new javax.swing.JLabel();
        txt_adminid = new javax.swing.JTextField();
        lbl_2 = new javax.swing.JLabel();
        txt_adminpwd = new javax.swing.JPasswordField();
        btn_adminlogin_login = new javax.swing.JButton();
        btn_adminlogin_back = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_h_adminlogin.setFont(new java.awt.Font("Baskerville Old Face", 1, 28)); // NOI18N
        lbl_h_adminlogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_h_adminlogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/login/admin_login_Img.png"))); // NOI18N
        lbl_h_adminlogin.setText("  Admin Login");
        getContentPane().add(lbl_h_adminlogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 417, 60));

        lbl_1.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
        lbl_1.setText("Admin ID : ");
        getContentPane().add(lbl_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(68, 139, -1, -1));

        txt_adminid.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
        getContentPane().add(txt_adminid, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 136, 175, -1));

        lbl_2.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
        lbl_2.setText("Password : ");
        getContentPane().add(lbl_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(69, 202, -1, -1));

        txt_adminpwd.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        getContentPane().add(txt_adminpwd, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 199, 175, -1));

        btn_adminlogin_login.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_adminlogin_login.setText("Login");
        btn_adminlogin_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_adminlogin_loginActionPerformed(evt);
            }
        });
        getContentPane().add(btn_adminlogin_login, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 267, -1, -1));

        btn_adminlogin_back.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_adminlogin_back.setText("Back");
        btn_adminlogin_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_adminlogin_backActionPerformed(evt);
            }
        });
        getContentPane().add(btn_adminlogin_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(261, 267, -1, -1));

        jPanel1.setBackground(new java.awt.Color(238, 78, 52));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 420, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, 100));

        jPanel2.setBackground(new java.awt.Color(252, 237, 218));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 420, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 240, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 96, 420, 240));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_adminlogin_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_adminlogin_loginActionPerformed
        // TODO add your handling code here:
        Properties props = new Properties();
        InputStream input = getClass().getClassLoader().getResourceAsStream("resources/config.properties");
        try {
            if (input != null) {
                props.load(input);
            } 
            else {
                throw new FileNotFoundException("Property file not found");
            }
        } 
        catch (IOException ex) {
            Logger.getLogger(Admin_Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String admin_id = props.getProperty("admin.id");
        String admin_pwd = props.getProperty("admin.password");
        String ent_username = txt_adminid.getText();
        String ent_pwd = txt_adminpwd.getText();
        
        if(ent_username.equals(admin_id) && ent_pwd.equals(admin_pwd)){
            dispose();
            Admin_Dashboard ad = new Admin_Dashboard();
        }
        else if(ent_username.isEmpty()){
            JOptionPane.showMessageDialog(null, "Please, Enter Admin ID...!");
        }
        else if(ent_pwd.isEmpty()){
            JOptionPane.showMessageDialog(null, "Please, Enter PASSWORD...!");
        }
        else{
            JOptionPane.showMessageDialog(null, "Invalid Credentials/Admin...!");
        }
    
    }//GEN-LAST:event_btn_adminlogin_loginActionPerformed

    private void btn_adminlogin_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_adminlogin_backActionPerformed
        // TODO add your handling code here:
        
        dispose();
        First_frame uli = new First_frame();
    }//GEN-LAST:event_btn_adminlogin_backActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(Admin_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin_Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_adminlogin_back;
    private javax.swing.JButton btn_adminlogin_login;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbl_1;
    private javax.swing.JLabel lbl_2;
    private javax.swing.JLabel lbl_h_adminlogin;
    private javax.swing.JTextField txt_adminid;
    private javax.swing.JPasswordField txt_adminpwd;
    // End of variables declaration//GEN-END:variables
}
