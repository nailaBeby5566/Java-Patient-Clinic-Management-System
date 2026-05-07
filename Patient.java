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
        this.active = true;
        this.visitCount = visitCount;
    }

    public void display() {
        System.out.println("ID: " + id + " | Nama: " + name + " | Kategori: " + category + " | Status: " + (active ? "Aktif" : "Nonaktif") +
                " | Kunjungan: " + visitCount);
    }
}