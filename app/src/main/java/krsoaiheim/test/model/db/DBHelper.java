package krsoaiheim.test.model.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import krsoaiheim.test.model.Item;

import static krsoaiheim.test.model.db.DBFactory.TABLE_NAME;

public class DBHelper {
  private DBFactory factory;


  public DBHelper(DBFactory factory) {
    this.factory = factory;
  }


  public void saveList(List<Item> list) {
    SQLiteDatabase writableDb = factory.getWritableDatabase();
    for (Item item : list) {
      ContentValues values = new ContentValues();
      values.put(DBFactory.TEXT_ID, item.getTextId());
      values.put(DBFactory.NAME, item.getName());
      values.put(DBFactory.IMG, item.getImage());
      values.put(DBFactory.COUNTRY, item.getCountry());
      values.put(DBFactory.LAT, item.getLatitude());
      values.put(DBFactory.LON, item.getLongitude());
      long newRowId = writableDb.insert(TABLE_NAME, null, values);
    }
    writableDb.close();
  }


  public List<Item> getList() {
    SQLiteDatabase db = factory.getReadableDatabase();
    String[] cols = {DBFactory.ID, DBFactory.TEXT_ID, DBFactory.NAME, DBFactory.IMG};
    Cursor cursor = db.query(TABLE_NAME, cols, null, null,
          null, null, null);
    int idIndex = cursor.getColumnIndex(DBFactory.ID);
    int textIdIndex = cursor.getColumnIndex(DBFactory.TEXT_ID);
    int nameIndex = cursor.getColumnIndex(DBFactory.NAME);
    int imgIndex = cursor.getColumnIndex(DBFactory.IMG);
    List<Item> list = new ArrayList<>();
    while (cursor.moveToNext()) {
      Item item = new Item();
      item.setId(cursor.getInt(idIndex));
      item.setTextId(cursor.getString(textIdIndex));
      item.setImage(cursor.getString(imgIndex));
      item.setName(cursor.getString(nameIndex));
      list.add(item);
    }
    db.close();
    return list;
  }


  public Item getItem(String id) {
    SQLiteDatabase db = factory.getReadableDatabase();
    Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+DBFactory.TEXT_ID+"=?",
          new String[]{String.valueOf(id)});
    Item item = new Item();
    if (cursor.getCount()>0) {
      cursor.moveToFirst();
      int textIdIndex = cursor.getColumnIndex(DBFactory.TEXT_ID);
      int nameIndex = cursor.getColumnIndex(DBFactory.NAME);
      int countryIndex = cursor.getColumnIndex(DBFactory.COUNTRY);
      int latIndex = cursor.getColumnIndex(DBFactory.LAT);
      int lonIndex = cursor.getColumnIndex(DBFactory.LON);
      item.setTextId(cursor.getString(textIdIndex));
      item.setName(cursor.getString(nameIndex));
      item.setCountry(cursor.getString(countryIndex));
      item.setLatitude(cursor.getLong(latIndex));
      item.setLongitude(cursor.getLong(lonIndex));
    }
    db.close();
    return item;
  }


  public void clearDB() {
    SQLiteDatabase writableDb = factory.getWritableDatabase();
    writableDb.execSQL("DELETE FROM "+TABLE_NAME);
  }
}
