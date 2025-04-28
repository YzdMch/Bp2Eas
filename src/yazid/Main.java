package yazid;

import java.util.ArrayList;
import java.util.Scanner;

//Class parent untuk perangkat komputer dan printer
//Bisa menambah informasi dasar perangkat seperti id, nama, dan status
class Perangkat {
    String id;
    String nama;
    String status;

    Perangkat(String id, String nama, String status) {
        this.id = id;
        this.nama = nama;
        this.status = status;
    }

    //menggunakan equalsIgnoreCase agar tidak case-sensitive
    boolean tersedia() {
        return status.equalsIgnoreCase("Tersedia");
    }

    void setStatus(String status) {
        this.status = status;
    }

    String getInfo() {
        return id + " - " + nama + " - Status: " + status;
    }
}

//Child class dari Perangkat untuk komputer
//Menambah informasi seperti CPU, RAM, penyimpanan, dan OS
class Komputer extends Perangkat {
    String cpu;
    int ram;
    int penyimpanan;
    String os;

    Komputer(String id, String nama, String status, String cpu, int ram, int penyimpanan, String os) {
        super(id, nama, status);
        this.cpu = cpu;
        this.ram = ram;
        this.penyimpanan = penyimpanan;
        this.os = os;
    }

    String getInfo() {
        return id + " - Komputer: " + nama + ", CPU: " + cpu + ", RAM: " + ram + "GB, Storage: " + penyimpanan + "GB, OS: " + os + ", Status: " + status;
    }
}

//Child class dari Perangkat untuk printer
//Menambah informasi seperti tinta dan kecepatan cetak
class Printer extends Perangkat {
    String tinta;
    int kecepatan;

    Printer(String id, String nama, String status, String tinta, int kecepatan) {
        super(id, nama, status);
        this.tinta = tinta;
        this.kecepatan = kecepatan;
    }

    String getInfo() {
        return id + " - Printer: " + nama + ", Tinta: " + tinta + ", Kecepatan: " + kecepatan + " ppm, Status: " + status;
    }
}

//Class untuk mencatat pemakaian perangkat
//Menampung informasi pemakai seperti nama, peran(seperti siswa/teknisi), dan id perangkat yang digunakan
class Pemakai {
    String nama;
    String peran;
    String idPerangkat;

    Pemakai(String nama, String peran, String idPerangkat) {
        this.nama = nama;
        this.peran = peran;
        this.idPerangkat = idPerangkat;
    }
}

//Class untuk mengelola perangkat dan pemakai
/*Menampung list perangkat dan pemakai dengan arraylist, serta menyediakan method untuk menambah perangkat, 
menampilkan perangkat, mencari perangkat, dan mencatat pemakaian*/
class Lab {
    ArrayList<Perangkat> perangkatList = new ArrayList<>(); //List untuk perangkat
    ArrayList<Pemakai> pemakaiList = new ArrayList<>(); //List untuk pemakai

    /*Method untuk menambah perangkat ke dalam list
    Menggunakan polymorphism agar bisa menampung objek dari class Perangkat dan subclassnya (Komputer dan Printer)*/
    void tambahPerangkat(Perangkat p) {
        perangkatList.add(p);
    }

    /*Method untuk menampilkan semua perangkat yang ada di dalam list 
    Menggunakan method getInfo() dari class Perangkat */
    void tampilkanPerangkat() {
        for (Perangkat p : perangkatList) {
            System.out.println(p.getInfo());
        }
    }

    /*Method untuk menampilkan perangkat yang tersedia
    Menggunakan method tersedia() dari class Perangkat untuk mengecek status perangkat*/
    void tampilkanPerangkatTersedia() {
        for (Perangkat p : perangkatList) {
            if (p.tersedia()) {
                System.out.println(p.getInfo());
            }
        }
    }

    //Method untuk mencari perangkat berdasarkan id
    //Menggunakan equalsIgnoreCase agar tidak case-sensitive
    Perangkat cariPerangkat(String id) {
        for (Perangkat p : perangkatList) {
            if (p.id.equalsIgnoreCase(id)) {
                return p;
            }
        }
        return null;
    }

