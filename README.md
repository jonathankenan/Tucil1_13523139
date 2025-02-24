# README

## 1. Penjelasan Singkat Program
Program ini merupakan solusi untuk menyelesaikan teka-teki IQ Puzzler Pro menggunakan algoritma pencarian. Program akan membaca papan permainan dan potongan puzzle dari sebuah file input, kemudian mencoba menempatkan potongan-potongan tersebut ke dalam papan hingga seluruh papan terisi dengan benar. Jika ditemukan solusi, program akan menampilkan hasilnya dan memberikan opsi untuk menyimpannya dalam file teks atau gambar.

## 2. Requirement Program dan Instalasi
Program ini ditulis dalam bahasa Java dan memerlukan lingkungan Java Development Kit (JDK) untuk menjalankannya. Berikut adalah requirement yang dibutuhkan:
- Java JDK 8 atau lebih baru
- File input dalam format yang sesuai dengan program

### Instalasi:
1. Pastikan Java sudah terinstal di sistem Anda.
2. Simpan semua file program dalam satu folder.
3. Siapkan file input sesuai dengan format yang telah ditentukan.

## 3. Cara Mengkompilasi Program
Jika ingin mengompilasi program secara manual, gunakan perintah berikut di terminal atau command prompt:
```sh
javac -d bin src/solve.java src/Print.java src/Conditions.java src/Rotation.java
```
Pastikan semua file yang dibutuhkan berada dalam direktori yang sama atau sudah ditentukan dalam classpath.

## 4. Cara Menjalankan dan Menggunakan Program
Setelah program dikompilasi, jalankan dengan perintah:
```sh
java -cp bin solve
```
Saat program berjalan:
1. Program akan meminta nama file input. Masukkan nama file sesuai dengan yang tersedia dalam folder input.
2. Program akan membaca papan permainan dan potongan puzzle dari file tersebut.
3. Jika ditemukan solusi, program akan menampilkan hasilnya dan memberikan pilihan untuk menyimpannya.
4. Jika pengguna memilih untuk menyimpan solusi, program akan menyimpannya dalam file teks dan memberikan opsi untuk menyimpan dalam format gambar.

## 5. Author
- **Nama**: Jonathan Kenan Budianto

