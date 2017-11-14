package com.firstacademy.piofx.models;

import com.firstacademy.piofx.data.db.model.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ananth on 7/9/17.
 */

public class PracticeModel {
    int id,levelText;
    List<Question> practiceCompletedList = new ArrayList<>();
    public PracticeModel(int id,int levelText, List<Question> practiceCompletedList){
        this.id=id;
        this.levelText=levelText;
        this.practiceCompletedList = practiceCompletedList;
    }

    public int getId() {
        return id;
    }

    public int getLevelText() {
        return levelText;
    }

    public List<Question> getPracticeCompletedList() {
        return practiceCompletedList;
    }
}
