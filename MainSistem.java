import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MainSistem {
    static ArrayList<Patient> patients = new ArrayList<>();
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        initData();
        saveToCSV();
        int choice;

        do{
            System.out.println("\n=== SISTEM MANAJEMEN PASIEN ===");
            System.out.println("1. Tambah Pasien");
            System.out.println("2. Tampilkan Semua");
            System.out.println("3. Edit Pasien");
            System.out.println("4. Hapus (Soft Delete)");
            System.out.println("5. Cari Nama (Linear)");
            System.out.println("6. Cari ID (Binary)");
            System.out.println("7. Cari Berdasarkan Kategori (Linear)");
            System.out.println("8. Cari Berdasarkan Status (Linear)");
            System.out.println("9. Sort ID (Bubble)");
            System.out.println("10. Sort Nama (Selection)");
            System.out.println("11. Sort Kunjungan (Descending)");
            System.out.println("12. Statistik");
            System.out.println("0. Keluar");
            System.out.print("Pilih: ");

            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1: addPatient();
                break;
                case 2: showAll();
                break;
                case 3: editPatient();
                break;
                case 4: deletePatient();
                break;
                case 5: searchByName();
                break;
                case 6: searchByID();
                break;
                case 7: searchByCategory();
                break;
                case 8: searchByActive();
                break;
                case 9: sortByID();
                System.out.println("\n=== Hasil Sort Berdasarkan ID ===");
                showAll();
                break;
                case 10: sortByName();
                System.out.println("\n=== Hasil Sort Berdasarkan Nama ===");
                showAll();
                break;
                case 11: sortByvisitCount();
                System.out.println("\n=== Hasil Sort Berdasarkan Kunjungan Terbanyak ===");
                showAll();
                break;
                case 12:
                statistics();
                break;
            }
        }
        while(choice != 0);
    }

    static void initData(){
        patients.add(new Patient(1, "Alya", "Umum", true, 2));
        patients.add(new Patient(2, "Bima", "Anak", false, 5));
        patients.add(new Patient(3, "Citra", "Gigi", true, 1));
        patients.add(new Patient(4, "Doni", "THT", false, 3));
        patients.add(new Patient(5, "Eka", "Umum", true, 4));
        patients.add(new Patient(6, "Ali", "Umum", true, 2));
        patients.add(new Patient(7, "Aldo", "Anak", true, 4));
        patients.add(new Patient(8, "Ryul", "Mata", true, 5));
        patients.add(new Patient(9, "Yujin", "KIA", false, 7));
        patients.add(new Patient(10, "Dian", "Umum", true, 4));
        patients.add(new Patient(11, "Daniella", "Gigi", true, 6));
        patients.add(new Patient(12, "Andrew", "Anak", false, 1));
        patients.add(new Patient(13, "Clein", "Mata", false, 3));
        patients.add(new Patient(14, "Dominic", "Umum", true, 5));
        patients.add(new Patient(15, "Kyan", "Gigi", true, 4));
        patients.add(new Patient(16, "Magdalein", "Umum", true, 2));
        patients.add(new Patient(17, "Silvy", "Anak", false, 5));
        patients.add(new Patient(18, "Hana", "Umum", true, 8));
        patients.add(new Patient(19, "Grace", "KIA", false, 3));
        patients.add(new Patient(20, "Crish", "Mata", true, 4));
        patients.add(new Patient(21, "Ilyas", "Mata", true, 1));
        patients.add(new Patient(22, "Asteer", "Anak", false, 1));
        patients.add(new Patient(23, "Penelope", "THT", true, 2));
        patients.add(new Patient(24, "Zenya", "Umum", false, 1));
        patients.add(new Patient(25, "Upin", "Mata", true, 4));
        patients.add(new Patient(26, "Itachi", "THT", true, 8));
        patients.add(new Patient(27, "Sanemi", "Mata", true, 3));
        patients.add(new Patient(28, "Kiryu", "Umum", true, 2));
        patients.add(new Patient(29, "Hanabi", "KIA", false, 9));
        patients.add(new Patient(30, "Yuxiu", "Umum", true, 4));
    }

    //CRUD
    static void addPatient(){ //TAMBAH PASIEN
        System.out.print("ID: ");
        int id = input.nextInt();
        input.nextLine();

        boolean idSudahAda = false;
        for(Patient p : patients){
            if(p.id == id){
                idSudahAda = true;
                break;
            }
        }
        if(idSudahAda){
            System.out.println("ID: " + id + " sudah terdaftar! Gagal menambahkan pasien.");
            return;
        }

        System.out.print("Nama: ");
        String name = input.nextLine();

        boolean namaSudahAda = false;
        for(Patient p : patients){
            if(p.name.equalsIgnoreCase(name)){
                namaSudahAda = true;
                break;
            }
        }
        if(namaSudahAda){
            System.out.println("Nama: " + name + " sudah terdaftar! Gagal menambahkan pasien.");
            return;
        }

        System.out.print("Kategori [Umum, Anak, Spesialis, KIA, THT, Gigi, Mata]: ");
        String category = input.nextLine();

        System.out.print("Status [1 = Aktif, 0 = Nonaktif]: ");
        Boolean active = input.nextInt() == 1;

        System.out.print("Jumlah kunjungan: ");
        int visitCount = input.nextInt();
        patients.add(new Patient(id, name, category, active, visitCount));
        saveToCSV();
        System.out.println("Data pasien berhasil ditambahkan!");
    }

    static void showAll(){ //TAMPILKAN DATA
        for(Patient p : patients){
            p.display();
        }
    }

    static void editPatient(){ //EDIT PASIEN
        System.out.print("Masukkan ID: ");
        int id = input.nextInt();

        for(Patient p : patients){
            if(p.id == id){
                input.nextLine();
                System.out.print("Nama: ");
                p.name = input.nextLine();
                System.out.print("Kategori baru [Umum, Anak, Spesialis, KIA, THT, Gigi, Mata]: ");
                p.category = input.nextLine();
                System.out.print("Status baru [1 = Aktif, 0 = Nonaktif]: ");
                p.active= Boolean.parseBoolean(input.nextLine());
                System.out.print("Jumlah kunjungan baru: ");
                p.visitCount = Integer.parseInt(input.nextLine());
                break;
            }
        }
    }

    static void deletePatient() { //SOFT DELETE
        System.out.print("ID: ");
        int id = input.nextInt();

        for (Patient p : patients) {
            if (p.id == id) {
                p.active = false;
            }
        }
        saveToCSV();
    }

    // SEARCH
    static void searchByName() { //CARI NAMA
        System.out.print("Nama: ");
        String name = input.nextLine();

        boolean ditemukan = false;

        for (Patient p : patients) {
            if (p.name.equalsIgnoreCase(name)) {
                p.display();
                ditemukan = true;
            } 
        }
        if(!ditemukan) {
            System.out.println("Nama Pasien " + name + " Tidak Ditemukan.");
        }
        
    }

    static void searchByID() { //CARI ID
        sortByID(); // syarat binary search

        System.out.print("ID: ");
        int target = input.nextInt();

        int left = 0, right = patients.size() - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (patients.get(mid).id == target) {
                patients.get(mid).display();
                return;
            } else if (patients.get(mid).id < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println("ID Pasien " +  target + " Tidak ditemukan");
    }

    static void searchByCategory() { //CARI KATEGORI
        System.out.print("Masukkan Kategori yang Dicari [Umum, Anak, Spesialis, KIA, THT, Gigi, Mata]: ");
        String category = input.nextLine();

        boolean ditemukan = false;

        for (Patient p : patients) {
            if (p.category.equalsIgnoreCase(category)) {
                p.display();
                ditemukan = true;
            } 
        }
        if(!ditemukan) {
            System.out.println("Pasien dengan Kategori " + category + " Tidak Ditemukan.");
        }
        
    }

    static void searchByActive(){ //CARI STATUS
        System.out.print("Masukkan Status yang Dicari [1 = Aktif, 0 = Nonaktif]: ");
        boolean active = input.nextInt() == 1;

        boolean ditemukan = false;

        for(Patient p : patients){
            if(p.active == (active)){
                p.display();
                ditemukan = true;
            }
        }
        if(!ditemukan){
            System.out.println("Tidak ada pasien dengan status tersebut.");
        }
    }
 
    // SORTING
    static void sortByID() { //SORT ID
        for (int i = 0; i < patients.size() - 1; i++) {
            for (int j = 0; j < patients.size() - i - 1; j++) {
                if (patients.get(j).id > patients.get(j + 1).id) {
                    
                    Patient temp = patients.get(j);
                    patients.set(j, patients.get(j + 1));
                    patients.set(j + 1, temp);
                }
            }
        }
    }

    static void sortByName() { //SORT NAMA
        for (int i = 0; i < patients.size() - 1; i++) {
            int min = i;
            for (int j = i + 1; j < patients.size(); j++) {
                if (patients.get(j).name.compareTo(patients.get(min).name) < 0) {
                    min = j;
                }
            }
            Patient temp = patients.get(i);
            patients.set(i, patients.get(min));
            patients.set(min, temp);
        }
    }

    static void sortByvisitCount() { //SORT KUNJUNGAN
        for (int i = 0; i < patients.size() - 1; i++) {
            for (int j = 0; j < patients.size() - i - 1; j++) {
                if (patients.get(j).visitCount < patients.get(j + 1).visitCount) {
                    
                    Patient temp = patients.get(j);
                    patients.set(j, patients.get(j + 1));
                    patients.set(j + 1, temp);
                }
            }
        }
    }

    // STATISTIK
    static void statistics() {
        int total = patients.size();
        int active = 0;

        for (Patient p : patients) {
            if (p.active) active++;
        }
        System.out.println("Total: " + total);
        System.out.println("Aktif: " + active);
    }

    static void saveToCSV() {

    try {

        FileWriter writer = new FileWriter("data.csv");

        writer.write("ID,Nama,Kategori,Status,Kunjungan\n");

        for (Patient p : patients) {

            writer.write(
                p.id + "," +
                p.name + "," +
                p.category + "," +
                p.active + "," +
                p.visitCount + "\n"
            );
        }
        writer.close();

        System.out.println("Data berhasil disimpan ke CSV!");

    } catch(IOException e) {

        System.out.println(e);
    }
    }
}
