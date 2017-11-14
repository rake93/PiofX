/*
 * Copyright (C) 2017 Pi(X) LABS PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.firstacademy.piofx.data.db.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Transient;

import java.util.List;

/**
 * Created by Rakesh Muppa on 08-08-2017.
 */
@Entity(nameInDb = "questions")
public class Question {

    @Expose
    @SerializedName("id")
    @Id
    private Long id;

    @Expose
    @SerializedName("question_text")
    @Property(nameInDb = "question_text")
    private String questionText;

    @Expose
    @SerializedName("question_clause")
    @Property(nameInDb = "question_clause")
    private String questionClause;

    @Expose
    @SerializedName("question_category")
    @Property(nameInDb = "question_category")
    private String questionCategory;

    @Expose
    @SerializedName("question_set")
    @Property(nameInDb = "question_set")
    private String questionSet;

    @Expose
    @SerializedName("word_definition")
    @Property(nameInDb = "word_definition")
    private String word_definition;

    @Expose
    @SerializedName("word_description1")
    @Property(nameInDb = "word_description1")
    private String word_description1;

    @Expose
    @SerializedName("word_description2")
    @Property(nameInDb = "word_description2")
    private String word_description2;

    @Expose
    @SerializedName("is_answered")
    @Property(nameInDb = "is_answered")
    private boolean isAnswered;

    @Expose
    @SerializedName("is_practiced")
    @Property(nameInDb = "is_practiced")
    private boolean isPracticed;

    @Expose
    @SerializedName("created_at")
    @Property(nameInDb = "created_at")
    private String createdAt;

    @Expose
    @SerializedName("updated_at")
    @Property(nameInDb = "updated_at")
    private String updatedAt;

    @Transient
    private boolean isQuizAnswered;

    @Transient
    private String selectedOption;

    @Generated(hash = 972434178)
    public Question(Long id, String questionText, String questionClause,
            String questionCategory, String questionSet, String word_definition,
            String word_description1, String word_description2, boolean isAnswered,
            boolean isPracticed, String createdAt, String updatedAt) {
        this.id = id;
        this.questionText = questionText;
        this.questionClause = questionClause;
        this.questionCategory = questionCategory;
        this.questionSet = questionSet;
        this.word_definition = word_definition;
        this.word_description1 = word_description1;
        this.word_description2 = word_description2;
        this.isAnswered = isAnswered;
        this.isPracticed = isPracticed;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Generated(hash = 1868476517)
    public Question() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionText() {
        return this.questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getQuestionClause() {
        return this.questionClause;
    }

    public void setQuestionClause(String questionClause) {
        this.questionClause = questionClause;
    }

    public String getQuestionCategory() {
        return this.questionCategory;
    }

    public void setQuestionCategory(String questionCategory) {
        this.questionCategory = questionCategory;
    }

    public String getWord_definition() {
        return this.word_definition;
    }

    public void setWord_definition(String word_definition) {
        this.word_definition = word_definition;
    }

    public String getWord_description1() {
        return this.word_description1;
    }

    public void setWord_description1(String word_description1) {
        this.word_description1 = word_description1;
    }

    public String getWord_description2() {
        return this.word_description2;
    }

    public void setWord_description2(String word_description2) {
        this.word_description2 = word_description2;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getQuestionSet() {
        return this.questionSet;
    }

    public void setQuestionSet(String questionSet) {
        this.questionSet = questionSet;
    }

    public boolean getIsAnswered() {
        return this.isAnswered;
    }

    public void setIsAnswered(boolean isAnswered) {
        this.isAnswered = isAnswered;
    }

    public boolean getIsPracticed() {
        return this.isPracticed;
    }

    public void setIsPracticed(boolean isPracticed) {
        this.isPracticed = isPracticed;
    }

    public boolean isQuizAnswered() {
        return isQuizAnswered;
    }

    public void setQuizAnswered(boolean quizAnswered) {
        isQuizAnswered = quizAnswered;
    }

    public String getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
    }
}
