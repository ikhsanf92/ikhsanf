import java.util.ArrayList;

public class JadwalPemeliharaanController {
    private ArrayList<JadwalPemeliharaan> jadwalList = new ArrayList<>();

    public void addJadwal(JadwalPemeliharaan jadwal) {
        jadwalList.add(jadwal);
        System.out.println("Jadwal pemeliharaan berhasil ditambahkan.");
    }

    public void deleteJadwal(int id) {
        jadwalList.removeIf(jadwal -> jadwal.getId() == id);
        System.out.println("Jadwal pemeliharaan berhasil dihapus.");
    }

    public void showAllJadwal() {
        for (JadwalPemeliharaan jadwal : jadwalList) {
            System.out.println("ID: " + jadwal.getId() + ", Tanggal: " + jadwal.getTanggal() +
                               ", Keterangan: " + jadwal.getKeterangan() + ", ID Sepeda: " + jadwal.getIdSepeda());
        }
    }
}
