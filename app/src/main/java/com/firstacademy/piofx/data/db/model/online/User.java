package com.firstacademy.piofx.data.db.model.online;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Rakesh Muppa on 28-10-2017.
 */
@IgnoreExtraProperties
public class User {

    private String username;
    private String email;
    private String photoUrl;
    private int mobile;

    public User(){}

    public User(String username, String email, String photoUrl, int mobile) {
        this.username = username;
        this.email = email;
        this.photoUrl = photoUrl;
        this.mobile = mobile;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public int getMobile() {
        return mobile;
    }
}
