package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username_et;
    Button login_b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username_et = findViewById(R.id.username_edit_text);
        login_b = findViewById(R.id.login_button);
    }

    public void onButtonClick(View v) {
        String username = username_et.getText().toString();
        if (username.length() == 0) {
            Toast.makeText(this, "Must provide valid user name.", Toast.LENGTH_SHORT).show();
        }
        Intent intent = new Intent(getBaseContext(), NotesActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }
}
