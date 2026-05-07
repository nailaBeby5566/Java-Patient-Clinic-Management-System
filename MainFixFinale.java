import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

static ArrayList<Patient> patients = new ArrayList<>();
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n=== SISTEM MANAJEMEN PASIEN ===");
            System.out.println("1. Tambah Pasien");
            System.out.println("2. Tampilkan Semua");
            System.out.println("3. Edit Pasien");
            System.out.println("4. Hapus (Soft Delete)");
            System.out.println("5. Cari Nama (Linear)");
            System.out.println("6. Cari ID (Binary)");
            System.out.println("7. Cari Berdasarkan Kategori (Linear)");
            System.out.println("8. Sort ID (Bubble)");
            System.out.println("9. Sort Nama (Selection)");
            System.out.println("10. Sort Kunjungan (Descending)");
            System.out.println("11. Statistik");
            System.out.println("0. Keluar");
            System.out.print("Pilih: ");

            choice = input.nextInt();

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
                case 8: 
                sortByID();
                System.out.println("\n===Hasil Sort Berdasarkan ID===");
                showAll();
                break;
                case 9: 
                sortByName();
                System.out.println("\n===Hasil Sort Berdasarkan Nama===");
                showAll();
                break;
                case 10:
                sortByvisitCount();
                System.out.println("\n===Hasil Sort Berdasarkan Kunjungan Terbanyak===");
                showAll();
                break;
                case 11: statistics();
                break;
            }

        } while (choice != 0);
    }

    // CRUD
    static void addPatient() {
        System.out.print("ID: ");
        int id = input.nextInt();
        input.nextLine();
        System.out.print("Nama: ");
        String name = input.nextLine();
        System.out.print("Kategori [Umum, Anak, Spesialis, KIA (Kesehatan Ibu & Anak)]: ");
        String cat = input.nextLine();
        System.out.print("Status: ");
        boolean active = Boolean.parseBoolean(input.nextLine());
        System.out.print("Kunjungan ke: ");
        int visitCount = Integer.parseInt(input.nextLine());
        patients.add(new Patient(id, name, cat, active, visitCount));
    }

    static void showAll() {
        for (Patient p : patients) {
            p.display();
        }
    }

    static void editPatient() {
        System.out.print("\nMasukkan ID: ");
        int id = input.nextInt();

        for (Patient p : patients) {
            if (p.id == id) {
                input.nextLine();
                System.out.print("Nama: ");
                p.name = input.nextLine();
                System.out.print("Kategori baru [Umum, Anak, Spesialis, KIA (Kesehatan Ibu & Anak)]: ");
                p.category = input.nextLine();
                System.out.print("Status baru: ");
                p.active= Boolean.parseBoolean(input.nextLine());
                System.out.print("Jumlah kunjungan baru: ");
                p.visitCount = Integer.parseInt(input.nextLine());

            }
        }
    }

    static void deletePatient() {
        System.out.print("\nMasukkan ID: ");
        int id = input.nextInt();

        for (Patient p : patients) {
            if (p.id == id) {
                p.active = false;
            }
        }
    }

    // SEARCH
    static void searchByName() {
        input.nextLine();
        System.out.print("\nMasukkan Nama yang Dicari: ");
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

    static void searchByID() {
        sortByID(); // syarat binary search

        System.out.print("\nMasukkan ID yang Dicari: ");
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

    static void searchByCategory() {
        input.nextLine();
        System.out.print("\nMasukkan Kategori yang Dicari [Umum, Anak, Spesialis, KIA (Kesehatan Ibu & Anak)]: ");
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

    // SORTING
    static void sortByID() {
        for (int i = 0; i < patients.size() - 1; i++) {
            for (int j = 0; j < patients.size() - i - 1; j++) {
                if (patients.get(j).id > patients.get(j + 1).id) {
                    Collections.swap(patients, j, j + 1);
                }
            }
        }
    }

    static void sortByName() {
        for (int i = 0; i < patients.size() - 1; i++) {
            int min = i;
            for (int j = i + 1; j < patients.size(); j++) {
                if (patients.get(j).name.compareTo(patients.get(min).name) < 0) {
                    min = j;
                }
            }
            Collections.swap(patients, i, min);
        }
    }

    static void sortByvisitCount() {
        for (int i = 0; i < patients.size() - 1; i++) {
            for (int j = 0; j < patients.size() - i - 1; j++) {
                if (patients.get(j).visitCount < patients.get(j + 1).visitCount) {
                    Collections.swap(patients, j, j + 1);
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
