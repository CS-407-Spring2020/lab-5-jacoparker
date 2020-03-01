package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddNoteActivity extends AppCompatActivity {

    Button addNoteBtn;
    EditText noteEditText;

    DBHelper dbHelper;

    int noteId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        addNoteBtn = findViewById(R.id.save_note_btn);
        noteEditText = findViewById(R.id.note_edit_text);

        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase( "notes" ,
                Context.MODE_PRIVATE, null );
        dbHelper = new DBHelper(sqLiteDatabase);

        Intent i = getIntent();
        noteId = i.getIntExtra("noteid", -1);

        if (noteId != -1) {
            Note note = NotesActivity.notes.get(noteId);
            String noteContent = note.getContent();
            noteEditText.setText(noteContent);
        }
    }

    public void saveMethod(View v) {
        SharedPreferences sp = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        String username = sp.getString(MainActivity.USERNAME_KEY, "");
        String title;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date = dateFormat.format(new Date());
        String content = noteEditText.getText().toString();

        if (noteId == -1) {
            title = "NOTE_" + (NotesActivity.notes.size() + 1);
            dbHelper.saveNotes(username, title, content, date);
        } else {
            title = "NOTE_" + (noteId + 1);
            dbHelper.updateNotes(username, title, content, date);
        }

        Intent intent = new Intent(this, NotesActivity.class);
        startActivity(intent);
    }
}
