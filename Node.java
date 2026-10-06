/**
 * Class Node: menyimpan data satu pelanggan (kode, nama, total).
 * Dipakai bersama oleh class Queue dan class Stack.
 */
public class Node {
    String kode;    // nomor antrian pelanggan, contoh: A001
    String nama;    // nama pelanggan
    long total;     // total belanja (Rupiah)
    Node next;      // pointer ke node berikutnya

    public Node(String kode, String nama, long total) {
        this.kode = kode;
        this.nama = nama;
        this.total = total;
        this.next = null;
    }
}
