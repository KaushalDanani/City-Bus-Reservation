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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class User_Bookings extends javax.swing.JFrame {

    /**
     * Creates new form User_Bookings
     */
    public User_Bookings() {
        initComponents();
        
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Bus-Favicon.png")));
        this.setTitle("CITY TRAVELS");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
        TableHeader();
        
        try {
            Connection con = makeCon();
            setData(con);
            calculateUser(con);
        } catch (IOException ex) {
            Logger.getLogger(User_Bookings.class.getName()).log(Level.SEVERE, null, ex);
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
    
    private void calculateUser(Connection con){
        try {
            String q = "select count(*) from user_bookings";
            PreparedStatement ps = con.prepareStatement(q);
            ResultSet rs = ps.executeQuery(q);
            while(rs.next()){
                int cnt = rs.getInt(1);
                txt_totalbookings.setText(String.valueOf(cnt));
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(User_Bookings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void setData(Connection con){
        try {
            String q = "select * from user_bookings";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(q);
            while(rs.next()){
                String un = rs.getString(1);
                String bn = rs.getString(2);
                String f = rs.getString(3);
                String t = rs.getString(4);
                String dt = rs.getString(5);
                String d_time = rs.getString(6);
                String b_dt = rs.getString(7);
                String b_time = rs.getString(8);
                String seats = String.valueOf(rs.getInt(9));
                String price = String.valueOf(rs.getInt(10));
                
                String[] tbData = {un, bn, f, t, dt, d_time, b_dt, b_time, seats, price};
                DefaultTableModel tbml = (DefaultTableModel) tbl_bookings.getModel();
                tbml.addRow(tbData);
            }
        } catch (SQLException ex) {
            Logger.getLogger(User_Bookings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void TableHeader()
    {
        JTableHeader thead = tbl_bookings.getTableHeader();
        thead.setBackground(Color.BLACK);
        thead.setFont(new Font("Times New Roman", Font.BOLD, 15));
        
        TableColumn col0 = tbl_bookings.getColumnModel().getColumn(0);
        col0.setPreferredWidth(130);
        TableColumn col1 = tbl_bookings.getColumnModel().getColumn(1);
        col1.setPreferredWidth(70);
        TableColumn col2 = tbl_bookings.getColumnModel().getColumn(2);
        col2.setPreferredWidth(140);
        TableColumn col3 = tbl_bookings.getColumnModel().getColumn(3);
        col3.setPreferredWidth(140);
        TableColumn col4 = tbl_bookings.getColumnModel().getColumn(4);
        col4.setPreferredWidth(120);
        TableColumn col5 = tbl_bookings.getColumnModel().getColumn(5);
        col5.setPreferredWidth(135);
        TableColumn col6 = tbl_bookings.getColumnModel().getColumn(6);
        col6.setPreferredWidth(125);
        TableColumn col7 = tbl_bookings.getColumnModel().getColumn(7);
        col7.setPreferredWidth(140);
        TableColumn col8 = tbl_bookings.getColumnModel().getColumn(8);
        col8.setPreferredWidth(100);
        TableColumn col9 = tbl_bookings.getColumnModel().getColumn(9);
        col9.setPreferredWidth(84);
        
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_bookings = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txt_totalbookings = new javax.swing.JLabel();
        bookings_back = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Baskerville Old Face", 1, 32)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admin/user_booking_Img.png"))); // NOI18N
        jLabel1.setText("  User Bookings");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 18, 1101, 58));

        tbl_bookings.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        tbl_bookings.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Username", "Bus No.", "From", "To", "Date", "Departure Time", "Booked Date", "Time of Booking", "No of Seats", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_bookings.setRowHeight(25);
        tbl_bookings.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tbl_bookings);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 184, 1038, 314));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setText("* Total Bookings :");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 142, -1, -1));

        txt_totalbookings.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        txt_totalbookings.setForeground(new java.awt.Color(255, 0, 0));
        txt_totalbookings.setText("0");
        getContentPane().add(txt_totalbookings, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 142, 37, -1));

        bookings_back.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        bookings_back.setText("Back");
        bookings_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookings_backActionPerformed(evt);
            }
        });
        getContentPane().add(bookings_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(555, 527, 92, -1));

        jPanel1.setBackground(new java.awt.Color(238, 78, 52));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1101, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1101, 100));

        jPanel2.setBackground(new java.awt.Color(252, 237, 218));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1101, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 494, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 1101, 494));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bookings_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookings_backActionPerformed
        // TODO add your handling code here:
        dispose();
        Admin_Dashboard ad = new Admin_Dashboard();
    }//GEN-LAST:event_bookings_backActionPerformed

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
            java.util.logging.Logger.getLogger(User_Bookings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(User_Bookings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(User_Bookings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(User_Bookings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new User_Bookings().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bookings_back;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_bookings;
    private javax.swing.JLabel txt_totalbookings;
    // End of variables declaration//GEN-END:variables
}
