package com.firstacademy.piofx.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ananth on 11/8/17.
 */

public class Constants {

    public static String vocabularyTitle;
    public static int image;
    public static int backGroundColor;
    public static int homeAdapterId;
	public static String skip;
    public static String back;
    public static int practiceLevel;

    //Login user constants.
    public static String email;
    public static String userId;
    public static boolean isLoggedIn;
    public static String userName;
    public static int mobile;

    //Quiz and Practice related Constants.
    public static int BRONZE_SCORE = 5;
    public static int SILVER_SCORE_START = 6;
    public static int SILVER_SCORE_END = 9;
    public static int GOLD_SCORE = 10;
    public static final int LIMIT = 10;
    public static final double PROGRESS_START_LIMIT = 50;
    public static final double PROGRESS_CENTER_LIMIT = 80;
    public static final double PROGRESS_END_LIMIT = 100;
    public static final int OVERALL_LIMIT = 80;

    /** A flag to show how easily you can switch from standard SQLite to the encrypted SQLCipher. */
    public static final boolean ENCRYPTED = false;
    public static final String ENCRYPT_DB_NAME = "piofX-db-encrypted";
    public static final String DB_NAME = "piofX-db";

	public static final String SEED_DATABASE_OPTIONS = "seed/options.json";
    public static final String SEED_DATABASE_QUESTIONS = "seed/questions.json";

    private static Map<String,String> questionCategories = new HashMap<>();
    private static Map<Integer,String> questionSets = new HashMap<>();
    static {
        questionCategories.put("Basic 1", "basic1");
        questionCategories.put("Basic 2", "basic2");
        questionCategories.put("Intermediate 1", "intermediate1");
        questionCategories.put("Intermediate 2", "intermediate2");
        questionCategories.put("Advanced 1", "advanced1");
        questionCategories.put("Advanced 2", "advanced2");
        questionCategories.put("Super Hero", "superadvanced");

        questionSets.put(1,"set1");
        questionSets.put(2,"set2");
        questionSets.put(3,"set3");
        questionSets.put(4,"set4");
        questionSets.put(5,"set5");
        questionSets.put(6,"set6");
        questionSets.put(7,"set7");
        questionSets.put(8,"set8");
    }

    public static String getQuestionCategoryByKey(final String categoryKey){
        return questionCategories.get(categoryKey);
    }

    public static String getQuestionSetsByKey(final Integer setVal){
        return questionSets.get(setVal);
    }
}
