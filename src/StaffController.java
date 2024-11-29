import java.util.ArrayList;

public class StaffController {
    private ArrayList<Staff> staffList = new ArrayList<>();

    public void addStaff(Staff staff) {
        staffList.add(staff);
        System.out.println("Staff berhasil ditambahkan.");
    }

    public void deleteStaff(int id) {
        staffList.removeIf(staff -> staff.getId() == id);
        System.out.println("Staff berhasil dihapus.");
    }

    public void showAllStaff() {
        for (Staff staff : staffList) {
            System.out.println("ID: " + staff.getId() + ", Nama: " + staff.getNama() +
                               ", Posisi: " + staff.getPosisi() + ", Kontak: " + staff.getKontak());
        }
    }
}
