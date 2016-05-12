package espol.fiec.edu.lego.domain;

import com.mikepenz.materialdrawer.model.ProfileDrawerItem;

import java.io.Serializable;

/**
 * Created by mm on 21/03/2016.
 */
public class Person implements Serializable {
    private ProfileDrawerItem profile;
    private int background;

    private String username;
    private String name;

    public Person(){}

    public ProfileDrawerItem getProfile(){
        return profile;
    }

    public void setProfile(ProfileDrawerItem profile){
        this.profile = profile;
    }

    public int getBackground(){
        return background;
    }

    public void setBackground(int background){
        this.background = background;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}