package espol.fiec.edu.lego.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mm on 10/03/2016.
 */
public class Robot implements Parcelable {

    private String title;
    private String brand;
    private String description;
    private int category;
    private int photo;

    private String url;

    public Robot(){}
    public Robot(String title, String brand, String description){
        this.title = title;
        this.brand = brand;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //PARCELABLE
    public Robot(Parcel parcel){
        setTitle(parcel.readString());
        setBrand(parcel.readString());
        setDescription(parcel.readString());
        setCategory(parcel.readInt());
        setPhoto(parcel.readInt());

        setUrl(parcel.readString());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getTitle());
        dest.writeString(getBrand());
        dest.writeString(getDescription());
        dest.writeInt(getCategory());
        dest.writeInt(getPhoto());

        dest.writeString(getUrl());
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
