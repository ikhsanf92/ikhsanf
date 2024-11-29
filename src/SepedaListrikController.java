import java.util.ArrayList;

public class SepedaListrikController {
    private ArrayList<SepedaListrik> sepedaList = new ArrayList<>();

    public void addSepeda(SepedaListrik sepeda) {
        sepedaList.add(sepeda);
        System.out.println("Sepeda berhasil ditambahkan.");
    }

    public void updateSepeda(int id, String model, String status, int jumlahStok) {
        for (SepedaListrik sepeda : sepedaList) {
            if (sepeda.getId() == id) {
                sepeda.setModel(model);
                sepeda.setStatus(status);
                sepeda.setJumlahStok(jumlahStok);
                System.out.println("Sepeda berhasil diperbarui.");
                return;
            }
        }
        System.out.println("Sepeda tidak ditemukan.");
    }

    public void deleteSepeda(int id) {
        sepedaList.removeIf(sepeda -> sepeda.getId() == id);
        System.out.println("Sepeda berhasil dihapus.");
    }

    public void showAllSepeda() {
        for (SepedaListrik sepeda : sepedaList) {
            System.out.println("ID: " + sepeda.getId() + ", Model: " + sepeda.getModel() +
                               ", Status: " + sepeda.getStatus() + ", Stok: " + sepeda.getJumlahStok());
        }
    }
}