    //Method untuk mencatat pemakaian perangkat
    //Menambahkan objek Pemakai ke dalam list pemakaiList
    void catatPemakaian(Pemakai p) {
        pemakaiList.add(p);
    }

    //Method untuk menghitung jumlah perangkat yang tersedia 
    //Menggunakan instanceof untuk mengecek jenis perangkat
    //Menggunakan equalsIgnoreCase agar tidak case-sensitive
    int hitungTersedia(String jenis) {
        int jumlah = 0;
        for (Perangkat p : perangkatList) {
            if (p.tersedia()) {
                if ((jenis.equalsIgnoreCase("Komputer") && p instanceof Komputer) ||
                    (jenis.equalsIgnoreCase("Printer") && p instanceof Printer)) {
                    jumlah++;
                }
            }
        }
        return jumlah;
    }

    //Method untuk menghitung jumlah pemakaian perangkat berdasarkan id
    //Menggunakan equalsIgnoreCase agar tidak case-sensitive
    int hitungPenggunaan(String idPerangkat) {
        int jumlah = 0;
        for (Pemakai p : pemakaiList) {
            if (p.idPerangkat.equalsIgnoreCase(idPerangkat)) jumlah++;
        }
        return jumlah;
    }
}

//Main class untuk menjalankan program
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Lab lab = new Lab();

        // Tambah perangkat (3 komputer, 2 printer)
        lab.tambahPerangkat(new Komputer("K001", "Komputer A", "Tersedia", "Intel i5", 8, 512, "Windows"));
        lab.tambahPerangkat(new Komputer("K002", "Komputer B", "Tersedia", "Intel i7", 16, 1024, "Windows"));
        lab.tambahPerangkat(new Komputer("K003", "Komputer C", "Tersedia", "AMD Ryzen 5", 8, 512, "Linux"));
        lab.tambahPerangkat(new Printer("P001", "Printer A", "Tersedia", "Hitam", 20));
        lab.tambahPerangkat(new Printer("P002", "Printer B", "Tersedia", "Warna", 15));

        while (true) {
            System.out.println("Login sebagai: \n1. Teknisi\n2. Siswa\n0. Keluar");
            System.out.print("Pilih: ");
            int pilihan = Integer.parseInt(input.nextLine());

            if (pilihan == 0) break;

            if (pilihan == 1) {
                while (true) {
                    System.out.println("1. Tampilkan Semua Perangkat");
                    System.out.println("2. Hitung Perangkat Tersedia");
                    System.out.println("3. Hitung Penggunaan Perangkat");
                    System.out.println("4. Tambah Perangkat");
                    System.out.println("5. Kembali");
                    System.out.print("Pilih: ");
                    int pilih = Integer.parseInt(input.nextLine());

                    //menampilkan semua perangkat menggunakan method tampilkanPerangkat 
                    if (pilih == 1) lab.tampilkanPerangkat();
                    //menghitung perangkat tersedia menggunakan method hitungTersedia dari class lab
                    else if (pilih == 2) {
                        System.out.println("Komputer tersedia: " + lab.hitungTersedia("Komputer"));
                        System.out.println("Printer tersedia: " + lab.hitungTersedia("Printer"));
                    //menghitung penggunaan perangkat menggunakan method hitungPenggunaan dari class lab
                    } else if (pilih == 3) {
                        System.out.print("ID Perangkat: ");
                        String id = input.nextLine();
                        System.out.println("Digunakan sebanyak: " + lab.hitungPenggunaan(id));
                    //memilih jenis perangkat yang akan ditambahkan
                    } else if (pilih == 4) {
                        System.out.print("Jenis (Komputer/Printer): ");
                        String jenis = input.nextLine();
                        System.out.print("ID: ");
                        String id = input.nextLine();
                        System.out.print("Nama: ");
                        String nama = input.nextLine();
                        System.out.print("Status: ");
                        String status = input.nextLine();
                        
                        //menambahkan perangkat sesuai dengan jenis yang dipilih 
                        //menambahkan informasi tambahan sesuai dengan jenis perangkat(komputer)
                        if (jenis.equalsIgnoreCase("Komputer")) {
                            System.out.print("Contoh: Intel i3, AMD Ryzen 3, Snapdragon Elite \nCPU: ");
                            String cpu = input.nextLine();
                            System.out.print("Input Dengan Satuan GB\nRAM: ");
                            int ram = Integer.parseInt(input.nextLine());
                            System.out.print("Input Dengan Satuan GB\nPenyimpanan: ");
                            int penyimpanan = Integer.parseInt(input.nextLine());
                            System.out.print("Contoh: Windows, Linuc, MacOS\nOS: ");
                            String os = input.nextLine();
                            lab.tambahPerangkat(new Komputer(id, nama, status, cpu, ram, penyimpanan, os));
                        //menambahkan informasi tambahan sesuai dengan jenis perangkat(printer)
                        } else if (jenis.equalsIgnoreCase("Printer")) {
                            System.out.print("Contoh: Warna, Hitam, Serbuk, Thermal\nTinta: ");
                            String tinta = input.nextLine();
                            System.out.print("Input dengan satuan ppm\nKecepatan: ");
                            int kecepatan = Integer.parseInt(input.nextLine());
                            lab.tambahPerangkat(new Printer(id, nama, status, tinta, kecepatan));
                        } else {
                            System.out.println("Jenis tidak dikenal.");
                        }
                    } else if (pilih == 5) {
                        break;
                    } else {
                        System.out.println("Pilihan tidak valid!");
                    }
                }
            //menu jika peran adalah siswa
            } else if (pilihan == 2) {
                while (true) {
                    System.out.println("1. Lihat Perangkat Tersedia");
                    System.out.println("2. Pakai Perangkat");
                    System.out.println("3. Selesai Memakai Perangkat");
                    System.out.println("4. Kembali");
                    System.out.print("Pilih: ");
                    int pilih = Integer.parseInt(input.nextLine());

                    //menampilkan perangkat yang tersedia menggunakan method tampilkanPerangkatTersedia
                    //menggunakan method tersedia() dari class Perangkat untuk mengecek status perangkat
                    if (pilih == 1) lab.tampilkanPerangkatTersedia();
                    //memakai perangkat menggunakan method cariPerangkat dari class lab 
                    else if (pilih == 2) {
                        System.out.print("Nama Anda: ");
                        String nama = input.nextLine();
                        System.out.print("ID Perangkat: ");
                        String id = input.nextLine();

                        Perangkat p = lab.cariPerangkat(id);
                        if (p != null && p.tersedia()) { 
                            p.setStatus("Digunakan"); //mengubah status perangkat menjadi digunakan
                            lab.catatPemakaian(new Pemakai(nama, "Siswa", id)); //menambahkan objek pemakai ke dalam list pemakaiList
                            System.out.println("Penggunaan tercatat.");
                        } else {
                            System.out.println("Perangkat tidak tersedia atau tidak ditemukan.");
                        }
                    //menyelesaikan pemakaian perangkat menggunakan method cariPerangkat dari class lab
                    } else if (pilih == 3) {
                        System.out.print("ID Perangkat yang sudah selesai digunakan: ");
                        String id = input.nextLine();
                        Perangkat p = lab.cariPerangkat(id);
                        if (p != null && !p.tersedia()) {
                            p.setStatus("Tersedia"); //mengubah status perangkat menjadi tersedia kembali
                            //menggunakan method setStatus dari class Perangkat untuk mengubah status perangkat
                            System.out.println("Status perangkat diperbarui menjadi tersedia.");
                        } else {
                            System.out.println("Perangkat tidak ditemukan atau sudah tersedia.");
                        }
                    } else if (pilih == 4) {
                        break;
                    } else {
                        System.out.println("Pilihan tidak valid!");
                    }
                }
            } else {
                System.out.println("Peran tidak dikenal.");
            }
        }

        input.close();
        System.out.println("Program selesai.");
    }
}
