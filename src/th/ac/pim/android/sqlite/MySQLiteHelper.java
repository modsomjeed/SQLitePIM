package th.ac.pim.android.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

	public static final String TABLE_MESSAGES = "messages";

	private static final String DATABASE_NAME = "sqlitepim.db";
	
	private static final int DATABASE_VERSION = 1;
	
	// Database creation sql statement
	  private static final String DATABASE_CREATE = "create table "
	      + TABLE_MESSAGES + "(id integer primary key autoincrement," +
	      		              "message text not null);";
	  /*create table messager(id integer primary key autoincrement,message text not null)*/
	  
	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		// TODO Auto-generated method stub
		database.execSQL(DATABASE_CREATE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.w(MySQLiteHelper.class.getName(),
		        "Upgrading database from version " + oldVersion + " to "
		            + newVersion + ", which will destroy all old data");
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_MESSAGES);
		    onCreate(database);
	}

}
