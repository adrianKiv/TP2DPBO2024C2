/*
    Saya Adrian Mulianto NIM [2200939] mengerjakan soal Tugas Praktikum-2 dalam mata kuliah DPBO
    untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin
*/

public class Mahasiswa {
    private String nim;
    private String nama;
    private String jenisKelamin;
    private String semester;

    // construktor
    public Mahasiswa(String nim, String nama, String jenisKelamin, String semester) {
        this.nim = nim;
        this.nama = nama;
        this.jenisKelamin = jenisKelamin;
        this.semester = semester;
    }

    // setter
    public void setNim(String nim) {
        this.nim = nim;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    //getter
    public String getNim() {
        return this.nim;
    }

    public String getNama() {
        return this.nama;
    }

    public String getJenisKelamin() {
        return this.jenisKelamin;
    }

    public String getSemester() {
        return this.semester;
    }

}
