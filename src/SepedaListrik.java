public class SepedaListrik {
    private int id;
    private String model;
    private String status;
    private int jumlahStok;

    public SepedaListrik(int id, String model, String status, int jumlahStok) {
        this.id = id;
        this.model = model;
        this.status = status;
        this.jumlahStok = jumlahStok;
    }

    // Getter dan Setter
    public int getId() { return id; }
    public String getModel() { return model; }
    public String getStatus() { return status; }
    public int getJumlahStok() { return jumlahStok; }

    public void setModel(String model) { this.model = model; }
    public void setStatus(String status) { this.status = status; }
    public void setJumlahStok(int jumlahStok) { this.jumlahStok = jumlahStok; }
}
