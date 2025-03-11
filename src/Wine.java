
import java.sql.Date;
import java.time.LocalDate;

public class Wine {
    private int wineNumber;
    private Date date;
    private String color;
    private String quality;
    private float alcohol;
    private int pH;
    private String fixedAcidity;
    private String volatileAcidity;
    private float citricAcid;
    private int residualSugar;
    private String chlorides;
    private float freeSulfurDioxide;
    private int totalSulfurDioxide;
    private String density;
    private String sulphates;

    // Constructor
    public Wine(int wineNumber, Date date, String color, String quality, float alcohol, int pH,
                String fixedAcidity, String volatileAcidity, float citricAcid,
                int residualSugar, String chlorides, float freeSulfurDioxide,
                int totalSulfurDioxide, String density, String sulphates) {
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

    // Getters and Setters
    public int getWineNumber() {
        return wineNumber;
    }

    public void setWineNumber(int wineNumber) {
        this.wineNumber = wineNumber;
    }

    public Date getDate() {return date;}

    public void setdate(Date date) {this.date = date;}

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

    public int getPH() {
        return pH;
    }

    public void setPH(int pH) {
        this.pH = pH;
    }

    public String getFixedAcidity() {
        return fixedAcidity;
    }

    public void setFixedAcidity(String fixedAcidity) {
        this.fixedAcidity = fixedAcidity;
    }

    public String getVolatileAcidity() {
        return volatileAcidity;
    }

    public void setVolatileAcidity(String volatileAcidity) {
        this.volatileAcidity = volatileAcidity;
    }

    public float getCitricAcid() {
        return citricAcid;
    }

    public void setCitricAcid(float citricAcid) {
        this.citricAcid = citricAcid;
    }

    public int getResidualSugar() {
        return residualSugar;
    }

    public void setResidualSugar(int residualSugar) {
        this.residualSugar = residualSugar;
    }

    public String getChlorides() {
        return chlorides;
    }

    public void setChlorides(String chlorides) {
        this.chlorides = chlorides;
    }

    public float getFreeSulfurDioxide() {
        return freeSulfurDioxide;
    }

    public void setFreeSulfurDioxide(float freeSulfurDioxide) {
        this.freeSulfurDioxide = freeSulfurDioxide;
    }

    public int getTotalSulfurDioxide() {
        return totalSulfurDioxide;
    }

    public void setTotalSulfurDioxide(int totalSulfurDioxide) {
        this.totalSulfurDioxide = totalSulfurDioxide;
    }

    public String getDensity() {
        return density;
    }

    public void setDensity(String density) {
        this.density = density;
    }

    public String getSulphates() {
        return sulphates;
    }

    public void setSulphates(String sulphates) {
        this.sulphates = sulphates;
    }
}
