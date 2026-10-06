import java.util.Scanner;

/**
 * Program Pengelolaan Daftar Buku Perpustakaan (Single Linked List).
 */
public class Main {
    static final int MAKS_KODE = 5;  // panjang maksimal kodeBuku
    static final int MIN_BUKU = 5;   // jumlah data minimal yang harus dimiliki

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedList daftar = new LinkedList();
        int pilihan = 0;

        do {
            tampilMenu();
            System.out.print("Pilih menu: ");
            if (!sc.hasNextLine()) break;          // antisipasi input habis
            try {
                pilihan = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                pilihan = -1;                      // input bukan angka
            }
            System.out.println();

            switch (pilihan) {
                case 1: tambahBuku(sc, daftar); break;
                case 2: hapusBuku(daftar);      break;
                case 3: cariBuku(sc, daftar);   break;
                case 4: daftar.tampilSemua();
                        if (daftar.getSize() < MIN_BUKU) {
                            System.out.println("Catatan: jumlah data belum mencapai minimal "
                                    + MIN_BUKU + " buku.");
                        }
                        break;
                case 5:
                    if (daftar.getSize() < MIN_BUKU) {
                        System.out.println("Catatan: jumlah data saat keluar adalah "
                                + daftar.getSize() + " buku (minimal " + MIN_BUKU + ").");
                    }
                    System.out.println("Terima kasih. Program selesai.");
                    break;
                default: System.out.println("Pilihan tidak valid! Pilih menu 1-5.");
            }
            System.out.println();
        } while (pilihan != 5);

        sc.close();
    }

    // Menampilkan menu utama
    static void tampilMenu() {
        System.out.println("===== SISTEM DATA BUKU =====");
        System.out.println("1. Tambah Buku");
        System.out.println("2. Hapus Buku");
        System.out.println("3. Cari Buku");
        System.out.println("4. Lihat Semua Buku");
        System.out.println("5. Keluar");
    }

    // Menu 1: input data + validasi, lalu tambah di akhir daftar
    static void tambahBuku(Scanner sc, LinkedList daftar) {
        System.out.print("Masukkan Kode Buku: ");
        String kode = sc.nextLine().trim();
        if (kode.isEmpty()) {
            System.out.println("Gagal: kode buku tidak boleh kosong!");
            return;
        }
        if (kode.length() > MAKS_KODE) {              // validasi maksimal 5 karakter
            System.out.println("Gagal: kode buku maksimal " + MAKS_KODE + " karakter!");
            return;
        }
        if (daftar.cari(kode) != null) {              // cegah kode ganda
            System.out.println("Gagal: kode buku sudah terdaftar!");
            return;
        }

        System.out.print("Masukkan Judul: ");
        String judul = sc.nextLine().trim();
        System.out.print("Masukkan Penulis: ");
        String penulis = sc.nextLine().trim();
        if (judul.isEmpty() || penulis.isEmpty()) {
            System.out.println("Gagal: judul dan penulis tidak boleh kosong!");
            return;
        }

        daftar.tambahDiAkhir(kode, judul, penulis);
        System.out.println("Data berhasil ditambahkan!");
        if (daftar.getSize() < MIN_BUKU) {            // info progres menuju minimal 5 buku
            System.out.println("Info: baru " + daftar.getSize() + " buku, minimal "
                    + MIN_BUKU + " buku (kurang " + (MIN_BUKU - daftar.getSize()) + " lagi).");
        }
    }

    // Menu 2: hapus buku terakhir (Pop)
    static void hapusBuku(LinkedList daftar) {
        Node dihapus = daftar.hapusTerakhir();
        if (dihapus == null) {
            System.out.println("Tidak ada data untuk dihapus.");
        } else {
            System.out.println("Buku terakhir berhasil dihapus:");
            System.out.println("Kode: " + dihapus.kodeBuku
                    + " | Judul: " + dihapus.judul
                    + " | Penulis: " + dihapus.penulis);
        }
    }

    // Menu 3: cari buku berdasarkan kode
    static void cariBuku(Scanner sc, LinkedList daftar) {
        System.out.print("Masukkan Kode Buku yang dicari: ");
        String kode = sc.nextLine().trim();
        Node hasil = daftar.cari(kode);
        if (hasil == null) {
            System.out.println("Buku tidak ditemukan.");
        } else {
            System.out.println("Buku ditemukan!");
            System.out.println("Kode    : " + hasil.kodeBuku);
            System.out.println("Judul   : " + hasil.judul);
            System.out.println("Penulis : " + hasil.penulis);
        }
    }
}
