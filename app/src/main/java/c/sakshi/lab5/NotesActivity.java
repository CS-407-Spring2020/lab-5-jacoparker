package c.sakshi.lab5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean  onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case (R.id.new_note_option):
                // do nothing in this milestone
                break;
            case (R.id.logout_option):
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
        }
        return true;
    }
}
