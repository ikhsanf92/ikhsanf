import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ViewSepedaListrik extends javax.swing.JFrame {

    private DefaultTableModel modelTable;
    private Connection connection;

    public ViewSepedaListrik() {
        initComponents();
        initTableModel();
        
        // Membuat koneksi ke database
        connectToDatabase();

        // Tambahkan action listener setelah komponen diinisialisasi
        BtnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambahActionPerformed(evt);
            }
        });
        
        // Tambahkan di konstruktor ViewSepedaListrik setelah komponen diinisialisasi
        BtnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusActionPerformed(evt);
            }
        });
    }
    
    private void connectToDatabase() {
        try {
            // Ganti sesuai konfigurasi database Anda
            String url = "jdbc:mysql://localhost:3306/db_pedalpal";
            String user = "root"; // Ganti sesuai username MySQL Anda
            String password = ""; // Ganti sesuai password MySQL Anda

            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Koneksi ke database berhasil.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Koneksi ke database gagal!", "Error", 
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initTableModel() {
        // Inisialisasi model untuk tabel
        modelTable = (DefaultTableModel) jTable1.getModel();
        modelTable.setRowCount(0);
    }

    private void BtnTambahActionPerformed(java.awt.event.ActionEvent evt) {
        String namaSepeda = NmSepeda.getText();
        String modelSepeda = (String) MdlSepeda.getSelectedItem();
        String statusSepeda = (String) StsSepeda.getSelectedItem();
        String jumlahSepeda = JmlhSepeda.getText();

        if (namaSepeda.isEmpty() || jumlahSepeda.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua kolom harus diisi!", "Error", 
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int jumlah = Integer.parseInt(jumlahSepeda);

            // Masukkan data ke tabel di GUI
            modelTable.addRow(new Object[]{namaSepeda, modelSepeda, statusSepeda, jumlah});

            // Masukkan data ke database
            insertDataToDatabase(namaSepeda, modelSepeda, statusSepeda, jumlah);

            jLabelInfo.setText("Data berhasil ditambahkan: Nama Sepeda = " + namaSepeda 
                    + ", Model = " + modelSepeda + 
                               ", Status = " + statusSepeda + ", Jumlah = " + jumlah);

            NmSepeda.setText("");
            JmlhSepeda.setText("");
            MdlSepeda.setSelectedIndex(0);
            StsSepeda.setSelectedIndex(0);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Jumlah harus berupa angka!", "Error", 
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updateDataInDatabase(String nama, String model, String status, int jumlah) {
        try {
            String updateQuery = "UPDATE stok_sepeda SET model_sepeda = ?, status_sepeda = ?, "
                    + "jumlah_sepeda = ? WHERE nama_sepeda = ?";

            PreparedStatement stmt = connection.prepareStatement(updateQuery);
            stmt.setString(1, model);
            stmt.setString(2, status);
            stmt.setInt(3, jumlah);
            stmt.setString(4, nama);

            stmt.executeUpdate();
            stmt.close();

            System.out.println("Data berhasil diubah di tabel stok_sepeda.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal mengubah data di database!", "Error", 
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih baris data yang ingin dihapus!", "Error", 
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String namaSepeda = (String) modelTable.getValueAt(selectedRow, 0);

        // Hapus data dari tabel
        modelTable.removeRow(selectedRow);

        // Hapus data dari database
        deleteDataFromDatabase(namaSepeda);

        jLabelInfo.setText("Data berhasil dihapus: Nama Sepeda = " + namaSepeda);
    }

    private void deleteDataFromDatabase(String nama) {
        try {
            String deleteQuery = "DELETE FROM stok_sepeda WHERE nama_sepeda = ?";

            PreparedStatement stmt = connection.prepareStatement(deleteQuery);
            stmt.setString(1, nama);

            stmt.executeUpdate();
            stmt.close();

            System.out.println("Data berhasil dihapus dari tabel stok_sepeda.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal menghapus data dari database!", "Error", 
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void insertDataToDatabase(String nama, String model, String status, int jumlah) {
        try {
            // Menggunakan satu prepared statement untuk tabel stok_sepeda
            String insertQuery = "INSERT INTO stok_sepeda (nama_sepeda, model_sepeda, "
                    + "status_sepeda, jumlah_sepeda) VALUES (?, ?, ?, ?)";
            
            PreparedStatement stmt = connection.prepareStatement(insertQuery);
            stmt.setString(1, nama);
            stmt.setString(2, model);
            stmt.setString(3, status);
            stmt.setInt(4, jumlah);

            // Eksekusi statement
            stmt.executeUpdate();

            // Tutup statement setelah eksekusi
            stmt.close();

            System.out.println("Data berhasil dimasukkan ke tabel stok_sepeda.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal memasukkan data ke database!", "Error", 
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        JmlhSepeda = new javax.swing.JTextField();
        NmSepeda = new javax.swing.JTextField();
        StsSepeda = new javax.swing.JComboBox<>();
        MdlSepeda = new javax.swing.JComboBox<>();
        BtnTambah = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabelInfo = new javax.swing.JLabel();
        BtnEdit = new javax.swing.JButton();
        BtnHapus = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Kelola Sepeda Listrik");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Nama Sepeda  :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Model Sepeda  :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Status Sepeda  :");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Jumlah Sepeda :");

        StsSepeda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Baik", "Rusak" }));

        MdlSepeda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dengan Pedal", "Tanpa Pedal" }));

        BtnTambah.setText("Tambah");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nama Sepeda", "Model", "Status", "Jumlah"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        BtnEdit.setText("Edit");
        BtnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditActionPerformed(evt);
            }
        });

        BtnHapus.setText("Hapus");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 521, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(101, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(177, 177, 177))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(BtnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BtnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addComponent(BtnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2))
                                .addGap(68, 68, 68)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(MdlSepeda, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(NmSepeda, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(StsSepeda, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(JmlhSepeda, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(91, 91, 91))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel1)
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(NmSepeda, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(MdlSepeda, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(StsSepeda, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JmlhSepeda, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
         int selectedRow = jTable1.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Pilih baris data yang ingin diubah!", "Error", 
                JOptionPane.ERROR_MESSAGE);
        return;
    }

    String namaSepeda = NmSepeda.getText();
    String modelSepeda = (String) MdlSepeda.getSelectedItem();
    String statusSepeda = (String) StsSepeda.getSelectedItem();
    String jumlahSepeda = JmlhSepeda.getText();

    if (namaSepeda.isEmpty() || jumlahSepeda.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Semua kolom harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        int jumlah = Integer.parseInt(jumlahSepeda);

        // Update data di tabel
        modelTable.setValueAt(namaSepeda, selectedRow, 0);
        modelTable.setValueAt(modelSepeda, selectedRow, 1);
        modelTable.setValueAt(statusSepeda, selectedRow, 2);
        modelTable.setValueAt(jumlah, selectedRow, 3);

        // Update data di database
        updateDataInDatabase(namaSepeda, modelSepeda, statusSepeda, jumlah);

        jLabelInfo.setText("Data berhasil diubah: Nama Sepeda = " + namaSepeda 
                + ", Model = " + modelSepeda + 
                               ", Status = " + statusSepeda + ", Jumlah = " + jumlah);

        NmSepeda.setText("");
        JmlhSepeda.setText("");
        MdlSepeda.setSelectedIndex(0);
        StsSepeda.setSelectedIndex(0);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Jumlah harus berupa angka!", "Error", 
                JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_BtnEditActionPerformed
    
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
            java.util.logging.Logger.getLogger(ViewSepedaListrik.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewSepedaListrik.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewSepedaListrik.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewSepedaListrik.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewSepedaListrik().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnEdit;
    private javax.swing.JButton BtnHapus;
    private javax.swing.JButton BtnTambah;
    private javax.swing.JTextField JmlhSepeda;
    private javax.swing.JComboBox<String> MdlSepeda;
    private javax.swing.JTextField NmSepeda;
    private javax.swing.JComboBox<String> StsSepeda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelInfo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
