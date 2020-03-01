package c.sakshi.lab5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class NotesActivity extends AppCompatActivity {

    String username;
    TextView welcome_message_et;

    public static ArrayList<Note> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        // show welcome message, setup GUI components and username
        welcome_message_et = findViewById(R.id.welcome_text_view);
        SharedPreferences sp =
                getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        username = sp.getString(MainActivity.USERNAME_KEY, "");
        showWelcomeMessage();

        // get the instance of sql lite database and grab user notes
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase( "notes" ,
                Context.MODE_PRIVATE, null );

        DBHelper dbHelper = new DBHelper(sqLiteDatabase);
        notes = dbHelper.readNotes(username);

        // setup the user notes
        ArrayList<String> displayNotes = new ArrayList<>();
        for (Note note : notes) {
            displayNotes.add(String.format("Title: %s\nDate: %s", note.getTitle(), note.getDate()));
        }

        ArrayAdapter adapapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, displayNotes);
        ListView listView = (ListView) findViewById(R.id.notes_list_view);
        listView.setAdapter(adapapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), AddNoteActivity.class);
                intent.putExtra("noteid", position);
                startActivity(intent);
            }
        });
    }

    private void showWelcomeMessage() {
        welcome_message_et.setText("Welcome " + username + "!");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean  onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch(item.getItemId()) {
            case (R.id.new_note_option):
                intent = new Intent(this, AddNoteActivity.class);
                startActivity(intent);
                break;
            case (R.id.logout_option):
                SharedPreferences sp =
                        getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
                sp.edit().remove(MainActivity.USERNAME_KEY).apply();
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
        }
        return true;
    }
}
