public class Patient {
    int id;
    String name;
    String category;
    boolean active;
    int visitCount;

    public Patient(int id, String name, String category, boolean active, int visitCount) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.active = active;
        this.visitCount = visitCount;
    }

    public void display() {
        String statusTeks = active ? "Aktif" : "Nonaktif";
        System.out.printf("ID: %-4d | Nama: %-15s | Kategori: %-10s | Status: %-10s | Kunjungan: %-3d\n", id, name, category, statusTeks, visitCount);
    }

}
  