package espol.fiec.edu.lego.domain;

/**
 * Created by mm on 10/03/2016.
 */
public class Robot {

    private String model;
    private String brand;
    private int photo;

    public Robot(){}
    public Robot(String m, String b, int p){
        model = m;
        brand = b;
        photo = p;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }
}
