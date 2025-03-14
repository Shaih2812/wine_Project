import java.sql.Date;

public class Wine {
    private int wineNumber;
    private Date date;
    private String color;
    private String quality;
    private float alcohol;
    private float pH;
    private float fixedAcidity;
    private float volatileAcidity;
    private float citricAcid;
    private float residualSugar;
    private float chlorides;
    private int freeSulfurDioxide;
    private int totalSulfurDioxide;
    private float density;
    private float sulphates;

    // Construc
    public Wine(int wineNumber, Date date, String color, String quality, float alcohol, float pH,
                float fixedAcidity, float volatileAcidity, float citricAcid,
                float residualSugar, float chlorides, int freeSulfurDioxide,
                int totalSulfurDioxide, float density, float sulphates) {
        this.wineNumber = wineNumber;
        this.date = date;
        this.color = color;
        this.quality = quality;
        this.alcohol = alcohol;
        this.pH = pH;
        this.fixedAcidity = fixedAcidity;
        this.volatileAcidity = volatileAcidity;
        this.citricAcid = citricAcid;
        this.residualSugar = residualSugar;
        this.chlorides = chlorides;
        this.freeSulfurDioxide = freeSulfurDioxide;
        this.totalSulfurDioxide = totalSulfurDioxide;
        this.density = density;
        this.sulphates = sulphates;
    }


    public int getWineNumber() {
        return wineNumber;
    }

    public void setWineNumber(int wineNumber) {
        this.wineNumber = wineNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public float getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(float alcohol) {
        this.alcohol = alcohol;
    }

    public float getPH() {
        return pH;
    }

    public void setPH(float pH) {
        this.pH = pH;
    }

    public float getFixedAcidity() {
        return fixedAcidity;
    }

    public void setFixedAcidity(float fixedAcidity) {
        this.fixedAcidity = fixedAcidity;
    }

    public float getVolatileAcidity() {
        return volatileAcidity;
    }

    public void setVolatileAcidity(float volatileAcidity) {
        this.volatileAcidity = volatileAcidity;
    }

    public float getCitricAcid() {
        return citricAcid;
    }

    public void setCitricAcid(float citricAcid) {
        this.citricAcid = citricAcid;
    }

    public float getResidualSugar() {
        return residualSugar;
    }

    public void setResidualSugar(float residualSugar) {
        this.residualSugar = residualSugar;
    }

    public float getChlorides() {
        return chlorides;
    }

    public void setChlorides(float chlorides) {
        this.chlorides = chlorides;
    }

    public int getFreeSulfurDioxide() {
        return freeSulfurDioxide;
    }

    public void setFreeSulfurDioxide(int freeSulfurDioxide) {
        this.freeSulfurDioxide = freeSulfurDioxide;
    }

    public int getTotalSulfurDioxide() {
        return totalSulfurDioxide;
    }

    public void setTotalSulfurDioxide(int totalSulfurDioxide) {
        this.totalSulfurDioxide = totalSulfurDioxide;
    }

    public float getDensity() {
        return density;
    }

    public void setDensity(float density) {
        this.density = density;
    }

    public float getSulphates() {
        return sulphates;
    }

    public void setSulphates(float sulphates) {
        this.sulphates = sulphates;
    }
}
