package uz.gita.lesson3puzzlegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ImageView menu = findViewById(R.id.menu_about);
        menu.setOnClickListener(view -> {
//            Intent intent = new Intent(this, MainActivity.class);
//            startActivity(intent);
            finish();
        });

    }
}