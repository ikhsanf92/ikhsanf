import java.util.Scanner;

public class MainApp {
    private static SepedaListrikController sepedaController = new SepedaListrikController();
    private static JadwalPemeliharaanController jadwalController = new JadwalPemeliharaanController();
    private static StaffController staffController = new StaffController();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int mainChoice;

        do {
            System.out.println("\n=== Menu Utama Aplikasi Manajemen Sepeda Listrik ===");
            System.out.println("1. Kelola Sepeda Listrik");
            System.out.println("2. Kelola Jadwal Pemeliharaan");
            System.out.println("3. Kelola Staff");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            mainChoice = scanner.nextInt();

            switch (mainChoice) {
                case 1:
                    kelolaSepedaListrik();
                    break;
                case 2:
                    kelolaJadwalPemeliharaan();
                    break;
                case 3:
                    kelolaStaff();
                    break;
                case 0:
                    System.out.println("Keluar dari aplikasi...");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } while (mainChoice != 0);

        scanner.close();
    }

    private static void kelolaSepedaListrik() {
        int choice;
        do {
            System.out.println("\n=== Kelola Sepeda Listrik ===");
            System.out.println("1. Tambah Sepeda Listrik");
            System.out.println("2. Tampilkan Semua Sepeda Listrik");
            System.out.println("3. Hapus Sepeda Listrik");
            System.out.println("0. Kembali ke Menu Utama");
            System.out.print("Pilih menu: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Masukkan ID Sepeda: ");
                    int idSepeda = scanner.nextInt();
                    System.out.print("Masukkan Model Sepeda: ");
                    String model = scanner.next();
                    System.out.print("Masukkan Status Sepeda: ");
                    String status = scanner.next();
                    System.out.print("Masukkan Jumlah Stok: ");
                    int stok = scanner.nextInt();
                    sepedaController.addSepeda(new SepedaListrik(idSepeda, model, status, stok));
                    break;
                case 2:
                    sepedaController.showAllSepeda();
                    break;
                case 3:
                    System.out.print("Masukkan ID Sepeda yang ingin dihapus: ");
                    int idHapus = scanner.nextInt();
                    sepedaController.deleteSepeda(idHapus);
                    break;
                case 0:
                    System.out.println("Kembali ke Menu Utama...");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } while (choice != 0);
    }

    private static void kelolaJadwalPemeliharaan() {
        int choice;
        do {
            System.out.println("\n=== Kelola Jadwal Pemeliharaan ===");
            System.out.println("1. Tambah Jadwal Pemeliharaan");
            System.out.println("2. Tampilkan Semua Jadwal Pemeliharaan");
            System.out.println("3. Hapus Jadwal Pemeliharaan");
            System.out.println("0. Kembali ke Menu Utama");
            System.out.print("Pilih menu: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Masukkan ID Jadwal: ");
                    int idJadwal = scanner.nextInt();
                    System.out.print("Masukkan Tanggal (YYYY-MM-DD): ");
                    String tanggal = scanner.next();
                    System.out.print("Masukkan Keterangan: ");
                    String keterangan = scanner.next();
                    System.out.print("Masukkan ID Sepeda: ");
                    int idSepedaJadwal = scanner.nextInt();
                    jadwalController.addJadwal(new JadwalPemeliharaan(idJadwal, tanggal, keterangan, idSepedaJadwal));
                    break;
                case 2:
                    jadwalController.showAllJadwal();
                    break;
                case 3:
                    System.out.print("Masukkan ID Jadwal yang ingin dihapus: ");
                    int idHapusJadwal = scanner.nextInt();
                    jadwalController.deleteJadwal(idHapusJadwal);
                    break;
                case 0:
                    System.out.println("Kembali ke Menu Utama...");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } while (choice != 0);
    }

    private static void kelolaStaff() {
        int choice;
        do {
            System.out.println("\n=== Kelola Staff ===");
            System.out.println("1. Tambah Staff");
            System.out.println("2. Tampilkan Semua Staff");
            System.out.println("3. Hapus Staff");
            System.out.println("0. Kembali ke Menu Utama");
            System.out.print("Pilih menu: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Masukkan ID Staff: ");
                    int idStaff = scanner.nextInt();
                    System.out.print("Masukkan Nama Staff: ");
                    String nama = scanner.next();
                    System.out.print("Masukkan Posisi: ");
                    String posisi = scanner.next();
                    System.out.print("Masukkan Kontak: ");
                    String kontak = scanner.next();
                    staffController.addStaff(new Staff(idStaff, nama, posisi, kontak));
                    break;
                case 2:
                    staffController.showAllStaff();
                    break;
                case 3:
                    System.out.print("Masukkan ID Staff yang ingin dihapus: ");
                    int idHapusStaff = scanner.nextInt();
                    staffController.deleteStaff(idHapusStaff);
                    break;
                case 0:
                    System.out.println("Kembali ke Menu Utama...");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } while (choice != 0);
    }
}
