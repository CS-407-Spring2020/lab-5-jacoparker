package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class NotesActivity extends AppCompatActivity {

    String username;
    TextView welcome_message_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        welcome_message_et = findViewById(R.id.welcome_text_view);
        showWelcomeMessage();
    }

    private void showWelcomeMessage() {
        username = getIntent().getStringExtra("username");
        welcome_message_et.setText("Welcome " + username + "!");
    }
}
