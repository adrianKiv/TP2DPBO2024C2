/*
    Saya Adrian Mulianto NIM [2200939] mengerjakan soal Tugas Praktikum-2 dalam mata kuliah DPBO
    untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin
*/

import java.sql.*;

public class Database {
    private Connection connection;
    private Statement statement;

    public Database() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_mahasiswa", "root", "");
            statement = connection.createStatement();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    //Untuk select query
    public ResultSet selectQuery(String sql){
        try {
            statement.executeQuery(sql);
            return statement.getResultSet();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public int InsertUpdateDelete(String sql){
        try {
            return statement.executeUpdate(sql);
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Statement getStatement(){
        return statement;
    }

    public ResultSet executeQuery(String query) {
        ResultSet rs = null;
        try {
            // Membuat objek Statement
            Statement stmt = connection.createStatement();

            // Menjalankan query dan mendapatkan hasil dalam objek ResultSet
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("Error executing query: " + e.getMessage());
        }
        return rs;
    }
}
