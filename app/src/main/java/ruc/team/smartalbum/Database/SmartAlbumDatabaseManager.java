package ruc.team.smartalbum.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 边园 on 2016/5/9.
 */
public class SmartAlbumDatabaseManager {
    public static String tabInitState = "tab_init_state";
    private static String name = "image";
    private SQLiteDatabase db;
    private SmartAlbumDatabaseHelper smartAlbumDatabaseHelper;
    private SQLiteDatabase.CursorFactory factory = null;
    private int version = 1;

    public SmartAlbumDatabaseManager(Context context) {
        this(context, null, 1);
    }

    public SmartAlbumDatabaseManager(Context context, SQLiteDatabase.CursorFactory factory, int version) {
        this.factory = factory;
        this.version = version;

        smartAlbumDatabaseHelper = new SmartAlbumDatabaseHelper(context, name, factory, version);
    }

    public void init() {
        db = smartAlbumDatabaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", tabInitState);
        contentValues.put("state", 1);
        db.insert("state", "0", contentValues);

        for (int i = 0; i < 10; i++) {
            ContentValues contentValuesTab = new ContentValues();
            contentValuesTab.put("name", "tab" + (i + 1));
            db.insert("tab", null, contentValuesTab);
        }
    }

    public List<Tab> getTabs() {
        db = smartAlbumDatabaseHelper.getReadableDatabase();

        List<Tab> tabsList = new ArrayList<>();
        String table = "tab";
        String[] columns = new String[]{"_id", "name"};

        Cursor cursor = db.query(table, columns, null, null, null, null, null);

        if (cursor == null)
            return tabsList;

        while (cursor.moveToNext()) {
            Tab tab = new Tab();
            tab.setTabId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("_id"))));
            tab.setTabName(cursor.getString(cursor.getColumnIndex("name")));

            tabsList.add(tab);
        }

        db.close();

        return tabsList;
    }
}
