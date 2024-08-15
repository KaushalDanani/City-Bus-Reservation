package user;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.util.StringTokenizer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class My_Bookings extends javax.swing.JFrame {

    private static String username;
    private static String path;
    private static String []str = {"", "", "", "", "", ""};
    //public static String bus_no, from, to, date, d_time, dd, booked_time, no, total_p;
    /**
     * Creates new form My_Bookings
     */
    
    
    public My_Bookings(String username) {
        initComponents();
        
        this.username = username;
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Bus-Favicon.png")));
        this.setTitle("CITY TRAVELS");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
        TableHeader();
        setTableValue();    
    }
    
    public My_Bookings() {
        initComponents();
        
    }
    
    private void TableHeader()
    {
        JTableHeader thead = tbl_history.getTableHeader();
        thead.setBackground(Color.BLACK);
        thead.setFont(new Font("Times New Roman", Font.BOLD, 16));
        
        TableColumn col1 = tbl_history.getColumnModel().getColumn(0);
        col1.setPreferredWidth(79);
        TableColumn col2 = tbl_history.getColumnModel().getColumn(1);
        col2.setPreferredWidth(135);
        TableColumn col3 = tbl_history.getColumnModel().getColumn(2);
        col3.setPreferredWidth(135);
        TableColumn col4 = tbl_history.getColumnModel().getColumn(3);
        col4.setPreferredWidth(105);
        TableColumn col5 = tbl_history.getColumnModel().getColumn(4);
        col5.setPreferredWidth(137);
        TableColumn col6 = tbl_history.getColumnModel().getColumn(5);
        col6.setPreferredWidth(113);
        TableColumn col7 = tbl_history.getColumnModel().getColumn(6);
        col7.setPreferredWidth(142);
        TableColumn col8 = tbl_history.getColumnModel().getColumn(7);
        col8.setPreferredWidth(103);
        TableColumn col9 = tbl_history.getColumnModel().getColumn(8);
        col9.setPreferredWidth(115);
        
    }
        
    static String recursion(String fna, File ff, String []insideFile)
    {
	if(ff.isDirectory())
	{
            int j;
            insideFile = ff.list();
			
            for(j = 0; j<insideFile.length; j++)
            {
		if(fna.equals(insideFile[j]))
		{
                    //System.out.print("\n File Path : "+ff.getAbsolutePath());
                    path = ff.getAbsolutePath();
                    return path;
		}
		else 
		{
                    File ff2 = new File(ff,insideFile[j]);
                    if(ff2.isDirectory())
                    {
			path = recursion(fna, ff2, insideFile);
                    }
		}
            }
	}
        return path;
    }
    
    public void setTableValue()
    {
        try
        {
            FileReader fr = new FileReader("c:\\City Bus\\User Bookings\\User_"+username+"\\BookingTimes.txt");
            BufferedReader br = new BufferedReader(fr);
            
            String line = "";
            String data = "";
            
            while(line != null)
            {
                line = br.readLine();
                
                if(line != null)
                {
                    data = line;
                    
                    String file = "Time_"+data+".txt";
                    
                    // Code for to find out file...
                    try {
                        int i;
			File f1 = new File("c:\\City Bus\\User Bookings\\User_"+username);
		
			String[] mainFile = f1.list();
			String[] insideFile = null;
		
			for(i = 0; i<mainFile.length; i++)
			{
                            File ff = new File(f1, mainFile[i]);
				
                            if(file.equals(mainFile[i]))
                            {
                                path = ""+ff.getAbsolutePath();
                                //System.out.print("\n File Path : "+path);
				break;
                            }
			
                            try {
				if(ff.isDirectory())
				{
                                    path = recursion(file, ff, insideFile);
                                }
                            }
                            catch(Exception e) {
				System.out.print("\n RECURSION ERROR : "+e.getMessage());
                            }
			}
                    }
                    catch(Exception e) {
			System.out.print("\n ERROR 404 : "+e.getMessage());
                    }
                    
                    
                    StringTokenizer st = new StringTokenizer(path,"\\" );
                    
                    int i = -1;
                    
                    while(st.hasMoreTokens())
                    {
                        i++;
                        str[i] = st.nextToken();
                    }
                    
                    String []info = {"", "", "", "", "", "", "", "", ""};
                    try {
                    FileReader fread = new FileReader(""+str[0]+"\\"+str[1]+"\\"+str[2]+"\\"+str[3]+"\\"+str[4]+"\\"+str[5]+"\\Time_"+data+".txt");
                    BufferedReader bb = new BufferedReader(fread);
                    
                    int k = -1;
                    String s = "";
                    
                    while(s != null)
                    {
                        s = bb.readLine();
                        
                        if(s != null)
                        {
                            k++;
                            info[k] = s; 
                        }
                    }
                    bb.close();
                    
                    }
                    catch(Exception e)
                    {
                        System.out.print("\n READING DATA FROM IN PARTS ERROR : "+e.getMessage());
                    }
                    
                    String []tableData = {info[0], info[1], info[2], info[3], info[4], info[5], info[6], info[7], info[8]};
                    
                    DefaultTableModel model = (DefaultTableModel) tbl_history.getModel();
                    model.addRow(tableData);
                                        
                }  
            }
            br.close();
        }
        catch(Exception e)
        {
            System.out.print("\n DATA COPYING ERROR : "+e.getMessage());
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

        jLabel1 = new javax.swing.JLabel();
        mybookings_back = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_history = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Baskerville Old Face", 1, 32)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/user/History_Img.png"))); // NOI18N
        jLabel1.setText("  My Booking History");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 1001, 60));

        mybookings_back.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        mybookings_back.setText("Back");
        mybookings_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mybookings_backActionPerformed(evt);
            }
        });
        getContentPane().add(mybookings_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(451, 520, 100, -1));

        jPanel1.setBackground(new java.awt.Color(238, 78, 52));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1002, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1002, 100));

        jPanel2.setBackground(new java.awt.Color(252, 237, 218));

        tbl_history.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        tbl_history.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Bus No.", "From", "To", "Date", "Departure Time", "Booked Date", "Time of Booking", "No of Seats", "Paid Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_history.setRowHeight(25);
        tbl_history.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tbl_history);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setText("* You can resize table's column size according to your requirement ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 941, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(96, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 1000, 483));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mybookings_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mybookings_backActionPerformed
        // TODO add your handling code here:
        dispose();
        User_Dashboard ud = new User_Dashboard(username);
    }//GEN-LAST:event_mybookings_backActionPerformed

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
            java.util.logging.Logger.getLogger(My_Bookings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(My_Bookings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(My_Bookings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(My_Bookings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new My_Bookings().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton mybookings_back;
    private static javax.swing.JTable tbl_history;
    // End of variables declaration//GEN-END:variables
}
