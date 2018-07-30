package krsoaiheim.test.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBFactory extends SQLiteOpenHelper {
  private static final String DATABASE_NAME = "items.db";
  private static final int DATABASE_VERSION = 1;
  public static final String TABLE_NAME = "main_table";
  public static final String ID = "_id";
  public static final String TEXT_ID = "text_id";
  public static final String NAME = "name";
  public static final String COUNTRY = "country";
  public static final String IMG = "image";
  public static final String LAT = "lat";
  public static final String LON = "lon";


  public DBFactory(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }


  @Override
  public void onCreate(SQLiteDatabase db) {
    String createTableQuery =
          "CREATE TABLE "+TABLE_NAME+" ("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                TEXT_ID+" TEXT, "+NAME+" TEXT, "+
                COUNTRY+" TEXT, "+IMG+" TEXT, "+LAT+" LONG,"+LON+" "+"LONG);";
    db.execSQL(createTableQuery);
  }


  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
  }
}
