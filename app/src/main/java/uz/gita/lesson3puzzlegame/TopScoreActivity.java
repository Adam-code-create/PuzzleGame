package uz.gita.lesson3puzzlegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class TopScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_score);

        ImageView back = findViewById(R.id.backMedium);
        back.setOnClickListener(view -> {
            finish();
        });
        TextView easy = findViewById(R.id.easy);
        easy.setOnClickListener(view -> {
            Intent intent = new Intent(this, EasyTopResultActivity.class);
            startActivity(intent);

        });
        TextView medium = findViewById(R.id.medium);
        medium.setOnClickListener(view -> {
            Intent intent = new Intent(this, MediumTopResultActivity.class);
            startActivity(intent);
        });

    }
}