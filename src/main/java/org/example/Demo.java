package org.example;

import org.example.facade.Category;
import org.example.facade.Transaction;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Demo penggunaan API Category & Transaction
 * 
 * Semua komen di sini pakai bahasa Indonesia yang santai biar gampang dipahami kolega. Pastikan
 * Context.appRepo sudah diinisialisasi sebelum menjalankan demo ini!
 */
public class Demo {
   public static void run() {
      // --- Bagian Kategori ---
      System.out.println("=== Demo Kategori ===");
      Category makanan = new Category("Makanan");
      printCategory(makanan, "Kategori baru");

      // Rename kategori
      makanan.setName("Makanan & Minuman");
      printCategory(makanan, "Kategori setelah di-rename");

      // Ambil semua kategori yang ada di sistem
      List<Category> semuaKategori = Category.getAll();
      printCategoryList(semuaKategori, "Daftar semua kategori");

      // Ambil kategori by id & by name
      Category byId = Category.getById(makanan.getId());
      Category byName = Category.getByName("Makanan & Minuman");
      printCategory(byId, "Ambil kategori by id");
      printCategory(byName, "Ambil kategori by name");

      // --- Bagian Transaksi ---
      System.out.println("\n=== Demo Transaksi ===");
      // Bikin transaksi baru pakai kategori yang tadi
      Transaction trx1 = new Transaction("Beli bakso", 20000, makanan);
      printTransaction(trx1, "Transaksi baru");

      // Bikin transaksi lain pakai nama kategori (otomatis lookup)
      Transaction trx2 =
            new Transaction("Ngopi di cafe", 35000, "Makanan & Minuman", LocalDateTime.now());
      printTransaction(trx2, "Transaksi kedua");

      // Edit judul transaksi
      trx1.setTitle("Beli bakso malang");
      printTransaction(trx1, "Judul transaksi pertama setelah diedit");

      // Edit nominal transaksi
      trx1.setAmount(25000);
      printTransaction(trx1, "Nominal transaksi pertama setelah diedit");

      // Edit kategori transaksi (pakai objek Category)
      trx1.setCategory(byId);
      printTransaction(trx1, "Kategori transaksi pertama setelah diedit (pakai objek)");

      // Edit kategori transaksi (pakai nama kategori)
      trx1.setCategory("Makanan & Minuman");
      printTransaction(trx1, "Kategori transaksi pertama setelah diedit (pakai nama)");

      // Ambil semua histori transaksi
      List<Transaction> semuaTrx = Transaction.getHistory();
      printTransactionList(semuaTrx, "Daftar semua transaksi");

      // Ambil transaksi by id (pakai method baru getById)
      if (!semuaTrx.isEmpty()) {
         int idTrxPertama = semuaTrx.get(0).getId();
         Transaction trxById = Transaction.getById(idTrxPertama);
         printTransaction(trxById, "Ambil transaksi by id (fitur baru)");
      }

      // Hapus transaksi kedua
      trx2.delete();
      System.out.println("Transaksi kedua sudah dihapus!");

      // Ambil histori transaksi setelah penghapusan
      List<Transaction> sisaTrx = Transaction.getHistory();
      printTransactionList(sisaTrx, "Daftar transaksi setelah penghapusan");

      // --- Penjelasan singkat cara kerja sistem ---
      /*
       * Secara garis besar: - Semua data (kategori & transaksi) disimpan/diambil lewat repository
       * (IAppRepository) yang diakses via Context.appRepo (Istilahnya Depedency Injection). -
       * Category & Transaction ini semacam "facade" alias pembungkus biar interaksi ke data lebih
       * gampang & OOP banget. - Kalau bikin kategori/transaction baru, otomatis data disimpan ke
       * repo & objeknya langsung siap dipakai. - Kalau mau edit/hapus, tinggal panggil method di
       * objeknya, nanti repo yang urus update ke data aslinya. - Relasi antara transaksi & kategori
       * dihubungkan lewat id/nama kategori. - Semua method static di Category/Transaction itu buat
       * ambil data dari repo (misal: getAll, getHistory, getById, dst).
       * 
       * Kalian bisa langsung pake Transaction dan Category dari sekarang, untuk sekarang
       * penyimpanan data masih di dalam struktur data, jadi engga bakal kesave di setiap restart
       * program. Kalo kita pake arsitektur ini nantinya kalian ga perlu buat ngubah kode apapun
       * (penggunaan Transaction dan Category) walau kita pindah dari penyimpanan berbasis struktur
       * data ke format lain kayak database, semuanya udh diabstraksi. Tapi ini belum fix karena blm
       * ada Exception yang dilempar kalo operasi gagal.
       * 
       * Intinya, API ini dibuat biar developer nggak perlu ribet mikirin detail database, cukup
       * pakai objek & method yang disediakan.
       */
   }

   // --- Utility print method biar main() ga pusing ---

   // Print satu kategori dengan label
   private static void printCategory(Category cat, String label) {
      System.out.println(label + ": " + cat.getName() + " (id: " + cat.getId() + ")");
   }

   // Print list kategori dengan label
   private static void printCategoryList(List<Category> list, String label) {
      System.out.println(label + ":");
      for (Category cat : list) {
         System.out.println("- " + cat.getName() + " (id: " + cat.getId() + ")");
      }
   }

   // Print satu transaksi dengan label
   private static void printTransaction(Transaction trx, String label) {
      System.out.println(label + ": " + trx.getTitle() + ", Rp" + trx.getAmount() + ", kategori: "
            + trx.getCategoryName() + ", dibuat: " + trx.getCreatedAt());
   }

   // Print list transaksi dengan label
   private static void printTransactionList(List<Transaction> list, String label) {
      System.out.println(label + ":");
      for (Transaction t : list) {
         System.out.println("- " + t.getTitle() + ", Rp" + t.getAmount() + ", kategori: "
               + t.getCategoryName() + ", dibuat: " + t.getCreatedAt());
      }
   }
}
