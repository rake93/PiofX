package com.firstacademy.piofx.models;

import com.firstacademy.piofx.activities.Home;
import com.firstacademy.piofx.data.db.model.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ananth on 5/8/17.
 */

public class HomeModel {
    int id;
    int image;
    String vocubularyHeading,vocabularyDesc;
    List<Question> practiceCompletedList = new ArrayList<>();

    public HomeModel(int id,int image,String vocubularyHeading,String vocabularyDesc,List<Question> practiceCompletedList){
        this.id=id;
        this.image=image;
        this.vocubularyHeading=vocubularyHeading;
        this.vocabularyDesc=vocabularyDesc;
        this.practiceCompletedList=practiceCompletedList;
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

    public List<Question> getPracticeCompletedList() {
        return practiceCompletedList;
    }
}
