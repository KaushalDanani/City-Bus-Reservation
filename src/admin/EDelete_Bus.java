package admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

public class EDelete_Bus extends javax.swing.JFrame {

    /**
     * Creates new form EDelete_Bus
     */
    
    Connection con;
    public EDelete_Bus() {
        initComponents();
        
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Bus-Favicon.png")));
        this.setTitle("CITY TRAVELS");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
        TableHeader();
        
        Connection con;
        try {
            con = makeCon();
            setData(con);
        } catch (IOException ex) {
            Logger.getLogger(EDelete_Bus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private Connection makeCon() throws IOException{
        Properties props = new Properties();
        InputStream input = getClass().getClassLoader().getResourceAsStream("resources/config.properties");
        if (input != null) {
            props.load(input);
        } else {
            throw new FileNotFoundException("Property file not found");
        }

        String url = props.getProperty("db.url");
        String username = props.getProperty("db.username");
        String password = props.getProperty("db.password");

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, password);
            return con;
        }
        catch(ClassNotFoundException | SQLException e){
            System.out.println(e);
            return null;
        }
    }
    
    private void setData(Connection con){
        try {
            String q = "select * from bus_schedule";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(q);
            while(rs.next()){
                String b_no = rs.getString(1);
                String f = rs.getString(2);
                String t = rs.getString(3);
                String dt = rs.getString(4);
                String d_time = rs.getString(5);
                String seats = String.valueOf(rs.getInt(6));
                String price = String.valueOf(rs.getInt(7));
                
                String tbData[] = {b_no, f, t, dt, d_time, seats, price};
                DefaultTableModel tbm = (DefaultTableModel) tbl_edit.getModel();
                
                tbm.addRow(tbData);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Admin_Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void editData(){
        int index = tbl_edit.getSelectedRow();
        if(index == -1){
            JOptionPane.showMessageDialog(null, "Select a row first!!");
        }
        else{
            TableModel tmdl = tbl_edit.getModel();
            String bus_no = tmdl.getValueAt(index, 0).toString();
            String from = tmdl.getValueAt(index, 1).toString();
            String to = tmdl.getValueAt(index, 2).toString();
            String date = tmdl.getValueAt(index, 3).toString();
            String d_time = tmdl.getValueAt(index, 4).toString();
            int seats = Integer.parseInt(tmdl.getValueAt(index, 5).toString());
            int price = Integer.parseInt(tmdl.getValueAt(index, 6).toString());
            int i = JOptionPane.showConfirmDialog(null, "Do you want to edit bus details having bus no : " + bus_no + " ?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if(i == 0){
                dispose();
                Edit_Bus eb = new Edit_Bus(bus_no, from, to, date, d_time, seats, price);
            }
        }
    }
    
    private void deleteData(Connection con){
        int index = tbl_edit.getSelectedRow();
        if(index == -1){
            JOptionPane.showMessageDialog(null, "Select a row first!!");
        }
        else{
            TableModel tmdl = tbl_edit.getModel();
            String bus_no = tmdl.getValueAt(index, 0).toString();
            String from = tmdl.getValueAt(index, 1).toString();
            String to = tmdl.getValueAt(index, 2).toString();
            String date = tmdl.getValueAt(index, 3).toString();
            String d_time = tmdl.getValueAt(index, 4).toString();
            int seats = Integer.parseInt(tmdl.getValueAt(index, 5).toString());
            int price = Integer.parseInt(tmdl.getValueAt(index, 6).toString());
            int i = JOptionPane.showConfirmDialog(null, "Do you want to delete bus details having bus no : " + bus_no + " ?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if(i == 0){
                try {
                    String q = "delete from bus_schedule where bus_no=? and dt=? and departure_time=?";
                    PreparedStatement ps = con.prepareStatement(q);
                    ps.setString(1, bus_no);
                    ps.setString(2, date);
                    ps.setString(3, d_time);
                    ps.executeUpdate();
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EDelete_Bus.class.getName()).log(Level.SEVERE, null, ex);
                }
                JOptionPane.showMessageDialog(null, "Bus details deleted successfully!");
                dispose();
                Admin_Dashboard ad = new Admin_Dashboard();
            }
        }
        
    }
    
    private void TableHeader()
    {
        JTableHeader thead = tbl_edit.getTableHeader();
        thead.setBackground(Color.BLACK);
        thead.setFont(new Font("Times New Roman", Font.BOLD, 16));
        
        TableColumn col1 = tbl_edit.getColumnModel().getColumn(0);
        col1.setPreferredWidth(79);
        TableColumn col2 = tbl_edit.getColumnModel().getColumn(1);
        col2.setPreferredWidth(135);
        TableColumn col3 = tbl_edit.getColumnModel().getColumn(2);
        col3.setPreferredWidth(135);
        TableColumn col4 = tbl_edit.getColumnModel().getColumn(3);
        col4.setPreferredWidth(105);
        TableColumn col5 = tbl_edit.getColumnModel().getColumn(4);
        col5.setPreferredWidth(120);
        TableColumn col6 = tbl_edit.getColumnModel().getColumn(5);
        col6.setPreferredWidth(120);
        TableColumn col7 = tbl_edit.getColumnModel().getColumn(6);
        col7.setPreferredWidth(80);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_edit = new javax.swing.JTable();
        btn_edit = new javax.swing.JButton();
        btn_deletebus = new javax.swing.JButton();
        edelete_back = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Baskerville Old Face", 1, 32)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Edit / Delete Bus");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 25, 939, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setText("* Select Bus to Edit/Delete");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 133, -1, -1));

        tbl_edit.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        tbl_edit.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Bus No.", "From", "To", "Date", "Departure Time", "Available Seats", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_edit.setRowHeight(25);
        tbl_edit.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tbl_edit);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 171, 858, 233));

        btn_edit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_edit.setText("Edit");
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });
        getContentPane().add(btn_edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(223, 434, 83, -1));

        btn_deletebus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_deletebus.setText("Delete");
        btn_deletebus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deletebusActionPerformed(evt);
            }
        });
        getContentPane().add(btn_deletebus, new org.netbeans.lib.awtextra.AbsoluteConstraints(416, 434, 93, -1));

        edelete_back.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        edelete_back.setText("Back");
        edelete_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edelete_backActionPerformed(evt);
            }
        });
        getContentPane().add(edelete_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(612, 434, 84, -1));

        jPanel1.setBackground(new java.awt.Color(238, 78, 52));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 941, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 941, 90));

        jPanel2.setBackground(new java.awt.Color(252, 237, 218));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 941, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 410, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 941, 410));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void edelete_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edelete_backActionPerformed
        // TODO add your handling code here:
        dispose();
        Admin_Dashboard ad = new Admin_Dashboard();
    }//GEN-LAST:event_edelete_backActionPerformed

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        // TODO add your handling code here:
        editData();
    }//GEN-LAST:event_btn_editActionPerformed

    private void btn_deletebusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deletebusActionPerformed
        try {
            con = makeCon();
            deleteData(con);
        } catch (IOException ex) {
            Logger.getLogger(EDelete_Bus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_deletebusActionPerformed

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
            java.util.logging.Logger.getLogger(EDelete_Bus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EDelete_Bus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EDelete_Bus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EDelete_Bus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EDelete_Bus().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_deletebus;
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton edelete_back;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_edit;
    // End of variables declaration//GEN-END:variables
}
