# Expense Tracker Java GUI

Halo! 👋  
Ini adalah proyek Expense Tracker berbasis Java, dengan arsitektur OOP yang rapi dan sudah dipisah-pisah per modul biar gampang dikembangin bareng-bareng.  
Di bawah ini ada penjelasan struktur proyek, fungsi masing-masing modul, dan cara pakai API-nya (khususnya facade Category & Transaction).

---

## Struktur Proyek

```
src/
  main/
    java/
      org/
        example/
          App.java              // Entry point utama aplikasi
          Context.java          // Tempat dependency injection (repo, dsb)
          Demo.java             // Contoh/demo penggunaan API (bisa dijalankan buat belajar)
          facade/
            Category.java       // API OOP untuk kategori
            Transaction.java    // API OOP untuk transaksi
          models/
            CategoryModel.java      // Struktur data kategori (POJO)
            TransactionModel.java   // Struktur data transaksi (POJO)
          repositories/
            IAppRepository.java     // Interface repository (abstraksi data)
            TemporaryRepository.java// Implementasi repo (bisa diganti ke DB beneran)
          gui/
            ...                // GUI-related code
  resources/
    db/
      migration/               // (Kalau ada) file migrasi DB
test/
  java/
    org/
      example/
        AppTest.java           // Unit test
pom.xml                       // File konfigurasi Maven
README.md                     // Penjelasan proyek ini
```

---

## Penjelasan Modul/Package

-  **App.java**  
   Entry point aplikasi. Biasanya di sini inisialisasi Context, repo, dan jalankan GUI/main loop.

-  **Context.java**  
   Tempat naruh dependency global, misal: `Context.appRepo` buat repository utama.  
   Biar gampang inject repo ke mana-mana tanpa ribet.

-  **facade/**  
   Isinya API OOP yang ramah developer, biar interaksi ke data gampang dan ga perlu mikirin detail repo/database.

   -  `Category.java`: Buat ngatur kategori (tambah, rename, ambil semua, cari by id/nama, dsb)
   -  `Transaction.java`: Buat ngatur transaksi (tambah, edit, hapus, ambil histori, dsb)

-  **models/**  
   Kumpulan POJO (Plain Old Java Object) yang representasi data mentah (biasanya mirip tabel di DB).

-  **repositories/**  
   Abstraksi dan implementasi data storage.

   -  `IAppRepository.java`: Interface, biar gampang ganti-ganti backend (misal: dari memory ke DB beneran)
   -  `TemporaryRepository.java`: Implementasi sementara (biasanya in-memory, buat demo/dev)

-  **gui/**  
   (Kalau ada) Kode terkait tampilan/GUI.

-  **Demo.java**  
   Contoh pemakaian API facade.  
   Cocok buat belajar atau ngetes fitur-fitur utama.

---

## Cara Pakai API (Category & Transaction)

### 1. Pastikan `Context.appRepo` sudah diinisialisasi

Biasanya di App.java atau sebelum pakai API facade.

### 2. Contoh Penggunaan

```java
// --- Kategori ---
Category makanan = new Category("Makanan"); // bikin kategori baru
makanan.setName("Makanan & Minuman");       // rename kategori

List<Category> semuaKategori = Category.getAll(); // ambil semua kategori

Category byId = Category.getById(makanan.getId()); // cari kategori by id
Category byName = Category.getByName("Makanan & Minuman"); // cari by nama

// --- Transaksi ---
Transaction trx1 = new Transaction("Beli bakso", 20000, makanan); // bikin transaksi baru
trx1.setTitle("Beli bakso malang");      // edit judul
trx1.setAmount(25000);                   // edit nominal
trx1.setCategory(byId);                  // ganti kategori (pakai objek)
trx1.setCategory("Makanan & Minuman");   // ganti kategori (pakai nama)

List<Transaction> semuaTrx = Transaction.getHistory(); // ambil semua histori transaksi

// Ambil transaksi by id
if (!semuaTrx.isEmpty()) {
    int idTrx = semuaTrx.get(0).getId();
    Transaction trxById = Transaction.getById(idTrx); // ambil instance Transaction berdasarkan id
    // trxById siap dipakai, misal print detailnya
}

trx1.delete(); // hapus transaksi
```

> **Catatan:**  
> Semua perubahan/add/delete otomatis update ke repository (Context.appRepo).  
> Kalau mau ganti backend (misal: dari memory ke database), cukup ganti implementasi IAppRepository.

---

## Tips & Catatan

-  **Demo.java**  
   Lihat file ini buat contoh lengkap pemakaian API, sudah ada komentar santai di setiap langkahnya.
-  **Arsitektur**  
   Proyek ini pakai pattern facade + repository, jadi gampang di-maintain dan scalable, familiar sama konsep dasar OOP.
-  **Mau nambah fitur?**  
   Tinggal extend facade/repo/model sesuai kebutuhan, ga perlu takut ngacak-ngacak kode lain selama kalian bikin branch kalian sendiri.
-  **Testing**  
   Cek folder `test/` buat unit test.

---

## Aturan Kolaborasi GitHub

Biar kerja bareng makin lancar, ada beberapa aturan main soal GitHub di proyek ini:

-  **Branch `main`**  
   Ini branch utama, isinya cuma kode yang udah stabil & siap release. Jangan commit langsung ke sini, semua perubahan harus lewat PR dari branch lain.

-  **Branch `dev`**  
   Tempat integrasi semua fitur/bugfix sebelum akhirnya di-merge ke `main`. Semua fitur baru, perbaikan, atau refactor, merge-nya ke `dev` dulu.

-  **Branch untuk fitur/bugfix**  
   Kalau mau nambah fitur atau benerin bug, bikin branch baru dari `dev` dengan format:

   -  `feature/nama-fitur` (misal: `feature/export-csv`)
   -  `fix/issue-123` (misal: `fix/nullpointer-login`)
      Setelah selesai, bikin Pull Request (PR) ke `dev`.

   Caranya dengan:

   -  git pull (biar dapet kondisi project paling terkini)
   -  git checkout dev (pindah ke dev)
   -  git checkout -b <nama branch kalian> (-b buat bikin branch baru)
   -  lalu bisa koding kyk biasa, klo sudah bisa di commit

-  **Commit message**  
   Biar histori git rapi & gampang dilacak, pakai format commit message:

   ```
   feat/fix/chore: deskripsi singkat
   ```

   Contoh:

   -  `feat: tambah fitur export ke CSV`
   -  `fix: benerin error waktu login`
   -  `chore: rapihin struktur folder model`
      Commit message boleh pakai bahasa Indonesia aja biar gampang dipahami;

-  **Review & Merge**  
   Setiap PR ke `dev` sebaiknya di-review dulu sama minimal 1 orang sebelum di-merge.

---
