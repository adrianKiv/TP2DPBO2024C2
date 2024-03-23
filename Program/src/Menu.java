/*
    Saya Adrian Mulianto NIM [2200939] mengerjakan soal Tugas Praktikum-2 dalam mata kuliah DPBO
    untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin
*/

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Menu extends JFrame{
    public static void main(String[] args) {
        // buat object window
        Menu window = new Menu();

        // atur ukuran window
        window.setSize(780, 560);

        // letakkan window di tengah layar
        window.setLocationRelativeTo(null);

        // isi window
        window.setContentPane(window.mainPanel);

        // ubah warna background
        window.getContentPane().setBackground(Color.lightGray);

        // tampilkan window
        window.setVisible(true);

        // agar program ikut berhenti saat window diclose
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // index baris yang diklik
    private int selectedIndex = -1;
    // list untuk menampung semua mahasiswa
    private ArrayList<Mahasiswa> listMahasiswa;
    private Database database;
    private JPanel mainPanel;
    private JTextField nimField;
    private JTextField namaField;
    private JTable mahasiswaTable;
    private JButton addUpdateButton;
    private JButton cancelButton;
    private JComboBox jenisKelaminComboBox;
    private JButton deleteButton;
    private JLabel titleLabel;
    private JLabel nimLabel;
    private JLabel namaLabel;
    private JLabel jenisKelaminLabel;
    private JComboBox SemesterComboBox;
    private JLabel smesterLabel;

    // constructor
    public Menu() {
        // inisialisasi listMahasiswa
        listMahasiswa = new ArrayList<>();

        // isi listMahasiswa
        database = new Database();

        // isi tabel mahasiswa
        mahasiswaTable.setModel(setTable());

        // ubah styling title
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 20f));

        // atur isi combo box
        String[] jenisKelaminData = {"", "Laki-laki", "Perempuan"};
        jenisKelaminComboBox.setModel(new DefaultComboBoxModel(jenisKelaminData));

        // atur isi combo box semester
        String[] semesterdData = {"", "Semester 1", "Semester 2", "Semester 3", "Semester 4", "Semester 5", "Semester 6", "Semester 7", "Semester 8", "Semester 9", "Semester 10", "Semester 11", "Semester 12", "Semester 13", "Semester 14"};
        SemesterComboBox.setModel(new DefaultComboBoxModel(semesterdData));

        // sembunyikan button delete
        deleteButton.setVisible(false);

        // saat tombol add/update ditekan
        addUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(selectedIndex == -1) {
                    insertData();
                } else {
                    updateData();
                }
            }
        });
        // saat tombol delete ditekan
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(selectedIndex >= 0) {
                    int cek = 0;
                    int cek_comfirm = JOptionPane.showConfirmDialog(null, "Apakah Anda Yakin Mau Menghapus Data?", "Konfirmasi Hapus Data", JOptionPane.YES_NO_OPTION);
                    if (cek_comfirm == JOptionPane.YES_OPTION) {
                        cek = 1;
                    }
                    if (cek == 1){
                        deleteData();
                    }
                }
            }
        });
        // saat tombol cancel ditekan
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });
        // saat salah satu baris tabel ditekan
        mahasiswaTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // ubah selectedIndex menjadi baris tabel yang diklik
                selectedIndex = mahasiswaTable.getSelectedRow();

                // simpan value textfield dan combo box
                String selectedNim = mahasiswaTable.getModel().getValueAt(selectedIndex, 1).toString();
                String selectedNama = mahasiswaTable.getModel().getValueAt(selectedIndex, 2).toString();
                String selectedJenisKelamin = mahasiswaTable.getModel().getValueAt(selectedIndex, 3).toString();
                String selectedsemester = mahasiswaTable.getModel().getValueAt(selectedIndex, 4).toString();

                // ubah isi textfield dan combo box
                nimField.setText(selectedNim);
                namaField.setText(selectedNama);
                jenisKelaminComboBox.setSelectedItem(selectedJenisKelamin);
                SemesterComboBox.setSelectedItem(selectedsemester);

                // ubah button "Add" menjadi "Update"
                addUpdateButton.setText("Update");
                // tampilkan button delete
                deleteButton.setVisible(true);
            }
        });
    }

    public final DefaultTableModel setTable() {
        // tentukan kolom tabel
        Object[] column = {"No", "NIM", "Nama", "Jenis Kelamin", "Tempat Tinggal"};

        // buat objek tabel dengan kolom yang sudah dibuat
        DefaultTableModel temp = new DefaultTableModel(null, column);

        try {
            ResultSet resultSet = database.selectQuery("SELECT * FROM mahasiswa");

            int i = 0;
            while (resultSet.next()){
                Object[] row = new Object[5];

                row[0] = i + 1;
                row[1] = resultSet.getString("nim");
                row[2] = resultSet.getString("nama");
                row[3] = resultSet.getString("jenis_kelamin");
                row[4] = resultSet.getString("semester");

                temp.addRow(row);
                i++;
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return temp; // return juga harus diganti
    }

    public void insertData() {
        // ambil value dari textfield dan combobox
        String nim = nimField.getText();
        String nama = namaField.getText();
        String jenisKelamin = jenisKelaminComboBox.getSelectedItem().toString();
        String semester = SemesterComboBox.getSelectedItem().toString();

        // cek apakah semua data telah diisi
        if (nim.isEmpty() || nama.isEmpty() || jenisKelamin.isEmpty() || semester.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Harap isi semua data!");
            return;
        }

        // cek apakah NIM sudah ada di database
        String checkSql = "SELECT * FROM mahasiswa WHERE nim = '" + nim + "';";
        ResultSet rs = database.executeQuery(checkSql);
        try {
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "NIM yang kamu masukan sudah ada!");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // tambahkan data ke dalam database
        String sql = "INSERT INTO mahasiswa values(null, '"+ nim +"', '"+ nama +"','"+ jenisKelamin +"', '"+ semester +"');";
        database.InsertUpdateDelete(sql);

        // update tabel
        mahasiswaTable.setModel(setTable());

        // bersihkan form
        clearForm();

        // feedback
        System.out.println("Insert berhasil!");
        JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan!");
    }

    public void updateData() {
        // ambil data dari form
        String nim = nimField.getText();
        String nama = namaField.getText();
        String jenisKelamin = jenisKelaminComboBox.getSelectedItem().toString();
        String semester = SemesterComboBox.getSelectedItem().toString();

        // cek apakah semua data telah diisi
        if (nim.isEmpty() || nama.isEmpty() || jenisKelamin.isEmpty() || semester.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Harap isi semua data!");
            return;
        }

        // ubah data mahasiswa di database
        String sql = "UPDATE mahasiswa SET nim = '" + nim + "', nama = '" + nama + "', jenis_kelamin = '" + jenisKelamin + "', semester = '" + semester + "' WHERE nim = " + nim + ";";
        database.InsertUpdateDelete(sql);

        // update tabel
        mahasiswaTable.setModel(setTable());

        // bersihkan form
        clearForm();

        // feedback
        System.out.println("Update berhasil!");
        JOptionPane.showMessageDialog(null, "Data berhasil diubah!");
    }

    public void deleteData() {
        // hapus data dari list
        String nim = nimField.getText();
        String sql = "DELETE FROM mahasiswa WHERE nim = '" + nim + "';";
        database.InsertUpdateDelete(sql);

        // update tabel
        mahasiswaTable.setModel(setTable());

        // bersihkan form
        clearForm();

        // feedback
        System.out.println("Delete berhasil!");
        JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
    }

    public void clearForm() {
        // kosongkan semua texfield dan combo box
        nimField.setText("");
        namaField.setText("");
        jenisKelaminComboBox.setSelectedItem("");
        SemesterComboBox.setSelectedItem("");

        // ubah button "Update" menjadi "Add"
        addUpdateButton.setText("Add");
        // sembunyikan button delete
        deleteButton.setVisible(false);
        // ubah selectedIndex menjadi -1 (tidak ada baris yang dipilih)
        selectedIndex = -1;
    }

    private void populateList() {
        listMahasiswa.add(new Mahasiswa("2203999", "Amelia Zalfa Julianti", "Perempuan", "Semester 1"));
        listMahasiswa.add(new Mahasiswa("2202292", "Muhammad Iqbal Fadhilah", "Laki-laki", "Semester 3"));
        listMahasiswa.add(new Mahasiswa("2202346", "Muhammad Rifky Afandi", "Laki-laki", "Semester 5"));
        listMahasiswa.add(new Mahasiswa("2210239", "Muhammad Hanif Abdillah", "Laki-laki", "Semester 1"));
        listMahasiswa.add(new Mahasiswa("2202046", "Nurainun", "Perempuan", "Semester 3"));
        listMahasiswa.add(new Mahasiswa("2205101", "Kelvin Julian Putra", "Laki-laki", "Semester 2"));
        listMahasiswa.add(new Mahasiswa("2200163", "Rifanny Lysara Annastasya", "Perempuan", "Semester 12"));
        listMahasiswa.add(new Mahasiswa("2202869", "Revana Faliha Salma", "Perempuan", "Semester 13"));
        listMahasiswa.add(new Mahasiswa("2209489", "Rakha Dhifiargo Hariadi", "Laki-laki", "Semester 8"));
        listMahasiswa.add(new Mahasiswa("2203142", "Roshan Syalwan Nurilham", "Laki-laki", "Semester 14"));
        listMahasiswa.add(new Mahasiswa("2200311", "Raden Rahman Ismail", "Laki-laki", "Semester 11"));
        listMahasiswa.add(new Mahasiswa("2200978", "Ratu Syahirah Khairunnisa", "Perempuan", "Semester 6"));
        listMahasiswa.add(new Mahasiswa("2204509", "Muhammad Fahreza Fauzan", "Laki-laki", "Semester 7"));
        listMahasiswa.add(new Mahasiswa("2205027", "Muhammad Rizki Revandi", "Laki-laki", "Semester 12"));
        listMahasiswa.add(new Mahasiswa("2203484", "Arya Aydin Margono", "Laki-laki", "Semester 9"));
        listMahasiswa.add(new Mahasiswa("2200481", "Marvel Ravindra Dioputra", "Laki-laki", "Semester 2"));
        listMahasiswa.add(new Mahasiswa("2209889", "Muhammad Fadlul Hafiizh", "Laki-laki", "Semester 11"));
        listMahasiswa.add(new Mahasiswa("2206697", "Rifa Sania", "Perempuan", "Semester 4"));
        listMahasiswa.add(new Mahasiswa("2207260", "Imam Chalish Rafidhul Haque", "Laki-laki", "Semester 4"));
        listMahasiswa.add(new Mahasiswa("2204343", "Meiva Labibah Putri", "Perempuan", "Semester 4"));
    }
}
