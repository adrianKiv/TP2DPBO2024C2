# TP2DPBO2024
## Janji
Saya Adrian Mulianto NIM [2200939] mengerjakan soal Tugas Praktukum-2 dalam mata kuliah DPBO untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin 

# Penjelasan Program
Pada kelas Mahasiswa, terdapat 4 atribut yaitu:
- `nim`: Menyimpan data NIM dalam bentuk string.
- `nama`: Menyimpan data Nama dalam bentuk string.
- `jenisKelamin`: Menyimpan data Jenis kelamin dalam bentuk string.
- `Semester`: Menyimpan data Semester dalam bentuk string.

Pada kelas database, terdapat 2 atribut yaitu:
- `connection`: Menyambungkan dengan database.
- `statement`: Untuk mengeksekusi SQL dan mengembalikan nilai dari database.

 Adapun component input yang digunakan dalam Menu.form antara lain:
1. JtextField : digunakan untuk menampung Nim dan Nama
2. JcomboBox : digunakan untuk menampung Jenis kelamin dan Semester

# Alur Program
1. Ketika Program dijalankan akan menampilkan sebuah window yang berfungsi untuk memasukan data Mahasiswa.
2. user dapat mengisi form Mahasiswa dengan memasukan data Nim, Nama, Jenis kelamin dan Semester.
3. user diharuskan mengisi semua data yang ingin di masukan jika terdapat kolom yang kosong maka program akan memberikan jendela pemberitahuan bahwa terdapat kolom data yang belum di isi.
4. Setelah user mengisi data, klik tombol `ADD` untuk menambahkan data ke list data Mahasiswa Program akan menampilkan Jendela kecil menyatakan bahwa data berhasil di tambahkan.
5. Terdapat kondisi dimana jika Nim yang dimasukan oleh user sudah ada di database maka akan menampilkan jendela pemberitahuan bahwa Nim sudah ada.
6. Jika user ingin memperbarui data, user bisa meng klik baris data dari table yang ingin di ubah, setelah meng klik user bisa mengubah data yang ingin di ubah lalu meng klik tombol `Update` untuk memperbarui data.
7. user di haruskan mengisi data yang ingin di ubah, jika perunbahan user hanya menghapus data yang sudah ada ketika Update maka program akan menampilkan jendela pemberitahuan bahwa terdapat kolom data yang belum di isi
8. Program akan menampilkan jendela kecil menyatakan bahwa pembaruan berhasil dilakukan.
9. Jika user ingin membatalkan perubahan bisa meng klik `Cancel`, maka imput perubahan tadi akan di hapus.
10. jika user ingin menghapus data user bisa meng klik baris data yang ingin di hapus, lalu user bisa meng klik tombol `Delete` untuk menghapus data yang di inginkan.
11. Program akan menampilkan jendela kecil menyatakan apakah anda yakin untuk menghapus data tersebut.
12. jika user meng klik `Yes` maka data akan di hapus.
13. Program akan menampilkan jendela kecil menyatakan bahwa penghapusan data berhasil.

# Dokumentasi Program
https://github.com/adrianKiv/TP2DPBO2024C2/assets/134991383/9db3246c-4ff6-422d-b592-bd703702f503

