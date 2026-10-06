/**
 * Class Stack (LIFO - Last In First Out) untuk riwayat transaksi.
 * Transaksi terbaru berada di puncak (top) dan tampil paling atas.
 */
public class Stack {
    private Node top;     // transaksi terbaru
    private int size;     // jumlah transaksi tersimpan

    public Stack() {
        top = null;
        size = 0;
    }

    public boolean isEmpty() { return top == null; }
    public int getSize()     { return size; }

    /** PUSH: menyimpan transaksi baru di puncak stack. O(1). */
    public void push(Node data) {
        Node baru = new Node(data.kode, data.nama, data.total);
        baru.next = top;      // node baru menunjuk ke top lama
        top = baru;           // top berpindah ke node baru
        size++;
    }

    /** POP: mengambil transaksi teratas (dipakai bila perlu). O(1). */
    public Node pop() {
        if (isEmpty()) return null;
        Node keluar = top;
        top = top.next;
        keluar.next = null;
        size--;
        return keluar;
    }

    /** Menampilkan riwayat dari terbaru (atas) ke terlama (bawah). */
    public void display() {
        if (isEmpty()) {
            System.out.println("Belum ada riwayat transaksi.");
            return;
        }
        System.out.println("Riwayat Transaksi (terbaru -> terlama):");
        Node bantu = top;
        int no = 1;
        long jumlah = 0;
        while (bantu != null) {
            System.out.println(no + ". " + bantu.kode + " | " + bantu.nama
                    + " | Rp" + Main.rupiah(bantu.total));
            jumlah += bantu.total;
            bantu = bantu.next;
            no++;
        }
        System.out.println("Total transaksi: " + size + " | Total pendapatan: Rp"
                + Main.rupiah(jumlah));
    }
}
