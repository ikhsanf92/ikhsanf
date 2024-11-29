public class JadwalPemeliharaan {
    private int id;
    private String tanggal;
    private String keterangan;
    private int idSepeda;

    public JadwalPemeliharaan(int id, String tanggal, String keterangan, int idSepeda) {
        this.id = id;
        this.tanggal = tanggal;
        this.keterangan = keterangan;
        this.idSepeda = idSepeda;
    }

    // Getter dan Setter
    public int getId() { return id; }
    public String getTanggal() { return tanggal; }
    public String getKeterangan() { return keterangan; }
    public int getIdSepeda() { return idSepeda; }

    public void setTanggal(String tanggal) { this.tanggal = tanggal; }
    public void setKeterangan(String keterangan) { this.keterangan = keterangan; }
    public void setIdSepeda(int idSepeda) { this.idSepeda = idSepeda; }
}
