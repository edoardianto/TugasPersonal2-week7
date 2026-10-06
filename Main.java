import java.util.Scanner;

/**
 * Program Sistem Kasir Toko menggunakan Queue (antrian pelanggan)
 * dan Stack (riwayat transaksi).
 */
public class Main {
    static final int MAKS_ANTRIAN = 5;   // validasi jumlah data <= 5

    // Format angka dengan pemisah ribuan titik, contoh: 125000 -> 125.000
    static String rupiah(long angka) {
        return String.format("%,d", angka).replace(',', '.');
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Queue antrian = new Queue(MAKS_ANTRIAN);
        Stack riwayat = new Stack();
        int pilihan = 0;

        do {
            tampilMenu();
            System.out.print("Pilih menu: ");
            if (!sc.hasNextLine()) break;
            try {
                pilihan = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                pilihan = -1;
            }
            System.out.println();

            switch (pilihan) {
                case 1: tambahAntrian(sc, antrian);    break;
                case 2: layaniPelanggan(antrian, riwayat); break;
                case 3: antrian.display();             break;
                case 4: riwayat.display();             break;
                case 5: System.out.println("Terima kasih. Program selesai."); break;
                default: System.out.println("Pilihan tidak valid! Pilih menu 1-5.");
            }
            System.out.println();
        } while (pilihan != 5);

        sc.close();
    }

    static void tampilMenu() {
        System.out.println("=== SISTEM KASIR TOKO ===");
        System.out.println("1. Tambah Antrian");
        System.out.println("2. Layani Pelanggan");
        System.out.println("3. Tampilkan Antrian");
        System.out.println("4. Lihat Riwayat Transaksi");
        System.out.println("5. Keluar");
    }

    // Menu 1: enqueue pelanggan baru (validasi antrian penuh, input kosong, angka)
    static void tambahAntrian(Scanner sc, Queue antrian) {
        if (antrian.isFull()) {
            System.out.println("Antrian penuh! Maksimal " + MAKS_ANTRIAN
                    + " pelanggan. Layani pelanggan terlebih dahulu.");
            return;
        }
        System.out.print("Masukkan Nomor Antrian: ");
        String kode = sc.nextLine().trim();
        if (kode.isEmpty()) {
            System.out.println("Gagal: nomor antrian tidak boleh kosong!");
            return;
        }
        if (antrian.contains(kode)) {
            System.out.println("Gagal: nomor antrian sudah ada di antrian!");
            return;
        }
        System.out.print("Masukkan Nama Pelanggan: ");
        String nama = sc.nextLine().trim();
        if (nama.isEmpty()) {
            System.out.println("Gagal: nama pelanggan tidak boleh kosong!");
            return;
        }
        System.out.print("Masukkan Total Belanja: ");
        long total;
        try {
            total = Long.parseLong(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Gagal: total belanja harus berupa angka!");
            return;
        }
        if (total <= 0) {
            System.out.println("Gagal: total belanja harus lebih dari 0!");
            return;
        }

        antrian.enqueue(kode, nama, total);
        System.out.println("Data pelanggan ditambahkan ke antrian!");
        System.out.println("Jumlah dalam antrian: " + antrian.getSize() + " dari " + MAKS_ANTRIAN);
    }

    // Menu 2: dequeue pelanggan terdepan lalu push transaksinya ke stack
    static void layaniPelanggan(Queue antrian, Stack riwayat) {
        Node dilayani = antrian.dequeue();
        if (dilayani == null) {
            System.out.println("Tidak ada pelanggan dalam antrian.");
            return;
        }
        System.out.println("Melayani pelanggan " + dilayani.kode + " (" + dilayani.nama + ")");
        System.out.println("Total belanja: Rp" + rupiah(dilayani.total));
        riwayat.push(dilayani);
        System.out.println("Transaksi disimpan ke riwayat.");
    }
}
