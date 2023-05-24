public class BookingDetails {
    private String userId;
    private String colegCamera;
    private String domiciliu;
    private String an;
    private String medieAdmitere;
    private String medieAnuala;

    public BookingDetails(String userId, String colegCamera, String domiciliu, String an, String medieAdmitere, String medieAnuala){

        this.userId = userId;
        this.colegCamera = colegCamera;
        this.domiciliu = domiciliu;
        this.an = an;
        this.medieAdmitere = medieAdmitere;
        this.medieAnuala = medieAnuala;
    }
    public String getUserId() {
        return userId;
    }

    public String getColegCamera() {
        return colegCamera;
    }

    public String getDomiciliu() {
        return domiciliu;
    }

    public String getAn() {
        return an;
    }

    public String getMedieAdmitere() {
        return medieAdmitere;
    }

    public String getMedieAnuala() {
        return medieAnuala;
    }
}

