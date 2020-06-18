package br.unicamp.ft.d166336_m202618.trashtime.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SeriesMigration extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "trash_time_db";

    public SeriesMigration(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE series (id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "name VARCHAR," +
            "image VARCHAR," +
            "grade FLOAT," +
            "tmdb_code INTEGER  " +
        ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
