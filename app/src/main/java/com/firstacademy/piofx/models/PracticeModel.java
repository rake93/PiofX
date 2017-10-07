package com.firstacademy.piofx.models;

/**
 * Created by ananth on 7/9/17.
 */

public class PracticeModel {
    int id,levelText;

    public PracticeModel(int id,int levelText){
        this.id=id;
        this.levelText=levelText;
    }

    public int getId() {
        return id;
    }

    public int getLevelText() {
        return levelText;
    }

}
