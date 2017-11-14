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

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.DaoException;

/**
 * Created by Rakesh Muppa on 08-08-2017.
 */
@Entity(nameInDb = "options")
public class Option {

    @Expose
    @SerializedName("id")
    @Id(autoincrement = true)
    private Long id;

    @Expose
    @SerializedName("question_id")
    @Property(nameInDb = "question_id")
    private Long questionId;

    @Expose
    @SerializedName("option_textA")
    @Property(nameInDb = "option_textA")
    private String optionTextA;

    @Expose
    @SerializedName("option_textB")
    @Property(nameInDb = "option_textB")
    private String optionTextB;

    @Expose
    @SerializedName("option_textC")
    @Property(nameInDb = "option_textC")
    private String optionTextC;

    @Expose
    @SerializedName("option_textD")
    @Property(nameInDb = "option_textD")
    private String optionTextD;

    @Expose
    @SerializedName("answer_text")
    @Property(nameInDb = "answer_text")
    private String answerText;

    @Expose
    @SerializedName("created_at")
    @Property(nameInDb = "created_at")
    private String createdAt;

    @Expose
    @SerializedName("updated_at")
    @Property(nameInDb = "updated_at")
    private String updatedAt;

    @ToOne(joinProperty = "questionId")
    private Question question;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1708785938)
    private transient OptionDao myDao;

    @Generated(hash = 1116796311)
    public Option(Long id, Long questionId, String optionTextA, String optionTextB,
            String optionTextC, String optionTextD, String answerText,
            String createdAt, String updatedAt) {
        this.id = id;
        this.questionId = questionId;
        this.optionTextA = optionTextA;
        this.optionTextB = optionTextB;
        this.optionTextC = optionTextC;
        this.optionTextD = optionTextD;
        this.answerText = answerText;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Generated(hash = 104107376)
    public Option() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getOptionTextA() {
        return this.optionTextA;
    }

    public void setOptionTextA(String optionTextA) {
        this.optionTextA = optionTextA;
    }

    public String getOptionTextB() {
        return this.optionTextB;
    }

    public void setOptionTextB(String optionTextB) {
        this.optionTextB = optionTextB;
    }

    public String getOptionTextC() {
        return this.optionTextC;
    }

    public void setOptionTextC(String optionTextC) {
        this.optionTextC = optionTextC;
    }

    public String getOptionTextD() {
        return this.optionTextD;
    }

    public void setOptionTextD(String optionTextD) {
        this.optionTextD = optionTextD;
    }

    public String getAnswerText() {
        return this.answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
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

    @Generated(hash = 527827701)
    private transient Long question__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1402978864)
    public Question getQuestion() {
        Long __key = this.questionId;
        if (question__resolvedKey == null || !question__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            QuestionDao targetDao = daoSession.getQuestionDao();
            Question questionNew = targetDao.load(__key);
            synchronized (this) {
                question = questionNew;
                question__resolvedKey = __key;
            }
        }
        return question;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 38462263)
    public void setQuestion(Question question) {
        synchronized (this) {
            this.question = question;
            questionId = question == null ? null : question.getId();
            question__resolvedKey = questionId;
        }
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1972897771)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getOptionDao() : null;
    }


}
