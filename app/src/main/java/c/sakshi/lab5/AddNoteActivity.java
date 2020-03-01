package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNoteActivity extends AppCompatActivity {

    Button addNoteBtn;
    EditText noteEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        addNoteBtn = findViewById(R.id.save_note_btn);
        noteEditText = findViewById(R.id.note_edit_text);
    }

    public void onButtonClick(View v) {
        System.out.println("Button Clicked");
    }
}
