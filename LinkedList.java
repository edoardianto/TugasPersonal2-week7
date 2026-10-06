/**
 * Class LinkedList: implementasi Single Linked List untuk daftar buku.
 * Operasi: tambah di akhir (push), hapus terakhir (pop), cari, dan tampil.
 */
public class LinkedList {
    private Node head;  // node pertama dalam daftar
    private int size;   // jumlah buku yang tersimpan

    // Constructor: daftar awalnya kosong
    public LinkedList() {
        head = null;
        size = 0;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int getSize() {
        return size;
    }

    /** Menambah buku baru di AKHIR daftar. Kompleksitas O(n). */
    public void tambahDiAkhir(String kode, String judul, String penulis) {
        Node baru = new Node(kode, judul, penulis);
        if (head == null) {
            head = baru;                 // daftar kosong: node baru menjadi head
        } else {
            Node bantu = head;
            while (bantu.next != null) { // telusuri sampai node terakhir
                bantu = bantu.next;
            }
            bantu.next = baru;           // sambungkan node terakhir ke node baru
        }
        size++;
    }

    /** Menghapus buku TERAKHIR. Mengembalikan node yang dihapus, atau null jika kosong. */
    public Node hapusTerakhir() {
        if (head == null) {
            return null;                 // daftar kosong, tidak ada yang dihapus
        }
        Node dihapus;
        if (head.next == null) {         // kasus hanya ada satu node
            dihapus = head;
            head = null;
        } else {
            Node bantu = head;
            while (bantu.next.next != null) { // berhenti di node sebelum yang terakhir
                bantu = bantu.next;
            }
            dihapus = bantu.next;
            bantu.next = null;           // putuskan node terakhir dari daftar
        }
        size--;
        return dihapus;
    }

    /** Mencari buku berdasarkan kode (tidak membedakan huruf besar/kecil). */
    public Node cari(String kode) {
        Node bantu = head;
        while (bantu != null) {
            if (bantu.kodeBuku.equalsIgnoreCase(kode)) {
                return bantu;            // ditemukan
            }
            bantu = bantu.next;
        }
        return null;                     // tidak ditemukan
    }

    /** Menampilkan seluruh buku sesuai urutan input beserta total buku. */
    public void tampilSemua() {
        if (head == null) {
            System.out.println("Daftar buku masih kosong.");
            System.out.println("Total Buku: 0");
            return;
        }
        System.out.println("Daftar Buku:");
        Node bantu = head;
        while (bantu != null) {
            System.out.println("Kode: " + bantu.kodeBuku
                    + " | Judul: " + bantu.judul
                    + " | Penulis: " + bantu.penulis);
            bantu = bantu.next;
        }
        System.out.println("Total Buku: " + size);
    }
}
