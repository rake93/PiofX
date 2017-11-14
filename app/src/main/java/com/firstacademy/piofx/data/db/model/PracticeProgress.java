package com.firstacademy.piofx.data.db.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

/**
 * Created by Rakesh Muppa on 16-10-2017.
 */
@Entity(nameInDb = "practice_progress",active = true)
public class PracticeProgress {

    @Expose
    @SerializedName("id")
    @Id
    private Long id;

    @Expose
    @SerializedName("question_id")
    @Property(nameInDb = "question_id")
    private Long questionId;

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

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 673757435)
    private transient PracticeProgressDao myDao;

    @Generated(hash = 2066832472)
    public PracticeProgress(Long id, Long questionId, String questionText,
            String questionClause, String questionCategory, String questionSet,
            String word_definition, String word_description1,
            String word_description2, boolean isAnswered, boolean isPracticed,
            String createdAt, String updatedAt) {
        this.id = id;
        this.questionId = questionId;
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

    @Generated(hash = 1090196357)
    public PracticeProgress() {
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

    public String getQuestionSet() {
        return this.questionSet;
    }

    public void setQuestionSet(String questionSet) {
        this.questionSet = questionSet;
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
    @Generated(hash = 1701315133)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getPracticeProgressDao() : null;
    }
}
