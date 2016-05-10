package ruc.team.smartalbum.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 边园 on 2016/5/9.
 */
public class SmartAlbumDatabaseHelper extends SQLiteOpenHelper{
    public SmartAlbumDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS image" +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, identification VARCHAR, update_time TIMESTAMP)");
        db.execSQL("CREATE TABLE IF NOT EXISTS tab" +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR)");
        db.execSQL("CREATE TABLE IF NOT EXISTS image_tab" +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, image_id INTEGER, tab_id INTEGER)");
        db.execSQL("CREATE TABLE IF NOT EXISTS state" +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, state INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
