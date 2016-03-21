package espol.fiec.edu.lego.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mm on 10/03/2016.
 */
public class Robot implements Parcelable {
    /*public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }*/

    private String model;
    private String brand;
    private String description;
    private int category;
    //private String tel;
    private int photo;


    public Robot(){}
    public Robot(String m, String b, int p, String d){
        model = m;
        brand = b;
        photo = p;
        description = d;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    //PARCELABLE
    public Robot(Parcel parcel){
        setModel(parcel.readString());
        setBrand(parcel.readString());
        setDescription(parcel.readString());
        setCategory(parcel.readInt());
        setPhoto(parcel.readInt());
        //setTel(parcel.readString());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getModel());
        dest.writeString(getBrand());
        dest.writeString(getDescription());
        dest.writeInt(getCategory());
        dest.writeInt(getPhoto());
        //dest.writeString(getTel());
    }

    public static final Parcelable.Creator<Robot> CREATOR = new Parcelable.Creator<Robot>(){

        @Override
        public Robot createFromParcel(Parcel source) {
            return new Robot(source);
        }

        @Override
        public Robot[] newArray(int size) {
            return new Robot[size];
        }
    };
}
