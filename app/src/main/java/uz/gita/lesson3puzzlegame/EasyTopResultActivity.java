package uz.gita.lesson3puzzlegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class EasyTopResultActivity extends AppCompatActivity {
    private int gold, silver, bronze;
    EasyLocalStorage storageEasy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_top_result);

        ImageView back = findViewById(R.id.backEasyTopScore);
        back.setOnClickListener(view -> {
            finish();
        });
        storageEasy = new EasyLocalStorage(this);
        TextView goldScore = findViewById(R.id.scoreGoldEasy);
        gold = storageEasy.getGoldScoreEasy();
        goldScore.setText(String.valueOf(gold));
        TextView silverScore = findViewById(R.id.scoreSilverEasy);
        silver = storageEasy.getSilverScoreEasy();
        silverScore.setText(String.valueOf(silver));
        TextView bronzeScore = findViewById(R.id.scoreBronzeEasy);
        bronze = storageEasy.getBronzeScoreEasy();
        bronzeScore.setText(String.valueOf(bronze));


    }
}