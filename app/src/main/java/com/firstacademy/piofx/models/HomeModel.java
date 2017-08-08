package com.firstacademy.piofx.models;

import com.firstacademy.piofx.activities.Home;

/**
 * Created by ananth on 5/8/17.
 */

public class HomeModel {
    int id;
    int image;
    String vocubularyHeading,vocabularyDesc;

    public HomeModel(int id,int image,String vocubularyHeading,String vocabularyDesc){
        this.id=id;
        this.image=image;
        this.vocubularyHeading=vocubularyHeading;
        this.vocabularyDesc=vocabularyDesc;
    }

    public int getId() {
        return id;
    }

    public int getImage() {
        return image;
    }

    public String getVocabularyHeading() {
        return vocubularyHeading;
    }

    public String getVocabularyDesc() {
        return vocabularyDesc;
    }
}
