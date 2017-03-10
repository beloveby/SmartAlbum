package ruc.team.smartalbum.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;

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
    //返回所有表中所有tab
    public List<Tab> getTabs() {
        db = smartAlbumDatabaseHelper.getReadableDatabase();

        List<Tab> tabsList = new ArrayList<>();
        String table = "tab";
        String[] columns = new String[]{"_id", "name"," LogisticArg"};

        Cursor cursor = db.query(table, columns, null, null, null, null, null);

        if (cursor == null)
            return tabsList;

        while (cursor.moveToNext()) {
            Tab tab = new Tab();
            tab.setTabId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("_id"))));
            tab.setTabName(cursor.getString(cursor.getColumnIndex("name")));
            tab.setLogisticArg(cursor.getString(cursor.getColumnIndex("LogisticArg")));
            tabsList.add(tab);
        }

        db.close();

        return tabsList;
    }
    //返回数据库中最后一行Image
    public Image getLastImage(){
        db = smartAlbumDatabaseHelper.getReadableDatabase();
        Image lastImage = new Image();
        String table = "image";
        String[] columns = new String[]{"_id","path", "feature", "status"};
        Cursor cursor = db.query(table, columns, null, null, null, null, null);
        if (cursor == null)
            return lastImage;

        if(cursor.moveToLast()) {
            lastImage.setImageId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("_id"))));
            lastImage.setImagePath(cursor.getString(cursor.getColumnIndex("path")));
            lastImage.setImageFeature(cursor.getString(cursor.getColumnIndex("feature")));
            lastImage.setImageStatus(cursor.getString(cursor.getColumnIndex("status")));
        }
        db.close();
        return lastImage;
    }
    //存储分类标签结果
    public int setImageTab(int imageId, int tabId){
        db = smartAlbumDatabaseHelper.getWritableDatabase();
        db.execSQL("insert into image_tab(image_id, tab_id) values(?, ?)",new Object[]{imageId, tabId});
        db.close();
        return 0;
    }
    //存储图片特征
    public int saveFeature(String feature, int imageId){
        db = smartAlbumDatabaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("feature",feature);
        String args[] = new String[]{""+imageId};

        db.update("image", values, "imageId=?",args);
        db.close();
        return 0;

    }
    //更改分类之后的图片标签状况
    public int alterImageStatus(int status, int imageId){
        db = smartAlbumDatabaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("status",status);
        String args[] = new String[]{""+imageId};

        db.update("image", values, "imageId=?",args);
        db.close();
        return 0;

    }
    //取得图片标签
    public List<String>  getImageTab(int imageId){
        db = smartAlbumDatabaseHelper.getReadableDatabase();
        Image lastImage = new Image();
        List<String> tabNameList = new ArrayList<>();
        String table = "image_tab";
        String[] columns = new String[]{"image_id","tab_id"};
        String[] columns2 = new String[]{"name","LogisticArg"};
        Cursor cursor = db.query(table, columns, null, null, null, null, null);
        if (cursor == null)
            return tabNameList;
        while (cursor.moveToNext()) {
            int tabId = Integer.parseInt(cursor.getString(cursor.getColumnIndex("tab_id")));
            Cursor cr = db.query("tab",columns2, null, null, null, null, null);
            if(cr==null)continue;
            String tabName = cr.getString(cursor.getColumnIndex("name"));
            tabNameList.add(tabName);
        }
        return tabNameList;
    }

}
