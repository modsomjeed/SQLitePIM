package th.ac.pim.android.sqlite;

import java.util.List;

import th.ac.pim.android.sqlite.models.MessageModel;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.example.th.ac.pim.android.sqlite.R;

public class SQLiteActivity extends ListActivity {
	private MessageDAO datasource;

	private EditText edtMessage;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sqlite);

		edtMessage = (EditText) findViewById(R.id.edtMessage); //find by widgets

		datasource = new MessageDAO(this);
		datasource.open();

		List<MessageModel> values = datasource.getAllMessages();

		// use the SimpleCursorAdapter to show the
		// elements in a ListView
		ArrayAdapter<MessageModel> adapter = new ArrayAdapter<MessageModel>(
				this, android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);
	}

	// Will be called via the onClick attribute
	// of the buttons in main.xml
	public void onClick(View view) {
		@SuppressWarnings("unchecked")
		ArrayAdapter<MessageModel> adapter = (ArrayAdapter<MessageModel>) getListAdapter();
		MessageModel message = null;
		switch (view.getId()) {//ค่าที่จะอยู่ใน switch คือ  byte shot int chaer
		case R.id.add:

			// save the new message to the database
			message = datasource.createMessageModel(edtMessage.getText()
					.toString());
			adapter.add(message);
			break;
		case R.id.delete:
			if (getListAdapter().getCount() > 0) {
				message = (MessageModel) getListAdapter().getItem(0);
				datasource.deleteMessage(message);
				adapter.remove(message);
			}
			break;
		}
		adapter.notifyDataSetChanged();
	}

	@Override
	protected void onResume() {
		datasource.open();
		super.onResume();
	}

	@Override
	protected void onPause() {
		datasource.close();
		super.onPause();
	}

}
