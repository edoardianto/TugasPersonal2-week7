/**
 * Class Node: satu elemen (simpul) pada Single Linked List.
 * Menyimpan data buku dan referensi (pointer) ke node berikutnya.
 */
public class Node {
    String kodeBuku;   // kode unik buku (maksimal 5 karakter)
    String judul;      // judul buku
    String penulis;    // nama penulis buku
    Node next;         // pointer ke node berikutnya (null jika node terakhir)

    // Constructor: membuat node baru, next awalnya null
    public Node(String kodeBuku, String judul, String penulis) {
        this.kodeBuku = kodeBuku;
        this.judul = judul;
        this.penulis = penulis;
        this.next = null;
    }
}
