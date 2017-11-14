package com.firstacademy.piofx;

import android.app.Application;

import com.firstacademy.piofx.data.db.DbOpenHelper;
import com.firstacademy.piofx.data.db.model.DaoMaster;
import com.firstacademy.piofx.data.db.model.DaoSession;
import com.firstacademy.piofx.utils.Constants;

import java.io.File;

/**
 * Created by Rakesh Muppa on 15-08-2017.
 */

public class
PiofXApp extends Application {

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        DbOpenHelper dbOpenHelper = new DbOpenHelper(this, Constants.DB_NAME);
        daoSession  = new DaoMaster(dbOpenHelper.getWritableDb()).newSession();
    }

    @Override
    public boolean deleteDatabase(String name) {
        return super.deleteDatabase(name);
    }

    @Override
    public File getDatabasePath(String name) {
        return super.getDatabasePath(name);
    }

    public boolean doesDatabaseExist(String dbName) {
        File dbFile = getDatabasePath(dbName);
        return dbFile.exists();
    }

    public DaoSession getDaoSession(){
        return daoSession;
    }
}
