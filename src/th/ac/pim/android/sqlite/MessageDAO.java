package th.ac.pim.android.sqlite;

import java.util.ArrayList;
import java.util.List;

import th.ac.pim.android.sqlite.models.MessageModel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class MessageDAO { // Insert Update Delete Table
	// Database fields
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;

	public MessageDAO(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public MessageModel createMessageModel(String message) {
		
		ContentValues values = new ContentValues();
		
		values.put("message", message);
		
		long insertId = database.insert(MySQLiteHelper.TABLE_MESSAGES, null,
				values);//insert ข้อมูลเรียบร้อย
		
		Cursor cursor = database.query(MySQLiteHelper.TABLE_MESSAGES,
				new String[]{"id","message"}/*ชื่อคลอลัมน์*/, "id" + " = " + insertId, null,
				null, null, null);
		
		/*selsect id/message from message where id = 1*/
		
		cursor.moveToFirst();
		MessageModel newMessage = cursorToMessage(cursor);
		cursor.close();
		return newMessage;
	}

	public void deleteMessage(MessageModel message) {
		int id = message.getId();
		database.delete(MySQLiteHelper.TABLE_MESSAGES, "ID" + " = " + id, null);
		/*DELETE FROM table_name WHERE id = ? */
	}

	public List<MessageModel> getAllMessages() {
		List<MessageModel> messages = new ArrayList<MessageModel>();

		Cursor cursor = database.query(MySQLiteHelper.TABLE_MESSAGES,
				new String[]{"id","message"}, null, null, null, null, null);
		
		/* */

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {//ในเงื่อนไข เป็น true หรือ false เป็นแบบเช็คแล้วค่อยทำ
			MessageModel message = cursorToMessage(cursor);
			messages.add(message);
			cursor.moveToNext();
		}
		// make sure to close the cursor
		cursor.close();
		return messages;
	}

	private MessageModel cursorToMessage(Cursor cursor) {
		MessageModel message = new MessageModel();
		message.setId(cursor.getInt(0));
		message.setMessage(cursor.getString(1));
		return message;
	}
}
