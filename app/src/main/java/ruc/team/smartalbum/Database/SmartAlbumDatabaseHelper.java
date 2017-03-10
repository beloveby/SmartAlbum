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
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, path VARCHAR, feature TEXT, status VARCHAR)");
        db.execSQL("CREATE TABLE IF NOT EXISTS tab" +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, LogisticArg TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS category" +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR)");
        db.execSQL("CREATE TABLE IF NOT EXISTS image_tab" +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, image_id INTEGER, tab_id INTEGER)");
        db.execSQL("CREATE TABLE IF NOT EXISTS tab_category" +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, tab_id VARCHAR, category_id INTEGER)");
        db.execSQL("CREATE TABLE IF NOT EXISTS system_info" +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, info INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
