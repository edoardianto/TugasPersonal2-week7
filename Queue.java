/**
 * Class Queue (FIFO - First In First Out) untuk antrian pelanggan.
 * Pelanggan masuk lewat rear (belakang) dan dilayani dari front (depan).
 */
public class Queue {
    private Node front;            // pelanggan terdepan (dilayani lebih dulu)
    private Node rear;             // pelanggan paling belakang
    private int size;              // jumlah pelanggan dalam antrian
    private final int kapasitas;   // batas maksimal data (validasi <= 5)

    public Queue(int kapasitas) {
        this.kapasitas = kapasitas;
        front = rear = null;
        size = 0;
    }

    public boolean isEmpty() { return size == 0; }
    public boolean isFull()  { return size >= kapasitas; }
    public int getSize()     { return size; }

    /** Cek apakah nomor antrian sudah ada di antrian. */
    public boolean contains(String kode) {
        Node bantu = front;
        while (bantu != null) {
            if (bantu.kode.equalsIgnoreCase(kode)) return true;
            bantu = bantu.next;
        }
        return false;
    }

    /** ENQUEUE: menambah pelanggan di belakang antrian. O(1). */
    public boolean enqueue(String kode, String nama, long total) {
        if (isFull()) {
            return false;                  // antrian penuh (maksimal 5)
        }
        Node baru = new Node(kode, nama, total);
        if (isEmpty()) {
            front = rear = baru;           // elemen pertama
        } else {
            rear.next = baru;              // sambung ke belakang
            rear = baru;
        }
        size++;
        return true;
    }

    /** DEQUEUE: mengeluarkan pelanggan terdepan. Mengembalikan null jika kosong. O(1). */
    public Node dequeue() {
        if (isEmpty()) {
            return null;
        }
        Node keluar = front;
        front = front.next;                // front maju ke pelanggan berikutnya
        if (front == null) {
            rear = null;                   // antrian menjadi kosong
        }
        keluar.next = null;                // lepaskan dari rantai antrian
        size--;
        return keluar;
    }

    /** Menampilkan antrian dari depan ke belakang. */
    public void display() {
        if (isEmpty()) {
            System.out.println("Antrian kosong.");
            return;
        }
        System.out.println("Daftar Antrian (depan -> belakang):");
        Node bantu = front;
        int no = 1;
        while (bantu != null) {
            System.out.println(no + ". " + bantu.kode + " | " + bantu.nama
                    + " | Rp" + Main.rupiah(bantu.total));
            bantu = bantu.next;
            no++;
        }
        System.out.println("Jumlah dalam antrian: " + size + " dari " + kapasitas);
    }
}
